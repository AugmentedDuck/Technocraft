package net.augmentedduck.technocraft.block.entity;

import java.util.Optional;

import javax.annotation.Nullable;

import net.augmentedduck.technocraft.block.custom.ElectricFurnaceBlock;
import net.augmentedduck.technocraft.energy.ConsumerEnergyStorage;
import net.augmentedduck.technocraft.energy.ModEnergyTiers;
import net.augmentedduck.technocraft.screen.custom.ElectricFurnaceMenu;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.SmeltingRecipe;
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
 * Block entity for the Electric Furnace machine.
 *
 * <p>The electric furnace uses stored energy to process vanilla smelting recipes. It manages:
 * <ul>
 *     <li>An input slot for items to smelt
 *     <li>An output slot for completed recipe
 *     <li>A fuel slot for energy-containing items
 *     <li>Internal energy storage
 *     <li>Cooking progress tracking
 * </ul>
 *
 * <p>The furnace logic runs only on the server through {@link #serverTick}.
 */
public class ElectricFurnaceBlockEntity extends BlockEntity implements MachineBlockEntity {
    
    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;
    public static final int BATTERY_SLOT = 2;
    
    public static final int ENERGY_CAPACITY = 4160;
    public static final int ENERGY_PER_TICK = 30;
    public static final int COOK_TIME = (int)(6.5F * 20); // 6.5s * 20 t/s 

    /**
     * Internal item inventory.
     *
     * <p>Only valid items can be inserted into each slot:
     * <ul>
     *     <li>Fuel slot requires an item with an energy capability
     *     <li>Input slot requires a valid smelting recipe
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
                case BATTERY_SLOT -> stack.getCapability(Capabilities.EnergyStorage.ITEM) != null;
                case INPUT_SLOT -> level != null && findRecipe(stack).isPresent();
                case OUTPUT_SLOT -> false;
                default -> false;
            };
        };

        @Override
        public int getSlotLimit(int slot) {
            return slot == BATTERY_SLOT ? 1 : 64;
        };
    };

    // ITEM HANDLERS FOR DIFFERENT SIDES
    private final IItemHandler inputHandler = new RangedWrapper(itemHandler, INPUT_SLOT, INPUT_SLOT + 1);
    private final IItemHandler batteryHandler = new RangedWrapper(itemHandler, BATTERY_SLOT, BATTERY_SLOT + 1);
    private final IItemHandler outputHandler = new RangedWrapper(itemHandler, OUTPUT_SLOT, OUTPUT_SLOT + 1);

    private final ConsumerEnergyStorage energyStorage = new ConsumerEnergyStorage(ENERGY_CAPACITY, ModEnergyTiers.LV.getMaxTransfer());

    private int cookProgress;
    private boolean activelyCooking;

    /**
     * Data synchronized between server and client menus.
     *
     * Index:
     * <ul>
     *     <li>0 - Current energy
     *     <li>1 - Maximum energy
     *     <li>2 - Current smelting progress
     *     <li>3 - Required process time
     * </ul>
     */
    private final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> energyStorage.getEnergyStored();
                case 1 -> energyStorage.getMaxEnergyStored();
                case 2 -> cookProgress;
                case 3 -> COOK_TIME;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> energyStorage.setEnergy(value);
                case 2 -> cookProgress = value;
                default -> {}
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    };

    public ElectricFurnaceBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.ELECTRIC_FURNACE_BE.get(), pos, blockState);
    }

    public IItemHandler getItemHandler() {
        return itemHandler;
    }

    @Nullable
    public IItemHandler getItemHandler(@Nullable Direction side) {
        if (side == null) return itemHandler;
        return switch (side) {
            case UP -> inputHandler;
            case DOWN -> outputHandler;
            default -> batteryHandler;
        };
    }

    public ConsumerEnergyStorage getEnergyStorage() {
        return energyStorage;
    }

    public ContainerData getData() {
        return data;
    }

    public boolean hasProgress() {
        return cookProgress > 0;
    }

    public boolean isCooking() {
        return activelyCooking;
    }

    private Optional<RecipeHolder<SmeltingRecipe>> findRecipe(ItemStack input) {
        if (level == null || input.isEmpty()) return Optional.empty();
        return level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SingleRecipeInput(input), level);
    }

    private boolean canInsertResult(RecipeHolder<SmeltingRecipe> recipe, Level level, ItemStack input) {
        ItemStack result = recipe.value().assemble(new SingleRecipeInput(input), level.registryAccess());
        ItemStack output = itemHandler.getStackInSlot(OUTPUT_SLOT);

        if (output.isEmpty()) return true;
        if (!ItemStack.isSameItemSameComponents(output, result)) return false;
        return output.getCount() + result.getCount() <= output.getMaxStackSize();
    }

    private void craftItem(RecipeHolder<SmeltingRecipe> recipe, Level level) {
        ItemStack input = itemHandler.getStackInSlot(INPUT_SLOT);
        ItemStack result = recipe.value().assemble(new SingleRecipeInput(input), level.registryAccess());
        ItemStack output = itemHandler.getStackInSlot(OUTPUT_SLOT);

        input.shrink(1);
        itemHandler.setStackInSlot(INPUT_SLOT, input);

        if (output.isEmpty()) {
            itemHandler.setStackInSlot(OUTPUT_SLOT, result.copy());
        } else {
            output.grow(result.getCount());
            itemHandler.setStackInSlot(OUTPUT_SLOT, output);
        }
    }

    /**
     * Main server-side machine tick.
     *
     * Handles:
     * <ul>
     *     <li>Charging from inserted energy items
     *     <li>Checking recipes
     *     <li>Consuming energy
     *     <li>Updating block state
     * </ul>
     */
    public static void serverTick(Level level, BlockPos pos, BlockState state, ElectricFurnaceBlockEntity be) {
        boolean wasLit = be.isCooking();
        boolean changed = false;

        changed |= be.dischargeItem();

        ItemStack input = be.itemHandler.getStackInSlot(INPUT_SLOT);
        Optional<RecipeHolder<SmeltingRecipe>> recipe = be.findRecipe(input);

        boolean canProgress = recipe.isPresent() && be.energyStorage.getEnergyStored() >= ENERGY_PER_TICK && be.canInsertResult(recipe.get(), level, input);
        be.activelyCooking = canProgress;

        if (canProgress) {

            be.energyStorage.consumeEnergy(ENERGY_PER_TICK);
            be.cookProgress++;
            changed = true;

            if (be.cookProgress >= COOK_TIME) {
                be.craftItem(recipe.get(), level);
                be.cookProgress = 0;
            }
        } else if (be.cookProgress > 0) {
            changed = true;
        }

        if (wasLit != be.isCooking()) {
            level.setBlock(pos, state.setValue(ElectricFurnaceBlock.LIT, be.isCooking()), Block.UPDATE_ALL);
        }

        if (changed) {
            setChanged(level, pos, state);
        }
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
        tag.putInt("cook_progress", cookProgress);
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, Provider registries) {
        super.loadAdditional(tag, registries);
        itemHandler.deserializeNBT(registries, tag.getCompound("inventory"));
        energyStorage.setEnergy(tag.getInt("energy"));
        cookProgress = tag.getInt("cook_progress");
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new ElectricFurnaceMenu(containerId, playerInventory, this);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.technocraft.electric_furnace");
    }

    private boolean dischargeItem() {
        ItemStack fuelStack = itemHandler.getStackInSlot(BATTERY_SLOT);
        if (fuelStack.isEmpty()) return false;

        IEnergyStorage itemEnergy = fuelStack.getCapability(Capabilities.EnergyStorage.ITEM);
        if (itemEnergy == null || !itemEnergy.canExtract()) return false;

        int missing = energyStorage.getMaxEnergyStored() - energyStorage.getEnergyStored();
        if (missing <= 0) return false;

        int simulated = itemEnergy.extractEnergy(missing, true);
        if (simulated <= 0) return false;

        int accepted = energyStorage.receiveEnergy(simulated, false);
        if (accepted <= 0) return false;

        itemEnergy.extractEnergy(accepted, false);
        return true;
    }
}
