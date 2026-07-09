package net.augmentedduck.technocraft.block.custom;

import javax.annotation.Nullable;

import net.augmentedduck.technocraft.block.entity.MachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

/**
 * Base class for all machine blocks.
 * 
 * <p>This class provides common functionality shared by every machine, including:
 * <ul>
 *  <li>Horizontal Facing
 *  <li>Lit/Unlit block state
 *  <li>Opening the machine menu when right-clicked
 *  <li>Dropping inventory contents when broken
 * </ul>
 * 
 * @param <T> The type of {@link BlockEntity} used by this machine.
 */
public abstract class AbstractMachineBlock<T extends BlockEntity & MachineBlockEntity> extends BaseEntityBlock {
    /** The horizontal diraction the machine is facing */
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    /** Whether the machine is currently active (lit) */
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    /**
     * Creates a new machine block with default properties.
     *
     * The default state faces north and starts in the unlit state.
     */
    protected AbstractMachineBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, false));
    }

    /**
     * Registers the block state properties used by every machine
     */
    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    /**
     * Gets the initial block state when the machine is placed.
     * 
     * The machine faces towards the player.
     */
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    /**
     * Create the block entity.
     * 
     * Each machine supplies its own block entity.
     */
    @Nullable
    @Override
    public abstract BlockEntity newBlockEntity(BlockPos pos, BlockState state);

    /**
     * Opens the machine GUI.
     * 
     * The interaction is only handled on the server, where the menu is opened
     * and the block position is sent to the screen handler.
     */
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        
        // Client reports success while the server performs the actual work.
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        // Open the machine if it exists
        if (level.getBlockEntity(pos) instanceof MachineBlockEntity be) {
            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.openMenu(be, buf -> buf.writeBlockPos(pos));
            }
            return InteractionResult.CONSUME;
        }

        // No valid machine block entity was found
        return InteractionResult.PASS;
    }

    /**
     * Called when block is destroyed.
     * 
     * The machine drops its inventory.
     */
    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        // Only drop items if the block itself is being replaced. (e.g. not when going from LIT to UNLIT)
        if (state.getBlock() != newState.getBlock()) {
            if (level.getBlockEntity(pos) instanceof MachineBlockEntity be) {
                be.drops();
            }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    /**
     * Render using its block model.
     */
    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}
