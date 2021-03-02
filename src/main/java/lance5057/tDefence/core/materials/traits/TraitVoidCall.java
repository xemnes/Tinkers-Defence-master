package lance5057.tDefence.core.materials.traits;

import lance5057.tDefence.core.library.ArmorHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class TraitVoidCall extends AbstractTDTrait
{
	public TraitVoidCall() {
		super("voidcall", TextFormatting.WHITE);
	}

	@Override
	  public void onArmorTick(ItemStack tool, World world, EntityPlayer player) {
		if(player.posY <= 0)
		{
			player.motionY+=3.0;
			ArmorHelper.damageTool(tool, 10, player);
		}
	  }
}
