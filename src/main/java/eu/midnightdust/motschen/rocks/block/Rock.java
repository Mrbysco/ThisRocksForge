package eu.midnightdust.motschen.rocks.block;

import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.blockstates.RockVariation;
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
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Objects;

public class Rock extends Block {

    private static final VoxelShape SHAPE;
    private static final EnumProperty<RockVariation> ROCK_VARIATION = Rocks.ROCK_VARIATION;

    public Rock() {
        super(Properties.copy(Blocks.POPPY).noOcclusion().sound(SoundType.STONE));
        this.registerDefaultState(this.stateDefinition.any().setValue(ROCK_VARIATION, RockVariation.TINY));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext itemPlacementContext) {
        return Objects.requireNonNull(super.getStateForPlacement(itemPlacementContext))
                .setValue(ROCK_VARIATION, RockVariation.TINY);
    }
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (player.isCreative()) {
            if (state.getValue(ROCK_VARIATION) == RockVariation.TINY) {
                world.setBlockAndUpdate(pos, state.setValue(ROCK_VARIATION, RockVariation.SMALL));
            }
            if (state.getValue(ROCK_VARIATION) == RockVariation.SMALL) {
                world.setBlockAndUpdate(pos, state.setValue(ROCK_VARIATION, RockVariation.MEDIUM));
            }
            if (state.getValue(ROCK_VARIATION) == RockVariation.MEDIUM) {
                world.setBlockAndUpdate(pos, state.setValue(ROCK_VARIATION, RockVariation.LARGE));
            }
            if (state.getValue(ROCK_VARIATION) == RockVariation.LARGE) {
                world.setBlockAndUpdate(pos, state.setValue(ROCK_VARIATION, RockVariation.TINY));
            }
            return InteractionResult.SUCCESS;
        }
        else return InteractionResult.FAIL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ROCK_VARIATION);
    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter view, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
    static {
        SHAPE = box(0, 0, 0, 16, 3, 16);
    }

    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return world.getBlockState(pos.below()).isFaceSturdy(world,pos,Direction.UP);
    }
    public BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor world, BlockPos pos, BlockPos posFrom) {
        return !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, newState, world, pos, posFrom);
    }
}
