package lance5057.tDefence.core.materials.traits;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class TraitHydrophobic extends AbstractTDTrait
{
	public TraitHydrophobic() {
		super("hydrophobic", TextFormatting.WHITE);
		// TODO Auto-generated constructor stub
	}

	@Override
	  public void onArmorTick(ItemStack tool, World world, EntityPlayer player) {
		if(player.isInWater())
		{
			player.motionY += 0.05;
		}
		BlockPos pos = new BlockPos(player.posX, player.posY-1, player.posZ);
		IBlockState block = world.getBlockState(pos);
		if(block == Blocks.WATER.getDefaultState() || block == Blocks.FLOWING_WATER)
		{
			if(player.motionY < 0)
				player.motionY = 0;
			player.posY = Math.floor(player.posY);
			player.fallDistance = 0;
		}
	  }
}
