package lance5057.tDefence.core.materials.traits;


import lance5057.tDefence.TDConfig;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.traits.AbstractTraitLeveled;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.Tags;
import slimeknights.tconstruct.library.utils.TinkerUtil;

/**
 * Mythical: Damaging the tool after repairing it after it has been broken will consume 30 levels and add a modifier
 */
public class TraitMythical extends AbstractTraitLeveled {

    private static final String READY_FOR_MODIFIER = "readyForModifier";
    private static final String BREAK_FOR_MODIFIER = "breakForModifier";
    private static final String MAX_MODIFIERS = "maxModifiers";
    private static final String BONUS_MODIFIERS = "bonusModifiers";

    public TraitMythical(int levels) {
        super("mythical", String.valueOf(levels), 0x10079487, 3, levels);
    }

    /**
     * Apply NBT tags for the maximum number of modifiers and the number of bonus modifiers
     */
    @Override
    public void applyModifierEffect(NBTTagCompound rootCompound) {
        ModifierNBT data = new ModifierNBT(TinkerUtil.getModifierTag(rootCompound, "mythical"));
        rootCompound.setInteger(MAX_MODIFIERS, TDConfig.traits.extraModifiers * data.level);
        rootCompound.setInteger(BONUS_MODIFIERS, 0);
    }

    /**
     * When tool breaks and the number of bonus modifiers is below the max number of bonus modifiers, apply a ready for
     * modifier tag. When tool takes damage after it's been repaired, consume 30 levels and add a modifier and increment
     * the bonus modifier tag. Tool needs to have at least 800 maximum durability before it is eligible to receive extra modifiers on breaking.
     */
    @Override
    public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {
        NBTTagCompound itemTag = TagUtil.getTagSafe(tool);
        if (newDamage >= tool.getMaxDamage() - tool.getItemDamage() && itemTag.getInteger(BONUS_MODIFIERS) < itemTag.getInteger(MAX_MODIFIERS) && !itemTag.getBoolean(READY_FOR_MODIFIER) && tool.getMaxDamage() >= 800) {
            itemTag.setBoolean(READY_FOR_MODIFIER, true);
            if (entity.world.isRemote) {
                entity.sendMessage(new TextComponentString(TextFormatting.ITALIC + I18n.format("modifier.mythical.voiceRing") + "\n" + TextFormatting.BLUE + TextFormatting.ITALIC + I18n.format("modifier.mythical.instructions")));
            }
        }

        //When tool takes damage, increase the modifier count and deduct XP
        if (itemTag.getBoolean(BREAK_FOR_MODIFIER) && entity instanceof EntityPlayer && ((EntityPlayer) entity).experienceTotal >= 825) {
            //Deduct XP
            EntityPlayer player = (EntityPlayer) entity;
            player.addExperience(-825);

            //Add modifier
            NBTTagCompound toolTag = TagUtil.getToolTag(itemTag);
            int modifiers = toolTag.getInteger(Tags.FREE_MODIFIERS) + 1;
            toolTag.setInteger(Tags.FREE_MODIFIERS, Math.max(0, modifiers));
            TagUtil.setToolTag(itemTag, toolTag);

            //Increment bonus modifier tag
            int bonusModifiers = itemTag.getInteger(BONUS_MODIFIERS) + 1;
            itemTag.setInteger(BONUS_MODIFIERS, bonusModifiers);

            //Revert tags
            TagUtil.setEnchantEffect(tool, false);
            itemTag.setBoolean(READY_FOR_MODIFIER, false);
            itemTag.setBoolean(BREAK_FOR_MODIFIER, false);

            if (entity.world.isRemote) {
                entity.sendMessage(new TextComponentString(TextFormatting.ITALIC + I18n.format("modifier.mythical.modifier_up")));
            }
            return super.onToolDamage(tool, damage, 0, entity);
        }
        return super.onToolDamage(tool, damage, newDamage, entity);
    }

    /**
     * When tool is healed to max durability, apply break for new modifier tag and add enchantment glow
     */
    @Override
    public int onToolHeal(ItemStack tool, int amount, int newAmount, EntityLivingBase entity) {
        if (tool.getItemDamage() <= newAmount * -1 && TagUtil.getTagSafe(tool).getBoolean(READY_FOR_MODIFIER)) {
            TagUtil.getTagSafe(tool).setBoolean(BREAK_FOR_MODIFIER, true);
            TagUtil.setEnchantEffect(tool, true);
        }
        return super.onToolHeal(tool, amount, newAmount, entity);
    }
}
