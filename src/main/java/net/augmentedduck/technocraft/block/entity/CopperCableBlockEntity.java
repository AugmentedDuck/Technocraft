package net.augmentedduck.technocraft.block.entity;

import net.augmentedduck.technocraft.energy.ModEnergyTiers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class CopperCableBlockEntity extends CableBlockEntity {

    public CopperCableBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.COPPER_CABLE_BE.get(), pos, blockState, ModEnergyTiers.MV.getMaxTransfer());
    }

}
