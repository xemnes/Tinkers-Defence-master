package lance5057.tDefence.core.materials.traits;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class TraitDulling extends AbstractTDTrait {

	public TraitDulling() {
		super("dulling", TextFormatting.GRAY);
	}

	@Override
	public void onBlock(ItemStack tool, EntityPlayer player,
			LivingHurtEvent event) {
		Entity src = event.getSource().getTrueSource();
		if (src != null && src instanceof EntityLiving) {
			EntityLivingBase liv = (EntityLivingBase) src;

			if (liv.getHeldItemMainhand() != null
					&& liv.getHeldItemMainhand().isItemStackDamageable()) {
				ItemStack held = liv.getHeldItemMainhand();
				if(held.getItem() instanceof ToolCore)
				{
					ToolHelper.damageTool(held, 5, player);
				}
				else if (held.isItemStackDamageable()) {
					held.damageItem(5, liv);
				}
			}
		}
	}
}
