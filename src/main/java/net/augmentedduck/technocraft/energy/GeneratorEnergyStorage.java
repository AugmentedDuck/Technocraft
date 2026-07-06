package net.augmentedduck.technocraft.energy;

import net.neoforged.neoforge.energy.EnergyStorage;

public class GeneratorEnergyStorage extends EnergyStorage {

    public GeneratorEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, 0, maxTransfer, 0);
    }

    public void addEnergy(int amount) {
        this.energy = Math.max(0, Math.min(this.capacity, this.energy + amount));
    }

    public void setEnergy(int amount) {
        this.energy = Math.max(0, Math.min(this.capacity, amount));
    }
}
