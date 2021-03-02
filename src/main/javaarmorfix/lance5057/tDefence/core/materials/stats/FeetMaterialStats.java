package lance5057.tDefence.core.materials.stats;

public class FeetMaterialStats extends ArmorMaterialStats {
	public final static String TYPE = "feet";

	public FeetMaterialStats(int durability, float defence, float toughness, float potency) {
		super(durability, defence, toughness, potency, TYPE);
	}

}