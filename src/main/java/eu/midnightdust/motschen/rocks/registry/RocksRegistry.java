package eu.midnightdust.motschen.rocks.registry;

import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.block.NetherGeyser;
import eu.midnightdust.motschen.rocks.block.OverworldGeyser;
import eu.midnightdust.motschen.rocks.block.Pinecone;
import eu.midnightdust.motschen.rocks.block.Rock;
import eu.midnightdust.motschen.rocks.block.Seashell;
import eu.midnightdust.motschen.rocks.block.Starfish;
import eu.midnightdust.motschen.rocks.block.Stick;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

public class RocksRegistry {
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Rocks.MOD_ID);
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Rocks.MOD_ID);
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Rocks.MOD_ID);

	//Blocks
	public static final DeferredBlock<Block> ROCK = BLOCKS.register("rock", () -> new Rock());
	public static final DeferredBlock<Block> GRANITE_ROCK = BLOCKS.register("granite_rock", () -> new Rock());
	public static final DeferredBlock<Block> DIORITE_ROCK = BLOCKS.register("diorite_rock", () -> new Rock());
	public static final DeferredBlock<Block> ANDESITE_ROCK = BLOCKS.register("andesite_rock", () -> new Rock());
	public static final DeferredBlock<Block> SAND_ROCK = BLOCKS.register("sand_rock", () -> new Rock());
	public static final DeferredBlock<Block> RED_SAND_ROCK = BLOCKS.register("red_sand_rock", () -> new Rock());
	public static final DeferredBlock<Block> GRAVEL_ROCK = BLOCKS.register("gravel_rock", () -> new Rock());
	public static final DeferredBlock<Block> END_STONE_ROCK = BLOCKS.register("end_stone_rock", () -> new Rock());
	public static final DeferredBlock<Block> NETHERRACK_ROCK = BLOCKS.register("netherrack_rock", () -> new Rock());
	public static final DeferredBlock<Block> SOUL_SOIL_ROCK = BLOCKS.register("soul_soil_rock", () -> new Rock());

	public static final DeferredBlock<Block> OAK_STICK = BLOCKS.register("oak_stick", () -> new Stick());
	public static final DeferredBlock<Block> SPRUCE_STICK = BLOCKS.register("spruce_stick", () -> new Stick());
	public static final DeferredBlock<Block> BIRCH_STICK = BLOCKS.register("birch_stick", () -> new Stick());
	public static final DeferredBlock<Block> ACACIA_STICK = BLOCKS.register("acacia_stick", () -> new Stick());
	public static final DeferredBlock<Block> JUNGLE_STICK = BLOCKS.register("jungle_stick", () -> new Stick());
	public static final DeferredBlock<Block> DARK_OAK_STICK = BLOCKS.register("dark_oak_stick", () -> new Stick());
	public static final DeferredBlock<Block> MANGROVE_STICK = BLOCKS.register("mangrove_stick", () -> new Stick());
	public static final DeferredBlock<Block> CHERRY_STICK = BLOCKS.register("cherry_stick", () -> new Stick());
	public static final DeferredBlock<Block> BAMBOO_STICK = BLOCKS.register("bamboo_stick", () -> new Stick());
	public static final DeferredBlock<Block> CRIMSON_STICK = BLOCKS.register("crimson_stick", () -> new Stick());
	public static final DeferredBlock<Block> WARPED_STICK = BLOCKS.register("warped_stick", () -> new Stick());

	public static final DeferredBlock<Block> PINECONE = BLOCKS.register("pinecone", () -> new Pinecone());
	public static final DeferredBlock<Block> SEASHELL = BLOCKS.register("seashell", () -> new Seashell());
	public static final DeferredBlock<Block> STARFISH = BLOCKS.register("starfish", () -> new Starfish());
	public static final DeferredBlock<Block> GEYSER = BLOCKS.register("geyser", () -> new OverworldGeyser());
	public static final DeferredBlock<Block> NETHER_GEYSER = BLOCKS.register("nether_geyser", () -> new NetherGeyser());

	//Items
	public static final DeferredItem<BlockItem> ROCK_ITEM = ITEMS.registerSimpleBlockItem(ROCK);
	public static final DeferredItem<BlockItem> GRANITE_ROCK_ITEM = ITEMS.registerSimpleBlockItem(GRANITE_ROCK);
	public static final DeferredItem<BlockItem> DIORITE_ROCK_ITEM = ITEMS.registerSimpleBlockItem(DIORITE_ROCK);
	public static final DeferredItem<BlockItem> ANDESITE_ROCK_ITEM = ITEMS.registerSimpleBlockItem(ANDESITE_ROCK);
	public static final DeferredItem<BlockItem> SAND_ROCK_ITEM = ITEMS.registerSimpleBlockItem(SAND_ROCK);
	public static final DeferredItem<BlockItem> RED_SAND_ROCK_ITEM = ITEMS.registerSimpleBlockItem(RED_SAND_ROCK);
	public static final DeferredItem<BlockItem> GRAVEL_ROCK_ITEM = ITEMS.registerSimpleBlockItem(GRAVEL_ROCK);
	public static final DeferredItem<BlockItem> END_STONE_ROCK_ITEM = ITEMS.registerSimpleBlockItem(END_STONE_ROCK);
	public static final DeferredItem<BlockItem> NETHERRACK_ROCK_ITEM = ITEMS.registerSimpleBlockItem(NETHERRACK_ROCK);
	public static final DeferredItem<BlockItem> SOUL_SOIL_ROCK_ITEM = ITEMS.registerSimpleBlockItem(SOUL_SOIL_ROCK);

	public static final DeferredItem<BlockItem> OAK_STICK_ITEM = ITEMS.registerSimpleBlockItem(OAK_STICK);
	public static final DeferredItem<BlockItem> SPRUCE_STICK_ITEM = ITEMS.registerSimpleBlockItem(SPRUCE_STICK);
	public static final DeferredItem<BlockItem> BIRCH_STICK_ITEM = ITEMS.registerSimpleBlockItem(BIRCH_STICK);
	public static final DeferredItem<BlockItem> ACACIA_STICK_ITEM = ITEMS.registerSimpleBlockItem(ACACIA_STICK);
	public static final DeferredItem<BlockItem> JUNGLE_STICK_ITEM = ITEMS.registerSimpleBlockItem(JUNGLE_STICK);
	public static final DeferredItem<BlockItem> DARK_OAK_STICK_ITEM = ITEMS.registerSimpleBlockItem(DARK_OAK_STICK);
	public static final DeferredItem<BlockItem> MANGROVE_STICK_ITEM = ITEMS.registerSimpleBlockItem(MANGROVE_STICK);
	public static final DeferredItem<BlockItem> CHERRY_STICK_ITEM = ITEMS.registerSimpleBlockItem(CHERRY_STICK);
	public static final DeferredItem<BlockItem> BAMBOO_STICK_ITEM = ITEMS.registerSimpleBlockItem(BAMBOO_STICK);
	public static final DeferredItem<BlockItem> CRIMSON_STICK_ITEM = ITEMS.registerSimpleBlockItem(CRIMSON_STICK);
	public static final DeferredItem<BlockItem> WARPED_STICK_ITEM = ITEMS.registerSimpleBlockItem(WARPED_STICK);

	public static final DeferredItem<BlockItem> PINECONE_ITEM = ITEMS.registerSimpleBlockItem(PINECONE);
	public static final DeferredItem<BlockItem> SEASHELL_ITEM = ITEMS.registerSimpleBlockItem(SEASHELL);
	public static final DeferredItem<BlockItem> STARFISH_ITEM = ITEMS.registerSimpleBlockItem(STARFISH);
	public static final DeferredItem<BlockItem> GEYSER_ITEM = ITEMS.registerSimpleBlockItem(GEYSER);
	public static final DeferredItem<BlockItem> NETHER_GEYSER_ITEM = ITEMS.registerSimpleBlockItem(NETHER_GEYSER);

	public static final DeferredItem<Item> COBBLESTONE_SPLITTER = ITEMS.registerSimpleItem("cobblestone_splitter");
	public static final DeferredItem<Item> GRANITE_SPLITTER = ITEMS.registerSimpleItem("granite_splitter");
	public static final DeferredItem<Item> DIORITE_SPLITTER = ITEMS.registerSimpleItem("diorite_splitter");
	public static final DeferredItem<Item> ANDESITE_SPLITTER = ITEMS.registerSimpleItem("andesite_splitter");
	public static final DeferredItem<Item> SANDSTONE_SPLITTER = ITEMS.registerSimpleItem("sandstone_splitter");
	public static final DeferredItem<Item> RED_SANDSTONE_SPLITTER = ITEMS.registerSimpleItem("red_sandstone_splitter");
	public static final DeferredItem<Item> END_STONE_SPLITTER = ITEMS.registerSimpleItem("end_stone_splitter");
	public static final DeferredItem<Item> NETHERRACK_SPLITTER = ITEMS.registerSimpleItem("netherrack_splitter");
	public static final DeferredItem<Item> SOUL_SOIL_SPLITTER = ITEMS.registerSimpleItem("soul_soil_splitter");

	public static final Supplier<CreativeModeTab> ROCKS_TAB = CREATIVE_MODE_TABS.register("tab", () -> CreativeModeTab.builder()
			.icon(() -> new ItemStack(RocksRegistry.ROCK.get()))
			.title(Component.translatable("itemGroup.rocks"))
			.displayItems((displayParameters, output) -> {
				List<ItemStack> stacks = RocksRegistry.ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
				output.acceptAll(stacks);
			}).build());
}
