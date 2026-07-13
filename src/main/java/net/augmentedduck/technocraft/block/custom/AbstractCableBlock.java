package net.augmentedduck.technocraft.block.custom;

import java.util.Map;

import net.augmentedduck.technocraft.block.entity.CableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.capabilities.Capabilities;

/**
 * Base class for all cable tiers (Tin/LV, Bronze/MV, ...).
 *
 * <p>Provides the shared connection state (one {@code BooleanProperty} per
 * {@link Direction}), voxel shape, and neighbor-connection rules. A cable
 * connects toward a neighbor if that neighbor is <em>any</em> tier of cable,
 * or exposes an energy capability facing it — tier compatibility isn't
 * enforced at the connection level, only at transfer time via each tier's
 * {@link CableBlockEntity#getMaxTransfer()}.
 *
 * @param <T> The type of {@link CableBlockEntity} used by this cable tier.
 */
public abstract class AbstractCableBlock<T extends CableBlockEntity> extends BaseEntityBlock {

    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty UP = BlockStateProperties.UP;
    public static final BooleanProperty DOWN = BlockStateProperties.DOWN;

    private static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = Map.of(
        Direction.NORTH, NORTH,
        Direction.EAST, EAST,
        Direction.SOUTH, SOUTH,
        Direction.WEST, WEST,
        Direction.UP, UP,
        Direction.DOWN, DOWN
    );

    private static final VoxelShape CORE = Block.box(6, 6, 6, 10, 10, 10);

    private static final Map<Direction, VoxelShape> ARMS = Map.of(
        Direction.DOWN,  Block.box(6, 0, 6, 10, 6, 10),
        Direction.UP,    Block.box(6, 10, 6, 10, 16, 10),
        Direction.NORTH, Block.box(6, 6, 0, 10, 10, 6),
        Direction.SOUTH, Block.box(6, 6, 10, 10, 10, 16),
        Direction.WEST,  Block.box(0, 6, 6, 6, 10, 10),
        Direction.EAST,  Block.box(10, 6, 6, 16, 10, 10)
    );

    protected AbstractCableBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.stateDefinition.any().setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false).setValue(UP, false).setValue(DOWN, false));
    }

    public BooleanProperty propertyFor(Direction direction) {
        return PROPERTY_BY_DIRECTION.get(direction);
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = defaultBlockState();
        for (Direction direction : Direction.values()) {
            state = state.setValue(propertyFor(direction), connectsTo(context.getLevel(), context.getClickedPos(), direction));
        }

        return state;
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        return state.setValue(propertyFor(direction), connectsTo(level, pos, direction));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape shape = CORE;
        for (Direction direction : Direction.values()) {
            if (state.getValue(propertyFor(direction))) {
                shape = Shapes.or(shape, ARMS.get(direction));
            }
        }

        return shape;
    }

    private static boolean connectsTo(LevelAccessor level, BlockPos pos, Direction direction) {
        BlockPos neighborPos = pos.relative(direction);
        BlockState neighborState = level.getBlockState(neighborPos);

        if (neighborState.getBlock() instanceof AbstractCableBlock<?>) return true;
        if (!(level instanceof Level realLevel)) return false;

        return realLevel.getCapability(Capabilities.EnergyStorage.BLOCK, neighborPos, direction.getOpposite()) != null;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}
