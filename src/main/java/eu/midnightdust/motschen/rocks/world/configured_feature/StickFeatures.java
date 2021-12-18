package eu.midnightdust.motschen.rocks.world.configured_feature;

import com.google.common.collect.ImmutableList;
import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.blockstates.StickVariation;
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

public class StickFeatures {
    public static ConfiguredFeature<?, ?> OAK_STICK_FEATURE = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(128, 0, 0,() -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(RocksRegistry.OAK_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION,StickVariation.SMALL), 7)
                            .add(RocksRegistry.OAK_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION,StickVariation.MEDIUM), 5)
                            .add(RocksRegistry.OAK_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION,StickVariation.LARGE), 1).build())))
            .filtered(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE))));
    public static ConfiguredFeature<?, ?> SPRUCE_STICK_FEATURE = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(128, 0, 0,() -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(RocksRegistry.SPRUCE_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION,StickVariation.SMALL), 7)
                            .add(RocksRegistry.SPRUCE_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION,StickVariation.MEDIUM), 5)
                            .add(RocksRegistry.SPRUCE_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION,StickVariation.LARGE), 1)
                            .add(RocksRegistry.PINECONE.get().defaultBlockState(), 1).build())))
            .filtered(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE))));
    public static ConfiguredFeature<?, ?> BIRCH_STICK_FEATURE = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(128, 0, 0,() -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(RocksRegistry.BIRCH_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION,StickVariation.SMALL), 7)
                            .add(RocksRegistry.BIRCH_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION,StickVariation.MEDIUM), 5)
                            .add(RocksRegistry.BIRCH_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION,StickVariation.LARGE), 1).build())))
            .filtered(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE))));
    public static ConfiguredFeature<?, ?> ACACIA_STICK_FEATURE = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(128, 0, 0,() -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(RocksRegistry.ACACIA_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION,StickVariation.SMALL), 7)
                            .add(RocksRegistry.ACACIA_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION,StickVariation.MEDIUM), 5)
                            .add(RocksRegistry.ACACIA_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION,StickVariation.LARGE), 1).build())))
            .filtered(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE))));
    public static ConfiguredFeature<?, ?> JUNGLE_STICK_FEATURE = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(128, 0, 0,() -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(RocksRegistry.JUNGLE_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION,StickVariation.SMALL), 7)
                            .add(RocksRegistry.JUNGLE_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION,StickVariation.MEDIUM), 5)
                            .add(RocksRegistry.JUNGLE_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION,StickVariation.LARGE), 1).build())))
            .filtered(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE))));
    public static ConfiguredFeature<?, ?> DARK_OAK_STICK_FEATURE = Feature.RANDOM_PATCH.configured(new RandomPatchConfiguration(128, 0, 0,() -> Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(
                    new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                            .add(RocksRegistry.DARK_OAK_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION,StickVariation.SMALL), 7)
                            .add(RocksRegistry.DARK_OAK_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION,StickVariation.MEDIUM), 5)
                            .add(RocksRegistry.DARK_OAK_STICK.get()
                                    .defaultBlockState().setValue(Rocks.STICK_VARIATION,StickVariation.LARGE), 1).build())))
            .filtered(BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE))));

    public static PlacedFeature OAK_STICK_PLACED_FEATURE = OAK_STICK_FEATURE.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    public static PlacedFeature SPRUCE_STICK_PLACED_FEATURE = SPRUCE_STICK_FEATURE.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    public static PlacedFeature BIRCH_STICK_PLACED_FEATURE = BIRCH_STICK_FEATURE.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    public static PlacedFeature ACACIA_STICK_PLACED_FEATURE = ACACIA_STICK_FEATURE.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    public static PlacedFeature JUNGLE_STICK_PLACED_FEATURE = JUNGLE_STICK_FEATURE.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    public static PlacedFeature DARK_OAK_STICK_PLACED_FEATURE = DARK_OAK_STICK_FEATURE.placed(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

    public static void init() {
        FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "oak_stick").toString(), OAK_STICK_FEATURE);
        FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "spruce_stick").toString(), SPRUCE_STICK_FEATURE);
        FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "birch_stick").toString(), BIRCH_STICK_FEATURE);
        FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "acacia_stick").toString(), ACACIA_STICK_FEATURE);
        FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "jungle_stick").toString(), JUNGLE_STICK_FEATURE);
        FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "dark_oak_stick").toString(), DARK_OAK_STICK_FEATURE);

        PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "oak_stick").toString(), OAK_STICK_PLACED_FEATURE);
        PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "spruce_stick").toString(), SPRUCE_STICK_PLACED_FEATURE);
        PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "birch_stick").toString(), BIRCH_STICK_PLACED_FEATURE);
        PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "acacia_stick").toString(), ACACIA_STICK_PLACED_FEATURE);
        PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "jungle_stick").toString(), JUNGLE_STICK_PLACED_FEATURE);
        PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "dark_oak_stick").toString(), DARK_OAK_STICK_PLACED_FEATURE);
    }

}
