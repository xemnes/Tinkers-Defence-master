package lance5057.tDefence.core.materials.traits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class TraitDamageNegation extends AbstractTDTrait {
	List<DamageSource> damageTypes = new ArrayList<DamageSource>();
	float negate = 0;

	public TraitDamageNegation(String identifier, TextFormatting color, float amount) {
		super(identifier, color);
		negate = amount;
	}
	
	public TraitDamageNegation(String identifier, TextFormatting color, float amount, DamageSource... sources) {
		super(identifier, color);
		negate = amount;
		damageTypes.addAll(Arrays.asList(sources));
	}
	
	@Override
	public void onDamagePre(ItemStack tool, LivingAttackEvent entity) {

		if (damageTypes.size() > 0) {
			for (DamageSource damage : damageTypes) {
				if (damage == entity.getSource() && entity.getAmount() <= negate) {
					ToolHelper.damageTool(tool, (int) entity.getAmount(), entity.getEntityLiving());
					entity.setCanceled(true);
				}
			}
		} else {
			if (entity.getAmount() <= negate) {
				ToolHelper.damageTool(tool, (int) entity.getAmount(), entity.getEntityLiving());
				entity.setCanceled(true);
			}
		}
	}

}
