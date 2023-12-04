package eu.midnightdust.motschen.rocks.config;

import eu.midnightdust.motschen.rocks.Rocks;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

public class RocksConfig {

	public static class Common {
		public final ModConfigSpec.BooleanValue geyserLevitation;
		public final ModConfigSpec.BooleanValue netherGeyserDamage;

		Common(ModConfigSpec.Builder builder) {
			builder.comment("General settings")
					.push("General");

			geyserLevitation = builder
					.comment("Setting this to false disables the levitation effect from Geysers (Default: true)")
					.define("geyserLevitation", true);

			netherGeyserDamage = builder
					.comment("Setting this to false disables the damage from Nether Geysers (Default: true)")
					.define("netherGeyserDamage", true);

			builder.pop();
		}
	}

	public static final ModConfigSpec commonSpec;
	public static final Common COMMON;

	static {
		final Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Common::new);
		commonSpec = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	@SubscribeEvent
	public static void onLoad(final ModConfigEvent.Loading configEvent) {
		Rocks.LOGGER.debug("Loaded This Rocks' config file {}", configEvent.getConfig().getFileName());
	}

	@SubscribeEvent
	public static void onFileChange(final ModConfigEvent.Reloading configEvent) {
		Rocks.LOGGER.warn("This Rock' config just got changed on the file system!");
	}
}
