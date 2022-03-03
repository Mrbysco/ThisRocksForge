package eu.midnightdust.motschen.rocks.blockstates;

import net.minecraft.util.StringRepresentable;

public enum StickVariation implements StringRepresentable {
	SMALL("small"),
	MEDIUM("medium"),
	LARGE("large");

	private final String name;

	StickVariation(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	public String getSerializedName() {
		return this.name;
	}
}
