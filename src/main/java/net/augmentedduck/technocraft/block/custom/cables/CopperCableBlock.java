package net.augmentedduck.technocraft.block.custom.cables;

import com.mojang.serialization.MapCodec;

import net.augmentedduck.technocraft.block.entity.cables.CopperCableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CopperCableBlock extends AbstractCableBlock<CopperCableBlockEntity>{

    public static final MapCodec<CopperCableBlock> CODEC = simpleCodec(CopperCableBlock::new);

    public CopperCableBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CopperCableBlockEntity(pos, state);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

}
