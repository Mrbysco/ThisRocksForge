package eu.midnightdust.motschen.rocks.world.configured_feature;

import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.blockstates.StickVariation;
import eu.midnightdust.motschen.rocks.registry.RocksRegistry;
import eu.midnightdust.motschen.rocks.world.configured_feature.util.ListHelper;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
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

import java.util.List;

public class StickFeatures {
	public static List<PlacementModifier> stickModifiers = List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> OAK_STICK_FEATURE = FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "oak_stick").toString(),
			Feature.RANDOM_PATCH, new RandomPatchConfiguration(128, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
					new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
							.add(RocksRegistry.OAK_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.SMALL), 7)
							.add(RocksRegistry.OAK_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.MEDIUM), 5)
							.add(RocksRegistry.OAK_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.LARGE), 1))),
					BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE))));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> SPRUCE_STICK_FEATURE = FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "spruce_stick").toString(),
			Feature.RANDOM_PATCH, new RandomPatchConfiguration(128, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
					new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
							.add(RocksRegistry.SPRUCE_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.SMALL), 7)
							.add(RocksRegistry.SPRUCE_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.MEDIUM), 5)
							.add(RocksRegistry.SPRUCE_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.LARGE), 1))),
					BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE))));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PINECONE_FEATURE = FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "pinecone").toString(),
			Feature.RANDOM_PATCH, new RandomPatchConfiguration(128, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
					new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
							.add(RocksRegistry.PINECONE.get().defaultBlockState(), 1))),
					BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE))));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> BIRCH_STICK_FEATURE = FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "birch_stick").toString(),
			Feature.RANDOM_PATCH, new RandomPatchConfiguration(128, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
					new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
							.add(RocksRegistry.BIRCH_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.SMALL), 7)
							.add(RocksRegistry.BIRCH_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.MEDIUM), 5)
							.add(RocksRegistry.BIRCH_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.LARGE), 1))),
					BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE))));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> ACACIA_STICK_FEATURE = FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "acacia_stick").toString(),
			Feature.RANDOM_PATCH, new RandomPatchConfiguration(128, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
					new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
							.add(RocksRegistry.ACACIA_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.SMALL), 7)
							.add(RocksRegistry.ACACIA_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.MEDIUM), 5)
							.add(RocksRegistry.ACACIA_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.LARGE), 1))),
					BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE))));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> JUNGLE_STICK_FEATURE = FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "jungle_stick").toString(),
			Feature.RANDOM_PATCH, new RandomPatchConfiguration(128, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
					new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
							.add(RocksRegistry.JUNGLE_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.SMALL), 7)
							.add(RocksRegistry.JUNGLE_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.MEDIUM), 5)
							.add(RocksRegistry.JUNGLE_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.LARGE), 1))),
					BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE))));
	public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> DARK_OAK_STICK_FEATURE = FeatureUtils.register(new ResourceLocation(Rocks.MOD_ID, "dark_oak_stick").toString(),
			Feature.RANDOM_PATCH, new RandomPatchConfiguration(128, 0, 0, PlacementUtils.filtered(Feature.SIMPLE_BLOCK,
					new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
							.add(RocksRegistry.DARK_OAK_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.SMALL), 7)
							.add(RocksRegistry.DARK_OAK_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.MEDIUM), 5)
							.add(RocksRegistry.DARK_OAK_STICK.get().defaultBlockState().setValue(Rocks.STICK_VARIATION, StickVariation.LARGE), 1))),
					BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE))));

	public static Holder<PlacedFeature> OAK_STICK_PLACED_FEATURE = PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "oak_stick").toString(),
			OAK_STICK_FEATURE, ListHelper.getMergedModifierList(stickModifiers, CountPlacement.of(3)));
	public static Holder<PlacedFeature> SPRUCE_STICK_PLACED_FEATURE = PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "spruce_stick").toString(),
			SPRUCE_STICK_FEATURE, ListHelper.getMergedModifierList(stickModifiers, CountPlacement.of(3)));
	public static Holder<PlacedFeature> PINECONE_PLACED_FEATURE = PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "pinecone").toString(),
			PINECONE_FEATURE, CountPlacement.of(3), RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
	public static Holder<PlacedFeature> BIRCH_STICK_PLACED_FEATURE = PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "birch_stick").toString(),
			BIRCH_STICK_FEATURE, ListHelper.getMergedModifierList(stickModifiers, CountPlacement.of(3)));
	public static Holder<PlacedFeature> ACACIA_STICK_PLACED_FEATURE = PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "acacia_stick").toString(),
			ACACIA_STICK_FEATURE, ListHelper.getMergedModifierList(stickModifiers, CountPlacement.of(3)));
	public static Holder<PlacedFeature> JUNGLE_STICK_PLACED_FEATURE = PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "jungle_stick").toString(),
			JUNGLE_STICK_FEATURE, ListHelper.getMergedModifierList(stickModifiers, CountPlacement.of(3)));
	public static Holder<PlacedFeature> DARK_OAK_STICK_PLACED_FEATURE = PlacementUtils.register(new ResourceLocation(Rocks.MOD_ID, "dark_oak_stick").toString(),
			DARK_OAK_STICK_FEATURE, ListHelper.getMergedModifierList(stickModifiers, CountPlacement.of(3)));

	public static void init() {
		//Just here to load the class
	}

}
