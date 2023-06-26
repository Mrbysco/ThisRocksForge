package eu.midnightdust.motschen.rocks.datagen;

import com.google.gson.JsonObject;
import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.blockstates.StarfishVariation;
import eu.midnightdust.motschen.rocks.registry.RocksRegistry;
import eu.midnightdust.motschen.rocks.world.configured_feature.MiscFeatures;
import eu.midnightdust.motschen.rocks.world.configured_feature.NetherFeatures;
import eu.midnightdust.motschen.rocks.world.configured_feature.RockFeatures;
import eu.midnightdust.motschen.rocks.world.configured_feature.StickFeatures;
import eu.midnightdust.motschen.rocks.world.modifier.AddFeaturesBlacklistBiomeModifier;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetNbtFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RocksDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		if (event.includeServer()) {
			generator.addProvider(event.includeServer(), new Loots(packOutput));
			generator.addProvider(event.includeServer(), new Recipes(packOutput));
			generator.addProvider(event.includeServer(), new RocksBiomeTags(packOutput, lookupProvider, helper));

			generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(
					packOutput, CompletableFuture.supplyAsync(RocksDatagen::getProvider), Set.of("rocks")));
		}
	}

	private static HolderLookup.Provider getProvider() {
		final RegistrySetBuilder registryBuilder = new RegistrySetBuilder();
		registryBuilder.add(Registries.CONFIGURED_FEATURE, context -> {
			MiscFeatures.configuredBootstrap(context);
			NetherFeatures.configuredBootstrap(context);
			RockFeatures.configuredBootstrap(context);
			StickFeatures.configuredBootstrap(context);
		});
		registryBuilder.add(Registries.PLACED_FEATURE, context -> {
			MiscFeatures.placedBootstrap(context);
			NetherFeatures.placedBootstrap(context);
			RockFeatures.placedBootstrap(context);
			StickFeatures.placedBootstrap(context);
		});
		registryBuilder.add(ForgeRegistries.Keys.BIOME_MODIFIERS, context -> {
			HolderGetter<Biome> biomeGetter = context.lookup(Registries.BIOME);
			List<HolderSet<Biome>> overworld = List.of(biomeGetter.getOrThrow(BiomeTags.IS_OVERWORLD));

			List<HolderSet<Biome>> rockBlacklist = List.of(
					biomeGetter.getOrThrow(BiomeTags.IS_NETHER), biomeGetter.getOrThrow(BiomeTags.IS_END),
					biomeGetter.getOrThrow(BiomeTags.HAS_IGLOO), biomeGetter.getOrThrow(Tags.Biomes.IS_SANDY),
					biomeGetter.getOrThrow(BiomeTags.IS_BADLANDS), biomeGetter.getOrThrow(BiomeTags.IS_OCEAN));
			context.register(RocksModifiers.ROCK_MODIFIER, new AddFeaturesBlacklistBiomeModifier(overworld, rockBlacklist,
					HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(RockFeatures.PLACED_ROCK_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));
			context.register(RocksModifiers.GRANITE_ROCK_MODIFIER, new AddFeaturesBlacklistBiomeModifier(overworld, rockBlacklist,
					HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(RockFeatures.PLACED_GRANITE_ROCK_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));
			context.register(RocksModifiers.DIORITE_ROCK_MODIFIER, new AddFeaturesBlacklistBiomeModifier(overworld, rockBlacklist,
					HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(RockFeatures.PLACED_DIORITE_ROCK_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));
			context.register(RocksModifiers.ANDESITE_ROCK_MODIFIER, new AddFeaturesBlacklistBiomeModifier(overworld, rockBlacklist,
					HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(RockFeatures.PLACED_ANDESITE_ROCK_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));

			context.register(RocksModifiers.SAND_ROCK_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(BiomeTags.IS_BEACH),
							biomeGetter.getOrThrow(Tags.Biomes.IS_SANDY),
							biomeGetter.getOrThrow(BiomeTags.IS_BADLANDS)),
					List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(RockFeatures.PLACED_SAND_ROCK_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));
			context.register(RocksModifiers.RED_SAND_ROCK_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(Tags.Biomes.IS_SANDY),
							biomeGetter.getOrThrow(BiomeTags.IS_BADLANDS)),
					List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(RockFeatures.PLACED_RED_SAND_ROCK_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));
			context.register(RocksModifiers.ENDSTONE_ROCK_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(BiomeTags.IS_END)),
					List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(RockFeatures.PLACED_END_STONE_ROCK_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));

			context.register(RocksModifiers.OAK_STICK_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(BiomeTags.IS_FOREST),
							biomeGetter.getOrThrow(Tags.Biomes.IS_PLAINS),
							biomeGetter.getOrThrow(Tags.Biomes.IS_SWAMP)),
					List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(StickFeatures.PLACED_OAK_STICK_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));
			context.register(RocksModifiers.BIRCH_STICK_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(BiomeTags.IS_FOREST)),
					List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(StickFeatures.PLACED_BIRCH_STICK_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));
			context.register(RocksModifiers.PINECONE_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(BiomeTags.IS_TAIGA)),
					List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(StickFeatures.PLACED_PINECONE_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));
			context.register(RocksModifiers.SPRUCE_STICK_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(BiomeTags.IS_TAIGA)),
					List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(StickFeatures.PLACED_SPRUCE_STICK_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));
			context.register(RocksModifiers.ACACIA_STICK_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(BiomeTags.IS_SAVANNA)),
					List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(StickFeatures.PLACED_ACACIA_STICK_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));
			context.register(RocksModifiers.JUNGLE_STICK_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(BiomeTags.IS_JUNGLE)),
					List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(StickFeatures.PLACED_JUNGLE_STICK_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));
			context.register(RocksModifiers.BAMBOO_STICK_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(BiomeTags.IS_JUNGLE)),
					List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(StickFeatures.PLACED_BAMBOO_STICK_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));
			context.register(RocksModifiers.DARK_OAK_STICK_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(RocksBiomeTags.IS_DARK_FOREST)),
					List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(StickFeatures.PLACED_DARK_OAK_STICK_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));
			context.register(RocksModifiers.MANGROVE_STICK_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(RocksBiomeTags.IS_MANGROVE_SWAMP)),
					List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(StickFeatures.PLACED_MANGROVE_STICK_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));
			context.register(RocksModifiers.CHERRY_STICK_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(RocksBiomeTags.IS_CHERRY_GROVE)),
					List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(StickFeatures.PLACED_CHERRY_STICK_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));

			context.register(RocksModifiers.SEASHELL_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(BiomeTags.IS_BEACH)),
					List.of(biomeGetter.getOrThrow(Tags.Biomes.IS_SNOWY)),
					HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(MiscFeatures.PLACED_SEASHELL_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));
			context.register(RocksModifiers.STARFISH_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(BiomeTags.IS_BEACH)),
					List.of(biomeGetter.getOrThrow(Tags.Biomes.IS_SNOWY)),
					HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(MiscFeatures.PLACED_STARFISH_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));

			context.register(RocksModifiers.UNDERWATER_SEASHELL_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(BiomeTags.IS_OCEAN)),
					List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(MiscFeatures.PLACED_UNDERWATER_SEASHELL_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));
			context.register(RocksModifiers.UNDERWATER_STARFISH_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(BiomeTags.IS_OCEAN)),
					List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(MiscFeatures.PLACED_UNDERWATER_STARFISH_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));

			context.register(RocksModifiers.SNOWY_GEYSER_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(Tags.Biomes.IS_SNOWY)),
					List.of(), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(MiscFeatures.PLACED_SNOWY_GEYSER_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));
			context.register(RocksModifiers.GRAVEL_ROCK_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					overworld,
					List.of(biomeGetter.getOrThrow(BiomeTags.IS_NETHER),
							biomeGetter.getOrThrow(BiomeTags.IS_END)),
					HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(RockFeatures.PLACED_GRAVEL_ROCK_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));
			context.register(RocksModifiers.GRAVEL_ROCK_NETHER_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(BiomeTags.IS_NETHER)),
					List.of(biomeGetter.getOrThrow(BiomeTags.IS_END),
							biomeGetter.getOrThrow(BiomeTags.IS_NETHER)),
					HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(RockFeatures.PLACED_GRAVEL_ROCK_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));

			context.register(RocksModifiers.NETHERRACK_ROCK_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(BiomeTags.IS_NETHER)),
					List.of(),
					HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(NetherFeatures.PLACED_NETHERRACK_ROCK_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));
			context.register(RocksModifiers.SOUL_SOIL_ROCK_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(BiomeTags.IS_NETHER)),
					List.of(),
					HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(NetherFeatures.PLACED_SOUL_SOIL_ROCK_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));
			context.register(RocksModifiers.NETHER_GRAVEL_ROCK_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(BiomeTags.IS_NETHER)),
					List.of(),
					HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(NetherFeatures.PLACED_NETHER_GRAVEL_ROCK_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));
			context.register(RocksModifiers.WARPED_STICK_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(BiomeTags.IS_NETHER)),
					List.of(),
					HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(NetherFeatures.PLACED_WARPED_STICK_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));
			context.register(RocksModifiers.CRIMSON_STICK_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(BiomeTags.IS_NETHER)),
					List.of(),
					HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(NetherFeatures.PLACED_CRIMSON_STICK_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));
			context.register(RocksModifiers.NETHER_GEYSER_MODIFIER, new AddFeaturesBlacklistBiomeModifier(
					List.of(biomeGetter.getOrThrow(BiomeTags.IS_NETHER)),
					List.of(),
					HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(NetherFeatures.PLACED_NETHER_GEYSER_FEATURE)),
					Decoration.TOP_LAYER_MODIFICATION
			));
		});
		// We need the BIOME registry to be present so we can use a biome tag, doesn't matter that it's empty
		registryBuilder.add(Registries.BIOME, context -> {
		});
		RegistryAccess.Frozen regAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);
		return registryBuilder.buildPatch(regAccess, VanillaRegistries.createLookup());
	}


	private static class Loots extends LootTableProvider {
		public Loots(PackOutput packOutput) {
			super(packOutput, Set.of(), List.of(
					new SubProviderEntry(RocksBlockTables::new, LootContextParamSets.BLOCK)
			));
		}

		public static class RocksBlockTables extends BlockLootSubProvider {

			protected RocksBlockTables() {
				super(Set.of(), FeatureFlags.REGISTRY.allFlags());
			}

			private static final float[] NAUTILOUS_CHANCE = new float[]{0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F};

			@Override
			protected void generate() {
				this.dropOther(RocksRegistry.ACACIA_STICK.get(), Items.STICK);
				this.dropOther(RocksRegistry.BIRCH_STICK.get(), Items.STICK);
				this.dropOther(RocksRegistry.CHERRY_STICK.get(), Items.STICK);
				this.dropOther(RocksRegistry.CRIMSON_STICK.get(), Items.STICK);
				this.dropOther(RocksRegistry.DARK_OAK_STICK.get(), Items.STICK);
				this.dropOther(RocksRegistry.JUNGLE_STICK.get(), Items.STICK);
				this.dropOther(RocksRegistry.MANGROVE_STICK.get(), Items.STICK);
				this.dropOther(RocksRegistry.OAK_STICK.get(), Items.STICK);
				this.dropOther(RocksRegistry.SPRUCE_STICK.get(), Items.STICK);
				this.dropOther(RocksRegistry.WARPED_STICK.get(), Items.STICK);
				this.dropOther(RocksRegistry.BAMBOO_STICK.get(), Items.BAMBOO);

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

			protected LootTable.Builder createSeashellDrop(Block block) {
				return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(applyExplosionDecay(block, LootItem.lootTableItem(Items.NAUTILUS_SHELL).when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, NAUTILOUS_CHANCE)))));
			}

			protected LootTable.Builder createStarfishDrop(Block starFish) {
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
			map.forEach((name, table) -> table.validate(validationContext));
		}
	}

	public static class Recipes extends RecipeProvider {
		public Recipes(PackOutput packOutput) {
			super(packOutput);
		}

		@Override
		protected void buildRecipes(Consumer<FinishedRecipe> recipeConsumer) {
			ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.ANDESITE).requires(RocksRegistry.ANDESITE_SPLITTER.get()).requires(RocksRegistry.ANDESITE_SPLITTER.get()).requires(RocksRegistry.ANDESITE_SPLITTER.get()).requires(RocksRegistry.ANDESITE_SPLITTER.get()).unlockedBy("none", has(Items.DIRT)).save(recipeConsumer, new ResourceLocation(Rocks.MOD_ID, "andesite_from_splitter"));
			ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.COBBLESTONE).requires(RocksRegistry.COBBLESTONE_SPLITTER.get()).requires(RocksRegistry.COBBLESTONE_SPLITTER.get()).requires(RocksRegistry.COBBLESTONE_SPLITTER.get()).requires(RocksRegistry.COBBLESTONE_SPLITTER.get()).unlockedBy("none", has(Items.DIRT)).save(recipeConsumer, new ResourceLocation(Rocks.MOD_ID, "cobblestone_from_splitter"));
			ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.DIORITE).requires(RocksRegistry.DIORITE_SPLITTER.get()).requires(RocksRegistry.DIORITE_SPLITTER.get()).requires(RocksRegistry.DIORITE_SPLITTER.get()).requires(RocksRegistry.DIORITE_SPLITTER.get()).unlockedBy("none", has(Items.DIRT)).save(recipeConsumer, new ResourceLocation(Rocks.MOD_ID, "diorite_from_splitter"));
			ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.END_STONE).requires(RocksRegistry.END_STONE_SPLITTER.get()).requires(RocksRegistry.END_STONE_SPLITTER.get()).requires(RocksRegistry.END_STONE_SPLITTER.get()).requires(RocksRegistry.END_STONE_SPLITTER.get()).unlockedBy("none", has(Items.DIRT)).save(recipeConsumer, new ResourceLocation(Rocks.MOD_ID, "end_stone_from_splitter"));
			ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.GRANITE).requires(RocksRegistry.GRANITE_SPLITTER.get()).requires(RocksRegistry.GRANITE_SPLITTER.get()).requires(RocksRegistry.GRANITE_SPLITTER.get()).requires(RocksRegistry.GRANITE_SPLITTER.get()).unlockedBy("none", has(Items.DIRT)).save(recipeConsumer, new ResourceLocation(Rocks.MOD_ID, "granite_from_splitter"));
			ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.NETHERRACK).requires(RocksRegistry.NETHERRACK_SPLITTER.get()).requires(RocksRegistry.NETHERRACK_SPLITTER.get()).requires(RocksRegistry.NETHERRACK_SPLITTER.get()).requires(RocksRegistry.NETHERRACK_SPLITTER.get()).unlockedBy("none", has(Items.DIRT)).save(recipeConsumer, new ResourceLocation(Rocks.MOD_ID, "netherrack_from_splitter"));
			ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.RED_SANDSTONE).requires(RocksRegistry.RED_SANDSTONE_SPLITTER.get()).requires(RocksRegistry.RED_SANDSTONE_SPLITTER.get()).requires(RocksRegistry.RED_SANDSTONE_SPLITTER.get()).requires(RocksRegistry.RED_SANDSTONE_SPLITTER.get()).unlockedBy("none", has(Items.DIRT)).save(recipeConsumer, new ResourceLocation(Rocks.MOD_ID, "red_sandstone_from_splitter"));
			ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.SANDSTONE).requires(RocksRegistry.SANDSTONE_SPLITTER.get()).requires(RocksRegistry.SANDSTONE_SPLITTER.get()).requires(RocksRegistry.SANDSTONE_SPLITTER.get()).requires(RocksRegistry.SANDSTONE_SPLITTER.get()).unlockedBy("none", has(Items.DIRT)).save(recipeConsumer, new ResourceLocation(Rocks.MOD_ID, "sandstone_from_splitter"));
			ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Blocks.SOUL_SOIL).requires(RocksRegistry.SOUL_SOIL_SPLITTER.get()).requires(RocksRegistry.SOUL_SOIL_SPLITTER.get()).requires(RocksRegistry.SOUL_SOIL_SPLITTER.get()).requires(RocksRegistry.SOUL_SOIL_SPLITTER.get()).unlockedBy("none", has(Items.DIRT)).save(recipeConsumer, new ResourceLocation(Rocks.MOD_ID, "soul_soil_from_splitter"));
		}

		@Override
		protected @Nullable CompletableFuture<?> saveAdvancement(CachedOutput output, FinishedRecipe finishedRecipe, JsonObject advancementJson) {
			return null;
		}
	}


	public static class RocksBiomeTags extends BiomeTagsProvider {
		public RocksBiomeTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
			super(packOutput, lookupProvider, Rocks.MOD_ID, existingFileHelper);
		}

		public static final TagKey<Biome> IS_DARK_FOREST = create(new ResourceLocation("forge", "is_dark_forest"));
		public static final TagKey<Biome> IS_MANGROVE_SWAMP = create(new ResourceLocation("forge", "is_mangrove_swamp"));
		public static final TagKey<Biome> IS_CHERRY_GROVE = create(new ResourceLocation("forge", "is_cherry_grove"));

		private static TagKey<Biome> create(ResourceLocation location) {
			return TagKey.create(Registries.BIOME, location);
		}

		@Override
		protected void addTags(HolderLookup.Provider provider) {
			this.tag(IS_DARK_FOREST).add(Biomes.DARK_FOREST);
			this.tag(IS_MANGROVE_SWAMP).add(Biomes.MANGROVE_SWAMP);
			this.tag(IS_CHERRY_GROVE).add(Biomes.CHERRY_GROVE);
		}
	}
}
