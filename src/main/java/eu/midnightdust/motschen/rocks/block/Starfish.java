package eu.midnightdust.motschen.rocks.block;

import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.blockstates.StarfishVariation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlockItemStateProperties;
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
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Objects;

public class Starfish extends Block implements SimpleWaterloggedBlock {

	private static final VoxelShape SHAPE;
	private static final EnumProperty<StarfishVariation> STARFISH_VARIATION = Rocks.STARFISH_VARIATION;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public Starfish(Properties properties) {
		super(properties.noOcclusion().sound(SoundType.CORAL_BLOCK));
		this.registerDefaultState(this.stateDefinition.any().setValue(STARFISH_VARIATION, StarfishVariation.RED).setValue(WATERLOGGED, false));
	}

	@Override
	public FluidState getFluidState(BlockState blockState_1) {
		return blockState_1.getValue(WATERLOGGED) ? Fluids.WATER.getSource(true) : super.getFluidState(blockState_1);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext itemPlacementContext) {
		FluidState fluidState = itemPlacementContext.getLevel().getFluidState(itemPlacementContext.getClickedPos());
		return Objects.requireNonNull(super.getStateForPlacement(itemPlacementContext))
				.setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
	}

	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player) {
		ItemStack stack = new ItemStack(this);
		stack.applyComponents(DataComponentMap.builder()
				.set(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY
						.with(STARFISH_VARIATION, state.getValue(STARFISH_VARIATION))).build());
		return stack;
	}

	@Override
	public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
		if (player.isCreative()) {
			if (state.getValue(STARFISH_VARIATION) == StarfishVariation.RED) {
				level.setBlockAndUpdate(pos, state.setValue(STARFISH_VARIATION, StarfishVariation.PINK));
			}
			if (state.getValue(STARFISH_VARIATION) == StarfishVariation.PINK) {
				level.setBlockAndUpdate(pos, state.setValue(STARFISH_VARIATION, StarfishVariation.ORANGE));
			}
			if (state.getValue(STARFISH_VARIATION) == StarfishVariation.ORANGE) {
				level.setBlockAndUpdate(pos, state.setValue(STARFISH_VARIATION, StarfishVariation.RED));
			}
			return InteractionResult.SUCCESS;
		} else return InteractionResult.FAIL;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(STARFISH_VARIATION, WATERLOGGED);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	static {
		SHAPE = box(0, 0, 0, 16, 1, 16);
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		return world.getBlockState(pos.below()).isFaceSturdy(world, pos, Direction.UP);
	}

	@Override
	protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
		return !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
	}

	@Override
	protected boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
		return true;
	}
}
