package eu.midnightdust.motschen.rocks.datagen;

import com.mojang.math.Quadrant;
import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.block.NetherGeyser;
import eu.midnightdust.motschen.rocks.block.OverworldGeyser;
import eu.midnightdust.motschen.rocks.block.Pinecone;
import eu.midnightdust.motschen.rocks.block.Seashell;
import eu.midnightdust.motschen.rocks.block.Starfish;
import eu.midnightdust.motschen.rocks.blockstates.StarfishVariation;
import eu.midnightdust.motschen.rocks.registry.RocksRegistry;
import eu.midnightdust.motschen.rocks.util.RockType;
import eu.midnightdust.motschen.rocks.util.StickType;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class RocksModelProvider extends ModelProvider {
	public static final TextureSlot ZERO_TEXTURE_KEY = TextureSlot.create("0");

	public RocksModelProvider(PackOutput output) {
		super(output, Rocks.MOD_ID);
	}

	public static ResourceLocation getBlockId(String s) {
		return Rocks.modLoc("block/" + s);
	}

	public static ResourceLocation getItemId(String s) {
		return Rocks.modLoc("item/" + s);
	}

	public static ModelTemplate getSimpleParentModel(ResourceLocation parentId, String variant) {
		return new ModelTemplate(Optional.of(parentId), Optional.of(variant), ZERO_TEXTURE_KEY);
	}

	@Override
	protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
		// Blocks
		for (RockType type : RockType.values()) {
			Block block = BuiltInRegistries.BLOCK.getValue(Rocks.modLoc(type.getName()));
			RockModel.registerBlockModel(blockModels, block, type.getStoneBlock());
		}
		for (StickType type : StickType.values()) {
			Block block = BuiltInRegistries.BLOCK.getValue(Rocks.modLoc(type.getName() + "_stick"));
			StickModel.registerBlockModel(blockModels, block, type.getBaseBlock());
		}

		// Items
		for (RockType type : RockType.values()) {
			Item item = BuiltInRegistries.ITEM.getValue(Rocks.modLoc(type.getName()));
			registerParentedItemModel(itemModels, item, getItemId("rock_base"), type.getStoneBlock());

			if (type != RockType.GRAVEL) {
				Item splitter = BuiltInRegistries.ITEM.getValue(Rocks.modLoc(type.getFragment().getName()));
				registerParentedItemModel(itemModels, splitter, getItemId("splitter_base"), type.getFragment().getStoneBlock());
			}
		}
		for (StickType type : StickType.values()) {
			Item item = BuiltInRegistries.ITEM.getValue(Rocks.modLoc(type.getName() + "_stick"));
			registerParentedItemModel(itemModels, item, getItemId("stick_base"), type.getBaseBlock());
		}
		itemModels.declareCustomModelItem(RocksRegistry.GEYSER.asItem());
		itemModels.declareCustomModelItem(RocksRegistry.NETHER_GEYSER.asItem());
		registerStarfishItemVariations(itemModels, RocksRegistry.STARFISH.get());
		itemModels.declareCustomModelItem(RocksRegistry.SEASHELL.asItem());
		itemModels.declareCustomModelItem(RocksRegistry.PINECONE.asItem());
	}

	@Override
	protected Stream<? extends Holder<Block>> getKnownBlocks() {
		var blocks = new ArrayList<>(RocksRegistry.BLOCKS.getEntries());
		blocks.removeIf(deferred -> deferred.get() instanceof OverworldGeyser || deferred.get() instanceof NetherGeyser ||
				deferred.get() instanceof Pinecone || deferred.get() instanceof Seashell || deferred.get() instanceof Starfish);
		return blocks.stream();
	}

	public static void registerParentedItemModel(ItemModelGenerators modelGenerator, Item item, ResourceLocation parentId, Block textureSource) {
		TextureMapping textureMap = TextureMapping.singleSlot(ZERO_TEXTURE_KEY, TextureMapping.getBlockTexture(textureSource));

		ResourceLocation itemModel = getSimpleParentModel(parentId, "").create(item, textureMap, modelGenerator.modelOutput);
		modelGenerator.itemModelOutput.accept(item, ItemModelUtils.plainModel(itemModel));
	}
	public final void registerStarfishItemVariations(ItemModelGenerators modelGenerator, Block starfish) {
		Map<StarfishVariation, ItemModel.Unbaked> variantMap = new HashMap<>();
		for (StarfishVariation variation : StarfishVariation.values()) {
			variantMap.put(variation, ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(starfish, "_"+variation.toString())));
		}
		modelGenerator.itemModelOutput.accept(starfish.asItem(), ItemModelUtils.selectBlockItemProperty(Rocks.STARFISH_VARIATION, ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(starfish.asItem())), variantMap));
	}

	public static MultiVariant getRandomRotationWeightedVariant(ResourceLocation modelId) {
		WeightedList.Builder<Variant> list = WeightedList.builder();
		for (Quadrant rotation : Quadrant.values()) {
			Variant rotatedVariant = new Variant(modelId, Variant.SimpleModelState.DEFAULT.withY(rotation));
			list.add(rotatedVariant);
		}
		return new MultiVariant(list.build());
	}

	private static class RockModel {
		public static void registerBlockModel(BlockModelGenerators modelGenerator, Block rockBlock, Block textureSource) {
			TextureMapping textureMap = TextureMapping.singleSlot(ZERO_TEXTURE_KEY, TextureMapping.getBlockTexture(textureSource));

			ResourceLocation largeRock = getSimpleParentModel(getBlockId("large_rock"), "_large").create(rockBlock, textureMap, modelGenerator.modelOutput);
			ResourceLocation mediumRock = getSimpleParentModel(getBlockId("medium_rock"), "_medium").create(rockBlock, textureMap, modelGenerator.modelOutput);
			ResourceLocation smallRock = getSimpleParentModel(getBlockId("small_rock"), "_small").create(rockBlock, textureMap, modelGenerator.modelOutput);
			ResourceLocation tinyRock = getSimpleParentModel(getBlockId("tiny_rock"), "_tiny").create(rockBlock, textureMap, modelGenerator.modelOutput);
			modelGenerator.blockStateOutput.accept(createBlockState(rockBlock, new ResourceLocation[]{largeRock, mediumRock, smallRock, tinyRock}));
		}

		private static BlockModelDefinitionGenerator createBlockState(Block rockBlock, ResourceLocation[] modelIds) {
			return MultiVariantGenerator.dispatch(rockBlock)
					.with(PropertyDispatch.initial(Rocks.ROCK_VARIATION)
							.generate(variation -> getRandomRotationWeightedVariant(modelIds[3 - variation.ordinal()]))
					);
		}
	}
	private static class StickModel {
		public static void registerBlockModel(BlockModelGenerators modelGenerator, Block stickBlock, Block textureSource) {
			TextureMapping textureMap = TextureMapping.singleSlot(ZERO_TEXTURE_KEY, TextureMapping.getBlockTexture(textureSource));

			ResourceLocation largeRock = getSimpleParentModel(getBlockId("large_stick"), "_large").create(stickBlock, textureMap, modelGenerator.modelOutput);
			ResourceLocation mediumRock = getSimpleParentModel(getBlockId("medium_stick"), "_medium").create(stickBlock, textureMap, modelGenerator.modelOutput);
			ResourceLocation smallRock = getSimpleParentModel(getBlockId("small_stick"), "_small").create(stickBlock, textureMap, modelGenerator.modelOutput);
			modelGenerator.blockStateOutput.accept(createBlockState(stickBlock, new ResourceLocation[]{largeRock, mediumRock, smallRock}));
		}

		private static BlockModelDefinitionGenerator createBlockState(Block stickBlock, ResourceLocation[] modelIds) {
			return MultiVariantGenerator.dispatch(stickBlock)
					.with(PropertyDispatch.initial(Rocks.STICK_VARIATION)
							.generate(variation -> getRandomRotationWeightedVariant(modelIds[2 - variation.ordinal()]))
					);
		}
	}
}
