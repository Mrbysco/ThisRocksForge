package eu.midnightdust.motschen.rocks.datagen;

import eu.midnightdust.motschen.rocks.Rocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biomes;

import java.util.concurrent.CompletableFuture;

public class RocksTagProvider {
    public static class RocksBiomes extends BiomeTagsProvider {
        public RocksBiomes(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(packOutput, lookupProvider, Rocks.MOD_ID);
        }

        public static final TagKey<net.minecraft.world.level.biome.Biome> IS_MANGROVE_SWAMP = create(Identifier.fromNamespaceAndPath("c", "is_mangrove_swamp"));
        public static final TagKey<net.minecraft.world.level.biome.Biome> IS_CHERRY_GROVE = create(Identifier.fromNamespaceAndPath("c", "is_cherry_grove"));
        public static final TagKey<net.minecraft.world.level.biome.Biome> IS_PALE_GARDEN = create(Identifier.fromNamespaceAndPath("c", "is_pale_garden"));

        private static TagKey<net.minecraft.world.level.biome.Biome> create(Identifier location) {
            return TagKey.create(Registries.BIOME, location);
        }

        @Override
        protected void addTags(HolderLookup.Provider provider) {
            this.tag(IS_MANGROVE_SWAMP).add(Biomes.MANGROVE_SWAMP);
            this.tag(IS_CHERRY_GROVE).add(Biomes.CHERRY_GROVE);
            this.tag(IS_PALE_GARDEN).add(Biomes.PALE_GARDEN);
        }
    }
}
