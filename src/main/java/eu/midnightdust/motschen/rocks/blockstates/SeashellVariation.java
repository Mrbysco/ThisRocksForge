package eu.midnightdust.motschen.rocks.blockstates;

import net.minecraft.util.StringRepresentable;

public enum SeashellVariation implements StringRepresentable {
	YELLOW("yellow"),
	PINK("pink"),
	WHITE("white");

	private final String name;

	SeashellVariation(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}

	public String getSerializedName() {
		return this.name;
	}
}
