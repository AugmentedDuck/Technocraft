package net.augmentedduck.technocraft.energy;

import net.neoforged.neoforge.energy.EnergyStorage;

public abstract class AbstractModEnergyStorage extends EnergyStorage{

    public AbstractModEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    public void addEnergy(int amount) {
        this.energy = Math.max(0, Math.min(this.capacity, this.energy + amount));
    }

    public void setEnergy(int amount) {
        this.energy = Math.max(0, Math.min(this.capacity, amount));
    }

}
