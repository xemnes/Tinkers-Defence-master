package lance5057.tDefence.core.materials.traits;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class TraitBarbed extends AbstractTDTrait {

	public TraitBarbed() {
		super("barbed", TextFormatting.GREEN);
	}

	@Override
	public void onBlock(ItemStack tool, EntityPlayer player, LivingHurtEvent event) {
		if (event.getSource() != DamageSource.GENERIC) {
			player.attackEntityFrom(DamageSource.GENERIC, 1);
			event.getSource().getTrueSource().attackEntityFrom(DamageSource.GENERIC, 1);
		}
	}
}
