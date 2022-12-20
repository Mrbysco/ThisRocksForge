package eu.midnightdust.motschen.rocks.world.configured_feature;

import com.google.common.collect.ImmutableList;
import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.blockstates.SeashellVariation;
import eu.midnightdust.motschen.rocks.blockstates.StarfishVariation;
import eu.midnightdust.motschen.rocks.registry.RocksRegistry;
import eu.midnightdust.motschen.rocks.world.FeatureRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.ArrayList;
import java.util.List;


public class MiscFeatures {
	public static List<PlacementModifier> placementModifiers = List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final ResourceKey<ConfiguredFeature<?, ?>> SEASHELL_FEATURE = FeatureUtils.createKey("rocks:seashell");
	public static final ResourceKey<ConfiguredFeature<?, ?>> STARFISH_FEATURE = FeatureUtils.createKey("rocks:starfish");
	public static final ResourceKey<ConfiguredFeature<?, ?>> UNDERWATER_STARFISH_FEATURE = FeatureUtils.createKey("rocks:underwater_starfish");
	public static final ResourceKey<ConfiguredFeature<?, ?>> UNDERWATER_SEASHELL_FEATURE = FeatureUtils.createKey("rocks:underwater_seashell");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SNOWY_GEYSER_FEATURE = FeatureUtils.createKey("rocks:snowy_geyser");

	public static void configuredBootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
		FeatureUtils.register(context, SEASHELL_FEATURE,
				Feature.RANDOM_PATCH, new RandomPatchConfiguration(96, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
								.add(RocksRegistry.SEASHELL.get().defaultBlockState().setValue(Rocks.SEASHELL_VARIATION, SeashellVariation.YELLOW), 7)
								.add(RocksRegistry.SEASHELL.get().defaultBlockState().setValue(Rocks.SEASHELL_VARIATION, SeashellVariation.PINK), 2)
								.add(RocksRegistry.SEASHELL.get().defaultBlockState().setValue(Rocks.SEASHELL_VARIATION, SeashellVariation.WHITE), 6))),
						BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
								BlockPredicate.matchesBlocks(
										new BlockPos(0, -1, 0), ImmutableList.of(Blocks.SAND, Blocks.SANDSTONE, Blocks.RED_SAND, Blocks.RED_SANDSTONE))))));

		FeatureUtils.register(context, STARFISH_FEATURE,
				Feature.RANDOM_PATCH, new RandomPatchConfiguration(96, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
								.add(RocksRegistry.STARFISH.get().defaultBlockState().setValue(Rocks.STARFISH_VARIATION, StarfishVariation.RED), 2)
								.add(RocksRegistry.STARFISH.get().defaultBlockState().setValue(Rocks.STARFISH_VARIATION, StarfishVariation.PINK), 6)
								.add(RocksRegistry.STARFISH.get().defaultBlockState().setValue(Rocks.STARFISH_VARIATION, StarfishVariation.ORANGE), 7))),
						BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
								BlockPredicate.matchesBlocks(
										new BlockPos(0, -1, 0), ImmutableList.of(Blocks.SAND, Blocks.SANDSTONE, Blocks.RED_SAND, Blocks.RED_SANDSTONE))))));

		FeatureUtils.register(context, UNDERWATER_STARFISH_FEATURE,
				FeatureRegistry.UNDERWATER_STARFISH_FEATURE.get(), new ProbabilityFeatureConfiguration(1));

		FeatureUtils.register(context, UNDERWATER_SEASHELL_FEATURE,
				FeatureRegistry.UNDERWATER_SEASHELL_FEATURE.get(), new ProbabilityFeatureConfiguration(1));

		FeatureUtils.register(context, SNOWY_GEYSER_FEATURE,
				FeatureRegistry.SNOWY_GEYSER_FEATURE.get(), new ProbabilityFeatureConfiguration(1));
	}

	public static final ResourceKey<PlacedFeature> PLACED_SEASHELL_FEATURE = PlacementUtils.createKey("rocks:seashell");
	public static final ResourceKey<PlacedFeature> PLACED_STARFISH_FEATURE = PlacementUtils.createKey("rocks:starfish");
	public static final ResourceKey<PlacedFeature> PLACED_UNDERWATER_STARFISH_FEATURE = PlacementUtils.createKey("rocks:underwater_starfish");
	public static final ResourceKey<PlacedFeature> PLACED_UNDERWATER_SEASHELL_FEATURE = PlacementUtils.createKey("rocks:underwater_seashell");
	public static final ResourceKey<PlacedFeature> PLACED_SNOWY_GEYSER_FEATURE = PlacementUtils.createKey("rocks:snowy_geyser");

	public static void placedBootstrap(BootstapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);
		List<PlacementModifier> modifiers = new ArrayList<>(placementModifiers);
		modifiers.add(CountPlacement.of(1));

		PlacementUtils.register(context, PLACED_SEASHELL_FEATURE, holdergetter.getOrThrow(SEASHELL_FEATURE), modifiers);
		PlacementUtils.register(context, PLACED_STARFISH_FEATURE, holdergetter.getOrThrow(STARFISH_FEATURE), modifiers);
		PlacementUtils.register(context, PLACED_UNDERWATER_STARFISH_FEATURE, holdergetter.getOrThrow(UNDERWATER_STARFISH_FEATURE), modifiers);
		PlacementUtils.register(context, PLACED_UNDERWATER_SEASHELL_FEATURE, holdergetter.getOrThrow(UNDERWATER_SEASHELL_FEATURE), modifiers);
		PlacementUtils.register(context, PLACED_SNOWY_GEYSER_FEATURE, holdergetter.getOrThrow(SNOWY_GEYSER_FEATURE), modifiers);
	}
}
