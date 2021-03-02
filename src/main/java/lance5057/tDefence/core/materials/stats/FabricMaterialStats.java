package lance5057.tDefence.core.materials.stats;

public class FabricMaterialStats extends ArmorMaterialStats {
	public final static String TYPE = "fabric";

	public FabricMaterialStats(int durability, float defence, float toughness, float potency) {
		super(durability, defence, toughness, potency, TYPE);
	}

}