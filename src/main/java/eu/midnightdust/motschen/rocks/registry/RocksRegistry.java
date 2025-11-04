package eu.midnightdust.motschen.rocks.registry;

import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.block.NetherGeyser;
import eu.midnightdust.motschen.rocks.block.OverworldGeyser;
import eu.midnightdust.motschen.rocks.block.Pinecone;
import eu.midnightdust.motschen.rocks.block.Rock;
import eu.midnightdust.motschen.rocks.block.Seashell;
import eu.midnightdust.motschen.rocks.block.Starfish;
import eu.midnightdust.motschen.rocks.block.Stick;
import eu.midnightdust.motschen.rocks.util.RockType;
import eu.midnightdust.motschen.rocks.util.StickType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class RocksRegistry {
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Rocks.MOD_ID);
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Rocks.MOD_ID);
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Rocks.MOD_ID);

	//Blocks
	public static final DeferredBlock<Rock> ROCK = registerRock("rock", RockType.STONE);
	public static final DeferredBlock<Rock> GRANITE_ROCK = registerRock("granite_rock", RockType.GRANITE);
	public static final DeferredBlock<Rock> DIORITE_ROCK = registerRock("diorite_rock", RockType.DIORITE);
	public static final DeferredBlock<Rock> ANDESITE_ROCK = registerRock("andesite_rock", RockType.ANDESITE);
	public static final DeferredBlock<Rock> SAND_ROCK = registerRock("sand_rock", RockType.SANDSTONE);
	public static final DeferredBlock<Rock> RED_SAND_ROCK = registerRock("red_sand_rock", RockType.RED_SANDSTONE);
	public static final DeferredBlock<Rock> GRAVEL_ROCK = registerRock("gravel_rock", RockType.GRAVEL);
	public static final DeferredBlock<Rock> END_STONE_ROCK = registerRock("end_stone_rock", RockType.END_STONE);
	public static final DeferredBlock<Rock> NETHERRACK_ROCK = registerRock("netherrack_rock", RockType.NETHERRACK);
	public static final DeferredBlock<Rock> SOUL_SOIL_ROCK = registerRock("soul_soil_rock", RockType.SOUL_SOIL);
	public static final DeferredBlock<Rock> ICE_ROCK = registerRock("ice_rock", RockType.ICE);

	public static final DeferredBlock<Stick> OAK_STICK = registerStick("oak_stick", StickType.OAK);
	public static final DeferredBlock<Stick> SPRUCE_STICK = registerStick("spruce_stick", StickType.SPRUCE);
	public static final DeferredBlock<Stick> BIRCH_STICK = registerStick("birch_stick", StickType.BIRCH);
	public static final DeferredBlock<Stick> ACACIA_STICK = registerStick("acacia_stick", StickType.ACACIA);
	public static final DeferredBlock<Stick> JUNGLE_STICK = registerStick("jungle_stick", StickType.JUNGLE);
	public static final DeferredBlock<Stick> DARK_OAK_STICK = registerStick("dark_oak_stick", StickType.DARK_OAK);
	public static final DeferredBlock<Stick> MANGROVE_STICK = registerStick("mangrove_stick", StickType.MANGROVE);
	public static final DeferredBlock<Stick> CHERRY_STICK = registerStick("cherry_stick", StickType.CHERRY);
	public static final DeferredBlock<Stick> BAMBOO_STICK = registerStick("bamboo_stick", StickType.BAMBOO);
	public static final DeferredBlock<Stick> PALE_OAK_STICK = registerStick("pale_oak_stick", StickType.PALE_OAK);
	public static final DeferredBlock<Stick> CRIMSON_STICK = registerStick("crimson_stick", StickType.CRIMSON);
	public static final DeferredBlock<Stick> WARPED_STICK = registerStick("warped_stick", StickType.WARPED);

	public static final DeferredBlock<Pinecone> PINECONE = registerBlockWithItem("pinecone", Pinecone::new, () -> Properties.ofFullCopy(Blocks.POPPY));
	public static final DeferredBlock<Seashell> SEASHELL = registerBlockWithItem("seashell", Seashell::new, () -> Properties.ofFullCopy(Blocks.POPPY));
	public static final DeferredBlock<Starfish> STARFISH = registerBlockWithItem("starfish", Starfish::new, () -> Properties.ofFullCopy(Blocks.POPPY));
	public static final DeferredBlock<OverworldGeyser> GEYSER = registerBlockWithItem("geyser", OverworldGeyser::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
	public static final DeferredBlock<NetherGeyser> NETHER_GEYSER = registerBlockWithItem("nether_geyser", NetherGeyser::new, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));

	public static DeferredBlock<Rock> registerRock(String name, RockType type) {
		DeferredBlock<Rock> block = registerBlockWithItem(name, Rock::new, () -> Properties.ofFullCopy(Blocks.POPPY));
		Rocks.rocksByType.put(type, block);
		return block;
	}

	public static DeferredBlock<Stick> registerStick(String name, StickType type) {
		DeferredBlock<Stick> block = registerBlockWithItem(name, Stick::new, () -> Properties.ofFullCopy(Blocks.POPPY));
		Rocks.sticksByType.put(type, block);
		return block;
	}

	public static <B extends Block> DeferredBlock<B> registerBlockWithItem(String name, Function<Properties, ? extends B> supplier, Supplier<Properties> propertiesSupplier) {
		DeferredBlock<B> block = BLOCKS.registerBlock(name, supplier, propertiesSupplier);
		ITEMS.registerSimpleBlockItem(block);
		return block;
	}

	public static final DeferredItem<Item> COBBLESTONE_SPLITTER = registerSplitter("cobblestone_splitter", RockType.STONE);
	public static final DeferredItem<Item> GRANITE_SPLITTER = registerSplitter("granite_splitter", RockType.GRANITE);
	public static final DeferredItem<Item> DIORITE_SPLITTER = registerSplitter("diorite_splitter", RockType.DIORITE);
	public static final DeferredItem<Item> ANDESITE_SPLITTER = registerSplitter("andesite_splitter", RockType.ANDESITE);
	public static final DeferredItem<Item> SANDSTONE_SPLITTER = registerSplitter("sandstone_splitter", RockType.SANDSTONE);
	public static final DeferredItem<Item> RED_SANDSTONE_SPLITTER = registerSplitter("red_sandstone_splitter", RockType.RED_SANDSTONE);
	public static final DeferredItem<Item> END_STONE_SPLITTER = registerSplitter("end_stone_splitter", RockType.END_STONE);
	public static final DeferredItem<Item> NETHERRACK_SPLITTER = registerSplitter("netherrack_splitter", RockType.NETHERRACK);
	public static final DeferredItem<Item> SOUL_SOIL_SPLITTER = registerSplitter("soul_soil_splitter", RockType.SOUL_SOIL);
	public static final DeferredItem<Item> ICE_SPLITTER = registerSplitter("ice_splitter", RockType.ICE);

	public static DeferredItem<Item> registerSplitter(String name, RockType type) {
		DeferredItem<Item> item = ITEMS.registerSimpleItem(name);
		Rocks.splittersByType.put(type, item);
		return item;
	}
	public static final Supplier<CreativeModeTab> ROCKS_TAB = CREATIVE_MODE_TABS.register("tab", () -> CreativeModeTab.builder()
			.icon(() -> new ItemStack(RocksRegistry.ROCK.get()))
			.title(Component.translatable("itemGroup.rocks"))
			.displayItems((displayParameters, output) -> {
				List<ItemStack> stacks = RocksRegistry.ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
				output.acceptAll(stacks);
			}).build());
}
