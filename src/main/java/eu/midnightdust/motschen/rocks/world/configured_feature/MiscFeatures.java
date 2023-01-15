package eu.midnightdust.motschen.rocks.world.configured_feature;

import com.google.common.collect.ImmutableList;
import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.blockstates.SeashellVariation;
import eu.midnightdust.motschen.rocks.blockstates.StarfishVariation;
import eu.midnightdust.motschen.rocks.registry.RocksRegistry;
import eu.midnightdust.motschen.rocks.world.FeatureRegistry;
import eu.midnightdust.motschen.rocks.world.configured_feature.util.ListHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
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
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.List;


public class MiscFeatures {
	public static List<PlacementModifier> placementModifiers = List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> SEASHELL_FEATURE = FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "seashell").toString(),
			Feature.RANDOM_PATCH, new RandomPatchConfiguration(96, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
					new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
									.add(RocksRegistry.SEASHELL.get().defaultBlockState().setValue(Rocks.SEASHELL_VARIATION, SeashellVariation.YELLOW), 7)
									.add(RocksRegistry.SEASHELL.get().defaultBlockState().setValue(Rocks.SEASHELL_VARIATION, SeashellVariation.PINK), 2)
									.add(RocksRegistry.SEASHELL.get().defaultBlockState().setValue(Rocks.SEASHELL_VARIATION, SeashellVariation.WHITE), 6))),
							BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
									BlockPredicate.matchesBlocks(ImmutableList.of(Blocks.SAND, Blocks.SANDSTONE, Blocks.RED_SAND, Blocks.RED_SANDSTONE),
											new BlockPos(0, -1, 0))))));

	public static Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> STARFISH_FEATURE = FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "starfish").toString(),
			Feature.RANDOM_PATCH, new RandomPatchConfiguration(96, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
					new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
							.add(RocksRegistry.STARFISH.get().defaultBlockState().setValue(Rocks.STARFISH_VARIATION, StarfishVariation.RED), 2)
							.add(RocksRegistry.STARFISH.get().defaultBlockState().setValue(Rocks.STARFISH_VARIATION, StarfishVariation.PINK), 6)
							.add(RocksRegistry.STARFISH.get().defaultBlockState().setValue(Rocks.STARFISH_VARIATION, StarfishVariation.ORANGE), 7))),
							BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE,
									BlockPredicate.matchesBlocks(ImmutableList.of(Blocks.SAND, Blocks.SANDSTONE, Blocks.RED_SAND, Blocks.RED_SANDSTONE),
											new BlockPos(0, -1, 0))))));

	public static Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> UNDERWATER_STARFISH_FEATURE = FeatureUtils.register(
			new ResourceLocation(Rocks.MOD_ID, "underwater_starfish").toString(),
				FeatureRegistry.UNDERWATER_STARFISH_FEATURE.get(), new ProbabilityFeatureConfiguration(1));
	public static Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> UNDERWATER_SEASHELL_FEATURE = FeatureUtils.register(
			new ResourceLocation(Rocks.MOD_ID, "underwater_seashell").toString(),
				FeatureRegistry.UNDERWATER_SEASHELL_FEATURE.get(), new ProbabilityFeatureConfiguration(1));
	public static Holder<ConfiguredFeature<ProbabilityFeatureConfiguration, ?>> SNOWY_GEYSER_FEATURE = FeatureUtils.register(
			new ResourceLocation(Rocks.MOD_ID, "snowy_geyser").toString(),
				FeatureRegistry.SNOWY_GEYSER_FEATURE.get(), new ProbabilityFeatureConfiguration(1));

	public static final Holder<PlacedFeature> SEASHELL_PLACED_FEATURE = PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "seashell").toString(),
			SEASHELL_FEATURE, ListHelper.getMergedModifierList(placementModifiers, CountPlacement.of(1)));
	public static final Holder<PlacedFeature> STARFISH_PLACED_FEATURE = PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "starfish").toString(),
			STARFISH_FEATURE, ListHelper.getMergedModifierList(placementModifiers, CountPlacement.of(1)));
	public static final Holder<PlacedFeature> UNDERWATER_SEASHELL_PLACED_FEATURE = PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "underwater_seashell").toString(),
			UNDERWATER_STARFISH_FEATURE, ListHelper.getMergedModifierList(placementModifiers, CountPlacement.of(3)));
	public static final Holder<PlacedFeature> UNDERWATER_STARFISH_PLACED_FEATURE = PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "underwater_starfish").toString(),
			UNDERWATER_SEASHELL_FEATURE, ListHelper.getMergedModifierList(placementModifiers, CountPlacement.of(3)));
	public static final Holder<PlacedFeature> SNOWY_GEYSER_PLACED_FEATURE = PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "snowy_geyser").toString(),
			SNOWY_GEYSER_FEATURE, ListHelper.getMergedModifierList(placementModifiers, CountPlacement.of(3)));

	public static void init() {
		//Just here to load the class
	}
}
