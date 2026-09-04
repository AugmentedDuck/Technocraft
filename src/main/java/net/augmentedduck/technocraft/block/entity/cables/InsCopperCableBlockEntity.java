package net.augmentedduck.technocraft.block.entity.cables;

import net.augmentedduck.technocraft.block.entity.ModBlockEntities;
import net.augmentedduck.technocraft.energy.ModEnergyTiers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class InsCopperCableBlockEntity extends CableBlockEntity {

    public InsCopperCableBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.INS_COPPER_CABLE_BE.get(), pos, blockState, ModEnergyTiers.MV.getMaxTransfer());
    }

}
