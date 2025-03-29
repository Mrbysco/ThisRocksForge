package eu.midnightdust.motschen.rocks.util;

import eu.midnightdust.motschen.rocks.Rocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class RegistryUtil {
	public static void register(BootstrapContext<ConfiguredFeature<?, ?>> context, String name, ConfiguredFeature<?, ?> feature) {
		context.register(ResourceKey.create(Registries.CONFIGURED_FEATURE, Rocks.modLoc(name)), feature);
	}

	public static void register(BootstrapContext<PlacedFeature> context, String name, PlacedFeature feature) {
		context.register(ResourceKey.create(Registries.PLACED_FEATURE, Rocks.modLoc(name)), feature);
	}
}
