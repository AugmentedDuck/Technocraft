package net.augmentedduck.technocraft.screen.custom;

import net.augmentedduck.technocraft.block.entity.RTGBlockEntity;
import net.augmentedduck.technocraft.screen.ModMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class RTGMenu extends AbstractMachineMenu {

    public RTGMenu(int containerId, Inventory inventory, RegistryFriendlyByteBuf extraData) {
        this(containerId, inventory, getBlockEntity(inventory, extraData));
    }

    public RTGMenu(int containerId, Inventory inventory, RTGBlockEntity blockEntity) {
        super(ModMenuTypes.RTG_MENU.get(), containerId, blockEntity, blockEntity.getData());

        IItemHandler handler = blockEntity.getItemHandler();
        this.addSlot(new SlotItemHandler(handler, RTGBlockEntity.FUEL_SLOT_0, 56, 17));
        this.addSlot(new SlotItemHandler(handler, RTGBlockEntity.FUEL_SLOT_1, 56, 35));
        this.addSlot(new SlotItemHandler(handler, RTGBlockEntity.FUEL_SLOT_2, 56, 53));
        this.addSlot(new SlotItemHandler(handler, RTGBlockEntity.FUEL_SLOT_3, 92, 17));
        this.addSlot(new SlotItemHandler(handler, RTGBlockEntity.FUEL_SLOT_4, 92, 35));
        this.addSlot(new SlotItemHandler(handler, RTGBlockEntity.FUEL_SLOT_5, 92, 53));

        addPlayerInventory(inventory);
        addPlayerHotbar(inventory);
        addDataSlots(data);
    }

    private static RTGBlockEntity getBlockEntity(Inventory inventory, RegistryFriendlyByteBuf buf) {
        BlockPos pos = buf.readBlockPos();

        if (inventory.player.level().getBlockEntity(pos) instanceof RTGBlockEntity be) {
            return be;
        }

        throw new IllegalStateException("Missing RTG block entity at " + pos);
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
            if (!this.moveItemStackTo(sourceStack, RTGBlockEntity.FUEL_SLOT_0, RTGBlockEntity.FUEL_SLOT_0 + 1, false)) {
                if (!this.moveItemStackTo(sourceStack, RTGBlockEntity.FUEL_SLOT_1, RTGBlockEntity.FUEL_SLOT_1 + 1, false)) {
                    if (!this.moveItemStackTo(sourceStack, RTGBlockEntity.FUEL_SLOT_2, RTGBlockEntity.FUEL_SLOT_2 + 1, false)) {
                        if (!this.moveItemStackTo(sourceStack, RTGBlockEntity.FUEL_SLOT_3, RTGBlockEntity.FUEL_SLOT_3 + 1, false)) {
                            if (!this.moveItemStackTo(sourceStack, RTGBlockEntity.FUEL_SLOT_4, RTGBlockEntity.FUEL_SLOT_4 + 1, false)) {
                                if (!this.moveItemStackTo(sourceStack, RTGBlockEntity.FUEL_SLOT_5, RTGBlockEntity.FUEL_SLOT_5 + 1, false)) {
                                    return ItemStack.EMPTY;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (sourceStack.isEmpty()) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }

        return copy;
    }

}
