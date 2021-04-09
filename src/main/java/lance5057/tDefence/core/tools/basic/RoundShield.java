package lance5057.tDefence.core.tools.basic;

import lance5057.tDefence.core.library.ShieldNBT;
import lance5057.tDefence.core.materials.stats.ShieldMaterialStats;
import lance5057.tDefence.core.tools.bases.ShieldCore;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.tools.TinkerTools;
import c4.conarm.common.ConstructsRegistry;

import java.util.List;

public class RoundShield extends ShieldCore
{
	float induceDamage = 0;

	public RoundShield()
	{
	    super(PartMaterialType.handle(TinkerTools.toolRod),
	    	new PartMaterialType(ConstructsRegistry.armorPlate, ShieldMaterialStats.TYPE),
	            PartMaterialType.head(ConstructsRegistry.armorTrim));
		setUnlocalizedName("roundshield");
	}

	@Override
	public float PercentBlockedMultiplier() {
		return 0.6f;
	}

	protected ShieldNBT buildDefaultShieldTag(List<Material> materials) {
		ShieldNBT data = new ShieldNBT();

		if(materials.size() >= 2) {
			HandleMaterialStats handle = materials.get(0).getStatsOrUnknown(MaterialTypes.HANDLE);
			ShieldMaterialStats head = materials.get(1).getStatsOrUnknown(ShieldMaterialStats.TYPE);
			// start with head
			data.head(this, head);

			// add in accessoires if present
			if(materials.size() >= 3) {
				ExtraMaterialStats binding = materials.get(2).getStatsOrUnknown(MaterialTypes.EXTRA);
				data.extra(binding);
			}

			// calculate handle impact
			data.handle(handle);
		}

		// 3 free modifiers
		data.modifiers = 1;

		return data;
	}
}
