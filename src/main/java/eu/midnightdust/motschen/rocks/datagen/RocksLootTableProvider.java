package eu.midnightdust.motschen.rocks.datagen;

import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.registry.RocksRegistry;
import eu.midnightdust.motschen.rocks.util.RockType;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyBlockState;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class RocksLootTableProvider extends LootTableProvider {
	public RocksLootTableProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, Set.of(), List.of(
				new SubProviderEntry(RocksBlockLoot::new, LootContextParamSets.BLOCK)
		), lookupProvider);
	}

	private static class RocksBlockLoot extends BlockLootSubProvider {

		protected RocksBlockLoot(HolderLookup.Provider registries) {
			super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
		}

		@Override
		protected void generate() {
			Rocks.rocksByType.forEach((rockType, rock) ->
					this.addSilkTouchDrop(rock.get(), rockType != RockType.GRAVEL ? Rocks.splittersByType.get(rockType).asOptional().orElse(Items.EGG) : Items.FLINT));
			Rocks.sticksByType.forEach((stickType, stick) ->
					this.addSilkTouchDrop(stick.get(), Items.STICK));
			this.addSilkTouchDrop(RocksRegistry.PINECONE.get(), Items.SPRUCE_SAPLING);
			this.addSilkTouchOrRareDrop(RocksRegistry.SEASHELL.get(), Items.NAUTILUS_SHELL, 0.02f, 0.022222223f, 0.025f, 0.033333335f, 0.1f);
			this.addCopyStateDrop(RocksRegistry.STARFISH.get(), Rocks.STARFISH_VARIATION);

			this.add(RocksRegistry.GEYSER.get(), noDrop());
			this.add(RocksRegistry.NETHER_GEYSER.get(), noDrop());
		}

		public void addCopyStateDrop(Block block, Property<?>... properties) {
			var lootFunction = CopyBlockState.copyState(block);
			Arrays.stream(properties).forEach(lootFunction::copy);

			add(block, LootTable.lootTable().withPool(this.applyExplosionCondition(block,
					LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(block)
							.apply(lootFunction)))));
		}

		public void addSilkTouchDrop(Block block, Item alternative) {
			add(block, this.createSilkTouchDispatchTable(block, LootItem.lootTableItem(alternative)));
		}

		public void addSilkTouchOrRareDrop(Block block, Item alternative, float... chances) {
			HolderLookup.RegistryLookup<Enchantment> impl = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
			add(block, this.createSilkTouchDispatchTable(block, LootItem.lootTableItem(alternative)
					.when(BonusLevelTableCondition.bonusLevelFlatChance(impl.getOrThrow(Enchantments.FORTUNE), chances))));
		}

		@Override
		@NotNull
		protected Iterable<Block> getKnownBlocks() {
			return (Iterable<Block>) RocksRegistry.BLOCKS.getEntries().stream().map(holder -> (Block) holder.get())::iterator;
		}
	}
}
