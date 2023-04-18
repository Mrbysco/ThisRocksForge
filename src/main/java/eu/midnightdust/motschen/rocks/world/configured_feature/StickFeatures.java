package eu.midnightdust.motschen.rocks.world.configured_feature;

import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.blockstates.StickVariation;
import eu.midnightdust.motschen.rocks.registry.RocksRegistry;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
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

public class StickFeatures {
	public static List<PlacementModifier> stickModifiers = List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_STICK_FEATURE = FeatureUtils.createKey("rocks:oak_stick");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_STICK_FEATURE = FeatureUtils.createKey("rocks:spruce_stick");
	public static final ResourceKey<ConfiguredFeature<?, ?>> PINECONE_FEATURE = FeatureUtils.createKey("rocks:pinecone");
	public static final ResourceKey<ConfiguredFeature<?, ?>> BIRCH_STICK_FEATURE = FeatureUtils.createKey("rocks:birch_stick");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ACACIA_STICK_FEATURE = FeatureUtils.createKey("rocks:acacia_stick");
	public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_STICK_FEATURE = FeatureUtils.createKey("rocks:jungle_stick");
	public static final ResourceKey<ConfiguredFeature<?, ?>> DARK_OAK_STICK_FEATURE = FeatureUtils.createKey("rocks:dark_oak_stick");
	public static final ResourceKey<ConfiguredFeature<?, ?>> MANGROVE_STICK_FEATURE = FeatureUtils.createKey("rocks:mangrove_stick");

	public static void configuredBootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
		FeatureUtils.register(context, OAK_STICK_FEATURE,
				Feature.RANDOM_PATCH, new RandomPatchConfiguration(128, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
								.add(RocksRegistry.OAK_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.SMALL), 7)
								.add(RocksRegistry.OAK_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.MEDIUM), 5)
								.add(RocksRegistry.OAK_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.LARGE), 1))),
						BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE))));

		FeatureUtils.register(context, SPRUCE_STICK_FEATURE,
				Feature.RANDOM_PATCH, new RandomPatchConfiguration(128, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
								.add(RocksRegistry.SPRUCE_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.SMALL), 7)
								.add(RocksRegistry.SPRUCE_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.MEDIUM), 5)
								.add(RocksRegistry.SPRUCE_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.LARGE), 1))),
						BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE))));

		FeatureUtils.register(context, PINECONE_FEATURE,
				Feature.RANDOM_PATCH, new RandomPatchConfiguration(128, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
								.add(RocksRegistry.PINECONE.get().defaultBlockState(), 1))),
						BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE))));

		FeatureUtils.register(context, BIRCH_STICK_FEATURE,
				Feature.RANDOM_PATCH, new RandomPatchConfiguration(128, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
								.add(RocksRegistry.BIRCH_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.SMALL), 7)
								.add(RocksRegistry.BIRCH_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.MEDIUM), 5)
								.add(RocksRegistry.BIRCH_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.LARGE), 1))),
						BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE))));

		FeatureUtils.register(context, ACACIA_STICK_FEATURE,
				Feature.RANDOM_PATCH, new RandomPatchConfiguration(128, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
								.add(RocksRegistry.ACACIA_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.SMALL), 7)
								.add(RocksRegistry.ACACIA_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.MEDIUM), 5)
								.add(RocksRegistry.ACACIA_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.LARGE), 1))),
						BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE))));

		FeatureUtils.register(context, JUNGLE_STICK_FEATURE,
				Feature.RANDOM_PATCH, new RandomPatchConfiguration(128, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
								.add(RocksRegistry.JUNGLE_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.SMALL), 7)
								.add(RocksRegistry.JUNGLE_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.MEDIUM), 5)
								.add(RocksRegistry.JUNGLE_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.LARGE), 1))),
						BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE))));

		FeatureUtils.register(context, DARK_OAK_STICK_FEATURE,
				Feature.RANDOM_PATCH, new RandomPatchConfiguration(128, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
								.add(RocksRegistry.DARK_OAK_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.SMALL), 7)
								.add(RocksRegistry.DARK_OAK_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.MEDIUM), 5)
								.add(RocksRegistry.DARK_OAK_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.LARGE), 1))),
						BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE))));

		FeatureUtils.register(context, MANGROVE_STICK_FEATURE,
				Feature.RANDOM_PATCH, new RandomPatchConfiguration(128, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
								.add(RocksRegistry.MANGROVE_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.SMALL), 7)
								.add(RocksRegistry.MANGROVE_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.MEDIUM), 5)
								.add(RocksRegistry.MANGROVE_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.LARGE), 1))),
						BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE))));
	}

	public static final ResourceKey<PlacedFeature> PLACED_OAK_STICK_FEATURE = PlacementUtils.createKey("rocks:oak_stick");
	public static final ResourceKey<PlacedFeature> PLACED_SPRUCE_STICK_FEATURE = PlacementUtils.createKey("rocks:spruce_stick");
	public static final ResourceKey<PlacedFeature> PLACED_PINECONE_FEATURE = PlacementUtils.createKey("rocks:pinecone");
	public static final ResourceKey<PlacedFeature> PLACED_BIRCH_STICK_FEATURE = PlacementUtils.createKey("rocks:birch_stick");
	public static final ResourceKey<PlacedFeature> PLACED_ACACIA_STICK_FEATURE = PlacementUtils.createKey("rocks:acacia_stick");
	public static final ResourceKey<PlacedFeature> PLACED_JUNGLE_STICK_FEATURE = PlacementUtils.createKey("rocks:jungle_stick");
	public static final ResourceKey<PlacedFeature> PLACED_DARK_OAK_STICK_FEATURE = PlacementUtils.createKey("rocks:dark_oak_stick");
	public static final ResourceKey<PlacedFeature> PLACED_MANGROVE_STICK_FEATURE = PlacementUtils.createKey("rocks:mangrove_stick");

	public static void placedBootstrap(BootstapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

		List<PlacementModifier> stickModifiers = new ArrayList<>(StickFeatures.stickModifiers);
		stickModifiers.add(CountPlacement.of(3));

		PlacementUtils.register(context, PLACED_OAK_STICK_FEATURE, holdergetter.getOrThrow(OAK_STICK_FEATURE), stickModifiers);
		PlacementUtils.register(context, PLACED_SPRUCE_STICK_FEATURE, holdergetter.getOrThrow(SPRUCE_STICK_FEATURE), stickModifiers);
		PlacementUtils.register(context, PLACED_PINECONE_FEATURE, holdergetter.getOrThrow(PINECONE_FEATURE), stickModifiers);
		PlacementUtils.register(context, PLACED_BIRCH_STICK_FEATURE, holdergetter.getOrThrow(BIRCH_STICK_FEATURE), stickModifiers);
		PlacementUtils.register(context, PLACED_ACACIA_STICK_FEATURE, holdergetter.getOrThrow(ACACIA_STICK_FEATURE), stickModifiers);
		PlacementUtils.register(context, PLACED_JUNGLE_STICK_FEATURE, holdergetter.getOrThrow(JUNGLE_STICK_FEATURE), stickModifiers);
		PlacementUtils.register(context, PLACED_DARK_OAK_STICK_FEATURE, holdergetter.getOrThrow(DARK_OAK_STICK_FEATURE), stickModifiers);
		PlacementUtils.register(context, PLACED_MANGROVE_STICK_FEATURE, holdergetter.getOrThrow(MANGROVE_STICK_FEATURE), stickModifiers);
	}
}
