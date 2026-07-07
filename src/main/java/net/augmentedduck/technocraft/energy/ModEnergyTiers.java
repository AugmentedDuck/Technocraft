package net.augmentedduck.technocraft.energy;

public enum ModEnergyTiers {
    LV(320),
    MV(1280),
    HV(5120),
    EV(20480),
    IV(81920);

    
    private final int maxTransfer;
    
    ModEnergyTiers(int maxTransfer) {
        this.maxTransfer = maxTransfer;
    }

    public int getMaxTransfer() {
        return maxTransfer;
    }
}
