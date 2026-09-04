package net.augmentedduck.technocraft.block.custom.cables;

import com.mojang.serialization.MapCodec;

import net.augmentedduck.technocraft.block.entity.cables.InsCopperCableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class InsCopperCableBlock extends AbstractCableBlock<InsCopperCableBlockEntity>{

    public static final MapCodec<InsCopperCableBlock> CODEC = simpleCodec(InsCopperCableBlock::new);

    public InsCopperCableBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new InsCopperCableBlockEntity(pos, state);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

}
