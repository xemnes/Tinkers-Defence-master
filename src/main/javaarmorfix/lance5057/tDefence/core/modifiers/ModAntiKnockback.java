package lance5057.tDefence.core.modifiers;

import lance5057.tDefence.core.library.modifiers.ModifierTDTrait;
import lance5057.tDefence.core.tools.TDToolEvents;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.modifiers.ModifierAspect;

public class ModAntiKnockback extends ModifierTDTrait {

	public ModAntiKnockback() {
		super("antiknockback", 0xAAAAAA);
	}

	@Override
	public void onArmorTick(ItemStack tool, World world, EntityPlayer player) {
		IAttributeInstance att = player.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);

		if (!att.hasModifier(TDToolEvents.td_knockback)) {
			att.applyModifier(TDToolEvents.td_knockback);
		}
	}

	@Override
	protected ModifierAspect[] getAspects() {
		return new ModifierAspect[] {ModifierAspect.freeModifier};
	}
}
