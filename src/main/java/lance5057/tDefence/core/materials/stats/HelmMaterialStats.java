package lance5057.tDefence.core.materials.stats;

public class HelmMaterialStats extends ArmorMaterialStats {
		public final static String TYPE = "helm";

		public HelmMaterialStats(int durability, float defence, float toughness, float potency) {
			super(durability, defence, toughness, potency, TYPE);
		}

	}