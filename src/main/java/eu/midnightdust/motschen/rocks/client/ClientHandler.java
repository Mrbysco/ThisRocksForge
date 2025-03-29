package eu.midnightdust.motschen.rocks.client;

import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.blockstates.StarfishVariation;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.Objects;

public class ClientHandler {
	public static void onClientSetup(final FMLClientSetupEvent event) {
//		event.enqueueWork(() -> { TODO: TEST
//			ItemProperties.register(RocksRegistry.STARFISH_ITEM.get(),
//					ResourceLocation.withDefaultNamespace("red"), (stack, world, entity, seed) ->
//							matchesVariation(stack, StarfishVariation.RED));
//			ItemProperties.register(RocksRegistry.STARFISH_ITEM.get(),
//					ResourceLocation.withDefaultNamespace("pink"), (stack, world, entity, seed) ->
//							matchesVariation(stack, StarfishVariation.PINK));
//			ItemProperties.register(RocksRegistry.STARFISH_ITEM.get(),
//					ResourceLocation.withDefaultNamespace("orange"), (stack, world, entity, seed) ->
//							matchesVariation(stack, StarfishVariation.ORANGE));
//		});
	}

	private static Integer matchesVariation(ItemStack stack, StarfishVariation variation) {
		var blockStateData = stack.getComponents().get(DataComponents.BLOCK_STATE);
		if (blockStateData == null || blockStateData.isEmpty() || blockStateData.get(Rocks.ROCK_VARIATION) == null) return 0;
		return Objects.equals(blockStateData.get(Rocks.ROCK_VARIATION), variation) ? 1 : 0;
	}
}
