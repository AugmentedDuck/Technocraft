package net.augmentedduck.technocraft.energy;

import net.neoforged.neoforge.energy.EnergyStorage;

public class ConsumerEnergyStorage extends AbstractModEnergyStorage {

    public ConsumerEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer, 0);
    }

    public int consumeEnergy(int amount) {
        int toConsume = Math.min(amount, this.energy);
        this.energy -= toConsume;
        return toConsume;
    }
}
