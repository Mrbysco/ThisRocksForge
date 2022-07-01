package eu.midnightdust.motschen.rocks.world.modifier;

import com.mojang.serialization.Codec;
import eu.midnightdust.motschen.rocks.world.WorldGenHandler;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo.BiomeInfo.Builder;

import java.util.List;

public record AddFeaturesBlacklistBiomeModifier(List<HolderSet<Biome>> biomes, List<HolderSet<Biome>> blacklistBiomes,
												HolderSet<PlacedFeature> features,
												Decoration step) implements BiomeModifier {
	@Override
	public void modify(Holder<Biome> biome, Phase phase, Builder builder) {
		if (phase == Phase.ADD && biomes.stream().anyMatch(biomeSet -> biomeSet.contains(biome)) &&
				blacklistBiomes.stream().noneMatch(biomeSet -> biomeSet.contains(biome))) {
			BiomeGenerationSettingsBuilder generationSettings = builder.getGenerationSettings();
			this.features.forEach(holder -> generationSettings.addFeature(this.step, holder));
		}
	}

	@Override
	public Codec<? extends BiomeModifier> codec() {
		return WorldGenHandler.ADD_FEATURES_BLACKLIST_BIOME_MODIFIER_TYPE.get();
	}
}
