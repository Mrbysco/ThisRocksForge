package eu.midnightdust.motschen.rocks.blockstates;

import net.minecraft.util.StringRepresentable;

public enum StarfishVariation implements StringRepresentable {
	RED("red"),
	PINK("pink"),
	ORANGE("orange");

	private final String name;

	StarfishVariation(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	public String getSerializedName() {
		return this.name;
	}
}
