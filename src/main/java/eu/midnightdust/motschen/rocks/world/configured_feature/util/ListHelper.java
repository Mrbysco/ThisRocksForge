package eu.midnightdust.motschen.rocks.world.configured_feature.util;

import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.ArrayList;
import java.util.List;

public class ListHelper {
	public static List<PlacementModifier> getMergedModifierList(List<PlacementModifier> defaultList, PlacementModifier... placements) {
		List<PlacementModifier> modifiers = new ArrayList<>();
		modifiers.addAll(List.of(placements));
		modifiers.addAll(defaultList);

		return modifiers;
	}
}
