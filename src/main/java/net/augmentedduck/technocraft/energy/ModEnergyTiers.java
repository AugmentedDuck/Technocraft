package net.augmentedduck.technocraft.energy;

/**
 * IndustrialCraft-style voltage tiers (LV/MV/HV/EV/IV), each roughly 4x the
 * transfer rate of the one before it. This lets future machines/cables opt
 * into a "tier" instead of hardcoding arbitrary FE/t numbers, and gives players
 * a familiar mental model (overvolting a low-tier cable = bad) if we ever add
 * tier-mismatch explosions like IC2 did.
 *
 * <p>Not yet wired into most machines — currently only the Electric Furnace uses
 * {@link #LV} for its transfer rate. Treat other tiers as reserved for later
 * machines/cables rather than in active use.
 */
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
