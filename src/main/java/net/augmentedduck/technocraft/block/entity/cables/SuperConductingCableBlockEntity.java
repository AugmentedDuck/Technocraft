package net.augmentedduck.technocraft.block.entity.cables;

import net.augmentedduck.technocraft.block.entity.ModBlockEntities;
import net.augmentedduck.technocraft.energy.ModEnergyTiers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class SuperConductingCableBlockEntity extends CableBlockEntity {

    public SuperConductingCableBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.SUPER_CONDUCTING_CABLE_BE.get(), pos, blockState, ModEnergyTiers.IV.getMaxTransfer());
    }

}