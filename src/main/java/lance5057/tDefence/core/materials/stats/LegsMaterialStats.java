package lance5057.tDefence.core.materials.stats;

public class LegsMaterialStats extends ArmorMaterialStats {
	public final static String TYPE = "legs";

	public LegsMaterialStats(int durability, float defence, float toughness, float potency) {
		super(durability, defence, toughness, potency, TYPE);
	}

}