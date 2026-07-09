package net.augmentedduck.technocraft.screen.custom;

import net.augmentedduck.technocraft.block.entity.GeneratorBlockEntity;
import net.augmentedduck.technocraft.screen.ModMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class GeneratorMenu extends AbstractMachineMenu {

    public GeneratorMenu(int containerId, Inventory inventory, RegistryFriendlyByteBuf extraData) {
        this(containerId, inventory, getBlockEntity(inventory, extraData));
    }

    public GeneratorMenu(int containerId, Inventory inventory, GeneratorBlockEntity blockEntity) {
        super(ModMenuTypes.GENERATOR_MENU.get(), containerId, blockEntity, blockEntity.getData());

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

    public int getLitTime() {return data.get(2);}
    public int getLitDuration() {return data.get(3);}
    public boolean isLit() {return getLitDuration() > 0 && getLitTime() > 0; }

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
            if (!this.moveItemStackTo(sourceStack, GeneratorBlockEntity.CHARGE_SLOT, GeneratorBlockEntity.CHARGE_SLOT + 1, false)) {
                if (!this.moveItemStackTo(sourceStack, GeneratorBlockEntity.FUEL_SLOT, GeneratorBlockEntity.FUEL_SLOT + 1, false)) {
                    return ItemStack.EMPTY;
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
