package eu.midnightdust.motschen.rocks.world;

import eu.midnightdust.motschen.rocks.config.RocksConfig;
import eu.midnightdust.motschen.rocks.world.configured_feature.MiscFeatures;
import eu.midnightdust.motschen.rocks.world.configured_feature.NetherFeatures;
import eu.midnightdust.motschen.rocks.world.configured_feature.RockFeatures;
import eu.midnightdust.motschen.rocks.world.configured_feature.StickFeatures;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WorldGenHandler {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void biomeLoadingEvent(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder builder = event.getGeneration();
        if(event.getName() != null) {
            String name = event.getName().toString();
            BiomeCategory cat = event.getCategory();
            if(cat != Biome.BiomeCategory.NETHER && cat != Biome.BiomeCategory.THEEND && cat!= Biome.BiomeCategory.BEACH && cat != Biome.BiomeCategory.DESERT
                    && cat != Biome.BiomeCategory.MESA && cat != Biome.BiomeCategory.ICY && cat != Biome.BiomeCategory.OCEAN) {

                if (RocksConfig.COMMON.rock.get()) builder.addFeature(Decoration.TOP_LAYER_MODIFICATION, RockFeatures.ROCK_PLACED_FEATURE);
                if (RocksConfig.COMMON.granite_rock.get()) builder.addFeature(Decoration.TOP_LAYER_MODIFICATION, RockFeatures.GRANITE_ROCK_PLACED_FEATURE);
                if (RocksConfig.COMMON.diorite_rock.get()) builder.addFeature(Decoration.TOP_LAYER_MODIFICATION, RockFeatures.DIORITE_ROCK_PLACED_FEATURE);
                if (RocksConfig.COMMON.andesite_rock.get()) builder.addFeature(Decoration.TOP_LAYER_MODIFICATION, RockFeatures.ANDESITE_ROCK_PLACED_FEATURE);
            }

            if(cat == Biome.BiomeCategory.BEACH || cat == Biome.BiomeCategory.DESERT || cat == Biome.BiomeCategory.MESA) {
                if (RocksConfig.COMMON.sand_rock.get()) builder.addFeature(Decoration.TOP_LAYER_MODIFICATION, RockFeatures.SAND_ROCK_PLACED_FEATURE);
            }

            if(cat == Biome.BiomeCategory.MESA || cat == Biome.BiomeCategory.DESERT) {
                if (RocksConfig.COMMON.red_sand_rock.get()) builder.addFeature(Decoration.TOP_LAYER_MODIFICATION, RockFeatures.RED_SAND_ROCK_PLACED_FEATURE);
            }

            if (cat == Biome.BiomeCategory.THEEND) {
                if (RocksConfig.COMMON.end_stone_rock.get()) builder.addFeature(Decoration.TOP_LAYER_MODIFICATION, RockFeatures.END_STONE_ROCK_PLACED_FEATURE);
            }

            if(name.contains("minecraft:forest") || name.contains("minecraft:wooded_hills") || name.contains("oak") ||
                    name.contains("minecraft:wooded_mountains") || name.contains("minecraft:plains") ||
                    name.contains("minecraft:flower_forest") || name.contains("minecraft:wooded_badlands_plateau") ||
                    name.contains("minecraft:modified_wooded_badlands_plateau") || cat == Biome.BiomeCategory.SWAMP) {
                if (RocksConfig.COMMON.oak_stick.get()) builder.addFeature(Decoration.TOP_LAYER_MODIFICATION, StickFeatures.OAK_STICK_PLACED_FEATURE);
            }

            if(name.contains("minecraft:forest") || name.contains("birch") || name.contains("minecraft:flower_forest")) {
                if (RocksConfig.COMMON.birch_stick.get()) builder.addFeature(Decoration.TOP_LAYER_MODIFICATION, StickFeatures.BIRCH_STICK_PLACED_FEATURE);
            }

            if(name.contains("minecraft:wooded_mountains") || cat == Biome.BiomeCategory.TAIGA) {
                if (RocksConfig.COMMON.spruce_stick.get()) builder.addFeature(Decoration.TOP_LAYER_MODIFICATION, StickFeatures.SPRUCE_STICK_PLACED_FEATURE);
            }

            if(cat == Biome.BiomeCategory.SAVANNA) {
                if (RocksConfig.COMMON.acacia_stick.get()) builder.addFeature(Decoration.TOP_LAYER_MODIFICATION, StickFeatures.ACACIA_STICK_PLACED_FEATURE);
            }

            if(cat == Biome.BiomeCategory.JUNGLE) {
                if (RocksConfig.COMMON.jungle_stick.get()) builder.addFeature(Decoration.UNDERGROUND_DECORATION, StickFeatures.JUNGLE_STICK_PLACED_FEATURE);
            }
            
            if(name.contains("minecraft:dark_forest") || name.contains("minecraft:dark_forest_hills") || name.contains("minecraft:dark_forest_mountains")) {
                if (RocksConfig.COMMON.dark_oak_stick.get()) builder.addFeature(Decoration.TOP_LAYER_MODIFICATION, StickFeatures.DARK_OAK_STICK_PLACED_FEATURE);
            }

            if(cat == Biome.BiomeCategory.BEACH && !name.contains("snow")) {
                if (RocksConfig.COMMON.seashell.get()) builder.addFeature(Decoration.TOP_LAYER_MODIFICATION, MiscFeatures.SEASHELL_PLACED_FEATURE);
                if (RocksConfig.COMMON.starfish.get()) builder.addFeature(Decoration.TOP_LAYER_MODIFICATION, MiscFeatures.STARFISH_PLACED_FEATURE);
            }
            
            if(cat == Biome.BiomeCategory.OCEAN) {
                if (RocksConfig.COMMON.underwater_starfish.get()) builder.addFeature(Decoration.TOP_LAYER_MODIFICATION, MiscFeatures.UNDERWATER_STARFISH_PLACED_FEATURE);
                if (RocksConfig.COMMON.underwater_seashell.get()) builder.addFeature(Decoration.TOP_LAYER_MODIFICATION, MiscFeatures.UNDERWATER_SEASHELL_PLACED_FEATURE);
            }
     
            if(cat == Biome.BiomeCategory.NETHER) {
                if (RocksConfig.COMMON.netherrack_rock.get()) builder.addFeature(Decoration.TOP_LAYER_MODIFICATION, NetherFeatures.NETHERRACK_ROCK_PLACED_FEATURE);
                if (RocksConfig.COMMON.soul_soil_rock.get()) builder.addFeature(Decoration.TOP_LAYER_MODIFICATION, NetherFeatures.SOUL_SOIL_ROCK_PLACED_FEATURE);
                if (RocksConfig.COMMON.gravel_rock.get()) builder.addFeature(Decoration.TOP_LAYER_MODIFICATION, NetherFeatures.NETHER_GRAVEL_ROCK_PLACED_FEATURE);
                if (RocksConfig.COMMON.nether_geyser.get()) builder.addFeature(Decoration.TOP_LAYER_MODIFICATION, NetherFeatures.NETHER_GEYSER_PLACED_FEATURE);
                if (RocksConfig.COMMON.warped_stick.get()) builder.addFeature(Decoration.TOP_LAYER_MODIFICATION, NetherFeatures.WARPED_STICK_PLACED_FEATURE);
                if (RocksConfig.COMMON.crimson_stick.get()) builder.addFeature(Decoration.TOP_LAYER_MODIFICATION, NetherFeatures.CRIMSON_STICK_PLACED_FEATURE);
            }

            if(cat != Biome.BiomeCategory.NETHER) {
                if (RocksConfig.COMMON.gravel_rock.get()) builder.addFeature(Decoration.TOP_LAYER_MODIFICATION, RockFeatures.GRAVEL_ROCK_PLACED_FEATURE);
            }

            if(cat == Biome.BiomeCategory.ICY || name.contains("snowy")) {
                if (RocksConfig.COMMON.geyser.get()) builder.addFeature(Decoration.TOP_LAYER_MODIFICATION, MiscFeatures.SNOWY_GEYSER_PLACED_FEATURE);
            }
 
        }
    }
}