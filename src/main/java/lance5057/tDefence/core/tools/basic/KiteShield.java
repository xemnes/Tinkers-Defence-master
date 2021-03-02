package lance5057.tDefence.core.tools.basic;

import c4.conarm.common.ConstructsRegistry;
import lance5057.tDefence.core.materials.stats.ShieldMaterialStats;
import lance5057.tDefence.core.parts.TDParts;
import lance5057.tDefence.core.tools.bases.Shield;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.tools.TinkerTools;

public class KiteShield extends Shield
{
	int	induceDamage	= 0;

	public KiteShield()
	{
		super(PartMaterialType.handle(TinkerTools.toughToolRod),
			new PartMaterialType(ConstructsRegistry.armorPlate, ShieldMaterialStats.TYPE),
			new PartMaterialType(ConstructsRegistry.armorPlate, ShieldMaterialStats.TYPE),
			PartMaterialType.extra(TDParts.rivets));
		setUnlocalizedName("kiteshield");
	}

}
