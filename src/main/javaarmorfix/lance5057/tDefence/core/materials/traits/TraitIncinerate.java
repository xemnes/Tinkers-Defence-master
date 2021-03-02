package lance5057.tDefence.core.materials.traits;

import lance5057.tDefence.TDConfig;
import lance5057.tDefence.core.library.ArmorHelper;
import lance5057.tDefence.core.tools.bases.ArmorCore;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.utils.TagUtil;

public class TraitIncinerate extends AbstractTDTrait {

	public TraitIncinerate() {
		super("incinerate", TextFormatting.RED);
	}

	//Armor
	public void onDamageTaken(ItemStack tool, LivingHurtEvent e) {
		incinerate(tool, e);
	}

	//Shields
	@Override
	public void onBlock(ItemStack tool, EntityPlayer player, LivingHurtEvent e) {
		incinerate(tool, e);
	}

	//Extra Crispy
	void incinerate(ItemStack tool, LivingHurtEvent e) {
		ItemStack item = e.getEntityLiving().getHeldItemMainhand();
		if (item.getItem() instanceof ToolCore || item.getItem() instanceof ArmorCore) {
			NBTTagList tag = TagUtil.getBaseMaterialsTagList(item);
			boolean isFlammable = false;
			for (NBTBase s : tag) {
				if (TDConfig.traits.isFlammable(((NBTTagString) s).getString()))
					isFlammable = true;
			}
			if (isFlammable) {
				int dur = ArmorHelper.getCurrentDurability(item);
				ArmorHelper.damageTool(item, (int) Math.floor(dur / 2), e.getEntityLiving());
			}
		}
	}

}
