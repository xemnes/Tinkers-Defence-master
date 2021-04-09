package lance5057.tDefence.core.library;

import java.util.List;

import com.google.common.collect.Lists;

import lance5057.tDefence.core.materials.stats.ArmorMaterialStats;
import lance5057.tDefence.core.materials.stats.ShieldMaterialStats;
import lance5057.tDefence.util.ArmorTagUtil;
import lance5057.tDefence.util.ShieldTagUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.modifiers.IModifier;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.ToolHelper;

/**
 * Used for simple info buidling in the tools!
 */
public class ShieldToolTipBuilder {

	public final static String LOC_FreeModifiers = "tooltip.tool.modifiers";

	public static final String LOC_Broken = "tooltip.tool.broken";

	private final List<String> tips = Lists.newLinkedList();
	private final ItemStack stack;

	public ShieldToolTipBuilder(ItemStack stack) {
		this.stack = stack;
	}

	public List<String> getTooltip() {
		return tips;
	}

	public ShieldToolTipBuilder add(String text) {
		tips.add(text);

		return this;
	}

	public ShieldToolTipBuilder addDurability(boolean textIfBroken) {
		if (ToolHelper.isBroken(stack) && textIfBroken) {
			tips.add(String.format("%s: %s%s%s", Util.translate(HeadMaterialStats.LOC_Durability),
					TextFormatting.DARK_RED, TextFormatting.BOLD, Util.translate("tooltip.tool.broken")));
		} else {
			tips.add(HeadMaterialStats.formatDurability(ToolHelper.getCurrentDurability(stack),
					ToolHelper.getMaxDurability(stack)));
		}

		return this;
	}

	public ShieldToolTipBuilder addFreeModifiers() {
		tips.add(String.format("%s: %d", I18n.translateToLocal(LOC_FreeModifiers), ToolHelper.getFreeModifiers(stack)));

		return this;
	}

	public ShieldToolTipBuilder addPercentBlocked() {
	  ShieldNBT nbt = ShieldTagUtil.getToolStats(stack);

	  if (ToolHelper.isBroken(stack)) {
			tips.add(String.format("%s: %s%s%s", Util.translate(ShieldMaterialStats.LOC_PercentBlocked),
					TextFormatting.DARK_RED, TextFormatting.BOLD, "0"));
		} else {
			tips.add(String.format("%s: %s%s", Util.translate(ShieldMaterialStats.LOC_PercentBlocked), ShieldMaterialStats.COLOR_PercentBlocked, Util.df.format(nbt.percentBlocked) + "%"));
		}

	  return this;
  }

	public ShieldToolTipBuilder addModifierInfo() {
		NBTTagList tagList = TagUtil.getModifiersTagList(stack);
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = tagList.getCompoundTagAt(i);
			ModifierNBT data = ModifierNBT.readTag(tag);

			// get matching modifier
			IModifier modifier = TinkerRegistry.getModifier(data.identifier);
			if (modifier == null || modifier.isHidden()) {
				continue;
			}

			for (String string : modifier.getExtraInfo(stack, tag)) {
				if (!string.isEmpty()) {
					tips.add(data.getColorString() + string);
				}
			}
		}

		return this;
	}

	public static void addModifierTooltips(ItemStack stack, List<String> tooltips) {
		NBTTagList tagList = TagUtil.getModifiersTagList(stack);
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = tagList.getCompoundTagAt(i);
			ModifierNBT data = ModifierNBT.readTag(tag);

			// get matching modifier
			IModifier modifier = TinkerRegistry.getModifier(data.identifier);
			if (modifier == null || modifier.isHidden()) {
				continue;
			}

			tooltips.add(data.getColorString() + modifier.getTooltip(tag, false));
		}
	}
}