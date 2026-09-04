package net.augmentedduck.technocraft.energy;

import net.augmentedduck.technocraft.block.entity.cables.CableBlockEntity;
import net.neoforged.neoforge.energy.IEnergyStorage;

/**
 * Capability wrapper exposed by {@link CableBlockEntity}.
 *
 * <p>Cables never buffer energy themselves. Receiving energy immediately
 * triggers a network-wide push to every reachable machine, capped at the
 * cable's per-tick transfer limit. Extraction is not supported — cables only
 * relay energy that is actively pushed into them, they don't let machines
 * pull through them.
 */
public class CableEnergyStorage implements IEnergyStorage{

    private final CableBlockEntity cable;

    public CableEnergyStorage(CableBlockEntity cable) {
        this.cable = cable;
    }

    @Override
    public int receiveEnergy(int toReceive, boolean simulate) {
        return cable.distribute(toReceive, simulate);
    }

    @Override
    public int extractEnergy(int toExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored() {
        return 0;
    }

    @Override
    public int getMaxEnergyStored() {
        return 0;
    }

    @Override
    public boolean canExtract() {
        return false;
    }

    @Override
    public boolean canReceive() {
        return true;
    }

}
