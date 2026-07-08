package net.augmentedduck.technocraft.block.entity;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.augmentedduck.technocraft.block.custom.GeneratorBlock;
import net.augmentedduck.technocraft.energy.GeneratorEnergyStorage;
import net.augmentedduck.technocraft.screen.custom.GeneratorMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
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

public class GeneratorBlockEntity extends BlockEntity implements MenuProvider{

    public static final int FUEL_SLOT = 0;
    public static final int CHARGE_SLOT = 1;
    public static final int OUTPUT_SLOT = 2;

    public static final int ENERGY_CAPACITY = 4_0000;
    public static final int ENERGY_PER_TICK = 40;
    public static final int ENERGY_EXTRACT_RATE = 100;

    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        };

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return switch (slot) {
                case FUEL_SLOT -> stack.getBurnTime( null) > 0;
                case CHARGE_SLOT -> true;
                case OUTPUT_SLOT -> false;
                default -> false;
            };
        };

        @Override
        public int getSlotLimit(int slot) {
            return slot == OUTPUT_SLOT ? 1 : slot == CHARGE_SLOT ? 1 : 64;
        };
    };

    private final IItemHandler fuelHandler = new RangedWrapper(itemHandler, FUEL_SLOT, FUEL_SLOT + 1);
    private final IItemHandler chargeHandler = new RangedWrapper(itemHandler, CHARGE_SLOT, CHARGE_SLOT + 1);
    private final IItemHandler outputHandler = new RangedWrapper(itemHandler, OUTPUT_SLOT, OUTPUT_SLOT + 1);

    private final GeneratorEnergyStorage energyStorage = new GeneratorEnergyStorage(ENERGY_CAPACITY, ENERGY_EXTRACT_RATE);

    private int litTime;
    private int litDuration;

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
            int burnTime = (int)(fuel.getBurnTime(null) * 0.625f);

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

        changed |= be.distributeEnergy(level, pos);

        if (wasLit != be.isLit()) {
            level.setBlock(pos, state.setValue(GeneratorBlock.LIT, be.isLit()), Block.UPDATE_ALL);
        }

        if (changed) {
            setChanged(level, pos, state);
        }
    }

    private boolean distributeEnergy(Level level, BlockPos pos) {
        if (energyStorage.getEnergyStored() <= 0) return false;

        List<IEnergyStorage> receivers = new ArrayList<>();


        for (Direction direction : Direction.values()) {
            BlockPos neighborPos = pos.relative(direction);
            if (level.getBlockEntity(neighborPos) == null) continue;

            IEnergyStorage neighborStorage = level.getCapability(Capabilities.EnergyStorage.BLOCK, neighborPos, direction.getOpposite());

            if (neighborStorage != null && neighborStorage.canReceive()) {
                receivers.add(neighborStorage);
            }
        }

        if (receivers.isEmpty()) return false;
        boolean changed = false;
        int share = Math.max(1, ENERGY_EXTRACT_RATE / receivers.size());

        for (IEnergyStorage receiver : receivers) {
            int extracted = energyStorage.extractEnergy(share, true);
            if (extracted > 0) {
                int accepted = receiver.receiveEnergy(extracted, false);
                if (accepted > 0) {
                    energyStorage.extractEnergy(accepted, false);
                    changed = true;
                }
            }
        }
        
        return changed;
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

}
