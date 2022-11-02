package eu.midnightdust.motschen.rocks.world.configured_feature;

import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.blockstates.RockVariation;
import eu.midnightdust.motschen.rocks.blockstates.StickVariation;
import eu.midnightdust.motschen.rocks.registry.RocksRegistry;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.List;

public class NetherFeatures {
	public static List<PlacementModifier> netherModifiers = List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.RANGE_4_4, BiomeFilter.biome());

	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> NETHERRACK_ROCK_FEATURE = FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "netherrack_rock").toString(),
			Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
					new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
							.add(RocksRegistry.NETHERRACK_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.TINY), 10)
							.add(RocksRegistry.NETHERRACK_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.SMALL), 7)
							.add(RocksRegistry.NETHERRACK_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.MEDIUM), 5)
							.add(RocksRegistry.NETHERRACK_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.LARGE), 1))),
					List.of(Blocks.NETHERRACK, Blocks.WARPED_NYLIUM, Blocks.CRIMSON_NYLIUM)));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> SOUL_SOIL_ROCK_FEATURE = FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "soul_soil_rock").toString(),
			Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
					new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
							.add(RocksRegistry.SOUL_SOIL_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.TINY), 10)
							.add(RocksRegistry.SOUL_SOIL_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.SMALL), 7)
							.add(RocksRegistry.SOUL_SOIL_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.MEDIUM), 5)
							.add(RocksRegistry.SOUL_SOIL_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.LARGE), 1))),
					List.of(Blocks.SOUL_SOIL, Blocks.SOUL_SAND)));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> NETHER_GRAVEL_ROCK_FEATURE = FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "nether_gravel_rock").toString(),
			Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
					new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
							.add(RocksRegistry.GRAVEL_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.TINY), 10)
							.add(RocksRegistry.GRAVEL_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.SMALL), 7)
							.add(RocksRegistry.GRAVEL_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.MEDIUM), 5)
							.add(RocksRegistry.GRAVEL_ROCK.get().defaultBlockState().setValue(Rocks.ROCK_VARIATION, RockVariation.LARGE), 1))),
					List.of(Blocks.GRAVEL)));

	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> NETHER_GEYSER_FEATURE = FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "nether_geyser").toString(),
			Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
					new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
							.add(RocksRegistry.NETHER_GEYSER.get().defaultBlockState(), 1))),
					List.of(Blocks.NETHERRACK)));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> WARPED_STICK_FEATURE = FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "warped_stick").toString(),
			Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
					new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
							.add(RocksRegistry.WARPED_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.SMALL), 7)
							.add(RocksRegistry.WARPED_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.MEDIUM), 5)
							.add(RocksRegistry.WARPED_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.LARGE), 1))),
					List.of(Blocks.WARPED_NYLIUM)));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> CRIMSON_STICK_FEATURE = FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "crimson_stick").toString(),
			Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
					new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
							.add(RocksRegistry.CRIMSON_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.SMALL), 7)
							.add(RocksRegistry.CRIMSON_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.MEDIUM), 5)
							.add(RocksRegistry.CRIMSON_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.LARGE), 1))),
					List.of(Blocks.CRIMSON_NYLIUM)));

	public static void init() {
		//Just here to load the class
	}
}
