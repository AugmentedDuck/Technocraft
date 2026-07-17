package net.augmentedduck.technocraft.block.custom;

import com.mojang.serialization.MapCodec;

import net.augmentedduck.technocraft.block.entity.ExtruderBlockEntity;
import net.augmentedduck.technocraft.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ExtruderBlock extends AbstractMachineBlock<ExtruderBlockEntity> {

    public static final MapCodec<ExtruderBlock> CODEC = simpleCodec(ExtruderBlock::new);

    public ExtruderBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ExtruderBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? null : createTickerHelper(type, ModBlockEntities.EXTRUDER_BE.get(), ExtruderBlockEntity::serverTick);
    }
}
