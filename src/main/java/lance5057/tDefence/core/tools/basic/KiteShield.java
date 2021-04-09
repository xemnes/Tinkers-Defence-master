package lance5057.tDefence.core.tools.basic;

import c4.conarm.common.ConstructsRegistry;
import lance5057.tDefence.core.library.ShieldNBT;
import lance5057.tDefence.core.materials.stats.ShieldMaterialStats;
import lance5057.tDefence.core.parts.TDParts;
import lance5057.tDefence.core.tools.bases.ShieldCore;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.tconstruct.common.TinkerNetwork;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.tools.TinkerTools;
import slimeknights.tconstruct.tools.common.entity.EntityArrow;
import slimeknights.tconstruct.tools.common.network.EntityMovementChangePacket;

import java.util.List;

public class KiteShield extends ShieldCore
{
	float induceDamage = 0;

	public KiteShield()
	{
		super(PartMaterialType.handle(TinkerTools.toughToolRod),
			new PartMaterialType(ConstructsRegistry.armorPlate, ShieldMaterialStats.TYPE),
			new PartMaterialType(ConstructsRegistry.armorPlate, ShieldMaterialStats.TYPE),
			PartMaterialType.extra(TDParts.rivets));
		setUnlocalizedName("kiteshield");
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

	@SubscribeEvent
	public void reflectProjectiles(LivingAttackEvent event) {
		// only blockable projectile damage
		if(event.getSource().isUnblockable() || !event.getSource().isProjectile() || event.getSource().getImmediateSource() == null) {
			return;
		}
		if(!shouldBlockDamage(event.getEntityLiving())) {
			return;
		}

		EntityPlayer player = (EntityPlayer) event.getEntityLiving();
		ItemStack kiteshield = player.getActiveItemStack();

		// ensure the player is looking at the projectile (aka not getting shot into the back)
		Entity projectile = event.getSource().getImmediateSource();
		Vec3d motion = new Vec3d(projectile.motionX, projectile.motionY, projectile.motionZ);
		Vec3d look = player.getLookVec();

		// this gives a factor of how much we're looking at the incoming arrow
		double strength = -look.dotProduct(motion.normalize());
		// we're looking away. oh no.
		if(strength < 0.1) {
			return;
		}

		// caught that bastard! block it!
		event.setCanceled(true);

		// and return it to the sender
		// calc speed of the projectile
		double speed = projectile.motionX * projectile.motionX + projectile.motionY * projectile.motionY + projectile.motionZ * projectile.motionZ;
		speed = Math.sqrt(speed);
		speed += 0.2f; // we add a bit speed

		// and redirect it to where the player is looking
		projectile.motionX = look.x * speed;
		projectile.motionY = look.y * speed;
		projectile.motionZ = look.z * speed;

		projectile.rotationYaw = (float) (Math.atan2(projectile.motionX, projectile.motionZ) * 180.0D / Math.PI);
		projectile.rotationPitch = (float) (Math.atan2(projectile.motionY, speed) * 180.0D / Math.PI);

		// notify clients from change, otherwise people will get veeeery confused
		TinkerNetwork.sendToAll(new EntityMovementChangePacket(projectile));

		// special treatement for arrows
		if(projectile instanceof EntityArrow) {
			((EntityArrow) projectile).shootingEntity = player;

			// the inverse is done when the event is cancelled in arrows etc.
			// we reverse it so it has no effect. yay
			projectile.motionX /= -0.10000000149011612D;
			projectile.motionY /= -0.10000000149011612D;
			projectile.motionZ /= -0.10000000149011612D;
		}

		// use durability equal to the damage prevented
		ToolHelper.damageTool(kiteshield, (int) event.getAmount(), player);
	}

	@Override
	public float PercentBlockedMultiplier() {
		return 1.0f;
	}

	protected ShieldNBT buildDefaultShieldTag(List<Material> materials) {
		ShieldNBT data = new ShieldNBT();

		if(materials.size() >= 2) {
			HandleMaterialStats handle = materials.get(0).getStatsOrUnknown(MaterialTypes.HANDLE);
			ShieldMaterialStats head = materials.get(1).getStatsOrUnknown(ShieldMaterialStats.TYPE);
			// start with head
			data.head(this, head);

			// add in accessoires if present
			if(materials.size() >= 3) {
				ExtraMaterialStats binding = materials.get(2).getStatsOrUnknown(MaterialTypes.EXTRA);
				data.extra(binding);
			}

			// calculate handle impact
			data.handle(handle);
		}

		// 3 free modifiers
		data.modifiers = 3;

		return data;
	}

}
