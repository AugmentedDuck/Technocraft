package net.augmentedduck.technocraft.block.custom;

import com.mojang.serialization.MapCodec;

import net.augmentedduck.technocraft.block.entity.CompressorBlockEntity;
import net.augmentedduck.technocraft.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class CompressorBlock extends AbstractMachineBlock<CompressorBlockEntity>{

    public static final MapCodec<CompressorBlock> CODEC = simpleCodec(CompressorBlock::new);

    public CompressorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CompressorBlockEntity(pos, state);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? null : createTickerHelper(type, ModBlockEntities.COMPRESSOR_BE.get(), CompressorBlockEntity::serverTick);
    }

}
