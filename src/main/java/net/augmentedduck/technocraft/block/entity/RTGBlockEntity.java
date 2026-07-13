package net.augmentedduck.technocraft.block.entity;

import java.util.ArrayList;
import java.util.List;

import net.augmentedduck.technocraft.energy.GeneratorEnergyStorage;
import net.augmentedduck.technocraft.item.ModItems;
import net.augmentedduck.technocraft.screen.custom.RTGMenu;
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
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

public class RTGBlockEntity extends BlockEntity implements MachineBlockEntity {

    public static final int FUEL_SLOT_0 = 0;
    public static final int FUEL_SLOT_1 = 1;
    public static final int FUEL_SLOT_2 = 2;
    public static final int FUEL_SLOT_3 = 3;
    public static final int FUEL_SLOT_4 = 4;
    public static final int FUEL_SLOT_5 = 5;

    public static final int ENERGY_CAPACITY = 320;
    public static final int ENERGY_PER_TICK = 320;
    public static final int ENERGY_EXTRACT_RATE = 320;

    private final ItemStackHandler itemHandler = new ItemStackHandler(6) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        };

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return switch (slot) {
                // TODO Change to RTG fuel pellets
                case FUEL_SLOT_0 -> stack.getItem() == ModItems.RTG_FUEL.get();
                case FUEL_SLOT_1 -> stack.getItem() == ModItems.RTG_FUEL.get();
                case FUEL_SLOT_2 -> stack.getItem() == ModItems.RTG_FUEL.get();
                case FUEL_SLOT_3 -> stack.getItem() == ModItems.RTG_FUEL.get();
                case FUEL_SLOT_4 -> stack.getItem() == ModItems.RTG_FUEL.get();
                case FUEL_SLOT_5 -> stack.getItem() == ModItems.RTG_FUEL.get();
                default -> false;
            };
        };

        @Override
        public int getSlotLimit(int slot) {
            return 1;
        };
    };

    private final GeneratorEnergyStorage energyStorage = new GeneratorEnergyStorage(ENERGY_CAPACITY, ENERGY_EXTRACT_RATE);

    private final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> energyStorage.getEnergyStored();
                case 1 -> energyStorage.getMaxEnergyStored();
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> energyStorage.setEnergy(value);
                default -> {}
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    public RTGBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.RTG_BE.get(), pos, blockState);
    }

    public IItemHandler getItemHandler() {
        return itemHandler;
    }

    public IEnergyStorage getEnergyStorage() {
        return energyStorage;
    }

    public ContainerData getData() {
        return data;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, RTGBlockEntity be) {
        boolean changed = false;

        int missing = be.energyStorage.getMaxEnergyStored() - be.energyStorage.getEnergyStored();
        
        if (missing > 0) {
            int energyToAdd = 0;
            for (int i = 0; i < 6; i++) {
                if (!be.itemHandler.getStackInSlot(i).isEmpty()) {
                    if (energyToAdd <= 0) {
                        energyToAdd++;
                        continue;
                    }
                    energyToAdd *= 2; 
                }
            }
            be.energyStorage.addEnergy(Math.min(energyToAdd * 10, missing));
            changed = true;
        }

        changed |= be.distributeEnergy(level, pos);

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

        int available = Math.min(ENERGY_EXTRACT_RATE, energyStorage.getEnergyStored());
        if (available <= 0) return false;
        
        int share = Math.max(1, available / receivers.size());
        boolean changed = false;

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
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, Provider registries) {
        super.loadAdditional(tag, registries);
        itemHandler.deserializeNBT(registries, tag.getCompound("inventory"));
        energyStorage.setEnergy(tag.getInt("energy"));
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.technocraft.rtg");
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new RTGMenu(containerId, playerInventory, this);
    }
}
