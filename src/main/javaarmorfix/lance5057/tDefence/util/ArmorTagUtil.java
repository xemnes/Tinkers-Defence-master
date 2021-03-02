package lance5057.tDefence.util;

import lance5057.tDefence.core.library.ArmorNBT;
import lance5057.tDefence.core.library.ArmorTags;
import lance5057.tDefence.core.tools.armor.heavy.TinkersHelm;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.Tags;

public final class ArmorTagUtil {

	public static int TAG_TYPE_STRING = (new NBTTagString()).getId();
	public static int TAG_TYPE_COMPOUND = (new NBTTagCompound()).getId();

	private ArmorTagUtil() {
	}

	/* Operations concerning the calculated tool data */
	public static NBTTagCompound getToolTag(ItemStack stack) {
		return getToolTag(TagUtil.getTagSafe(stack));
	}

	public static NBTTagCompound getToolTag(NBTTagCompound root) {
		return TagUtil.getTagSafe(root, Tags.TOOL_DATA);
	}

	/* Tool stats */
	public static ArmorNBT getToolStats(ItemStack stack) {
		return getToolStats(TagUtil.getTagSafe(stack));
	}

	public static ArmorNBT getToolStats(NBTTagCompound root) {
		return new ArmorNBT(getToolTag(root));
	}

	public static ArmorNBT getOriginalToolStats(ItemStack stack) {
		return getOriginalToolStats(TagUtil.getTagSafe(stack));
	}

	public static ArmorNBT getOriginalToolStats(NBTTagCompound root) {
		return new ArmorNBT(TagUtil.getTagSafe(root, Tags.TOOL_DATA_ORIG));
	}

	public static boolean getVisor(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		NBTTagCompound atag = tag.getCompoundTag(Tags.BASE_DATA).getCompoundTag(ArmorTags.ArmorBaseTag);
		return atag.getBoolean(ArmorTags.Visor);
	}

	public static void setVisor(ItemStack stack, boolean f) {
		if (stack.getItem() instanceof TinkersHelm) {
			NBTTagCompound tag = stack.getTagCompound();
			NBTTagCompound atag = tag.getCompoundTag(Tags.BASE_DATA).getCompoundTag(ArmorTags.ArmorBaseTag);
			atag.setBoolean(ArmorTags.Visor, f);
		}
	}

	public static float getVisorTime(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		NBTTagCompound atag = tag.getCompoundTag(Tags.BASE_DATA).getCompoundTag(ArmorTags.ArmorBaseTag);
		return atag.getFloat(ArmorTags.VisorTime);
	}

	public static void setVisorTime(ItemStack stack, float f) {
		if (stack.getItem() instanceof TinkersHelm) {
			NBTTagCompound tag = stack.getTagCompound();
			NBTTagCompound atag = tag.getCompoundTag(Tags.BASE_DATA).getCompoundTag(ArmorTags.ArmorBaseTag);
			atag.setFloat(ArmorTags.VisorTime, f);
		}
	}
	
	public static void setWater(ItemStack stack, int water) {
		NBTTagCompound tag = stack.getTagCompound();
		NBTTagCompound atag = tag.getCompoundTag(Tags.BASE_DATA).getCompoundTag(ArmorTags.ArmorBaseTag);
		atag.setInteger(ArmorTags.Water, water);
	}
	
	public static int getWater(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		NBTTagCompound atag = tag.getCompoundTag(Tags.BASE_DATA).getCompoundTag(ArmorTags.ArmorBaseTag);
		return atag.getInteger(ArmorTags.Water);
	}
	
	public static void setCharge(ItemStack stack, int charge) {
		NBTTagCompound tag = stack.getTagCompound();
		NBTTagCompound atag = tag.getCompoundTag(Tags.BASE_DATA).getCompoundTag(ArmorTags.ArmorBaseTag);
		atag.setInteger(ArmorTags.Charge, charge);
	}
	
	public static int getCharge(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		NBTTagCompound atag = tag.getCompoundTag(Tags.BASE_DATA).getCompoundTag(ArmorTags.ArmorBaseTag);
		return atag.getInteger(ArmorTags.Charge);
	}
	
	public static void setKills(ItemStack stack, int rank) {
		NBTTagCompound tag = stack.getTagCompound();
		NBTTagCompound atag = tag.getCompoundTag(Tags.BASE_DATA).getCompoundTag(ArmorTags.ArmorBaseTag);
		atag.setInteger(ArmorTags.Kills, rank);
	}
	
	public static int getKills(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();
		NBTTagCompound atag = tag.getCompoundTag(Tags.BASE_DATA).getCompoundTag(ArmorTags.ArmorBaseTag);
		return atag.getInteger(ArmorTags.Kills);
	}
}