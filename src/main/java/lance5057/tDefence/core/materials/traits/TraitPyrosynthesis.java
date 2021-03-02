package lance5057.tDefence.core.materials.traits;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class TraitPyrosynthesis extends AbstractTDTrait {

	int level;

	public TraitPyrosynthesis(int level) {
		super("pyrosynthetic_"+level, TextFormatting.DARK_RED);
		this.level = level;
	}

	@Override
	public void onDamageTaken(ItemStack tool, LivingHurtEvent e) {
		if (e.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) e.getEntityLiving();
			World world = e.getEntityLiving().world;

			if (!world.isRemote && e.getSource().isFireDamage()) {

				if (player.getFoodStats().getSaturationLevel() < player.getFoodStats().getFoodLevel()) {
					player.getFoodStats()
							.setFoodSaturationLevel(player.getFoodStats().getSaturationLevel() + (level * 0.5f));
				}
				if (player.getFoodStats().getSaturationLevel() < player.getFoodStats().getFoodLevel())
					player.getFoodStats().setFoodSaturationLevel(player.getFoodStats().getFoodLevel());
			}
		}
	}
}
