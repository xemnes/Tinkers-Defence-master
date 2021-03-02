package lance5057.tDefence.core.materials.traits;

import lance5057.tDefence.TDConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;

public class TraitPeyote extends AbstractTDTrait {

	public TraitPeyote() {
		super("peyote", TextFormatting.GREEN);
	}

	@Override
	public void onFoodEaten(LivingEntityUseItemEvent.Finish e) {
		EntityLivingBase p = e.getEntityLiving();
		int duration = p.world.rand.nextInt(100) + 20;
		switch (p.world.rand.nextInt(6)) {
		default:
			p.addPotionEffect(new PotionEffect(MobEffects.POISON, duration, 0));
			break;
		case 1:
			p.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, duration, 0));
			break;
		case 2:
			if (!TDConfig.KiddyCoastersMakeMeRetch)
				p.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, duration, 0));
			break;
		case 3:
			p.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, duration, 0));
			break;
		case 4:
			p.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, duration, 0));
			break;
		case 5:
			p.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, duration, 0));
			break;
		}
	}
}
