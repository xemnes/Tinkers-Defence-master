package lance5057.tDefence.core.materials.stats;

public class ChestMaterialStats extends ArmorMaterialStats {
	public final static String TYPE = "chest";

	public ChestMaterialStats(int durability, float defence, float toughness, float potency) {
		super(durability, defence, toughness, potency, TYPE);
	}

}