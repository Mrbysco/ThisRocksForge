package eu.midnightdust.motschen.rocks.config;

import eu.midnightdust.motschen.rocks.Rocks;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

public class RocksConfig {

    public static class Common {
        public final BooleanValue rock;
        public final BooleanValue granite_rock;
        public final BooleanValue diorite_rock;
        public final BooleanValue andesite_rock;
        public final BooleanValue sand_rock;
        public final BooleanValue red_sand_rock;
        public final BooleanValue gravel_rock;
        public final BooleanValue end_stone_rock;
        public final BooleanValue netherrack_rock;
        public final BooleanValue soul_soil_rock;

        public final BooleanValue oak_stick;
        public final BooleanValue spruce_stick;
        public final BooleanValue birch_stick;
        public final BooleanValue acacia_stick;
        public final BooleanValue jungle_stick;
        public final BooleanValue dark_oak_stick;
        public final BooleanValue crimson_stick;
        public final BooleanValue warped_stick;

        public final BooleanValue pinecone;
        public final BooleanValue geyser;
        public final BooleanValue nether_geyser;
        public final BooleanValue seashell;
        public final BooleanValue starfish;
        public final BooleanValue underwater_seashell;
        public final BooleanValue underwater_starfish;

        Common(ForgeConfigSpec.Builder builder) {
            builder.comment("Rock settings")
                    .push("Rocks");

            rock = builder.worldRestart()
                    .define("rock", true);
            granite_rock = builder.worldRestart()
                    .define("granite_rock", true);
            diorite_rock = builder.worldRestart()
                    .define("diorite_rock", true);
            andesite_rock = builder.worldRestart()
                    .define("andesite_rock", true);
            sand_rock = builder.worldRestart()
                    .define("sand_rock", true);
            red_sand_rock = builder.worldRestart()
                    .define("red_sand_rock", true);
            gravel_rock = builder.worldRestart()
                    .define("gravel_rock", true);
            end_stone_rock = builder.worldRestart()
                    .define("end_stone_rock", true);
            netherrack_rock = builder.worldRestart()
                    .define("netherrack_rock", true);
            soul_soil_rock = builder.worldRestart()
                    .define("soul_soil_rock", true);

            builder.pop();
            builder.comment("Stick settings")
                    .push("Sticks");

            oak_stick = builder.worldRestart()
                    .define("oak_stick", true);
            spruce_stick = builder.worldRestart()
                    .define("spruce_stick", true);
            birch_stick = builder.worldRestart()
                    .define("birch_stick", true);
            acacia_stick = builder.worldRestart()
                    .define("acacia_stick", true);
            jungle_stick = builder.worldRestart()
                    .define("jungle_stick", true);
            dark_oak_stick = builder.worldRestart()
                    .define("dark_oak_stick", true);
            crimson_stick = builder.worldRestart()
                    .define("crimson_stick", true);
            warped_stick = builder.worldRestart()
                    .define("warped_stick", true);

            builder.pop();
            builder.comment("Misc settings")
                    .push("Misc");

            pinecone = builder.worldRestart()
                    .define("pinecone", true);
            geyser = builder.worldRestart()
                    .define("geyser", true);
            nether_geyser = builder.worldRestart()
                    .define("nether_geyser", true);
            seashell = builder.worldRestart()
                    .define("seashell", true);
            starfish = builder.worldRestart()
                    .define("starfish", true);
            underwater_seashell = builder.worldRestart()
                    .define("underwater_seashell", true);
            underwater_starfish = builder.worldRestart()
                    .define("underwater_starfish", true);

            builder.pop();
        }
    }

    public static final ForgeConfigSpec commonSpec;
    public static final Common COMMON;

    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        commonSpec = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading configEvent) {
        Rocks.LOGGER.debug("Loaded This Rocks' config file {}", configEvent.getConfig().getFileName());
    }

    @SubscribeEvent
    public static void onFileChange(final ModConfigEvent.Reloading configEvent) {
        Rocks.LOGGER.debug("This Rock' config just got changed on the file system!");
    }
}
