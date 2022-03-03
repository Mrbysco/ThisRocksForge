package eu.midnightdust.motschen.rocks.world;

import eu.midnightdust.motschen.rocks.Rocks;
import eu.midnightdust.motschen.rocks.world.feature.SnowFeature;
import eu.midnightdust.motschen.rocks.world.feature.UnderwaterFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FeatureRegistry<FC extends FeatureConfiguration> {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Rocks.MOD_ID);

	public static final RegistryObject<UnderwaterFeature> UNDERWATER_STARFISH_FEATURE = FEATURES.register("underwater_starfish", () -> new UnderwaterFeature(ProbabilityFeatureConfiguration.CODEC, FeatureStates.StarfishStates));
	public static final RegistryObject<UnderwaterFeature> UNDERWATER_SEASHELL_FEATURE = FEATURES.register("underwater_seashell", () -> new UnderwaterFeature(ProbabilityFeatureConfiguration.CODEC, FeatureStates.SeashellStates));
	public static final RegistryObject<SnowFeature> SNOWY_GEYSER_FEATURE = FEATURES.register("snowy_geyser", () -> new SnowFeature(ProbabilityFeatureConfiguration.CODEC, FeatureStates.GeyserStates));
}
