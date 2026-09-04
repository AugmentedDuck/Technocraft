package net.augmentedduck.technocraft.block.custom.cables;

import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;

import net.augmentedduck.technocraft.block.entity.cables.SuperConductingCableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SuperConductingCableBlock extends AbstractCableBlock<SuperConductingCableBlockEntity> {

    public static final MapCodec<TinCableBlock> CODEC = simpleCodec(TinCableBlock::new);

    public SuperConductingCableBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SuperConductingCableBlockEntity(pos, state);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

}
