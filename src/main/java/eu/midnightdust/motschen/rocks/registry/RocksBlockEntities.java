package eu.midnightdust.motschen.rocks.registry;

import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.block.blockentity.NetherGeyserBlockEntity;
import eu.midnightdust.motschen.rocks.block.blockentity.OverworldGeyserBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RocksBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Rocks.MOD_ID);

	public static final RegistryObject<BlockEntityType<OverworldGeyserBlockEntity>> OVERWORLD_GEYSER_BE = BLOCK_ENTITY_TYPES.register("overworld_geyser_blockentity",
			() -> BlockEntityType.Builder.of(OverworldGeyserBlockEntity::new,
					RocksRegistry.GEYSER.get()).build(null));

	public static final RegistryObject<BlockEntityType<NetherGeyserBlockEntity>> NETHER_GEYSER_BE = BLOCK_ENTITY_TYPES.register("nether_geyser_blockentity",
			() -> BlockEntityType.Builder.of(NetherGeyserBlockEntity::new,
					RocksRegistry.NETHER_GEYSER.get()).build(null));
}
