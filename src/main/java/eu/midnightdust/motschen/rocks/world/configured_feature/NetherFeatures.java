package eu.midnightdust.motschen.rocks.world.configured_feature;

import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.blockstates.RockVariation;
import eu.midnightdust.motschen.rocks.blockstates.StickVariation;
import eu.midnightdust.motschen.rocks.registry.RocksRegistry;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
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

public class NetherFeatures {
	public static List<PlacementModifier> netherModifiers = List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.RANGE_4_4, BiomeFilter.biome());

	public static final ResourceKey<ConfiguredFeature<?, ?>> NETHERRACK_ROCK_FEATURE = FeatureUtils.createKey("rocks:netherrack_rock");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SOUL_SOIL_ROCK_FEATURE = FeatureUtils.createKey("rocks:soul_soil_rock");
	public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_GRAVEL_ROCK_FEATURE = FeatureUtils.createKey("rocks:nether_gravel_rock");
	public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_GEYSER_FEATURE = FeatureUtils.createKey("rocks:nether_geyser");
	public static final ResourceKey<ConfiguredFeature<?, ?>> WARPED_STICK_FEATURE = FeatureUtils.createKey("rocks:warped_stick");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CRIMSON_STICK_FEATURE = FeatureUtils.createKey("rocks:crimson_stick");


	public static void configuredBootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
		FeatureUtils.register(context, NETHERRACK_ROCK_FEATURE,
				Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
								.add(RocksRegistry.NETHERRACK_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.TINY), 10)
								.add(RocksRegistry.NETHERRACK_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.SMALL), 7)
								.add(RocksRegistry.NETHERRACK_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.MEDIUM), 5)
								.add(RocksRegistry.NETHERRACK_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.LARGE), 1))),
						List.of(Blocks.NETHERRACK, Blocks.WARPED_NYLIUM, Blocks.CRIMSON_NYLIUM)));

		FeatureUtils.register(context, SOUL_SOIL_ROCK_FEATURE,
				Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
								.add(RocksRegistry.SOUL_SOIL_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.TINY), 10)
								.add(RocksRegistry.SOUL_SOIL_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.SMALL), 7)
								.add(RocksRegistry.SOUL_SOIL_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.MEDIUM), 5)
								.add(RocksRegistry.SOUL_SOIL_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.LARGE), 1))),
						List.of(Blocks.SOUL_SOIL, Blocks.SOUL_SAND)));

		FeatureUtils.register(context, NETHER_GRAVEL_ROCK_FEATURE,
				Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
								.add(RocksRegistry.GRAVEL_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.TINY), 10)
								.add(RocksRegistry.GRAVEL_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.SMALL), 7)
								.add(RocksRegistry.GRAVEL_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.MEDIUM), 5)
								.add(RocksRegistry.GRAVEL_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.LARGE), 1))),
						List.of(Blocks.GRAVEL)));

		FeatureUtils.register(context, WARPED_STICK_FEATURE,
				Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
								.add(RocksRegistry.WARPED_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.SMALL), 7)
								.add(RocksRegistry.WARPED_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.MEDIUM), 5)
								.add(RocksRegistry.WARPED_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.LARGE), 1))),
						List.of(Blocks.WARPED_NYLIUM)));

		FeatureUtils.register(context, CRIMSON_STICK_FEATURE,
				Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
								.add(RocksRegistry.CRIMSON_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.SMALL), 7)
								.add(RocksRegistry.CRIMSON_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.MEDIUM), 5)
								.add(RocksRegistry.CRIMSON_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.LARGE), 1))),
						List.of(Blocks.CRIMSON_NYLIUM)));

		FeatureUtils.register(context, NETHER_GEYSER_FEATURE,
				Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
						new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
								.add(RocksRegistry.NETHER_GEYSER.get().defaultBlockState(), 1))),
						List.of(Blocks.NETHERRACK)));
	}

	public static final ResourceKey<PlacedFeature> PLACED_NETHERRACK_ROCK_FEATURE = PlacementUtils.createKey("rocks:netherrack_rock");
	public static final ResourceKey<PlacedFeature> PLACED_SOUL_SOIL_ROCK_FEATURE = PlacementUtils.createKey("rocks:soul_soil_rock");
	public static final ResourceKey<PlacedFeature> PLACED_NETHER_GRAVEL_ROCK_FEATURE = PlacementUtils.createKey("rocks:nether_gravel_rock");
	public static final ResourceKey<PlacedFeature> PLACED_WARPED_STICK_FEATURE = PlacementUtils.createKey("rocks:warped_stick");
	public static final ResourceKey<PlacedFeature> PLACED_CRIMSON_STICK_FEATURE = PlacementUtils.createKey("rocks:crimson_stick");
	public static final ResourceKey<PlacedFeature> PLACED_NETHER_GEYSER_FEATURE = PlacementUtils.createKey("rocks:nether_geyser");

	public static void placedBootstrap(BootstapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> holdergetter = context.lookup(Registries.CONFIGURED_FEATURE);
		List<PlacementModifier> netherModifierList = new ArrayList<>(netherModifiers);
		netherModifierList.add(CountPlacement.of(90));

		PlacementUtils.register(context, PLACED_NETHERRACK_ROCK_FEATURE, holdergetter.getOrThrow(NETHERRACK_ROCK_FEATURE), netherModifierList);
		PlacementUtils.register(context, PLACED_SOUL_SOIL_ROCK_FEATURE, holdergetter.getOrThrow(SOUL_SOIL_ROCK_FEATURE), netherModifierList);
		PlacementUtils.register(context, PLACED_NETHER_GRAVEL_ROCK_FEATURE, holdergetter.getOrThrow(NETHER_GRAVEL_ROCK_FEATURE), netherModifierList);
		PlacementUtils.register(context, PLACED_WARPED_STICK_FEATURE, holdergetter.getOrThrow(WARPED_STICK_FEATURE), netherModifierList);
		PlacementUtils.register(context, PLACED_CRIMSON_STICK_FEATURE, holdergetter.getOrThrow(CRIMSON_STICK_FEATURE), netherModifierList);

		List<PlacementModifier> geyserModifiers = new ArrayList<>(netherModifiers);
		geyserModifiers.add(CountPlacement.of(30));

		PlacementUtils.register(context, PLACED_NETHER_GEYSER_FEATURE, holdergetter.getOrThrow(NETHER_GEYSER_FEATURE), geyserModifiers);
	}
}
