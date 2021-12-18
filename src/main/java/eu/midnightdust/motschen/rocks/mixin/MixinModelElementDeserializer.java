package eu.midnightdust.motschen.rocks.mixin;

import com.google.gson.JsonObject;
import net.minecraft.client.renderer.block.model.BlockElement;
import net.minecraft.util.GsonHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = BlockElement.Deserializer.class, priority = 2000)
public class MixinModelElementDeserializer {

    /**
     * @author Motschen
     * @reason Not cancellable
     * Unlimited rotation angles for starfish
     * Inspired by https://github.com/CottonMC/ModelsUnlocked/blob/master/src/main/java/io/github/cottonmc/modelsunlocked/mixin/ModelElementDeserializerMixin.java
     */
    @Overwrite
    private float getAngle(JsonObject json) {
        return (GsonHelper.getAsFloat(json, "angle"));
    }
}
