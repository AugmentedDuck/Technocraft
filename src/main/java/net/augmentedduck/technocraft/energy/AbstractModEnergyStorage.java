package net.augmentedduck.technocraft.energy;

import net.neoforged.neoforge.energy.EnergyStorage;

/**
 * Base implementation of an energy storage used by Technocraft machines.
 * 
 * <p>This class extends NeoForge's {@link EnergyStorage} by providing utility methods for modifying stored energy while ensuring that the value always remains withing the valid range.
 * ({@code 0 <= energy <= capacity}).
 */

public abstract class AbstractModEnergyStorage extends EnergyStorage{

    /**
     * Creates a new energy storage.
     * 
     * @param capacity   The maximum amount of energy that can be stored.
     * @param maxReceive The maximum amount of energy that can be received per operation
     * @param maxExtract The maximum amount of energy that can be extracted per operation
     */
    public AbstractModEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    /**
     * Adds (or removes) energy from storage
     * 
     * <p>The resulting energy value is clamped betweem {@code 0} and the storage's maximum capacity.
     * 
     * @param amount The amount of energy to add. Negative values remove energy. 
     */
    public void addEnergy(int amount) {
        this.energy = Math.max(0, Math.min(this.capacity, this.energy + amount));
    }

    /**
     * Sets the stored energy to a specific value.
     * 
     * <p>The supplied value is clamped between {@code 0} and the storage's maximum capacity.
     * 
     * @param amount The new energy value.
     */
    public void setEnergy(int amount) {
        this.energy = Math.max(0, Math.min(this.capacity, amount));
    }

}
