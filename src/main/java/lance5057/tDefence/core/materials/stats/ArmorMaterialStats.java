package lance5057.tDefence.core.materials.stats;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.client.CustomFontColor;
import slimeknights.tconstruct.library.materials.AbstractMaterialStats;

public class ArmorMaterialStats extends AbstractMaterialStats {
	public final static String LOC_Durability = "stat.armor.durability.name";
	public final static String LOC_ArmorDefence = "stat.armor.defence.name";
	public final static String LOC_ArmorToughness = "stat.armor.toughness.name";
	public final static String LOC_MagicPotency = "stat.armor.potency.name";

	public final static String LOC_DurabilityDesc = "stat.armor.durability.desc";
	public final static String LOC_ArmorDefenceDesc = "stat.armor.defence.desc";
	public final static String LOC_ArmorToughnessDesc = "stat.armor.toughness.desc";
	public final static String LOC_MagicPotencyDesc = "stat.cloth.potency.desc";

	public final static String COLOR_Durability = CustomFontColor.valueToColorCode(1f);
	public final static String COLOR_ArmorDefence = CustomFontColor.encodeColor(215, 100, 100);
	public final static String COLOR_ArmorToughness = CustomFontColor.encodeColor(120, 160, 205);
	public final static String COLOR_MagicPotency = CustomFontColor.encodeColor(181, 100, 215);

	public final int durability; // usually between 1 and 1000
	public final float defence; // 1 - 20
	public final float toughness;
	public final float potency;

	public ArmorMaterialStats(int durability, float defence, float toughness, float potency, String TYPE) {
		super(TYPE);
		this.durability = durability;
		this.defence = defence;
		this.toughness = toughness;
		this.potency = potency;
	}

	@Override
	public List<String> getLocalizedInfo() {
		List<String> info = Lists.newArrayList();

		info.add(formatDurability(durability));
		info.add(formatArmorDefence(defence));
		info.add(formatArmorToughness(toughness));
		info.add(formatMagicPotency(potency));

		return info;
	}

	public static String formatDurability(int durability) {
		return formatNumber(LOC_Durability, COLOR_Durability, durability);
	}

	public static String formatDurability(int durability, int ref) {
		return String.format("%s: %s%s%s/%s%s", Util.translate(LOC_Durability),
				CustomFontColor.valueToColorCode((float) durability / (float) ref), Util.df.format(durability),
				TextFormatting.GRAY.toString(), COLOR_Durability, Util.df.format(ref)) + TextFormatting.RESET;
	}

	public static String formatArmorDefence(float percent) {
		return formatNumber(LOC_ArmorDefence, COLOR_ArmorDefence, percent);
	}

	public static String formatArmorToughness(float percent) {
		return formatNumber(LOC_ArmorToughness, COLOR_ArmorToughness, percent);
	}
	
	public static String formatMagicPotency(float potency2)
	{
		return formatNumber(LOC_MagicPotency, COLOR_MagicPotency, potency2);
	}

	@Override
	public List<String> getLocalizedDesc() {
		List<String> info = Lists.newArrayList();	

		info.add(Util.translate(LOC_DurabilityDesc));
		info.add(Util.translate(LOC_ArmorDefenceDesc));
		info.add(Util.translate(LOC_ArmorToughnessDesc));
		info.add(Util.translate(LOC_MagicPotencyDesc));

		return info;
	}
}
