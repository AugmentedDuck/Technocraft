package net.augmentedduck.technocraft.energy;

import net.neoforged.neoforge.energy.EnergyStorage;

public class BatteryEnergyStorage extends EnergyStorage {

    public BatteryEnergyStorage(int capacity, int maxInput, int maxOutput) {
        super(capacity, maxInput, maxOutput, 0);
    }

    public void addEnergy(int amount) {
        this.energy = Math.max(0, Math.min(this.capacity, this.energy + amount));
    }

    public void setEnergy(int amount) {
        this.energy = Math.max(0, Math.min(this.capacity, amount));
    }
}
