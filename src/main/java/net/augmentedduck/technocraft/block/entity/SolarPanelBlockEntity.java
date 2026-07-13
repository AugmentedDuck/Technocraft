package net.augmentedduck.technocraft.block.entity;

import java.util.ArrayList;
import java.util.List;

import net.augmentedduck.technocraft.energy.GeneratorEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;

public class SolarPanelBlockEntity extends BlockEntity implements MachineBlockEntity {
    public static final int ENERGY_CAPACITY = 10;
    public static final int ENERGY_PER_TICK = 10;
    public static final int ENERGY_EXTRACT_RATE = 10;

    private final GeneratorEnergyStorage energyStorage = new GeneratorEnergyStorage(ENERGY_CAPACITY, ENERGY_EXTRACT_RATE);

    public SolarPanelBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.SOLAR_PANEL_BE.get(), pos, blockState);
    }

    public IEnergyStorage getEnergyStorage() {
        return energyStorage;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, SolarPanelBlockEntity be) {
        boolean changed = false;

        if (isExposedToSky(level, pos) && level.isDay()) {
            int missing = be.energyStorage.getMaxEnergyStored() - be.energyStorage.getEnergyStored();
            
             if (missing > 0) {
                be.energyStorage.addEnergy(Math.min(ENERGY_PER_TICK, missing));
                changed = true;
            }
        }

        changed |= be.distributeEnergy(level, pos);

        if (changed) {
            setChanged(level, pos, state);
        }
    }

    private boolean distributeEnergy(Level level, BlockPos pos) {
        if (energyStorage.getEnergyStored() <= 0) return false;

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

        int available = Math.min(ENERGY_EXTRACT_RATE, energyStorage.getEnergyStored());
        if (available <= 0) return false;
        
        int share = Math.max(1, available / receivers.size());
        boolean changed = false;

        for (IEnergyStorage receiver : receivers) {
            int extracted = energyStorage.extractEnergy(share, true);
            if (extracted > 0) {
                int accepted = receiver.receiveEnergy(extracted, false);
                if (accepted > 0) {
                    energyStorage.extractEnergy(accepted, false);
                    changed = true;
                }
            }
        }
        
        return changed;
    }

    private static boolean isExposedToSky(Level level, BlockPos pos) {
        return level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE, pos).getY() <= pos.getY() + 1;
    }

    @Override
    public Component getDisplayName() {
        throw new UnsupportedOperationException("Unimplemented method 'getDisplayName'");
    }

    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        throw new UnsupportedOperationException("Unimplemented method 'createMenu'");
    }

    @Override
    public void drops() {
       
    }
}
