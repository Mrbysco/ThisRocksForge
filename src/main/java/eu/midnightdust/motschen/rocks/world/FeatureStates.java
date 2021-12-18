package eu.midnightdust.motschen.rocks.world;

import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.blockstates.SeashellVariation;
import eu.midnightdust.motschen.rocks.blockstates.StarfishVariation;
import eu.midnightdust.motschen.rocks.registry.RocksRegistry;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;

public class FeatureStates {
	protected static final WeightedStateProvider StarfishStates = new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
			.add(RocksRegistry.STARFISH.get().defaultBlockState().setValue(Rocks.STARFISH_VARIATION, StarfishVariation.RED).setValue(BlockStateProperties.WATERLOGGED, true), 6)
			.add(RocksRegistry.STARFISH.get().defaultBlockState().setValue(Rocks.STARFISH_VARIATION,StarfishVariation.PINK).setValue(BlockStateProperties.WATERLOGGED, true), 7)
			.add(RocksRegistry.STARFISH.get().defaultBlockState().setValue(Rocks.STARFISH_VARIATION,StarfishVariation.ORANGE).setValue(BlockStateProperties.WATERLOGGED, true), 2).build());

	protected static final WeightedStateProvider SeashellStates = new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
			.add(RocksRegistry.SEASHELL.get().defaultBlockState().setValue(Rocks.SEASHELL_VARIATION, SeashellVariation.YELLOW).setValue(BlockStateProperties.WATERLOGGED, true), 7)
			.add(RocksRegistry.SEASHELL.get().defaultBlockState().setValue(Rocks.SEASHELL_VARIATION,SeashellVariation.PINK).setValue(BlockStateProperties.WATERLOGGED, true), 2)
			.add(RocksRegistry.SEASHELL.get().defaultBlockState().setValue(Rocks.SEASHELL_VARIATION,SeashellVariation.WHITE).setValue(BlockStateProperties.WATERLOGGED, true), 6).build());

	protected static final WeightedStateProvider GeyserStates = new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
			.add(RocksRegistry.GEYSER.get().defaultBlockState().setValue(BlockStateProperties.SNOWY, true), 1).build());
}
