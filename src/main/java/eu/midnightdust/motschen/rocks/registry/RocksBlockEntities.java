package eu.midnightdust.motschen.rocks.registry;

import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.block.blockentity.NetherGeyserBlockEntity;
import eu.midnightdust.motschen.rocks.block.blockentity.OverworldGeyserBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class RocksBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Rocks.MOD_ID);

	public static final Supplier<BlockEntityType<OverworldGeyserBlockEntity>> OVERWORLD_GEYSER_BE = BLOCK_ENTITY_TYPES.register("overworld_geyser_blockentity",
			() -> BlockEntityType.Builder.of(OverworldGeyserBlockEntity::new,
					RocksRegistry.GEYSER.get()).build(null));

	public static final Supplier<BlockEntityType<NetherGeyserBlockEntity>> NETHER_GEYSER_BE = BLOCK_ENTITY_TYPES.register("nether_geyser_blockentity",
			() -> BlockEntityType.Builder.of(NetherGeyserBlockEntity::new,
					RocksRegistry.NETHER_GEYSER.get()).build(null));
}
