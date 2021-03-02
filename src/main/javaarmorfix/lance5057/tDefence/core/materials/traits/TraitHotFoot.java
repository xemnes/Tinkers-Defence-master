package lance5057.tDefence.core.materials.traits;

import lance5057.tDefence.core.tools.TDToolEvents;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class TraitHotFoot extends AbstractTDTrait {

	public TraitHotFoot() {
		super("hotfoot", TextFormatting.RED);
	}

	@Override
	public void onArmorTick(ItemStack tool, World world, EntityPlayer player) {
		IAttributeInstance att = player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
		if(player.isBurning())
		{
			if(!att.hasModifier(TDToolEvents.td_hotfoot))
				att.applyModifier(TDToolEvents.td_hotfoot);
		}
		else
			if(!att.hasModifier(TDToolEvents.td_hotfoot))
				att.removeModifier(TDToolEvents.td_hotfoot);
	}

}
