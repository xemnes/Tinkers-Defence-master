package lance5057.tDefence.util;

import lance5057.tDefence.core.library.ArmorNBT;
import lance5057.tDefence.core.library.ArmorTags;
import lance5057.tDefence.core.library.ShieldNBT;
import lance5057.tDefence.core.tools.armor.heavy.TinkersHelm;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.Tags;

public final class ShieldTagUtil {

	public static int TAG_TYPE_STRING = (new NBTTagString()).getId();
	public static int TAG_TYPE_COMPOUND = (new NBTTagCompound()).getId();

	private ShieldTagUtil() {
	}

	/* Operations concerning the calculated tool data */
	public static NBTTagCompound getToolTag(ItemStack stack) {
		return getToolTag(TagUtil.getTagSafe(stack));
	}

	public static NBTTagCompound getToolTag(NBTTagCompound root) {
		return TagUtil.getTagSafe(root, Tags.TOOL_DATA);
	}

	/* Tool stats */
	public static ShieldNBT getToolStats(ItemStack stack) {
		return getToolStats(TagUtil.getTagSafe(stack));
	}

	public static ShieldNBT getToolStats(NBTTagCompound root) {
		return new ShieldNBT(getToolTag(root));
	}

	public static ShieldNBT getOriginalToolStats(ItemStack stack) {
		return getOriginalToolStats(TagUtil.getTagSafe(stack));
	}

	public static ShieldNBT getOriginalToolStats(NBTTagCompound root) {
		return new ShieldNBT(TagUtil.getTagSafe(root, Tags.TOOL_DATA_ORIG));
	}
}