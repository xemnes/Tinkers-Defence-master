package lance5057.tDefence.core.tools.bases;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.BlockDispenser;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolNBT;

//@Optional.InterfaceList({@Optional.Interface(modid = "battlegear2", iface = "mods.battlegear2.api.ISheathed"), @Optional.Interface(modid = "battlegear2", iface = "mods.battlegear2.api.shield.IArrowCatcher"), @Optional.Interface(modid = "battlegear2", iface = "mods.battlegear2.api.shield.IArrowDisplay"), @Optional.Interface(modid = "battlegear2", iface = "mods.battlegear2.api.shield.IShield")})
public class Shield extends ToolCore //implements IShield, ISheathed, IArrowCatcher, IArrowDisplay
{
	public Shield(PartMaterialType... requiredComponents)
	{
		super(requiredComponents);
		this.addPropertyOverride(new ResourceLocation("block"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
            	float i = entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
                return i;
            }
        });
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, ItemArmor.DISPENSER_BEHAVIOR);
	}
	
	/**
	 * returns the action that specifies what animation to play when the items
	 * is being used
	 */
	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.BLOCK;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

	/**
	 * How long it takes to use or consume an item
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 72000;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
	{
		super.onUpdate(stack, world, entity, par4, par5);
		if(entity instanceof EntityPlayerSP)
		{
			final EntityPlayerSP player = (EntityPlayerSP) entity;
			final ItemStack usingItem = player.getActiveItemStack();
			if(usingItem != null && usingItem.getItem() == this)
			{
				player.movementInput.moveForward *= 2.5F;
				player.movementInput.moveStrafe *= 2.5F;
			}
		}
	}

	protected String getHarvestType()
	{
		return null;
	}

	@Override
	public float damagePotential() {
		return 0.0f;
	}

	@Override
	public double attackSpeed() {
		return 0;
	}

	@Override
	public NBTTagCompound buildTag(
			List<slimeknights.tconstruct.library.materials.Material> materials) {
		ToolNBT data = buildDefaultTag(materials);
		return data.get();
	}
}
