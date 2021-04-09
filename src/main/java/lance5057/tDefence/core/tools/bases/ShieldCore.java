package lance5057.tDefence.core.tools.bases;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import lance5057.tDefence.core.library.ArmorNBT;
import lance5057.tDefence.core.library.ArmorToolTipBuilder;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import lance5057.tDefence.core.library.ShieldNBT;
import lance5057.tDefence.core.library.ShieldToolTipBuilder;
import lance5057.tDefence.util.ShieldTagUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.traits.ITrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.library.utils.TooltipBuilder;

//@Optional.InterfaceList({@Optional.Interface(modid = "battlegear2", iface = "mods.battlegear2.api.ISheathed"), @Optional.Interface(modid = "battlegear2", iface = "mods.battlegear2.api.shield.IArrowCatcher"), @Optional.Interface(modid = "battlegear2", iface = "mods.battlegear2.api.shield.IArrowDisplay"), @Optional.Interface(modid = "battlegear2", iface = "mods.battlegear2.api.shield.IShield")})
public class ShieldCore extends ToolCore //implements IShield, ISheathed, IArrowCatcher, IArrowDisplay
{

	public ShieldCore(PartMaterialType... requiredComponents)
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
//		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, ItemArmor.DISPENSER_BEHAVIOR);


	}

	/**
	 * returns the action that specifies what animation to play when the items
	 * is being used
	 */
//	@Override
//	public EnumAction getItemUseAction(ItemStack par1ItemStack)
//	{
//		return EnumAction.BLOCK;
//	}


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

	@SubscribeEvent(priority = EventPriority.LOW) // lower priority so we get called later since we change tool NBT
	public void reducedDamageBlocked(LivingHurtEvent event, ItemStack stack) {
		// don't affect unblockable or magic damage or explosion damage
		// projectiles are handled in LivingAttackEvent
		if(event.getSource().isUnblockable() ||
				event.getSource().isMagicDamage() ||
				event.getSource().isProjectile() ||
				event.isCanceled()) {
			return;
		}
		if(!shouldBlockDamage(event.getEntityLiving())) {
			return;
		}

		EntityPlayer player = (EntityPlayer) event.getEntityLiving();
		ItemStack shield = player.getActiveItemStack();
		ShieldNBT nbt = ShieldTagUtil.getToolStats(stack);

		// got hit by something: reduce damage
		float damage = event.getAmount() < 2f ? 1 : Math.round(event.getAmount() / 2f);
		// reduce damage. After this event the damage will be halved again because we're blocking so we have to factor this in
		event.setAmount(event.getAmount() * nbt.percentBlocked);

		// reflect damage
		if(event.getSource().getTrueSource() != null) {
			damage = damage * 3 / nbt.percentBlocked;
		}
		player.playSound(SoundEvents.ITEM_SHIELD_BLOCK, 1.0F, 0.8F + player.world.rand.nextFloat() * 0.4F);
		ToolHelper.damageTool(shield, (int) damage, player);
	}

	protected boolean shouldBlockDamage(Entity entity) {
		// hit entity is a player?
		if(!(entity instanceof EntityPlayer)) {
			return false;
		}
		EntityPlayer player = (EntityPlayer) entity;
		// needs to be blocking with a shield
		if(!player.isActiveItemStackBlocking() || player.getActiveItemStack().getItem() != this) {
			return false;
		}

		// broken shield.
		return !ToolHelper.isBroken(player.getActiveItemStack());

	}

	@Nonnull
	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(@Nonnull EntityEquipmentSlot slot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(slot, stack);

		if(slot == EntityEquipmentSlot.MAINHAND && !ToolHelper.isBroken(stack)) {
			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", ToolHelper.getActualAttack(stack), 0));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", ToolHelper.getActualAttackSpeed(stack) - 4d, 0));
		}

		TinkerUtil.getTraitsOrdered(stack).forEach(trait -> trait.getAttributeModifiers(slot, stack, multimap));

		return multimap;
	}

	@Override
	public List<String> getInformation(ItemStack stack) {
		return getInformation(stack, true);
	}

	@Override
	public void getTooltip(ItemStack stack, List<String> tooltips) {
		if(ToolHelper.isBroken(stack)) {
			tooltips.add("" + TextFormatting.DARK_RED + TextFormatting.BOLD + getBrokenTooltip(stack));
		}
		super.getTooltip(stack, tooltips);
	}

	protected String getBrokenTooltip(ItemStack itemStack) {
		return Util.translate(TooltipBuilder.LOC_Broken);
	}

	@Override
	public void getTooltipDetailed(ItemStack stack, List<String> tooltips) {
		tooltips.addAll(getInformation(stack, false));
	}

	public List<String> getInformation(ItemStack stack, boolean detailed) {
		ShieldToolTipBuilder info = new ShieldToolTipBuilder(stack);

		info.addDurability(!detailed);
		info.addPercentBlocked();

		if(ToolHelper.getFreeModifiers(stack) > 0) {
			info.addFreeModifiers();
		}

		if(detailed) {
			info.addModifierInfo();
		}

		return info.getTooltip();
	}

	@Override
	public void getTooltipComponents(ItemStack stack, List<String> tooltips) {
		List<Material> materials = TinkerUtil.getMaterialsFromTagList(TagUtil.getBaseMaterialsTagList(stack));
		List<PartMaterialType> component = getRequiredComponents();

		if(materials.size() < component.size()) {
			return;
		}

		for(int i = 0; i < component.size(); i++) {
			PartMaterialType pmt = component.get(i);
			Material material = materials.get(i);

			// get (one possible) toolpart used to craft the thing
			Iterator<IToolPart> partIter = pmt.getPossibleParts().iterator();
			if(!partIter.hasNext()) {
				continue;
			}

			IToolPart part = partIter.next();
			ItemStack partStack = part.getItemstackWithMaterial(material);
			if(partStack != null) {
				// we have the part, add it
				tooltips.add(material.getTextColor() + TextFormatting.UNDERLINE + partStack.getDisplayName());

				Set<ITrait> usedTraits = Sets.newHashSet();
				// find out which stats and traits it contributes and add it to the tooltip
				for(IMaterialStats stats : material.getAllStats()) {
					if(pmt.usesStat(stats.getIdentifier())) {
						tooltips.addAll(stats.getLocalizedInfo());
						for(ITrait trait : pmt.getApplicableTraitsForMaterial(material)) {
							if(!usedTraits.contains(trait)) {
								tooltips.add(material.getTextColor() + trait.getLocalizedName());
								usedTraits.add(trait);
							}
						}
					}
				}
				tooltips.add("");
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

	public float PercentBlockedMultiplier() {
		return 0;
	}

	protected ShieldNBT buildDefaultShieldTag(List<Material> materials) {
		return null;
	}

	@Override
	public NBTTagCompound buildTag(
			List<slimeknights.tconstruct.library.materials.Material> materials) {
		ShieldNBT data = buildDefaultShieldTag(materials);
		return data.get();
	}
}
