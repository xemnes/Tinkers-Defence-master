package lance5057.tDefence.core.materials.traits;

import lance5057.tDefence.core.tools.TDToolEvents;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class TraitSuperSonic extends AbstractTDTrait {

	public TraitSuperSonic() {
		super("supersonic", TextFormatting.BLUE);
	}

	@Override
	public void onArmorTick(ItemStack tool, World world, EntityPlayer player) {
		if (player.isSprinting()) {
			IAttributeInstance att = player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
			if (player.isSprinting()) {
				if (!att.hasModifier(TDToolEvents.td_supersonic))
					att.applyModifier(TDToolEvents.td_supersonic);
			} else if (!att.hasModifier(TDToolEvents.td_supersonic))
				att.removeModifier(TDToolEvents.td_supersonic);

			if(world.rand.nextInt(100) == 0)
				ToolHelper.damageTool(tool, 1, null);
		}
	}
}
