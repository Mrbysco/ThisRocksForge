package eu.midnightdust.motschen.rocks.datagen;

import eu.midnightdust.motschen.rocks.datagen.RocksTagProvider.RocksBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber
public class RocksDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent.Client event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		generator.addProvider(true, new RocksLanguageProvider(packOutput));
		generator.addProvider(true, new RocksModelProvider(packOutput));

		generator.addProvider(true, new RocksLootTableProvider(packOutput, lookupProvider));
		generator.addProvider(true, new RocksRecipeProvider.Runner(packOutput, lookupProvider));
		generator.addProvider(true, new RocksBiomes(packOutput, lookupProvider));

		generator.addProvider(true, new RocksDatagenProvider(
				packOutput,
				event.getLookupProvider(),
				Set.of("rocks")
		));
	}
}
