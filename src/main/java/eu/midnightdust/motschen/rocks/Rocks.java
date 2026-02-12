package eu.midnightdust.motschen.rocks;

import com.mojang.logging.LogUtils;
import eu.midnightdust.motschen.rocks.block.Rock;
import eu.midnightdust.motschen.rocks.block.Stick;
import eu.midnightdust.motschen.rocks.blockstates.RockVariation;
import eu.midnightdust.motschen.rocks.blockstates.SeashellVariation;
import eu.midnightdust.motschen.rocks.blockstates.StarfishVariation;
import eu.midnightdust.motschen.rocks.blockstates.StickVariation;
import eu.midnightdust.motschen.rocks.client.ClientHandler;
import eu.midnightdust.motschen.rocks.config.RocksConfig;
import eu.midnightdust.motschen.rocks.registry.RocksBlockEntities;
import eu.midnightdust.motschen.rocks.registry.RocksRegistry;
import eu.midnightdust.motschen.rocks.util.RockType;
import eu.midnightdust.motschen.rocks.util.StickType;
import eu.midnightdust.motschen.rocks.world.FeatureRegistry;
import eu.midnightdust.motschen.rocks.world.WorldGenHandler;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

@Mod(Rocks.MOD_ID)
public class Rocks {
	public static final String MOD_ID = "rocks";
	public static final Logger LOGGER = LogUtils.getLogger();

	public static final EnumProperty<RockVariation> ROCK_VARIATION = EnumProperty.create("variation", RockVariation.class);
	public static final EnumProperty<StickVariation> STICK_VARIATION = EnumProperty.create("variation", StickVariation.class);
	public static final EnumProperty<SeashellVariation> SEASHELL_VARIATION = EnumProperty.create("variation", SeashellVariation.class);
	public static final EnumProperty<StarfishVariation> STARFISH_VARIATION = EnumProperty.create("variation", StarfishVariation.class);

	public static Map<RockType, DeferredBlock<Rock>> rocksByType = new HashMap<>();
	public static Map<StickType, DeferredBlock<Stick>> sticksByType = new HashMap<>();
	public static Map<RockType, DeferredItem<Item>> splittersByType = new HashMap<>();

	public Rocks(IEventBus eventBus, ModContainer container, Dist dist) {
		container.registerConfig(ModConfig.Type.COMMON, RocksConfig.commonSpec);
		eventBus.register(RocksConfig.class);

		RocksRegistry.BLOCKS.register(eventBus);
		RocksRegistry.ITEMS.register(eventBus);
		RocksRegistry.CREATIVE_MODE_TABS.register(eventBus);
		RocksBlockEntities.BLOCK_ENTITY_TYPES.register(eventBus);
		FeatureRegistry.FEATURES.register(eventBus);
		WorldGenHandler.BIOME_MODIFIER_SERIALIZERS.register(eventBus);

		if (dist.isClient()) {
			eventBus.addListener(ClientHandler::onClientSetup);
		}
	}

	public static Identifier modLoc(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
