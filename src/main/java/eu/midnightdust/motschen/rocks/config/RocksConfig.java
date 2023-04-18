package eu.midnightdust.motschen.rocks.config;

import eu.midnightdust.motschen.rocks.Rocks;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

public class RocksConfig {

	public static class Common {
		public final ForgeConfigSpec.BooleanValue geyserLevitation;
		public final ForgeConfigSpec.BooleanValue netherGeyserDamage;

		Common(ForgeConfigSpec.Builder builder) {
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

	public static final ForgeConfigSpec commonSpec;
	public static final Common COMMON;

	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
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
