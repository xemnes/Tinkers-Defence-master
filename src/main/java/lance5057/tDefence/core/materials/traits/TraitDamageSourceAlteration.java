package lance5057.tDefence.core.materials.traits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class TraitDamageSourceAlteration extends AbstractTDTrait {
	List<DamagePercent> damageTypes = new ArrayList<DamagePercent>();

	public TraitDamageSourceAlteration(String name, int icolor, DamagePercent... itypes) {
		super(name, icolor);
		damageTypes.addAll(Arrays.asList(itypes));
	}
	
	public TraitDamageSourceAlteration(String name, TextFormatting icolor, DamagePercent... itypes) {
		super(name, icolor);
		damageTypes.addAll(Arrays.asList(itypes));
	}
	
	@Override
	public void onDamageTaken(ItemStack tool, LivingHurtEvent e) {
		for(DamagePercent d: damageTypes)
		{
			if(e.getSource() == d.type)
			{
				float damage = e.getAmount();
				float alteration = damage * d.percent;
				
				damage += alteration;
				e.setAmount(damage);
			}
		}
	}
	
	public static class DamagePercent
	{
		public DamageSource type;
		public float percent;
		
		public DamagePercent(DamageSource d, float p)
		{
			type = d;
			percent = p;
		}
	}
}
