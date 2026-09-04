package net.augmentedduck.technocraft.block.entity.cables;

import net.augmentedduck.technocraft.block.entity.ModBlockEntities;
import net.augmentedduck.technocraft.energy.ModEnergyTiers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class InsTinCableBlockEntity extends CableBlockEntity {

    public InsTinCableBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.INS_TIN_CABLE_BE.get(), pos, blockState, ModEnergyTiers.LV.getMaxTransfer());
    }

}
