package net.augmentedduck.technocraft.energy;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;

/**
 * Shared "push energy to every adjacent receiver" logic used by every
 * generator-style block.
 * Now the fair-share rules only need to be correct in one place.
 */
public final class EnergyDistributor {
    private EnergyDistributor() {}

    /**
     * Pushes up to {@code maxOffer} energy from {@code source} to every
     * neighboring block exposing a receiving energy capability, split evenly
     * between them.
     *
     * <p>The share per receiver is computed from a fixed snapshot of what's
     * actually available this tick ({@code min(maxOffer, stored)}), not
     * re-read from the live buffer as we go - otherwise receivers early in
     * {@link Direction#values()} would starve receivers later in the loop.
     *
     * @param maxOffer The source's own per-tick extract rate.
     * @return         {@code true} if any energy actually moved.
     */
    public static boolean distributeToNeighbors(Level level, BlockPos pos, IEnergyStorage source, int maxOffer) {
        if (source.getEnergyStored() <= 0) return false;

        List<IEnergyStorage> receivers = new ArrayList<>();

        for (Direction direction : Direction.values()) {
            BlockPos neighborPos = pos.relative(direction);
            if (level.getBlockEntity(neighborPos) == null) continue;

            IEnergyStorage neighborStorage = level.getCapability(Capabilities.EnergyStorage.BLOCK, neighborPos, direction.getOpposite());

            if (neighborStorage != null && neighborStorage.canReceive()) {
                receivers.add(neighborStorage);
            }
        }

        if (receivers.isEmpty()) return false;

        int available = Math.min(maxOffer, source.getEnergyStored());
        if (available <= 0) return false;
        
        int share = Math.max(1, available / receivers.size());
        boolean changed = false;

        for (IEnergyStorage receiver : receivers) {
            int extracted = source.extractEnergy(share, true);
            if (extracted > 0) {
                int accepted = receiver.receiveEnergy(extracted, false);
                if (accepted > 0) {
                    source.extractEnergy(accepted, false);
                    changed = true;
                }
            }
        }
        
        return changed;
    }
}
