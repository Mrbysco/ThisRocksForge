package eu.midnightdust.motschen.rocks.block;

import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.blockstates.RockVariation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Objects;

public class Rock extends Block implements SimpleWaterloggedBlock {

	private static final VoxelShape SHAPE;
	private static final VoxelShape SHAPE_LARGE;
	private static final EnumProperty<RockVariation> ROCK_VARIATION = Rocks.ROCK_VARIATION;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public Rock(Properties properties) {
		super(properties.noOcclusion().sound(SoundType.STONE));
		this.registerDefaultState(this.stateDefinition.any().setValue(ROCK_VARIATION, RockVariation.TINY).setValue(WATERLOGGED, false));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext itemPlacementContext) {
		return Objects.requireNonNull(super.getStateForPlacement(itemPlacementContext))
				.setValue(ROCK_VARIATION, RockVariation.TINY).setValue(WATERLOGGED, false);
	}

	@Override
	public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
		if (player.isCreative()) {
			if (state.getValue(ROCK_VARIATION) == RockVariation.TINY) {
				level.setBlockAndUpdate(pos, state.setValue(ROCK_VARIATION, RockVariation.SMALL));
			}
			if (state.getValue(ROCK_VARIATION) == RockVariation.SMALL) {
				level.setBlockAndUpdate(pos, state.setValue(ROCK_VARIATION, RockVariation.MEDIUM));
			}
			if (state.getValue(ROCK_VARIATION) == RockVariation.MEDIUM) {
				level.setBlockAndUpdate(pos, state.setValue(ROCK_VARIATION, RockVariation.LARGE));
			}
			if (state.getValue(ROCK_VARIATION) == RockVariation.LARGE) {
				level.setBlockAndUpdate(pos, state.setValue(ROCK_VARIATION, RockVariation.TINY));
			}
			return InteractionResult.SUCCESS;
		} else return InteractionResult.FAIL;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(ROCK_VARIATION, WATERLOGGED);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext context) {
		return state.getValue(ROCK_VARIATION).equals(RockVariation.LARGE) ? SHAPE_LARGE : SHAPE;
	}

	static {
		SHAPE = box(0, 0, 0, 16, 3, 16);
		SHAPE_LARGE = box(0, 0, 0, 16, 3, 16);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		return world.getBlockState(pos.below()).isFaceSturdy(world, pos, Direction.UP);
	}

	@Override
	protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
		return !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
	}
}
