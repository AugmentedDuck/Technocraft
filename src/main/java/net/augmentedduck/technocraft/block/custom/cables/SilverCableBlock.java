package net.augmentedduck.technocraft.block.custom.cables;

import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;

import net.augmentedduck.technocraft.block.entity.cables.SilverCableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SilverCableBlock extends AbstractCableBlock<SilverCableBlockEntity> {

    public static final MapCodec<SilverCableBlock> CODEC = simpleCodec(SilverCableBlock::new);

    public SilverCableBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SilverCableBlockEntity(pos, state);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

}
