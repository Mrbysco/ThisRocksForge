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
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class RocksRegistry {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Rocks.MOD_ID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Rocks.MOD_ID);
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Rocks.MOD_ID);

	//Blocks
	public static final RegistryObject<Block> ROCK = BLOCKS.register("rock", () -> new Rock());
	public static final RegistryObject<Block> GRANITE_ROCK = BLOCKS.register("granite_rock", () -> new Rock());
	public static final RegistryObject<Block> DIORITE_ROCK = BLOCKS.register("diorite_rock", () -> new Rock());
	public static final RegistryObject<Block> ANDESITE_ROCK = BLOCKS.register("andesite_rock", () -> new Rock());
	public static final RegistryObject<Block> SAND_ROCK = BLOCKS.register("sand_rock", () -> new Rock());
	public static final RegistryObject<Block> RED_SAND_ROCK = BLOCKS.register("red_sand_rock", () -> new Rock());
	public static final RegistryObject<Block> GRAVEL_ROCK = BLOCKS.register("gravel_rock", () -> new Rock());
	public static final RegistryObject<Block> END_STONE_ROCK = BLOCKS.register("end_stone_rock", () -> new Rock());
	public static final RegistryObject<Block> NETHERRACK_ROCK = BLOCKS.register("netherrack_rock", () -> new Rock());
	public static final RegistryObject<Block> SOUL_SOIL_ROCK = BLOCKS.register("soul_soil_rock", () -> new Rock());

	public static final RegistryObject<Block> OAK_STICK = BLOCKS.register("oak_stick", () -> new Stick());
	public static final RegistryObject<Block> SPRUCE_STICK = BLOCKS.register("spruce_stick", () -> new Stick());
	public static final RegistryObject<Block> BIRCH_STICK = BLOCKS.register("birch_stick", () -> new Stick());
	public static final RegistryObject<Block> ACACIA_STICK = BLOCKS.register("acacia_stick", () -> new Stick());
	public static final RegistryObject<Block> JUNGLE_STICK = BLOCKS.register("jungle_stick", () -> new Stick());
	public static final RegistryObject<Block> DARK_OAK_STICK = BLOCKS.register("dark_oak_stick", () -> new Stick());
	public static final RegistryObject<Block> MANGROVE_STICK = BLOCKS.register("mangrove_stick", () -> new Stick());
	public static final RegistryObject<Block> CHERRY_STICK = BLOCKS.register("cherry_stick", () -> new Stick());
	public static final RegistryObject<Block> BAMBOO_STICK = BLOCKS.register("bamboo_stick", () -> new Stick());
	public static final RegistryObject<Block> CRIMSON_STICK = BLOCKS.register("crimson_stick", () -> new Stick());
	public static final RegistryObject<Block> WARPED_STICK = BLOCKS.register("warped_stick", () -> new Stick());

	public static final RegistryObject<Block> PINECONE = BLOCKS.register("pinecone", () -> new Pinecone());
	public static final RegistryObject<Block> SEASHELL = BLOCKS.register("seashell", () -> new Seashell());
	public static final RegistryObject<Block> STARFISH = BLOCKS.register("starfish", () -> new Starfish());
	public static final RegistryObject<Block> GEYSER = BLOCKS.register("geyser", () -> new OverworldGeyser());
	public static final RegistryObject<Block> NETHER_GEYSER = BLOCKS.register("nether_geyser", () -> new NetherGeyser());

	//Items
	public static final RegistryObject<Item> ROCK_ITEM = ITEMS.register("rock", () -> new BlockItem(ROCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> GRANITE_ROCK_ITEM = ITEMS.register("granite_rock", () -> new BlockItem(GRANITE_ROCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> DIORITE_ROCK_ITEM = ITEMS.register("diorite_rock", () -> new BlockItem(DIORITE_ROCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> ANDESITE_ROCK_ITEM = ITEMS.register("andesite_rock", () -> new BlockItem(ANDESITE_ROCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> SAND_ROCK_ITEM = ITEMS.register("sand_rock", () -> new BlockItem(SAND_ROCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> RED_SAND_ROCK_ITEM = ITEMS.register("red_sand_rock", () -> new BlockItem(RED_SAND_ROCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> GRAVEL_ROCK_ITEM = ITEMS.register("gravel_rock", () -> new BlockItem(GRAVEL_ROCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> END_STONE_ROCK_ITEM = ITEMS.register("end_stone_rock", () -> new BlockItem(END_STONE_ROCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> NETHERRACK_ROCK_ITEM = ITEMS.register("netherrack_rock", () -> new BlockItem(NETHERRACK_ROCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> SOUL_SOIL_ROCK_ITEM = ITEMS.register("soul_soil_rock", () -> new BlockItem(SOUL_SOIL_ROCK.get(), new Item.Properties()));

	public static final RegistryObject<Item> OAK_STICK_ITEM = ITEMS.register("oak_stick", () -> new BlockItem(OAK_STICK.get(), new Item.Properties()));
	public static final RegistryObject<Item> SPRUCE_STICK_ITEM = ITEMS.register("spruce_stick", () -> new BlockItem(SPRUCE_STICK.get(), new Item.Properties()));
	public static final RegistryObject<Item> BIRCH_STICK_ITEM = ITEMS.register("birch_stick", () -> new BlockItem(BIRCH_STICK.get(), new Item.Properties()));
	public static final RegistryObject<Item> ACACIA_STICK_ITEM = ITEMS.register("acacia_stick", () -> new BlockItem(ACACIA_STICK.get(), new Item.Properties()));
	public static final RegistryObject<Item> JUNGLE_STICK_ITEM = ITEMS.register("jungle_stick", () -> new BlockItem(JUNGLE_STICK.get(), new Item.Properties()));
	public static final RegistryObject<Item> DARK_OAK_STICK_ITEM = ITEMS.register("dark_oak_stick", () -> new BlockItem(DARK_OAK_STICK.get(), new Item.Properties()));
	public static final RegistryObject<Item> MANGROVE_STICK_ITEM = ITEMS.register("mangrove_stick", () -> new BlockItem(MANGROVE_STICK.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHERRY_STICK_ITEM = ITEMS.register("cherry_stick", () -> new BlockItem(CHERRY_STICK.get(), new Item.Properties()));
	public static final RegistryObject<Item> BAMBOO_STICK_ITEM = ITEMS.register("bamboo_stick", () -> new BlockItem(BAMBOO_STICK.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRIMSON_STICK_ITEM = ITEMS.register("crimson_stick", () -> new BlockItem(CRIMSON_STICK.get(), new Item.Properties()));
	public static final RegistryObject<Item> WARPED_STICK_ITEM = ITEMS.register("warped_stick", () -> new BlockItem(WARPED_STICK.get(), new Item.Properties()));

	public static final RegistryObject<Item> PINECONE_ITEM = ITEMS.register("pinecone", () -> new BlockItem(PINECONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> SEASHELL_ITEM = ITEMS.register("seashell", () -> new BlockItem(SEASHELL.get(), new Item.Properties()));
	public static final RegistryObject<Item> STARFISH_ITEM = ITEMS.register("starfish", () -> new BlockItem(STARFISH.get(), new Item.Properties()));
	public static final RegistryObject<Item> GEYSER_ITEM = ITEMS.register("geyser", () -> new BlockItem(GEYSER.get(), new Item.Properties()));
	public static final RegistryObject<Item> NETHER_GEYSER_ITEM = ITEMS.register("nether_geyser", () -> new BlockItem(NETHER_GEYSER.get(), new Item.Properties()));

	public static final RegistryObject<Item> COBBLESTONE_SPLITTER = ITEMS.register("cobblestone_splitter", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> GRANITE_SPLITTER = ITEMS.register("granite_splitter", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> DIORITE_SPLITTER = ITEMS.register("diorite_splitter", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> ANDESITE_SPLITTER = ITEMS.register("andesite_splitter", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> SANDSTONE_SPLITTER = ITEMS.register("sandstone_splitter", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> RED_SANDSTONE_SPLITTER = ITEMS.register("red_sandstone_splitter", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> END_STONE_SPLITTER = ITEMS.register("end_stone_splitter", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> NETHERRACK_SPLITTER = ITEMS.register("netherrack_splitter", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> SOUL_SOIL_SPLITTER = ITEMS.register("soul_soil_splitter", () -> new Item(new Item.Properties()));

	public static final RegistryObject<CreativeModeTab> ROCKS_TAB = CREATIVE_MODE_TABS.register("tab", () -> CreativeModeTab.builder()
			.icon(() -> new ItemStack(RocksRegistry.ROCK.get()))
			.title(Component.translatable("itemGroup.rocks"))
			.displayItems((displayParameters, output) -> {
				List<ItemStack> stacks = RocksRegistry.ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
				output.acceptAll(stacks);
			}).build());
}
