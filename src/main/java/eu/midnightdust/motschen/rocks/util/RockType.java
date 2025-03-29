package eu.midnightdust.motschen.rocks.util;

import eu.midnightdust.motschen.rocks.Rocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.Arrays;

public enum RockType {
	STONE("rock"), ANDESITE("andesite_rock"), GRANITE("granite_rock"),
	DIORITE("diorite_rock"), GRAVEL("gravel_rock"), SANDSTONE("sand_rock"),
	RED_SANDSTONE("red_sand_rock"), NETHERRACK("netherrack_rock"),
	SOUL_SOIL("soul_soil_rock"), END_STONE("end_stone_rock"), ICE("ice_rock");

	private final String name;

	RockType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public ResourceLocation getStoneId() {
		return ResourceLocation.withDefaultNamespace(this.toString().toLowerCase());
	}

	public Block getStoneBlock() {
		return BuiltInRegistries.BLOCK.getValue(getStoneId());
	}

	public ResourceLocation[] getVariations() {
		var variations = new ResourceLocation[4];
		variations[0] = Rocks.modLoc(name + "_tiny");
		variations[1] = Rocks.modLoc(name + "_small");
		variations[2] = Rocks.modLoc(name + "_medium");
		variations[3] = Rocks.modLoc(name + "_large");
		return variations;
	}

	public static RockType fromBlockName(String name) {
		return Arrays.stream(values()).filter(type -> name
				.replace("block.rocks.", "")
				.replace("tiny", "")
				.replace("small_", "")
				.replace("medium_", "")
				.replace("large_", "")
				.equals(type.getName())).findFirst().orElse(RockType.STONE);
	}

	public Fragment getFragment() {
		return new Fragment(this);
	}

	public static class Fragment {
		private final RockType type;

		Fragment(RockType type) {
			this.type = type;
		}

		public String getName() {
			String splitterName = type.name().toLowerCase() + "_splitter";
			if (type.equals(RockType.STONE)) splitterName = "cobblestone_splitter";
			return splitterName;
		}

		public ResourceLocation getStoneId() {
			if (type == STONE) return ResourceLocation.withDefaultNamespace("cobblestone");
			return ResourceLocation.withDefaultNamespace(type.toString().toLowerCase());
		}

		public Block getStoneBlock() {
			return BuiltInRegistries.BLOCK.getValue(getStoneId());
		}
	}
}
