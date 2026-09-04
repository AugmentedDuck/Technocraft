package net.augmentedduck.technocraft.block.custom.consumers;

import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;

import net.augmentedduck.technocraft.block.custom.AbstractMachineBlock;
import net.augmentedduck.technocraft.block.entity.ModBlockEntities;
import net.augmentedduck.technocraft.block.entity.consumers.ElectricFurnaceBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ElectricFurnaceBlock extends AbstractMachineBlock<ElectricFurnaceBlockEntity>{

    public static final MapCodec<ElectricFurnaceBlock> CODEC = simpleCodec(ElectricFurnaceBlock::new);
    
    public ElectricFurnaceBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ElectricFurnaceBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? null : createTickerHelper(type, ModBlockEntities.ELECTRIC_FURNACE_BE.get(), ElectricFurnaceBlockEntity::serverTick);
    }
}
