package net.augmentedduck.technocraft.block.custom.generators;

import javax.annotation.Nullable;

import com.mojang.serialization.MapCodec;

import net.augmentedduck.technocraft.block.custom.AbstractMachineBlock;
import net.augmentedduck.technocraft.block.entity.ModBlockEntities;
import net.augmentedduck.technocraft.block.entity.generators.SolarPanelBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class SolarPanelBlock extends AbstractMachineBlock<SolarPanelBlockEntity>{
    public static final MapCodec<GeneratorBlock> CODEC = simpleCodec(GeneratorBlock::new);
    
    public SolarPanelBlock(Properties properties) {
        super(properties);
    }
    
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SolarPanelBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? null : createTickerHelper(type, ModBlockEntities.SOLAR_PANEL_BE.get(), SolarPanelBlockEntity::serverTick);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        return InteractionResult.PASS;        
    }
}
