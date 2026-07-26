package net.augmentedduck.technocraft.block.entity;

import javax.annotation.Nullable;

import net.augmentedduck.technocraft.block.custom.GeneratorBlock;
import net.augmentedduck.technocraft.energy.EnergyDistributor;
import net.augmentedduck.technocraft.energy.GeneratorEnergyStorage;
import net.augmentedduck.technocraft.screen.custom.GeneratorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.wrapper.RangedWrapper;

/**
 * Block Entity for a Generator machine.
 * Burns fuel items to generate NeoForge Energy (FE) and can either charge 
 * an item inside its inventory or distribute energy to adjacent blocks.
 * 
 * <p>The furnace logic runs only on the server through {@link #serverTick}.
 */
public class GeneratorBlockEntity extends BlockEntity implements MachineBlockEntity{

    public static final int FUEL_SLOT = 0;
    public static final int CHARGE_SLOT = 1;
    public static final int OUTPUT_SLOT = 2;

    public static final int ENERGY_CAPACITY = 40_000;
    public static final int ENERGY_PER_TICK = 100;
    public static final int ENERGY_EXTRACT_RATE = 100;

     /**
     * Internal item inventory.
     *
     * <p>Only valid items can be inserted into each slot:
     * <ul>
     *     <li>Fuel slot requires an item that can burn
     *     <li>Charge slot requires an item with an energy capability
     *     <li>Output slot cannot be manually inserted into
     * </ul>
     */
    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        };

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return switch (slot) {
                case FUEL_SLOT -> stack.getBurnTime( null) > 0;
                case CHARGE_SLOT -> stack.getCapability(Capabilities.EnergyStorage.ITEM) != null;
                case OUTPUT_SLOT -> false;
                default -> false;
            };
        };

        @Override
        public int getSlotLimit(int slot) {
            return slot == OUTPUT_SLOT ? 1 : slot == CHARGE_SLOT ? 1 : 64;
        };
    };

    // ITEM HANDLERS FOR DIFFERENT SIDES
    private final IItemHandler fuelHandler = new RangedWrapper(itemHandler, FUEL_SLOT, FUEL_SLOT + 1);
    private final IItemHandler chargeHandler = new RangedWrapper(itemHandler, CHARGE_SLOT, CHARGE_SLOT + 1);
    private final IItemHandler outputHandler = new RangedWrapper(itemHandler, OUTPUT_SLOT, OUTPUT_SLOT + 1);

    private final GeneratorEnergyStorage energyStorage = new GeneratorEnergyStorage(ENERGY_CAPACITY, ENERGY_EXTRACT_RATE);

    private int litTime;
    private int litDuration;

    /**
     * Data synchronized between server and client menus.
     *
     * Index:
     * <ul>
     *     <li>0 - Current energy
     *     <li>1 - Maximum energy
     *     <li>2 - How long it will be lit
     *     <li>3 - Total burn time
     * </ul>
     */
    private final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> energyStorage.getEnergyStored();
                case 1 -> energyStorage.getMaxEnergyStored();
                case 2 -> litTime;
                case 3 -> litDuration;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> energyStorage.setEnergy(value);
                case 2 -> litTime = value;
                case 3 -> litDuration = value;
                default -> {}
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

    public GeneratorBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.GENERATOR_BE.get(), pos, blockState);
    }

    public IItemHandler getItemHandler() {
        return itemHandler;
    }

    @Nullable
    public IItemHandler getItemHandler(@Nullable Direction side) {
        if (side == null) return itemHandler;

        return switch (side) {
            case UP -> chargeHandler;
            case DOWN -> outputHandler;
            default -> fuelHandler;
        };
    }

    public IEnergyStorage getEnergyStorage() {
        return energyStorage;
    }

    public ContainerData getData() {
        return data;
    }

    public boolean isLit() {
        return litTime > 0;
    }

    /**
     * Core processing loop executed every single game tick on the Logical Server.
     */
    public static void serverTick(Level level, BlockPos pos, BlockState state, GeneratorBlockEntity be) {
        boolean wasLit = be.isLit();
        boolean changed = false;

        if (be.litTime > 0) {
            be.litTime--;

            int missing = be.energyStorage.getMaxEnergyStored() - be.energyStorage.getEnergyStored();

            if (missing > 0) {
                be.energyStorage.addEnergy(Math.min(ENERGY_PER_TICK, missing));
                changed = true;
            }
        }

        if (be.litTime <= 0 && be.energyStorage.getEnergyStored() < be.energyStorage.getMaxEnergyStored()) {
            ItemStack fuel = be.itemHandler.getStackInSlot(FUEL_SLOT);
            int burnTime = (int)(fuel.getBurnTime(null) * 0.25F);

            if (!fuel.isEmpty() && burnTime > 0) {
                be.litTime = burnTime;
                be.litDuration = burnTime;

                Item remainder = fuel.getItem().getCraftingRemainingItem();
                fuel.shrink(1);
                if (fuel.isEmpty() && remainder != null) {
                    be.itemHandler.setStackInSlot(FUEL_SLOT, new ItemStack(remainder));
                }
                changed = true;
            }
        }

        changed |= be.chargeItem();
        changed |= be.tryEjectFullBattery();
        changed |= be.distributeEnergy(level, pos);

        if (wasLit != be.isLit()) {
            level.setBlock(pos, state.setValue(GeneratorBlock.LIT, be.isLit()), Block.UPDATE_ALL);
        }

        if (changed) {
            setChanged(level, pos, state);
        }
    }

    /**
     * Pushes surplus energy to adjacent capability-exposing blocks.
     * 
     * <p>We snapshot {@code available} up front and simulate-extract per receiver
     * rather than extracting live from the storage while iterating. 
     */ 
    private boolean distributeEnergy(Level level, BlockPos pos) {
        return EnergyDistributor.distributeToNeighbors(level, pos, energyStorage, ENERGY_EXTRACT_RATE);
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, (Container) inv);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, Provider registries) {
        tag.put("inventory", itemHandler.serializeNBT(registries));
        tag.putInt("energy", energyStorage.getEnergyStored());
        tag.putInt("lit_time", litTime);
        tag.putInt("lit_duration", litDuration);
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, Provider registries) {
        super.loadAdditional(tag, registries);
        itemHandler.deserializeNBT(registries, tag.getCompound("inventory"));
        energyStorage.setEnergy(tag.getInt("energy"));
        litTime = tag.getInt("lit_time");
        litDuration = tag.getInt("lit_duration");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new GeneratorMenu(containerId, playerInventory, this);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.technocraft.generator");
    }

    private boolean chargeItem() {
        ItemStack chargeStack = itemHandler.getStackInSlot(CHARGE_SLOT);
        if (chargeStack.isEmpty() || energyStorage.getEnergyStored() <= 0) return false;

        IEnergyStorage itemEnergy = chargeStack.getCapability(Capabilities.EnergyStorage.ITEM);
        if (itemEnergy == null || !itemEnergy.canReceive()) return false;

        int simulated = itemEnergy.receiveEnergy(energyStorage.getEnergyStored(), true);
        if (simulated <= 0) return false;

        int extracted = energyStorage.extractEnergy(simulated, false);
        if (extracted <= 0) return false;

        itemEnergy.receiveEnergy(extracted, false);
        return true;
    }

    private boolean tryEjectFullBattery() {
        ItemStack chargeStack = itemHandler.getStackInSlot(CHARGE_SLOT);
        if (chargeStack.isEmpty()) return false;

        IEnergyStorage itemEnergy = chargeStack.getCapability(Capabilities.EnergyStorage.ITEM);
        if (itemEnergy == null) return false;

        if (itemEnergy.receiveEnergy(1, true) > 0) return false;

        ItemStack outputStack = itemHandler.getStackInSlot(OUTPUT_SLOT);
        if (!outputStack.isEmpty()) {
            if (!outputStack.isSameItemSameComponents(chargeStack, outputStack)) return false;

            if (outputStack.getMaxStackSize() <= outputStack.getCount()) return false;

            outputStack.grow(1);
            itemHandler.setStackInSlot(OUTPUT_SLOT, outputStack);
        } else {
            itemHandler.setStackInSlot(OUTPUT_SLOT, chargeStack);
        }

        itemHandler.setStackInSlot(CHARGE_SLOT, ItemStack.EMPTY);
        return true;        
    }
}
