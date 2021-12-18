package eu.midnightdust.motschen.rocks.item;

import eu.midnightdust.motschen.rocks.blockstates.StarfishVariation;
import net.minecraft.world.item.Item;

public class StarfishItem extends Item {
    public final StarfishVariation variation;

    public StarfishItem(Properties settings, StarfishVariation variation) {
        super(settings);
        this.variation = variation;
    }
}
