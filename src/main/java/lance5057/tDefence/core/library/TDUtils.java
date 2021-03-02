package lance5057.tDefence.core.library;

import lance5057.tDefence.Reference;
import net.minecraft.util.ResourceLocation;

import java.text.DecimalFormat;
import java.util.Locale;

public class TDUtils {

    public static final DecimalFormat dfPercentSpec = new DecimalFormat("#.#%");

    public static String getPrefixedName(String name) {
        if (!name.equals(name.toLowerCase(Locale.US))) {
            throw new IllegalArgumentException(String.format("Non-lowercase unlocalized name detected! %s", name));
        }
        return Reference.MOD_ID + "." + name;
    }

    public static ResourceLocation getResource(String name) {
        return new ResourceLocation(Reference.MOD_ID, name);
    }
}