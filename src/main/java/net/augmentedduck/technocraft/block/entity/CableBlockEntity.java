package net.augmentedduck.technocraft.block.entity;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.augmentedduck.technocraft.block.custom.AbstractCableBlock;
import net.augmentedduck.technocraft.energy.CableEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;

/**
 * Base block entity for all cable tiers (Tin/LV, Bronze/MV, ...).
 *
 * <p>Cables carry no energy of their own. Energy pushed into a cable is
 * relayed instantly across the whole connected network — following any
 * neighbor that is itself an {@link AbstractCableBlock}, regardless of tier —
 * to every reachable receiver at the edges of that network, split evenly
 * between them and capped at this cable's {@link #getMaxTransfer()}.
 *
 * <p>Subclasses just supply their {@link BlockEntityType} and per-tier
 * transfer rate (typically from {@link net.augmentedduck.technocraft.energy.ModEnergyTiers}).
 */
public abstract class CableBlockEntity extends BlockEntity{
    
    private final int maxTransfer;
    private final CableEnergyStorage energyStorage = new CableEnergyStorage(this);

    private int lastFlow;
    private long lastFlowGameTime = -1;

    protected CableBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState, int maxTransfer) {
        super(type, pos, blockState);
        this.maxTransfer = maxTransfer;
    }

    public CableEnergyStorage getEnergyStorage() {
        return energyStorage;
    }

    public int getMaxTransfer() {
        return maxTransfer;
    }

    /**
     * The throughput measured on the most recently completed network push
     * that touched this cable, in FE/t. Reads back as 0 once a tick has
     * passed without new flow, so a meter can't show a stale reading.
     */
    public int getFlow() {
        if (level == null || level.getGameTime() != lastFlowGameTime) return 0;
        return lastFlow;
    }

    /**
     * Pushes energy from this cable across the connected network to every
     * reachable energy receiver, splitting the available amount evenly the
     * same way {@code GeneratorBlockEntity.distributeEnergy} does for direct
     * neighbors. Offers are capped at this cable's own tier, so pushing
     * through a low-tier cable segment throttles the whole request even if
     * the source or a downstream cable is higher-tier.
     *
     * @param amount   The amount offered to the network this call.
     * @param simulate Whether to only calculate, without actually transferring.
     * @return         The amount actually accepted by receivers on the network.
     */
    public int distribute(int amount, boolean simulate) {
        if (level == null || amount <= 0) return 0;

        int offered = Math.min(amount, maxTransfer);

        List<CableBlockEntity> networkCables = new ArrayList<>();
        List<IEnergyStorage> receivers = new ArrayList<>();
        collectNetwork(networkCables, receivers);

        if (receivers.isEmpty()) return 0;

        int remaining = offered;
        int totalSent = 0;
        int share = Math.max(1, offered / receivers.size());

        for (IEnergyStorage receiver : receivers) {
            if (remaining <= 0) break;
            
            int attempt = Math.min(share, remaining);
            int accepted = receiver.receiveEnergy(attempt, simulate);

            totalSent += accepted;
            remaining -= accepted;
        }

        if (!simulate && totalSent > 0) {
            for (CableBlockEntity cable : networkCables) {
                cable.markFlow(totalSent);
            }
        }

        return totalSent;
    }

    private void collectNetwork(List<CableBlockEntity> visitedCables, List<IEnergyStorage> receivers) {
        Set<BlockPos> visitedPositions = new HashSet<>();
        Set<IEnergyStorage> seenReceivers = new HashSet<>();
        Deque<CableBlockEntity> queue = new ArrayDeque<>();

        visitedPositions.add(worldPosition);
        queue.add(this);

        while (!queue.isEmpty()) {
            CableBlockEntity current = queue.poll();
            visitedCables.add(current);

            BlockState state = current.getBlockState();
            if (!(state.getBlock() instanceof AbstractCableBlock<?> cableBlock)) continue;

            for (Direction direction : Direction.values()) {
                if (!state.getValue(cableBlock.propertyFor(direction))) continue;

                BlockPos neighborPos = current.getBlockPos().relative(direction);

                if (level.getBlockEntity(neighborPos) instanceof CableBlockEntity neighborCable) {
                    if (visitedPositions.add(neighborPos)) {
                        queue.add(neighborCable);
                    }

                    continue;
                }

                IEnergyStorage neighborStorage = level.getCapability(Capabilities.EnergyStorage.BLOCK, neighborPos, direction.getOpposite());
                if (neighborStorage != null && neighborStorage.canReceive() && seenReceivers.add(neighborStorage)) {
                    receivers.add(neighborStorage);
                }
            }
        }
    }

    private void markFlow(int amount) {
        if (level == null) return;
        this.lastFlow = amount;
        this.lastFlowGameTime = level.getGameTime();
    }
}
