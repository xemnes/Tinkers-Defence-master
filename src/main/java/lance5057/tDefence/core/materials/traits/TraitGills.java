package lance5057.tDefence.core.materials.traits;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class TraitGills extends AbstractTDTrait {

	public TraitGills() {
		super("gills", TextFormatting.AQUA);
	}

	@Override
	public void onArmorTick(ItemStack tool, World world, EntityPlayer player) {
		int air = player.getAir();
		if (air > 0 && air < 300) {
			int chance = world.rand.nextInt(1000);
			if(chance <= 5) //20%
			{
				player.setAir(player.getAir() + 30);
			}
		}
	}
}
