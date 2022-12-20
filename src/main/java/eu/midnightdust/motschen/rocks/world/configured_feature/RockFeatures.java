package eu.midnightdust.motschen.rocks.world.configured_feature;

import com.google.common.collect.ImmutableList;
import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.blockstates.RockVariation;
import eu.midnightdust.motschen.rocks.registry.RocksRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
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

public class RockFeatures {
	public static List<PlacementModifier> rockModifiers = List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final ResourceKey<ConfiguredFeature<?, ?>> ROCK_FEATURE = FeatureUtils.createKey("rocks:rock");
	public static final ResourceKey<ConfiguredFeature<?, ?>> GRANITE_ROCK_FEATURE = FeatureUtils.createKey("rocks:granite_rock");
	public static final ResourceKey<ConfiguredFeature<?, ?>> DIORITE_ROCK_FEATURE = FeatureUtils.createKey("rocks:diorite_rock");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ANDESITE_ROCK_FEATURE = FeatureUtils.createKey("rocks:andesite_rock");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SAND_ROCK_FEATURE = FeatureUtils.createKey("rocks:sand_rock");
	public static final ResourceKey<ConfiguredFeature<?, ?>> RED_SAND_ROCK_FEATURE = FeatureUtils.createKey("rocks:red_sand_rock");
	public static final ResourceKey<ConfiguredFeature<?, ?>> END_STONE_ROCK_FEATURE = FeatureUtils.createKey("rocks:endstone_rock");
	public static final ResourceKey<ConfiguredFeature<?, ?>> GRAVEL_ROCK_FEATURE = FeatureUtils.createKey("rocks:gravel_rock");

	public static void configuredBootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
		FeatureUtils.register(context, ROCK_FEATURE,
				Feature.RANDOM_PATCH, new RandomPatchConfiguration(128, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
								.add(RocksRegistry.ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.TINY), 10)
								.add(RocksRegistry.ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.SMALL), 7)
								.add(RocksRegistry.ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.MEDIUM), 5)
								.add(RocksRegistry.ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.LARGE), 1))),
						BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.not(
								BlockPredicate.matchesBlocks(
										new BlockPos(0, -1, 0), ImmutableList.of(Blocks.ICE, Blocks.SAND, Blocks.RED_SAND)))))));

		FeatureUtils.register(context, GRANITE_ROCK_FEATURE,
				Feature.RANDOM_PATCH, new RandomPatchConfiguration(128, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
								.add(RocksRegistry.GRANITE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.TINY), 10)
								.add(RocksRegistry.GRANITE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.SMALL), 7)
								.add(RocksRegistry.GRANITE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.MEDIUM), 5)
								.add(RocksRegistry.GRANITE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.LARGE), 1))),
						BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(
								new BlockPos(0, -1, 0), ImmutableList.of(Blocks.GRANITE))))));

		FeatureUtils.register(context, DIORITE_ROCK_FEATURE,
				Feature.RANDOM_PATCH, new RandomPatchConfiguration(128, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
								.add(RocksRegistry.DIORITE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.TINY), 10)
								.add(RocksRegistry.DIORITE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.SMALL), 7)
								.add(RocksRegistry.DIORITE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.MEDIUM), 5)
								.add(RocksRegistry.DIORITE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.LARGE), 1))),
						BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(
								new BlockPos(0, -1, 0), ImmutableList.of(Blocks.DIORITE))))));

		FeatureUtils.register(context, ANDESITE_ROCK_FEATURE,
				Feature.RANDOM_PATCH, new RandomPatchConfiguration(128, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
								.add(RocksRegistry.ANDESITE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.TINY), 10)
								.add(RocksRegistry.ANDESITE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.SMALL), 7)
								.add(RocksRegistry.ANDESITE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.MEDIUM), 5)
								.add(RocksRegistry.ANDESITE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.LARGE), 1))),
						BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(
								new BlockPos(0, -1, 0), ImmutableList.of(Blocks.ANDESITE))))));

		FeatureUtils.register(context, SAND_ROCK_FEATURE,
				Feature.RANDOM_PATCH, new RandomPatchConfiguration(128, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
								.add(RocksRegistry.SAND_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.TINY), 10)
								.add(RocksRegistry.SAND_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.SMALL), 7)
								.add(RocksRegistry.SAND_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.MEDIUM), 5)
								.add(RocksRegistry.SAND_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.LARGE), 1))),
						BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(
								new BlockPos(0, -1, 0), ImmutableList.of(Blocks.SAND, Blocks.SANDSTONE))))));

		FeatureUtils.register(context, RED_SAND_ROCK_FEATURE,
				Feature.RANDOM_PATCH, new RandomPatchConfiguration(128, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
								.add(RocksRegistry.RED_SAND_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.TINY), 10)
								.add(RocksRegistry.RED_SAND_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.SMALL), 7)
								.add(RocksRegistry.RED_SAND_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.MEDIUM), 5)
								.add(RocksRegistry.RED_SAND_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.LARGE), 1))),
						BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(
								new BlockPos(0, -1, 0), ImmutableList.of(Blocks.RED_SAND, Blocks.RED_SANDSTONE))))));

		FeatureUtils.register(context, END_STONE_ROCK_FEATURE,
				Feature.RANDOM_PATCH, new RandomPatchConfiguration(128, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
								.add(RocksRegistry.END_STONE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.TINY), 10)
								.add(RocksRegistry.END_STONE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.SMALL), 7)
								.add(RocksRegistry.END_STONE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.MEDIUM), 5)
								.add(RocksRegistry.END_STONE_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.LARGE), 1))),
						BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(
								new BlockPos(0, -1, 0), ImmutableList.of(Blocks.END_STONE))))));

		FeatureUtils.register(context, GRAVEL_ROCK_FEATURE,
				Feature.RANDOM_PATCH, new RandomPatchConfiguration(128, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
								.add(RocksRegistry.GRAVEL_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.TINY), 10)
								.add(RocksRegistry.GRAVEL_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.SMALL), 7)
								.add(RocksRegistry.GRAVEL_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.MEDIUM), 5)
								.add(RocksRegistry.GRAVEL_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.LARGE), 1))),
						BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.matchesBlocks(
								new BlockPos(0, -1, 0), ImmutableList.of(Blocks.GRAVEL))))));
	}

	public static final ResourceKey<PlacedFeature> PLACED_ROCK_FEATURE = PlacementUtils.createKey("rocks:rock");
	public static final ResourceKey<PlacedFeature> PLACED_GRANITE_ROCK_FEATURE = PlacementUtils.createKey("rocks:granite_rock");
	public static final ResourceKey<PlacedFeature> PLACED_DIORITE_ROCK_FEATURE = PlacementUtils.createKey("rocks:diorite_rock");
	public static final ResourceKey<PlacedFeature> PLACED_ANDESITE_ROCK_FEATURE = PlacementUtils.createKey("rocks:andesite_rock");
	public static final ResourceKey<PlacedFeature> PLACED_SAND_ROCK_FEATURE = PlacementUtils.createKey("rocks:sand_rock");
	public static final ResourceKey<PlacedFeature> PLACED_RED_SAND_ROCK_FEATURE = PlacementUtils.createKey("rocks:red_sand_rock");
	public static final ResourceKey<PlacedFeature> PLACED_END_STONE_ROCK_FEATURE = PlacementUtils.createKey("rocks:endstone_rock");
	public static final ResourceKey<PlacedFeature> PLACED_GRAVEL_ROCK_FEATURE = PlacementUtils.createKey("rocks:gravel_rock");

	public static void placedBootstrap(BootstapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);

		List<PlacementModifier> rockModifiers = new ArrayList<>(RockFeatures.rockModifiers);
		rockModifiers.add(CountPlacement.of(3));

		PlacementUtils.register(context, PLACED_ROCK_FEATURE, holdergetter.getOrThrow(ROCK_FEATURE), rockModifiers);
		PlacementUtils.register(context, PLACED_GRANITE_ROCK_FEATURE, holdergetter.getOrThrow(GRANITE_ROCK_FEATURE), rockModifiers);
		PlacementUtils.register(context, PLACED_DIORITE_ROCK_FEATURE, holdergetter.getOrThrow(DIORITE_ROCK_FEATURE), rockModifiers);
		PlacementUtils.register(context, PLACED_ANDESITE_ROCK_FEATURE, holdergetter.getOrThrow(ANDESITE_ROCK_FEATURE), rockModifiers);
		PlacementUtils.register(context, PLACED_SAND_ROCK_FEATURE, holdergetter.getOrThrow(SAND_ROCK_FEATURE), rockModifiers);
		PlacementUtils.register(context, PLACED_RED_SAND_ROCK_FEATURE, holdergetter.getOrThrow(RED_SAND_ROCK_FEATURE), rockModifiers);
		PlacementUtils.register(context, PLACED_END_STONE_ROCK_FEATURE, holdergetter.getOrThrow(END_STONE_ROCK_FEATURE), rockModifiers);
		PlacementUtils.register(context, PLACED_GRAVEL_ROCK_FEATURE, holdergetter.getOrThrow(GRAVEL_ROCK_FEATURE), rockModifiers);

	}
}
