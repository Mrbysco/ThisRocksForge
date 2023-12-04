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
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

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

		RocksRegistry.BLOCKS.register(eventBus);
		RocksRegistry.ITEMS.register(eventBus);
		RocksRegistry.CREATIVE_MODE_TABS.register(eventBus);
		RocksBlockEntities.BLOCK_ENTITY_TYPES.register(eventBus);
		FeatureRegistry.FEATURES.register(eventBus);
		WorldGenHandler.BIOME_MODIFIER_SERIALIZERS.register(eventBus);

		if (FMLEnvironment.dist.isClient()) {
			eventBus.addListener(ClientHandler::onClientSetup);
		}
	}
}
