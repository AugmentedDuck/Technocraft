package net.augmentedduck.technocraft.block.custom.cables;

import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;

import net.augmentedduck.technocraft.block.entity.cables.GoldCableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GoldCableBlock extends AbstractCableBlock<GoldCableBlockEntity> {

    public static final MapCodec<GoldCableBlock> CODEC = simpleCodec(GoldCableBlock::new);

    public GoldCableBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GoldCableBlockEntity(pos, state);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

}
