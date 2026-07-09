package net.augmentedduck.technocraft.energy;

/**
 * Energy storage for machines that only ever consume energy (e.g. the Electric
 * Furnace). maxExtract is forced to 0 so nothing can pull FE back out through
 * the capability. Energy only leaves via {@link #consumeEnergy}, called by the
 * machine itself when it does work.
 */
public class ConsumerEnergyStorage extends AbstractModEnergyStorage {

    public ConsumerEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer, 0);
    }

    /**
     * Spends up to {@code amount} energy on processing work this tick.
     * Clamped to whatever is actually stored so a machine can never go negative
     * even if its per-tick cost momentarily exceeds its buffer.
     *
     * @param amount The amount requested to use 
     * @return       The amount actually consumed (may be less than requested)
     */
    public int consumeEnergy(int amount) {
        int toConsume = Math.min(amount, this.energy);
        this.energy -= toConsume;
        return toConsume;
    }
}
