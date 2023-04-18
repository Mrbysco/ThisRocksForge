package eu.midnightdust.motschen.rocks.datagen;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.blockstates.StarfishVariation;
import eu.midnightdust.motschen.rocks.registry.RocksRegistry;
import eu.midnightdust.motschen.rocks.world.configured_feature.MiscFeatures;
import eu.midnightdust.motschen.rocks.world.configured_feature.NetherFeatures;
import eu.midnightdust.motschen.rocks.world.configured_feature.RockFeatures;
import eu.midnightdust.motschen.rocks.world.configured_feature.StickFeatures;
import eu.midnightdust.motschen.rocks.world.modifier.AddFeaturesBlacklistBiomeModifier;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetNbtFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RocksDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		final RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, RegistryAccess.builtinCopy());
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();

		if (event.includeServer()) {
			generator.addProvider(event.includeServer(), new Loots(generator));
			generator.addProvider(event.includeServer(), new Recipes(generator));
			generator.addProvider(event.includeServer(), new RocksBiomeTags(generator, helper));

			generator.addProvider(event.includeServer(), JsonCodecProvider.forDatapackRegistry(generator, helper, Rocks.MOD_ID, ops, Registry.PLACED_FEATURE_REGISTRY, getConfiguredFeatures(ops)));

			generator.addProvider(event.includeServer(), JsonCodecProvider.forDatapackRegistry(generator, helper, Rocks.MOD_ID, ops, ForgeRegistries.Keys.BIOME_MODIFIERS, getBiomeModifiers(ops)));
		}
	}

	public static Map<ResourceLocation, PlacedFeature> getConfiguredFeatures(RegistryOps<JsonElement> ops) {
		Map<ResourceLocation, PlacedFeature> map = Maps.newHashMap();

		List<PlacementModifier> miscModifiers = new ArrayList<>(MiscFeatures.placementModifiers);
		miscModifiers.add(CountPlacement.of(1));
		map.putAll(generateConfiguredFeature(ops, MiscFeatures.SEASHELL_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), miscModifiers));
		map.putAll(generateConfiguredFeature(ops, MiscFeatures.STARFISH_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), miscModifiers));
		map.putAll(generateConfiguredFeature(ops, MiscFeatures.UNDERWATER_STARFISH_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), miscModifiers));
		map.putAll(generateConfiguredFeature(ops, MiscFeatures.UNDERWATER_SEASHELL_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), miscModifiers));
		map.putAll(generateConfiguredFeature(ops, MiscFeatures.SNOWY_GEYSER_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), miscModifiers));

		List<PlacementModifier> netherModifiers = new ArrayList<>(NetherFeatures.netherModifiers);
		netherModifiers.add(CountPlacement.of(90));
		List<PlacementModifier> geyserModifiers = new ArrayList<>(NetherFeatures.netherModifiers);
		geyserModifiers.add(CountPlacement.of(30));
		map.putAll(generateConfiguredFeature(ops, NetherFeatures.NETHERRACK_ROCK_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), netherModifiers));
		map.putAll(generateConfiguredFeature(ops, NetherFeatures.SOUL_SOIL_ROCK_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), netherModifiers));
		map.putAll(generateConfiguredFeature(ops, NetherFeatures.NETHER_GRAVEL_ROCK_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), netherModifiers));
		map.putAll(generateConfiguredFeature(ops, NetherFeatures.NETHER_GEYSER_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), geyserModifiers));
		map.putAll(generateConfiguredFeature(ops, NetherFeatures.WARPED_STICK_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), netherModifiers));
		map.putAll(generateConfiguredFeature(ops, NetherFeatures.CRIMSON_STICK_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), netherModifiers));

		List<PlacementModifier> rockModifiers = new ArrayList<>(RockFeatures.rockModifiers);
		rockModifiers.add(CountPlacement.of(3));
		map.putAll(generateConfiguredFeature(ops, RockFeatures.ROCK_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), rockModifiers));
		map.putAll(generateConfiguredFeature(ops, RockFeatures.GRANITE_ROCK_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), rockModifiers));
		map.putAll(generateConfiguredFeature(ops, RockFeatures.DIORITE_ROCK_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), rockModifiers));
		map.putAll(generateConfiguredFeature(ops, RockFeatures.ANDESITE_ROCK_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), rockModifiers));
		map.putAll(generateConfiguredFeature(ops, RockFeatures.SAND_ROCK_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), rockModifiers));

		List<PlacementModifier> redSandModifier = new ArrayList<>(RockFeatures.rockModifiers);
		redSandModifier.add(CountPlacement.of(7));
		map.putAll(generateConfiguredFeature(ops, RockFeatures.RED_SAND_ROCK_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), redSandModifier));

		map.putAll(generateConfiguredFeature(ops, RockFeatures.END_STONE_ROCK_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), rockModifiers));
		map.putAll(generateConfiguredFeature(ops, RockFeatures.GRAVEL_ROCK_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), rockModifiers));

		List<PlacementModifier> stickModifiers = new ArrayList<>(StickFeatures.stickModifiers);
		stickModifiers.add(CountPlacement.of(3));
		map.putAll(generateConfiguredFeature(ops, StickFeatures.OAK_STICK_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), stickModifiers));
		map.putAll(generateConfiguredFeature(ops, StickFeatures.SPRUCE_STICK_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), stickModifiers));
		map.putAll(generateConfiguredFeature(ops, StickFeatures.PINECONE_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), stickModifiers));
		map.putAll(generateConfiguredFeature(ops, StickFeatures.BIRCH_STICK_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), stickModifiers));
		map.putAll(generateConfiguredFeature(ops, StickFeatures.ACACIA_STICK_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), stickModifiers));
		map.putAll(generateConfiguredFeature(ops, StickFeatures.JUNGLE_STICK_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), stickModifiers));
		map.putAll(generateConfiguredFeature(ops, StickFeatures.DARK_OAK_STICK_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), stickModifiers));
		map.putAll(generateConfiguredFeature(ops, StickFeatures.MANGROVE_STICK_FEATURE.unwrapKey().get().cast(Registry.CONFIGURED_FEATURE_REGISTRY).get(), stickModifiers));

		return map;
	}

	private static Map<ResourceLocation, PlacedFeature> generateConfiguredFeature(RegistryOps<JsonElement> ops, ResourceKey<ConfiguredFeature<?, ?>> featureKey, List<PlacementModifier> modifiers) {
		final Holder<ConfiguredFeature<?, ?>> featureKeyHolder = ops.registry(Registry.CONFIGURED_FEATURE_REGISTRY).get().getOrCreateHolderOrThrow(featureKey);
		final PlacedFeature feature = new PlacedFeature(featureKeyHolder, modifiers);
		return Map.of(featureKey.location(), feature);
	}

	public static Map<ResourceLocation, BiomeModifier> getBiomeModifiers(RegistryOps<JsonElement> ops) {
		Map<ResourceLocation, BiomeModifier> map = Maps.newHashMap();

		List<TagKey<Biome>> overworld = List.of(BiomeTags.IS_OVERWORLD);
		List<TagKey<Biome>> rockBlacklist = List.of(BiomeTags.IS_NETHER, BiomeTags.IS_END, BiomeTags.HAS_IGLOO, Tags.Biomes.IS_SANDY, BiomeTags.IS_BADLANDS, BiomeTags.IS_OCEAN);
		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "rock"), overworld, rockBlacklist, Decoration.TOP_LAYER_MODIFICATION));
		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "granite_rock"), overworld, rockBlacklist, Decoration.TOP_LAYER_MODIFICATION));
		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "diorite_rock"), overworld, rockBlacklist, Decoration.TOP_LAYER_MODIFICATION));
		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "andesite_rock"), overworld, rockBlacklist, Decoration.TOP_LAYER_MODIFICATION));

		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "sand_rock"), List.of(BiomeTags.IS_BEACH, Tags.Biomes.IS_SANDY, BiomeTags.IS_BADLANDS), List.of(), Decoration.TOP_LAYER_MODIFICATION));

		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "red_sand_rock"), List.of(Tags.Biomes.IS_SANDY, BiomeTags.IS_BADLANDS), List.of(), Decoration.TOP_LAYER_MODIFICATION));

		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "endstone_rock"), List.of(BiomeTags.IS_END), List.of(), Decoration.TOP_LAYER_MODIFICATION));

		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "oak_stick"), List.of(BiomeTags.IS_FOREST, Tags.Biomes.IS_PLAINS, Tags.Biomes.IS_SWAMP), List.of(), Decoration.TOP_LAYER_MODIFICATION));

		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "birch_stick"), List.of(BiomeTags.IS_FOREST), List.of(), Decoration.TOP_LAYER_MODIFICATION));

		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "pinecone"), List.of(BiomeTags.IS_TAIGA), List.of(), Decoration.TOP_LAYER_MODIFICATION));

		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "spruce_stick"), List.of(BiomeTags.IS_TAIGA), List.of(), Decoration.TOP_LAYER_MODIFICATION));

		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "acacia_stick"), List.of(BiomeTags.IS_SAVANNA), List.of(), Decoration.TOP_LAYER_MODIFICATION));

		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "jungle_stick"), List.of(BiomeTags.IS_JUNGLE), List.of(), Decoration.TOP_LAYER_MODIFICATION));

		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "dark_oak_stick"), List.of(RocksBiomeTags.IS_DARK_FOREST), List.of(), Decoration.TOP_LAYER_MODIFICATION));
		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "mangrove_stick"), List.of(RocksBiomeTags.IS_MANGROVE_SWAMP), List.of(), Decoration.TOP_LAYER_MODIFICATION));

		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "seashell"), List.of(BiomeTags.IS_BEACH), List.of(Tags.Biomes.IS_SNOWY), Decoration.TOP_LAYER_MODIFICATION));
		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "starfish"), List.of(BiomeTags.IS_BEACH), List.of(Tags.Biomes.IS_SNOWY), Decoration.TOP_LAYER_MODIFICATION));

		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "underwater_seashell"), List.of(BiomeTags.IS_OCEAN), List.of(), Decoration.TOP_LAYER_MODIFICATION));
		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "underwater_starfish"), List.of(BiomeTags.IS_OCEAN), List.of(), Decoration.TOP_LAYER_MODIFICATION));

		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "snowy_geyser"), List.of(Tags.Biomes.IS_SNOWY), List.of(), Decoration.TOP_LAYER_MODIFICATION));

		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "gravel_rock"), List.of(BiomeTags.IS_NETHER), List.of(), Decoration.TOP_LAYER_MODIFICATION));

		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "gravel_rock"), overworld, List.of(BiomeTags.IS_END, BiomeTags.IS_NETHER), Decoration.TOP_LAYER_MODIFICATION));

		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "netherrack_rock"), List.of(BiomeTags.IS_NETHER), List.of(), Decoration.TOP_LAYER_MODIFICATION));
		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "soul_soil_rock"), List.of(BiomeTags.IS_NETHER), List.of(), Decoration.TOP_LAYER_MODIFICATION));
		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "nether_gravel_rock"), List.of(BiomeTags.IS_NETHER), List.of(), Decoration.TOP_LAYER_MODIFICATION));
		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "nether_geyser"), List.of(BiomeTags.IS_NETHER), List.of(), Decoration.TOP_LAYER_MODIFICATION));
		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "warped_stick"), List.of(BiomeTags.IS_NETHER), List.of(), Decoration.TOP_LAYER_MODIFICATION));
		map.putAll(generateBiomeModifier(ops, new ResourceLocation(Rocks.MOD_ID, "crimson_stick"), List.of(BiomeTags.IS_NETHER), List.of(), Decoration.TOP_LAYER_MODIFICATION));

		return map;
	}

	private static Map<ResourceLocation, BiomeModifier> generateBiomeModifier(RegistryOps<JsonElement> ops, ResourceLocation location, @NotNull List<TagKey<Biome>> tags, @Nullable List<TagKey<Biome>> blacklistTags, Decoration decorationType) {
		final List<HolderSet<Biome>> tagHolders = tags.stream().map(tag -> new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), tag)).collect(Collectors.toList());
		final List<HolderSet<Biome>> blacklistTagHolders = blacklistTags.isEmpty() ? List.of() : blacklistTags.stream().map(tag -> new HolderSet.Named<>(ops.registry(Registry.BIOME_REGISTRY).get(), tag)).collect(Collectors.toList());
		final BiomeModifier addFeature = new AddFeaturesBlacklistBiomeModifier(tagHolders, blacklistTagHolders, HolderSet.direct(ops.registry(Registry.PLACED_FEATURE_REGISTRY).get().getOrCreateHolderOrThrow(ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, location))), decorationType);
		return Map.of(location, addFeature);
	}


	private static class Loots extends LootTableProvider {
		public Loots(DataGenerator gen) {
			super(gen);
		}

		@Override
		protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootContextParamSet>> getTables() {
			return ImmutableList.of(Pair.of(GeOreBlockTables::new, LootContextParamSets.BLOCK));
		}

		public static class GeOreBlockTables extends BlockLoot {
			private static final float[] NAUTILOUS_CHANCE = new float[]{0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F};

			@Override
			protected void addTables() {
				this.dropOther(RocksRegistry.ACACIA_STICK.get(), Items.STICK);
				this.dropOther(RocksRegistry.BIRCH_STICK.get(), Items.STICK);
				this.dropOther(RocksRegistry.CRIMSON_STICK.get(), Items.STICK);
				this.dropOther(RocksRegistry.DARK_OAK_STICK.get(), Items.STICK);
				this.dropOther(RocksRegistry.JUNGLE_STICK.get(), Items.STICK);
				this.dropOther(RocksRegistry.OAK_STICK.get(), Items.STICK);
				this.dropOther(RocksRegistry.MANGROVE_STICK.get(), Items.STICK);
				this.dropOther(RocksRegistry.SPRUCE_STICK.get(), Items.STICK);
				this.dropOther(RocksRegistry.WARPED_STICK.get(), Items.STICK);

				this.dropOther(RocksRegistry.PINECONE.get(), Items.SPRUCE_SAPLING);
				this.add(RocksRegistry.SEASHELL.get(), createSeashellDrop(RocksRegistry.SEASHELL.get()));
				this.add(RocksRegistry.STARFISH.get(), createStarfishDrop(RocksRegistry.STARFISH.get()));

				this.dropOther(RocksRegistry.ANDESITE_ROCK.get(), RocksRegistry.ANDESITE_SPLITTER.get());
				this.dropOther(RocksRegistry.DIORITE_ROCK.get(), RocksRegistry.DIORITE_SPLITTER.get());
				this.dropOther(RocksRegistry.END_STONE_ROCK.get(), RocksRegistry.END_STONE_SPLITTER.get());
				this.dropOther(RocksRegistry.GRANITE_ROCK.get(), RocksRegistry.GRANITE_SPLITTER.get());
				this.dropOther(RocksRegistry.NETHERRACK_ROCK.get(), RocksRegistry.NETHERRACK_SPLITTER.get());
				this.dropOther(RocksRegistry.RED_SAND_ROCK.get(), RocksRegistry.RED_SAND_ROCK_ITEM.get());
				this.dropOther(RocksRegistry.ROCK.get(), RocksRegistry.COBBLESTONE_SPLITTER.get());
				this.dropOther(RocksRegistry.SAND_ROCK.get(), RocksRegistry.SANDSTONE_SPLITTER.get());
				this.dropOther(RocksRegistry.SOUL_SOIL_ROCK.get(), RocksRegistry.SOUL_SOIL_SPLITTER.get());

				this.dropOther(RocksRegistry.GRAVEL_ROCK.get(), Items.FLINT);

				this.add(RocksRegistry.GEYSER.get(), noDrop());
				this.add(RocksRegistry.NETHER_GEYSER.get(), noDrop());
			}

			protected static LootTable.Builder createSeashellDrop(Block block) {
				return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(applyExplosionDecay(block, LootItem.lootTableItem(Items.NAUTILUS_SHELL).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, NAUTILOUS_CHANCE)))));
			}

			protected static LootTable.Builder createStarfishDrop(Block starFish) {
				return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(applyExplosionDecay(starFish, LootItem.lootTableItem(starFish).apply(SetNbtFunction.setTag(getStarfishTag("red"))).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(starFish).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(Rocks.STARFISH_VARIATION, StarfishVariation.RED))))).add(applyExplosionDecay(starFish, LootItem.lootTableItem(starFish).apply(SetNbtFunction.setTag(getStarfishTag("pink"))).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(starFish).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(Rocks.STARFISH_VARIATION, StarfishVariation.PINK))))).add(applyExplosionDecay(starFish, LootItem.lootTableItem(starFish).apply(SetNbtFunction.setTag(getStarfishTag("orange"))).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(starFish).setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(Rocks.STARFISH_VARIATION, StarfishVariation.ORANGE))))));
			}

			private static CompoundTag getStarfishTag(String color) {
				CompoundTag tag = new CompoundTag();
				tag.putString("variation", color);
				return tag;
			}

			@Override
			protected Iterable<Block> getKnownBlocks() {
				return (Iterable<Block>) RocksRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
			}
		}

		@Override
		protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationContext) {
			map.forEach((name, table) -> LootTables.validate(validationContext, name, table));
		}
	}

	public static class Recipes extends RecipeProvider {
		public Recipes(DataGenerator generator) {
			super(generator);
		}

		@Override
		protected void buildCraftingRecipes(Consumer<FinishedRecipe> recipeConsumer) {
			ShapelessRecipeBuilder.shapeless(Blocks.ANDESITE).requires(RocksRegistry.ANDESITE_SPLITTER.get()).requires(RocksRegistry.ANDESITE_SPLITTER.get()).requires(RocksRegistry.ANDESITE_SPLITTER.get()).requires(RocksRegistry.ANDESITE_SPLITTER.get()).unlockedBy("none", has(Items.DIRT)).save(recipeConsumer, new ResourceLocation(Rocks.MOD_ID, "andesite_from_splitter"));
			ShapelessRecipeBuilder.shapeless(Blocks.COBBLESTONE).requires(RocksRegistry.COBBLESTONE_SPLITTER.get()).requires(RocksRegistry.COBBLESTONE_SPLITTER.get()).requires(RocksRegistry.COBBLESTONE_SPLITTER.get()).requires(RocksRegistry.COBBLESTONE_SPLITTER.get()).unlockedBy("none", has(Items.DIRT)).save(recipeConsumer, new ResourceLocation(Rocks.MOD_ID, "cobblestone_from_splitter"));
			ShapelessRecipeBuilder.shapeless(Blocks.DIORITE).requires(RocksRegistry.DIORITE_SPLITTER.get()).requires(RocksRegistry.DIORITE_SPLITTER.get()).requires(RocksRegistry.DIORITE_SPLITTER.get()).requires(RocksRegistry.DIORITE_SPLITTER.get()).unlockedBy("none", has(Items.DIRT)).save(recipeConsumer, new ResourceLocation(Rocks.MOD_ID, "diorite_from_splitter"));
			ShapelessRecipeBuilder.shapeless(Blocks.END_STONE).requires(RocksRegistry.END_STONE_SPLITTER.get()).requires(RocksRegistry.END_STONE_SPLITTER.get()).requires(RocksRegistry.END_STONE_SPLITTER.get()).requires(RocksRegistry.END_STONE_SPLITTER.get()).unlockedBy("none", has(Items.DIRT)).save(recipeConsumer, new ResourceLocation(Rocks.MOD_ID, "end_stone_from_splitter"));
			ShapelessRecipeBuilder.shapeless(Blocks.GRANITE).requires(RocksRegistry.GRANITE_SPLITTER.get()).requires(RocksRegistry.GRANITE_SPLITTER.get()).requires(RocksRegistry.GRANITE_SPLITTER.get()).requires(RocksRegistry.GRANITE_SPLITTER.get()).unlockedBy("none", has(Items.DIRT)).save(recipeConsumer, new ResourceLocation(Rocks.MOD_ID, "granite_from_splitter"));
			ShapelessRecipeBuilder.shapeless(Blocks.NETHERRACK).requires(RocksRegistry.NETHERRACK_SPLITTER.get()).requires(RocksRegistry.NETHERRACK_SPLITTER.get()).requires(RocksRegistry.NETHERRACK_SPLITTER.get()).requires(RocksRegistry.NETHERRACK_SPLITTER.get()).unlockedBy("none", has(Items.DIRT)).save(recipeConsumer, new ResourceLocation(Rocks.MOD_ID, "netherrack_from_splitter"));
			ShapelessRecipeBuilder.shapeless(Blocks.RED_SANDSTONE).requires(RocksRegistry.RED_SANDSTONE_SPLITTER.get()).requires(RocksRegistry.RED_SANDSTONE_SPLITTER.get()).requires(RocksRegistry.RED_SANDSTONE_SPLITTER.get()).requires(RocksRegistry.RED_SANDSTONE_SPLITTER.get()).unlockedBy("none", has(Items.DIRT)).save(recipeConsumer, new ResourceLocation(Rocks.MOD_ID, "red_sandstone_from_splitter"));
			ShapelessRecipeBuilder.shapeless(Blocks.SANDSTONE).requires(RocksRegistry.SANDSTONE_SPLITTER.get()).requires(RocksRegistry.SANDSTONE_SPLITTER.get()).requires(RocksRegistry.SANDSTONE_SPLITTER.get()).requires(RocksRegistry.SANDSTONE_SPLITTER.get()).unlockedBy("none", has(Items.DIRT)).save(recipeConsumer, new ResourceLocation(Rocks.MOD_ID, "sandstone_from_splitter"));
			ShapelessRecipeBuilder.shapeless(Blocks.SOUL_SOIL).requires(RocksRegistry.SOUL_SOIL_SPLITTER.get()).requires(RocksRegistry.SOUL_SOIL_SPLITTER.get()).requires(RocksRegistry.SOUL_SOIL_SPLITTER.get()).requires(RocksRegistry.SOUL_SOIL_SPLITTER.get()).unlockedBy("none", has(Items.DIRT)).save(recipeConsumer, new ResourceLocation(Rocks.MOD_ID, "soul_soil_from_splitter"));
		}

		@Override
		protected void saveAdvancement(CachedOutput output, JsonObject jsonObject, Path path) {
			//NOOP
		}
	}


	public static class RocksBiomeTags extends BiomeTagsProvider {
		public RocksBiomeTags(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
			super(generator, Rocks.MOD_ID, existingFileHelper);
		}

		public static final TagKey<Biome> IS_DARK_FOREST = create(new ResourceLocation("forge", "is_dark_forest"));
		public static final TagKey<Biome> IS_MANGROVE_SWAMP = create(new ResourceLocation("forge", "is_mangrove_swamp"));

		private static TagKey<Biome> create(ResourceLocation location) {
			return TagKey.create(Registry.BIOME_REGISTRY, location);
		}

		@Override
		protected void addTags() {
			this.tag(IS_DARK_FOREST).add(Biomes.DARK_FOREST);
			this.tag(IS_MANGROVE_SWAMP).add(Biomes.MANGROVE_SWAMP);
		}
	}
}
