package net.augmentedduck.technocraft.block.entity;

import net.minecraft.world.MenuProvider;

/**
 * Common interface for all machine block entities.
 * 
 * <p>Implementing this interface indicates that the block entity:
 * <ul>
 *  <li>Provides a menu (GUI) for player interaction
 *  <li>Can drop its inventory contents when the machine is removed
 * </ul> 
 */
public interface MachineBlockEntity extends MenuProvider{
    /**
     * Drops the contents of the machine into the world.
     */
    void drops();
}
