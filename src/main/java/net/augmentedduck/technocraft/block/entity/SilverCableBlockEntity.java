package net.augmentedduck.technocraft.block.entity;

import net.augmentedduck.technocraft.energy.ModEnergyTiers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class SilverCableBlockEntity extends CableBlockEntity {

    public SilverCableBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.SILVER_CABLE_BE.get(), pos, blockState, ModEnergyTiers.EV.getMaxTransfer());
    }

}
