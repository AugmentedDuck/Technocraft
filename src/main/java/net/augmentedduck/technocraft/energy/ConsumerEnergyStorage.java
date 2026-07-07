package net.augmentedduck.technocraft.energy;

import net.neoforged.neoforge.energy.EnergyStorage;

public class ConsumerEnergyStorage extends EnergyStorage {

    public ConsumerEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer, 0, 0);
    }

    public void addEnergy(int amount) {
        this.energy = Math.max(0, Math.min(this.capacity, this.energy + amount));
    }

    public void setEnergy(int amount) {
        this.energy = Math.max(0, Math.min(this.capacity, amount));
    }

    public int consumeEnergy(int amount) {
        int toConsume = Math.min(amount, this.energy);
        this.energy -= toConsume;
        return toConsume;
    }
}
