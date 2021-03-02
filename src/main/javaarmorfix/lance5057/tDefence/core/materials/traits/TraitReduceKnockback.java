package lance5057.tDefence.core.materials.traits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lance5057.tDefence.core.tools.TDToolEvents;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class TraitReduceKnockback extends AbstractTDTrait {

	List<IBlockState> blocks = new ArrayList<IBlockState>();
	float percentReduced;

	public TraitReduceKnockback(String name, TextFormatting darkPurple, float percentReduced, IBlockState... iblocks) {
		super(name, darkPurple);
		blocks.addAll(Arrays.asList(iblocks));
	}
	
	public TraitReduceKnockback(String name, int darkPurple, float percentReduced, IBlockState... iblocks) {
		super(name, darkPurple);
		blocks.addAll(Arrays.asList(iblocks));
	}
	
	public TraitReduceKnockback(String name, TextFormatting darkPurple, float percentReduced) {
		super(name, darkPurple);
	}

	@Override
	public void onArmorTick(ItemStack tool, World world, EntityPlayer player) {
		IAttributeInstance att = player.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
		boolean block = checkBlock(player);
		if(blocks.isEmpty())
		{
			block = true;
		}
		
		if(!att.hasModifier(TDToolEvents.td_knockback))
		{
			if(block)
				att.applyModifier(TDToolEvents.td_knockback);
		}
	}

	private boolean checkBlock(EntityLivingBase e) {
		IBlockState b = e.world.getBlockState(new BlockPos(e.posX, e.posY - 1, e.posZ));
		for (IBlockState bl : blocks) {
			if (bl.equals(b))
				return true;
		}
		return false;
	}
}
