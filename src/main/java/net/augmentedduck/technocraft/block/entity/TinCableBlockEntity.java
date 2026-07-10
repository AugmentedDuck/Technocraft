package net.augmentedduck.technocraft.block.entity;

import net.augmentedduck.technocraft.energy.ModEnergyTiers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TinCableBlockEntity extends CableBlockEntity {

    public TinCableBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.TIN_CABLE_BE.get(), pos, blockState, ModEnergyTiers.LV.getMaxTransfer());
    }

}
