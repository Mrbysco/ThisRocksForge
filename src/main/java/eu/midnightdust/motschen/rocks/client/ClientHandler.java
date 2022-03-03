package eu.midnightdust.motschen.rocks.client;

import eu.midnightdust.motschen.rocks.registry.RocksRegistry;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientHandler {
	public static void onClientSetup(final FMLClientSetupEvent event) {
		ItemProperties.register(RocksRegistry.STARFISH_ITEM.get(),
				new ResourceLocation("red"), (stack, world, entity, seed) ->
						(stack.getTag() != null && stack.getTag().getString("variation").equals("red")) ? 1 : 0);
		ItemProperties.register(RocksRegistry.STARFISH_ITEM.get(),
				new ResourceLocation("pink"), (stack, world, entity, seed) ->
						(stack.getTag() != null && stack.getTag().getString("variation").equals("pink")) ? 1 : 0);
		ItemProperties.register(RocksRegistry.STARFISH_ITEM.get(),
				new ResourceLocation("orange"), (stack, world, entity, seed) ->
						(stack.getTag() != null && stack.getTag().getString("variation").equals("orange")) ? 1 : 0);
	}
}
