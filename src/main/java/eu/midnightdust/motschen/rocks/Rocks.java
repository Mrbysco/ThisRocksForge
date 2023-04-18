package eu.midnightdust.motschen.rocks;

import com.mojang.logging.LogUtils;
import eu.midnightdust.motschen.rocks.blockstates.RockVariation;
import eu.midnightdust.motschen.rocks.blockstates.SeashellVariation;
import eu.midnightdust.motschen.rocks.blockstates.StarfishVariation;
import eu.midnightdust.motschen.rocks.blockstates.StickVariation;
import eu.midnightdust.motschen.rocks.client.ClientHandler;
import eu.midnightdust.motschen.rocks.config.RocksConfig;
import eu.midnightdust.motschen.rocks.registry.RocksBlockEntities;
import eu.midnightdust.motschen.rocks.registry.RocksRegistry;
import eu.midnightdust.motschen.rocks.world.FeatureRegistry;
import eu.midnightdust.motschen.rocks.world.WorldGenHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.List;

@Mod(Rocks.MOD_ID)
public class Rocks {
	public static final String MOD_ID = "rocks";
	public static final Logger LOGGER = LogUtils.getLogger();

	public static final EnumProperty<RockVariation> ROCK_VARIATION = EnumProperty.create("variation", RockVariation.class);
	public static final EnumProperty<StickVariation> STICK_VARIATION = EnumProperty.create("variation", StickVariation.class);
	public static final EnumProperty<SeashellVariation> SEASHELL_VARIATION = EnumProperty.create("variation", SeashellVariation.class);
	public static final EnumProperty<StarfishVariation> STARFISH_VARIATION = EnumProperty.create("variation", StarfishVariation.class);

	public Rocks() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RocksConfig.commonSpec);
		eventBus.register(RocksConfig.class);

		eventBus.addListener(this::registerCreativeTabs);

		RocksRegistry.BLOCKS.register(eventBus);
		RocksRegistry.ITEMS.register(eventBus);
		RocksBlockEntities.BLOCK_ENTITY_TYPES.register(eventBus);
		FeatureRegistry.FEATURES.register(eventBus);
		WorldGenHandler.BIOME_MODIFIER_SERIALIZERS.register(eventBus);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			eventBus.addListener(ClientHandler::onClientSetup);
		});
	}

	private static CreativeModeTab ROCKS_GROUP;

	private void registerCreativeTabs(final CreativeModeTabEvent.Register event) {
		ROCKS_GROUP = event.registerCreativeModeTab(new ResourceLocation(MOD_ID, "tab"), builder ->
				builder.icon(() -> new ItemStack(RocksRegistry.ROCK.get()))
						.title(Component.translatable("itemGroup.rocks"))
						.displayItems((displayParameters, output) -> {
							List<ItemStack> stacks = RocksRegistry.ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
							output.acceptAll(stacks);
						}));
	}
}
