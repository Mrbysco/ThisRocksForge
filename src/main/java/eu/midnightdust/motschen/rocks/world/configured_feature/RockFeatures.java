package eu.midnightdust.motschen.rocks.world.configured_feature;

import com.google.common.collect.ImmutableList;
import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.blockstates.RockVariation;
import eu.midnightdust.motschen.rocks.registry.RocksRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class RockFeatures {
    public static ConfiguredFeature<?, ?> ROCK_FEATURE = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(128, 0, 0,() -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(RocksRegistry.ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.TINY), 10)
                            .add(RocksRegistry.ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.SMALL), 7)
                            .add(RocksRegistry.ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.MEDIUM), 5)
                            .add(RocksRegistry.ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.LARGE), 1).build())))
            .filtered(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.not(BlockPredicate.matchesBlocks(ImmutableList.of(Blocks.ICE,Blocks.SAND,Blocks.RED_SAND), new BlockPos(0, -1, 0)))))));
    public static ConfiguredFeature<?, ?> GRANITE_ROCK_FEATURE = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(128, 0, 0,() -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(RocksRegistry.GRANITE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.TINY), 10)
                            .add(RocksRegistry.GRANITE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.SMALL), 7)
                            .add(RocksRegistry.GRANITE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.MEDIUM), 5)
                            .add(RocksRegistry.GRANITE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.LARGE), 1).build())))
            .filtered(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(ImmutableList.of(Blocks.GRANITE), new BlockPos(0, -1, 0))))));
    public static ConfiguredFeature<?, ?> DIORITE_ROCK_FEATURE = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(128, 0, 0,() -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(RocksRegistry.DIORITE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.TINY), 10)
                            .add(RocksRegistry.DIORITE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.SMALL), 7)
                            .add(RocksRegistry.DIORITE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.MEDIUM), 5)
                            .add(RocksRegistry.DIORITE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.LARGE), 1).build())))
            .filtered(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(ImmutableList.of(Blocks.DIORITE), new BlockPos(0, -1, 0))))));
    public static ConfiguredFeature<?, ?> ANDESITE_ROCK_FEATURE = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(128, 0, 0,() -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(RocksRegistry.ANDESITE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.TINY), 10)
                            .add(RocksRegistry.ANDESITE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.SMALL), 7)
                            .add(RocksRegistry.ANDESITE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.MEDIUM), 5)
                            .add(RocksRegistry.ANDESITE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.LARGE), 1).build())))
            .filtered(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(ImmutableList.of(Blocks.ANDESITE), new BlockPos(0, -1, 0))))));
    public static ConfiguredFeature<?, ?> SAND_ROCK_FEATURE = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(128, 0, 0,() -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(RocksRegistry.SAND_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.TINY), 10)
                            .add(RocksRegistry.SAND_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.SMALL), 7)
                            .add(RocksRegistry.SAND_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.MEDIUM), 5)
                            .add(RocksRegistry.SAND_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.LARGE), 1).build())))
            .filtered(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(ImmutableList.of(Blocks.SAND, Blocks.SANDSTONE), new BlockPos(0, -1, 0))))));
    public static ConfiguredFeature<?, ?> RED_SAND_ROCK_FEATURE = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(128, 0, 0,() -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(RocksRegistry.RED_SAND_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.TINY), 10)
                            .add(RocksRegistry.RED_SAND_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.SMALL), 7)
                            .add(RocksRegistry.RED_SAND_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.MEDIUM), 5)
                            .add(RocksRegistry.RED_SAND_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.LARGE), 1).build())))
            .filtered(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(ImmutableList.of(Blocks.RED_SAND, Blocks.RED_SANDSTONE), new BlockPos(0, -1, 0))))));
    public static ConfiguredFeature<?, ?> END_STONE_ROCK_FEATURE = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(128, 0, 0,() -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(RocksRegistry.END_STONE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.TINY), 10)
                            .add(RocksRegistry.END_STONE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.SMALL), 7)
                            .add(RocksRegistry.END_STONE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.MEDIUM), 5)
                            .add(RocksRegistry.END_STONE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.LARGE), 1).build())
                    )).placed()));
    public static ConfiguredFeature<?, ?> GRAVEL_ROCK_FEATURE = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(128, 0, 0,() -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(RocksRegistry.GRAVEL_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.TINY), 10)
                            .add(RocksRegistry.GRAVEL_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.SMALL), 7)
                            .add(RocksRegistry.GRAVEL_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.MEDIUM), 5)
                            .add(RocksRegistry.GRAVEL_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION,RockVariation.LARGE), 1).build())))
            .filtered(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(ImmutableList.of(Blocks.GRAVEL), new BlockPos(0, -1, 0))))));

    public static PlacedFeature ROCK_PLACED_FEATURE = ROCK_FEATURE.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    public static PlacedFeature GRANITE_ROCK_PLACED_FEATURE = GRANITE_ROCK_FEATURE.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    public static PlacedFeature DIORITE_ROCK_PLACED_FEATURE = DIORITE_ROCK_FEATURE.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    public static PlacedFeature ANDESITE_ROCK_PLACED_FEATURE = ANDESITE_ROCK_FEATURE.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    public static PlacedFeature SAND_ROCK_PLACED_FEATURE = SAND_ROCK_FEATURE.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    public static PlacedFeature RED_SAND_ROCK_PLACED_FEATURE = RED_SAND_ROCK_FEATURE.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    public static PlacedFeature END_STONE_ROCK_PLACED_FEATURE = END_STONE_ROCK_FEATURE.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    public static PlacedFeature GRAVEL_ROCK_PLACED_FEATURE = GRAVEL_ROCK_FEATURE.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

    public static void init() {
        FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "rock").toString(), ROCK_FEATURE);
        FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "granite_rock").toString(), GRANITE_ROCK_FEATURE);
        FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "diorite_rock").toString(), DIORITE_ROCK_FEATURE);
        FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "andesite_rock").toString(), ANDESITE_ROCK_FEATURE);
        FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "sand_rock").toString(), SAND_ROCK_FEATURE);
        FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "red_sand_rock").toString(), RED_SAND_ROCK_FEATURE);
        FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "endstone_rock").toString(), END_STONE_ROCK_FEATURE);
        FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "gravel_rock").toString(), GRAVEL_ROCK_FEATURE);

        PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "rock").toString(), ROCK_PLACED_FEATURE);
        PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "granite_rock").toString(), GRANITE_ROCK_PLACED_FEATURE);
        PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "diorite_rock").toString(), DIORITE_ROCK_PLACED_FEATURE);
        PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "andesite_rock").toString(), ANDESITE_ROCK_PLACED_FEATURE);
        PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "sand_rock").toString(), SAND_ROCK_PLACED_FEATURE);
        PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "red_sand_rock").toString(), RED_SAND_ROCK_PLACED_FEATURE);
        PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "endstone_rock").toString(), END_STONE_ROCK_PLACED_FEATURE);
        PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "gravel_rock").toString(), GRAVEL_ROCK_PLACED_FEATURE);
    }

}
