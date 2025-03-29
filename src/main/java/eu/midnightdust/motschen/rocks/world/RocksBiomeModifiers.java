package eu.midnightdust.motschen.rocks.world;

import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.datagen.RocksTagProvider;
import eu.midnightdust.motschen.rocks.datagen.RocksTagProvider.RocksBiomes;
import eu.midnightdust.motschen.rocks.world.modifier.AddFeaturesBlacklistBiomeModifier;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.Tags.Biomes;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;

import java.util.List;

public class RocksBiomeModifiers {
	public static void bootstrap(BootstrapContext<BiomeModifier> context) {
		HolderGetter<Biome> biomeGetter = context.lookup(Registries.BIOME);
		List<HolderSet<Biome>> overworld = List.of(biomeGetter.getOrThrow(BiomeTags.IS_OVERWORLD));

		List<HolderSet<net.minecraft.world.level.biome.Biome>> rockBlacklist = List.of(
				biomeGetter.getOrThrow(BiomeTags.IS_NETHER), biomeGetter.getOrThrow(BiomeTags.IS_END),
				biomeGetter.getOrThrow(BiomeTags.HAS_IGLOO), biomeGetter.getOrThrow(Tags.Biomes.IS_SANDY),
				biomeGetter.getOrThrow(BiomeTags.IS_BADLANDS), biomeGetter.getOrThrow(BiomeTags.IS_OCEAN));
		context.register(getModifierKey("rock"), new AddFeaturesBlacklistBiomeModifier(overworld, rockBlacklist,
				HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("rock"))),
				Decoration.TOP_LAYER_MODIFICATION
		));
		context.register(getModifierKey("rock_mix"), new AddFeaturesBlacklistBiomeModifier(overworld, rockBlacklist,
				HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("rock_mix"))),
				Decoration.TOP_LAYER_MODIFICATION
		));
		context.register(getModifierKey("granite_rock"), new AddFeaturesBlacklistBiomeModifier(overworld, rockBlacklist,
				HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("granite_rock"))),
				Decoration.TOP_LAYER_MODIFICATION
		));
		context.register(getModifierKey("diorite_rock"), new AddFeaturesBlacklistBiomeModifier(overworld, rockBlacklist,
				HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("diorite_rock"))),
				Decoration.TOP_LAYER_MODIFICATION
		));
		context.register(getModifierKey("andesite_rock"), new AddFeaturesBlacklistBiomeModifier(overworld, rockBlacklist,
				HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("andesite_rock"))),
				Decoration.TOP_LAYER_MODIFICATION
		));

		context.register(getModifierKey("sand_rock"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(BiomeTags.IS_BEACH),
						biomeGetter.getOrThrow(Tags.Biomes.IS_SANDY),
						biomeGetter.getOrThrow(BiomeTags.IS_BADLANDS)),
				List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("sand_rock"))),
				Decoration.TOP_LAYER_MODIFICATION
		));
		context.register(getModifierKey("red_sand_rock"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(Tags.Biomes.IS_SANDY),
						biomeGetter.getOrThrow(BiomeTags.IS_BADLANDS)),
				List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("red_sand_rock"))),
				Decoration.TOP_LAYER_MODIFICATION
		));

		context.register(getModifierKey("ice_rock"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(Biomes.IS_COLD),
						biomeGetter.getOrThrow(BiomeTags.HAS_IGLOO)),
				List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("ice_rock"))),
				Decoration.TOP_LAYER_MODIFICATION
		));

		context.register(getModifierKey("end_stone_rock"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(BiomeTags.IS_END)),
				List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("end_stone_rock"))),
				Decoration.TOP_LAYER_MODIFICATION
		));

		context.register(getModifierKey("oak_stick"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(BiomeTags.IS_FOREST),
						biomeGetter.getOrThrow(Tags.Biomes.IS_PLAINS),
						biomeGetter.getOrThrow(Tags.Biomes.IS_SWAMP)),
				List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("oak_stick"))),
				Decoration.TOP_LAYER_MODIFICATION
		));
		context.register(getModifierKey("birch_stick"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(BiomeTags.IS_FOREST)),
				List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("birch_stick"))),
				Decoration.TOP_LAYER_MODIFICATION
		));
		context.register(getModifierKey("spruce_stick"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(BiomeTags.IS_TAIGA)),
				List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("spruce_stick"))),
				Decoration.TOP_LAYER_MODIFICATION
		));
		context.register(getModifierKey("pinecone"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(BiomeTags.IS_TAIGA)),
				List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("pinecone"))),
				Decoration.TOP_LAYER_MODIFICATION
		));

		context.register(getModifierKey("acacia_stick"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(BiomeTags.IS_SAVANNA)),
				List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("acacia_stick"))),
				Decoration.TOP_LAYER_MODIFICATION
		));
		context.register(getModifierKey("jungle_stick"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(BiomeTags.IS_JUNGLE)),
				List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("jungle_stick"))),
				Decoration.TOP_LAYER_MODIFICATION
		));
		context.register(getModifierKey("bamboo_stick"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(BiomeTags.IS_JUNGLE)),
				List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("bamboo_stick"))),
				Decoration.TOP_LAYER_MODIFICATION
		));
		context.register(getModifierKey("pale_oak_stick"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(RocksBiomes.IS_PALE_GARDEN)),
				List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("pale_oak_stick"))),
				Decoration.TOP_LAYER_MODIFICATION
		));
		context.register(getModifierKey("dark_oak_stick"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(Tags.Biomes.IS_DARK_FOREST)),
				List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("dark_oak_stick"))),
				Decoration.TOP_LAYER_MODIFICATION
		));
		context.register(getModifierKey("mangrove_stick"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(RocksTagProvider.RocksBiomes.IS_MANGROVE_SWAMP)),
				List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("mangrove_stick"))),
				Decoration.TOP_LAYER_MODIFICATION
		));
		context.register(getModifierKey("cherry_stick"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(RocksTagProvider.RocksBiomes.IS_CHERRY_GROVE)),
				List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("cherry_stick"))),
				Decoration.TOP_LAYER_MODIFICATION
		));

		context.register(getModifierKey("seashell"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(BiomeTags.IS_BEACH)),
				List.of(biomeGetter.getOrThrow(Tags.Biomes.IS_SNOWY)),
				HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("seashell"))),
				Decoration.TOP_LAYER_MODIFICATION
		));
		context.register(getModifierKey("starfish"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(BiomeTags.IS_BEACH)),
				List.of(biomeGetter.getOrThrow(Tags.Biomes.IS_SNOWY)),
				HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("starfish"))),
				Decoration.TOP_LAYER_MODIFICATION
		));

		context.register(getModifierKey("underwater_seashell"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(BiomeTags.IS_OCEAN)),
				List.of(),
				HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("underwater_seashell"))),
				Decoration.TOP_LAYER_MODIFICATION
		));
		context.register(getModifierKey("underwater_starfish"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(BiomeTags.IS_OCEAN)),
				List.of(),
				HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("underwater_starfish"))),
				Decoration.TOP_LAYER_MODIFICATION
		));

		context.register(getModifierKey("netherrack_rock"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(BiomeTags.IS_NETHER)),
				List.of(),
				HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("netherrack_rock"))),
				Decoration.TOP_LAYER_MODIFICATION
		));
		context.register(getModifierKey("soul_soil_rock"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(BiomeTags.IS_NETHER)),
				List.of(),
				HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("soul_soil_rock"))),
				Decoration.TOP_LAYER_MODIFICATION
		));
		context.register(getModifierKey("nether_gravel_rock"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(BiomeTags.IS_NETHER)),
				List.of(),
				HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("nether_gravel_rock"))),
				Decoration.TOP_LAYER_MODIFICATION
		));

		context.register(getModifierKey("nether_geyser"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(BiomeTags.IS_NETHER)),
				List.of(),
				HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("nether_geyser"))),
				Decoration.TOP_LAYER_MODIFICATION
		));
		context.register(getModifierKey("warped_stick"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(BiomeTags.IS_NETHER)),
				List.of(),
				HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("warped_stick"))),
				Decoration.TOP_LAYER_MODIFICATION
		));
		context.register(getModifierKey("crimson_stick"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(BiomeTags.IS_NETHER)),
				List.of(),
				HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("crimson_stick"))),
				Decoration.TOP_LAYER_MODIFICATION
		));

		context.register(getModifierKey("gravel_rock"), new AddFeaturesBlacklistBiomeModifier(
				List.of(),
				List.of(biomeGetter.getOrThrow(BiomeTags.IS_NETHER)),
				HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("gravel_rock"))),
				Decoration.TOP_LAYER_MODIFICATION
		));

		context.register(getModifierKey("snowy_geyser"), new AddFeaturesBlacklistBiomeModifier(
				List.of(biomeGetter.getOrThrow(Biomes.IS_SNOWY),
						biomeGetter.getOrThrow(BiomeTags.HAS_IGLOO)),
				List.of(),
				HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(getKey("snowy_geyser"))),
				Decoration.TOP_LAYER_MODIFICATION
		));
	}

	public static ResourceKey<PlacedFeature> getKey(String name) {
		return ResourceKey.create(Registries.PLACED_FEATURE, Rocks.modLoc(name));
	}

	public static ResourceKey<BiomeModifier> getModifierKey(String name) {
		return ResourceKey.create(Keys.BIOME_MODIFIERS, Rocks.modLoc(name));
	}
}
