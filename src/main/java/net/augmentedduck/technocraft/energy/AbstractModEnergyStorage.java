package net.augmentedduck.technocraft.energy;

import net.neoforged.neoforge.energy.EnergyStorage;

/**
 * Base implementation of an energy storage used by Technocraft machines.
 *
 * <p>NeoForge's {@link EnergyStorage} already clamps receiveEnergy/extractEnergy
 * calls, but it doesn't expose a way to set/adjust the internal energy value
 * directly (e.g. for generation ticks, NBT loading, or menu sync writes) without
 * going through the receive/extract capability contract. This class exposes
 * {@link #addEnergy} and {@link #setEnergy} for that internal bookkeeping while
 * still guaranteeing the value never leaves the valid {@code [0, capacity]} range.
 */

public abstract class AbstractModEnergyStorage extends EnergyStorage{

    /** 
     * @param capacity   The maximum amount of energy that can be stored.
     * @param maxReceive The maximum amount of energy that can be received per operation
     * @param maxExtract The maximum amount of energy that can be extracted per operation
     */
    public AbstractModEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    /**
     * Directly adjusts stored energy, bypassing the receive/extract capability
     * contract. Used internally by machines for things like generation ticks,
     * where there's no "sender" to negotiate a transfer with.
     *
     * @param amount The amount of energy to add. Negative values remove energy.
     */
    public void addEnergy(int amount) {
        this.energy = Math.max(0, Math.min(this.capacity, this.energy + amount));
    }

    /**
     * Directly overwrites stored energy. Used for NBT loading and menu data-sync
     * writes, where we're restoring a known value rather than transferring energy.
     *
     * @param amount The new energy value.
     */
    public void setEnergy(int amount) {
        this.energy = Math.max(0, Math.min(this.capacity, amount));
    }

}
