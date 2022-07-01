package eu.midnightdust.motschen.rocks.world.configured_feature;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.blockstates.SeashellVariation;
import eu.midnightdust.motschen.rocks.blockstates.StarfishVariation;
import eu.midnightdust.motschen.rocks.registry.RocksRegistry;
import eu.midnightdust.motschen.rocks.world.FeatureRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
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
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.List;
import java.util.Map;


public class MiscFeatures {
	public static List<PlacementModifier> placementModifiers = List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> SEASHELL_FEATURE = FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "seashell").toString(),
			Feature.RANDOM_PATCH, new RandomPatchConfiguration(96, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
					new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
									.add(RocksRegistry.SEASHELL.get().defaultBlockState().setValue(Rocks.SEASHELL_VARIATION, SeashellVariation.YELLOW), 7)
									.add(RocksRegistry.SEASHELL.get().defaultBlockState().setValue(Rocks.SEASHELL_VARIATION, SeashellVariation.PINK), 2)
									.add(RocksRegistry.SEASHELL.get().defaultBlockState().setValue(Rocks.SEASHELL_VARIATION, SeashellVariation.WHITE), 6))),
							BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
									BlockPredicate.matchesBlocks(
											new BlockPos(0, -1, 0), ImmutableList.of(Blocks.SAND, Blocks.SANDSTONE, Blocks.RED_SAND, Blocks.RED_SANDSTONE))))));

	public static Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> STARFISH_FEATURE = FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "starfish").toString(),
			Feature.RANDOM_PATCH, new RandomPatchConfiguration(96, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
					new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
							.add(RocksRegistry.STARFISH.get().defaultBlockState().setValue(Rocks.STARFISH_VARIATION, StarfishVariation.RED), 2)
							.add(RocksRegistry.STARFISH.get().defaultBlockState().setValue(Rocks.STARFISH_VARIATION, StarfishVariation.PINK), 6)
							.add(RocksRegistry.STARFISH.get().defaultBlockState().setValue(Rocks.STARFISH_VARIATION, StarfishVariation.ORANGE), 7))),
							BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
									BlockPredicate.matchesBlocks(
											new BlockPos(0, -1, 0), ImmutableList.of(Blocks.SAND, Blocks.SANDSTONE, Blocks.RED_SAND, Blocks.RED_SANDSTONE))))));

	public static Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> UNDERWATER_STARFISH_FEATURE = FeatureUtils.register(
			new ResourceLocation(Rocks.MOD_ID, "underwater_starfish").toString(),
				FeatureRegistry.UNDERWATER_STARFISH_FEATURE.get(), new ProbabilityFeatureConfiguration(1));
	public static Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> UNDERWATER_SEASHELL_FEATURE = FeatureUtils.register(
			new ResourceLocation(Rocks.MOD_ID, "underwater_seashell").toString(),
				FeatureRegistry.UNDERWATER_SEASHELL_FEATURE.get(), new ProbabilityFeatureConfiguration(1));
	public static Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> SNOWY_GEYSER_FEATURE = FeatureUtils.register(
			new ResourceLocation(Rocks.MOD_ID, "snowy_geyser").toString(),
				FeatureRegistry.SNOWY_GEYSER_FEATURE.get(), new ProbabilityFeatureConfiguration(1));

	public static void init() {
		//Just here to load the class
	}
}
