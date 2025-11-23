package eu.midnightdust.motschen.rocks.datagen;

import eu.midnightdust.motschen.rocks.world.RocksBiomeModifiers;
import eu.midnightdust.motschen.rocks.world.configured_feature.MiscFeatures;
import eu.midnightdust.motschen.rocks.world.configured_feature.NetherFeatures;
import eu.midnightdust.motschen.rocks.world.configured_feature.RockFeatures;
import eu.midnightdust.motschen.rocks.world.configured_feature.StickFeatures;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class RocksDatapackProvider extends DatapackBuiltinEntriesProvider {
	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
			.add(Registries.CONFIGURED_FEATURE, context -> {
				MiscFeatures.configuredBootstrap(context);
				NetherFeatures.configuredBootstrap(context);
				RockFeatures.configuredBootstrap(context);
				StickFeatures.configuredBootstrap(context);
			})
			.add(Registries.PLACED_FEATURE, context -> {
				MiscFeatures.placedBootstrap(context);
				NetherFeatures.placedBootstrap(context);
				RockFeatures.placedBootstrap(context);
				StickFeatures.placedBootstrap(context);
			})
			.add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, RocksBiomeModifiers::bootstrap);

	public RocksDatapackProvider(PackOutput output, CompletableFuture<Provider> registries, Set<String> modIds) {
		super(output, registries, BUILDER, modIds);
	}
}
