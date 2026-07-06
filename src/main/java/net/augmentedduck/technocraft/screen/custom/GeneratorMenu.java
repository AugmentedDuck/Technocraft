package net.augmentedduck.technocraft.screen.custom;

import net.augmentedduck.technocraft.block.entity.GeneratorBlockEntity;
import net.augmentedduck.technocraft.screen.ModMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class GeneratorMenu extends AbstractContainerMenu {

    public final GeneratorBlockEntity blockEntity;
    private final ContainerData data;

    public GeneratorMenu(int containerId, Inventory inventory, RegistryFriendlyByteBuf extraData) {
        this(containerId, inventory, getBlockEntity(inventory, extraData));
    }

    public GeneratorMenu(int containerId, Inventory inventory, GeneratorBlockEntity blockEntity) {
        super(ModMenuTypes.GENERATOR_MENU.get(), containerId);
        this.blockEntity = blockEntity;
        this.data = blockEntity.getData();

        IItemHandler handler = blockEntity.getItemHandler();
        this.addSlot(new SlotItemHandler(handler, GeneratorBlockEntity.CHARGE_SLOT, 56, 17));
        this.addSlot(new SlotItemHandler(handler, GeneratorBlockEntity.FUEL_SLOT, 56, 53));
        this.addSlot(new SlotItemHandler(handler, GeneratorBlockEntity.OUTPUT_SLOT, 116, 35) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
        });

        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);
        addDataSlots(data);
    }

    private static GeneratorBlockEntity getBlockEntity(Inventory inventory, RegistryFriendlyByteBuf buf) {
        BlockPos pos = buf.readBlockPos();

        if (inventory.player.level().getBlockEntity(pos) instanceof GeneratorBlockEntity be) {
            return be;
        }

        throw new IllegalStateException("Missing Generator block entity at " + pos);
    }

    public int getEnergy() {return data.get(0);}
    public int getMaxEnergy() {return data.get(1);}
    public int getLitTime() {return data.get(2);}
    public int getLitDuration() {return data.get(3);}
    public boolean isLit() {return getLitDuration() > 0 && getLitTime() > 0; }

    private void addPlayerInventory(Inventory inventory) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(inventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory inventory) {
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot sourceSlot = slots.get(index);
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY;

        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copy = sourceStack.copy();
        final int machineSlots = 3;

        if (index < machineSlots) {
            if (!this.moveItemStackTo(sourceStack, machineSlots, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }
        } else {
            // shift-click from player inventory: try fuel slot first, then charge slot
            if (!this.moveItemStackTo(sourceStack, GeneratorBlockEntity.FUEL_SLOT, GeneratorBlockEntity.FUEL_SLOT + 1, false)
                    && !this.moveItemStackTo(sourceStack, GeneratorBlockEntity.CHARGE_SLOT, GeneratorBlockEntity.CHARGE_SLOT + 1, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (sourceStack.isEmpty()) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }

        return copy;
    }

    @Override
    public boolean stillValid(Player player) {
        return blockEntity.getLevel() != null && player.distanceToSqr(blockEntity.getBlockPos().getX() + 0.5, blockEntity.getBlockPos().getY() + 0.5, blockEntity.getBlockPos().getZ() + 0.5) <= 64.0;
    }

}
