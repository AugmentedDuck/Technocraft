package net.augmentedduck.technocraft.screen.custom;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.block.entity.BlockEntity;

/**
 * Base menu implementation for Technocraft machines.
 * 
 * <p>This class provides functionality common to all machine menus, including:
 * <ul>
 *  <li>Access the the machine's block entity
 *  <li>Synchronization of machine data (energy)
 *  <li>Methods for adding the player's inventory and hotbar
 *  <li>Validation that the player is closwe enough to interact with the machine
 * </ul>
 */
public abstract class AbstractMachineMenu extends AbstractContainerMenu {
    /** Block entity backing this menu */
    protected final BlockEntity blockEntity;

    /** Synchonized machine data shared between server and client */
    protected final ContainerData data;

    /**
     * Creates a new machine menu.
     * 
     * @param menuType    The menu type registered for this machine.
     * @param containerId The container ID assigned by Minecraft
     * @param blockEntity The machine's block entity
     * @param data        The synchonized container data
     */
    protected AbstractMachineMenu(MenuType<?> menuType, int containerId, BlockEntity blockEntity, ContainerData data) {
        super(menuType, containerId);

        this.blockEntity = blockEntity;
        this.data = data;
    }

    /**
     * @return The current stored energy
     */
    public int getEnergy() { return data.get(0); }

    /**
     * @return The maximum energy capacity
     */
    public int getMaxEnergy() { return data.get(1); }

    /**
     * Adds the player's main inventory to slots to the menu. The inventory is arranged as a 3x9 grid.
     * 
     * @param inventory The player's inventory
     */
    protected void addPlayerInventory(Inventory inventory) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(inventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
    }

    /**
     * Adds the players hotbar slots to the menu
     * 
     * @param inventory The player's inventory
     */
    protected void addPlayerHotbar(Inventory inventory) {
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }

    /**
     * Determines whether the player can continue interacting with this menu. The player must be within 8 blocks of the machine.
     * 
     * @param player The player interacting with the menu
     * @return {@code true} if the player is close enough to use the menu
     */
    @Override
    public boolean stillValid(Player player) {
        return blockEntity.getLevel() != null && player.distanceToSqr(blockEntity.getBlockPos().getX() + 0.5, blockEntity.getBlockPos().getY() + 0.5, blockEntity.getBlockPos().getZ() + 0.5) <= 64.0;
    }
}
