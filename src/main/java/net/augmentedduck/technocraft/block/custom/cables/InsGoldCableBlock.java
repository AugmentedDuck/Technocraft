package net.augmentedduck.technocraft.block.custom.cables;

import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;

import net.augmentedduck.technocraft.block.entity.cables.InsGoldCableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class InsGoldCableBlock extends AbstractCableBlock<InsGoldCableBlockEntity> {

    public static final MapCodec<InsGoldCableBlock> CODEC = simpleCodec(InsGoldCableBlock::new);

    public InsGoldCableBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new InsGoldCableBlockEntity(pos, state);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

}
