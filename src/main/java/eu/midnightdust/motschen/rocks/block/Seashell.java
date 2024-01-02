package eu.midnightdust.motschen.rocks.block;

import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.blockstates.SeashellVariation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
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

public class Seashell extends Block implements SimpleWaterloggedBlock {

	private static final VoxelShape SHAPE;
	private static final EnumProperty<SeashellVariation> SEASHELL_VARIATION = Rocks.SEASHELL_VARIATION;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	public Seashell() {
		super(Properties.ofFullCopy(Blocks.POPPY).noOcclusion().sound(SoundType.STONE));
		this.registerDefaultState(this.stateDefinition.any().setValue(SEASHELL_VARIATION, SeashellVariation.PINK).setValue(WATERLOGGED, false));
	}

	@Override
	public FluidState getFluidState(BlockState blockState_1) {
		return blockState_1.getValue(WATERLOGGED) ? Fluids.WATER.getSource(true) : super.getFluidState(blockState_1);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext itemPlacementContext) {
		FluidState fluidState = itemPlacementContext.getLevel().getFluidState(itemPlacementContext.getClickedPos());
		return Objects.requireNonNull(super.getStateForPlacement(itemPlacementContext))
				.setValue(SEASHELL_VARIATION, SeashellVariation.PINK).setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
	}

	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (player.isCreative()) {
			if (state.getValue(SEASHELL_VARIATION) == SeashellVariation.YELLOW) {
				world.setBlockAndUpdate(pos, state.setValue(SEASHELL_VARIATION, SeashellVariation.WHITE));
			}
			if (state.getValue(SEASHELL_VARIATION) == SeashellVariation.WHITE) {
				world.setBlockAndUpdate(pos, state.setValue(SEASHELL_VARIATION, SeashellVariation.PINK));
			}
			if (state.getValue(SEASHELL_VARIATION) == SeashellVariation.PINK) {
				world.setBlockAndUpdate(pos, state.setValue(SEASHELL_VARIATION, SeashellVariation.YELLOW));
			}
			return InteractionResult.SUCCESS;
		} else return InteractionResult.FAIL;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(SEASHELL_VARIATION, WATERLOGGED);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	static {
		SHAPE = box(0, 0, 0, 16, 3, 16);
	}

	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		return world.getBlockState(pos.below()).isFaceSturdy(world, pos, Direction.UP);
	}

	public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor world, BlockPos pos, BlockPos posFrom) {
		return !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, newState, world, pos, posFrom);
	}
}
