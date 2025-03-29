package eu.midnightdust.motschen.rocks.datagen;

import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.registry.RocksRegistry;
import eu.midnightdust.motschen.rocks.util.RockType;
import eu.midnightdust.motschen.rocks.util.StickType;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class RocksLanguageProvider extends LanguageProvider {
	public RocksLanguageProvider(PackOutput output) {
		super(output, Rocks.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		createRepeatedTranslations(" Rock", " Fragment", " Stick");

		addBlock(RocksRegistry.GEYSER, "Geyser");
		addBlock(RocksRegistry.NETHER_GEYSER, "Nether Geyser");
		addBlock(RocksRegistry.STARFISH, "Starfish");
		addBlock(RocksRegistry.SEASHELL, "Seashell");
		addBlock(RocksRegistry.PINECONE, "Pinecone");
	}


	public void createRepeatedTranslations(String rockWord, String splitterWord, String stickWord) {
		for (RockType type : RockType.values()) {
			Block block = BuiltInRegistries.BLOCK.getValue(Rocks.modLoc(type.getName()));
			String baseTranslation = I18n.get(type.getStoneBlock().getDescriptionId());
			add(block, baseTranslation + rockWord);

			if (type != RockType.GRAVEL) {
				String splitterBaseTranslation = I18n.get(type.getFragment().getStoneBlock().getDescriptionId());
				Item item = BuiltInRegistries.ITEM.getValue(Rocks.modLoc(type.getFragment().getName()));
				add(item, splitterBaseTranslation + splitterWord);
			}
		}
		for (StickType type : StickType.values()) {
			Block block = BuiltInRegistries.BLOCK.getValue(Rocks.modLoc(type.getName() + "_stick"));
			if (type.getBaseBlock() instanceof Block logBlock &&
					BuiltInRegistries.BLOCK.getValue(ResourceLocation.withDefaultNamespace(type.getName() + "_planks")) instanceof Block plankBlock &&
					BuiltInRegistries.BLOCK.getValue(ResourceLocation.withDefaultNamespace(type.getName() + "_stairs")) instanceof Block stairBlock) {
				String logTranslation = I18n.get(logBlock.getDescriptionId());
				String plankTranslation = I18n.get(plankBlock.getDescriptionId());
				String stairTranslation = I18n.get(stairBlock.getDescriptionId());

				add(block, getCommonString(getCommonString(logTranslation, plankTranslation), getCommonString(plankTranslation, stairTranslation)) + stickWord);
			}
		}
	}

	public String getCommonString(String first, String second) {
		// To get the pure wood set name, we compare the names of the log and plank blocks and only keep the common part
		// Kinda cursed, but hey – it works :)
		StringBuilder commonTranslation = new StringBuilder();
		for (String subFirst : first.split(" ")) {
			for (String subSecond : second.split(" ")) {
				String commonPart = "";
				if (!second.contains(" ")) { // This is often the case in German
					for (char c : subFirst.toCharArray()) {
						String temp = commonPart + c;
						if (subSecond.startsWith(temp)) commonPart = temp;
						else break;
					}
				} else if (subFirst.equals(subSecond)) commonPart = subSecond; // This is common in English

				if (!commonPart.isEmpty()) {
					commonTranslation.append(commonPart).append(" ");
					break;
				}
			}
		}
		return commonTranslation.substring(0, commonTranslation.length() - 1);
	}
}
