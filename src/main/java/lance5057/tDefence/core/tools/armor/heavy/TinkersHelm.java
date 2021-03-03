package lance5057.tDefence.core.tools.armor.heavy;

import java.util.List;

import c4.conarm.common.ConstructsRegistry;
import lance5057.tDefence.Reference;
import lance5057.tDefence.TDConfig;
import lance5057.tDefence.TinkersDefence;
import lance5057.tDefence.core.library.ArmorNBT;
import lance5057.tDefence.core.library.ArmorTags;
import lance5057.tDefence.core.library.ArmorTextureBuilder;
import lance5057.tDefence.core.materials.TDMaterials;
import lance5057.tDefence.core.materials.stats.ArmorMaterialStats;
import lance5057.tDefence.core.materials.stats.FabricMaterialStats;
import lance5057.tDefence.core.materials.stats.HelmMaterialStats;
import lance5057.tDefence.core.parts.TDParts;
import lance5057.tDefence.core.tools.TDToolEvents;
import lance5057.tDefence.core.tools.armor.renderers.heavy.ModelTinkersHelm;
import lance5057.tDefence.core.tools.bases.ArmorCore;
import lance5057.tDefence.util.ArmorTagUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;

public class TinkersHelm extends ArmorCore {
	public TinkersHelm() {
		super(EntityEquipmentSlot.HEAD,
				new PartMaterialType(ConstructsRegistry.helmetCore, HelmMaterialStats.TYPE),
				new PartMaterialType(TDParts.chainmail, HelmMaterialStats.TYPE),
				PartMaterialType.handle(TDParts.filigree),
				PartMaterialType.extra(ConstructsRegistry.armorPlate),
				new PartMaterialType(TDParts.fabric, FabricMaterialStats.TYPE));
		setUnlocalizedName("tinkershelm");
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
		if (this.isInCreativeTab(tab)) {
			addDefaultSubItems(subItems, null, null, null, null,
					TDMaterials.white.mat);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public NBTTagCompound setupTexture(List<Material> materials) {
		NBTTagCompound base = new NBTTagCompound();

		ResourceLocation rc = ArmorTextureBuilder.createArmorTexture("helm",
				new String[] { "top", "chain", "trim", "plate", "cloth" }, materials, 64, 64);

		if (rc != null) {
			base.setString(ArmorTags.TexLoc, rc.toString());
			return base;
		}
		return null;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot,
			ModelBiped _default) {
		//return new ModelTinkerTabard(itemStack);
		return new ModelTinkersHelm(itemStack);
	}

	@Override
	public NBTTagCompound buildTag(List<Material> materials) {
		ArmorNBT data = buildDefaultTag(materials);
		return data.get();
	}

	@Override
	public EntityEquipmentSlot getArmorSlot(ItemStack stack, EntityEquipmentSlot armorType) {
		return EntityEquipmentSlot.HEAD;
	}

	@Override
	public float durabilityMultiplier() {
		// TODO Auto-generated method stub
		return 2.2f;
	}

	@Override
	public float armorMultiplier() {
		// TODO Auto-generated method stub
		return 1.6f;
	}

	@Override
	public float potencyMultiplier() {
		// TODO Auto-generated method stub
		return 0.5f;
	}

	@Override
	protected ArmorNBT buildDefaultTag(List<Material> materials) {
		ArmorNBT data = new ArmorNBT();

		ArmorMaterialStats head2 = materials.get(0).getStatsOrUnknown(HelmMaterialStats.TYPE);
		ArmorMaterialStats head = materials.get(1).getStatsOrUnknown(HelmMaterialStats.TYPE);
		HandleMaterialStats handle = materials.get(2).getStatsOrUnknown(MaterialTypes.HANDLE);
		ExtraMaterialStats extra = materials.get(3).getStatsOrUnknown(MaterialTypes.EXTRA);
		// start with head
		data.head(this, head, head2);
		data.extra(extra);
		data.handle(handle);

		data.modifiers = 5;

		return data;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void renderHelmetOverlay(ItemStack stack, EntityPlayer player,
			net.minecraft.client.gui.ScaledResolution resolution, float partialTicks) {
		if (TinkersDefence.config.armor.HelmOverlay) {

			if (ArmorTagUtil.getVisor(stack)) {
				GlStateManager.disableDepth();
				GlStateManager.depthMask(false);
				GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
						GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
						GlStateManager.DestFactor.ZERO);
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.disableAlpha();

				if (TDToolEvents.overlayJumpTimer <= 0) {

					Minecraft.getMinecraft().getTextureManager()
							.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/misc/helm_overlay.png"));
				} else {
					if (!TDConfig.IAmEasilyStartled)
						Minecraft.getMinecraft().getTextureManager()
								.bindTexture(new ResourceLocation("textures/misc/pumpkinblur.png"));
					TDToolEvents.overlayJumpTimer--;
				}

				Tessellator tessellator = Tessellator.getInstance();
				BufferBuilder bufferbuilder = tessellator.getBuffer();
				bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
				bufferbuilder.pos(0.0D, (double) resolution.getScaledHeight(), -90.0D).tex(0.0D, 1.0D).endVertex();
				bufferbuilder.pos((double) resolution.getScaledWidth(), (double) resolution.getScaledHeight(), -90.0D)
						.tex(1.0D, 1.0D).endVertex();
				bufferbuilder.pos((double) resolution.getScaledWidth(), 0.0D, -90.0D).tex(1.0D, 0.0D).endVertex();
				bufferbuilder.pos(0.0D, 0.0D, -90.0D).tex(0.0D, 0.0D).endVertex();
				tessellator.draw();
				GlStateManager.depthMask(true);
				GlStateManager.enableDepth();
				GlStateManager.enableAlpha();
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			}
		}
	}

	public static boolean canTick(long time,int interval,int offset)
	{
		return time%interval==offset;
	}
	public static boolean canTick(World world,int interval,int offset)
	{
		return canTick(world.getTotalWorldTime(),interval,offset);
	}

	@Override
	public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		super.onUpdate(tool, world, entity, itemSlot, isSelected);

		if(canTick(world,5,1) && entity instanceof EntityLivingBase)
		{
			EntityLivingBase enlb=(EntityLivingBase)entity;
			ItemStack hand = enlb.getHeldItemMainhand();
			ItemStack head = enlb.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
			if(world.isRemote) return;
			for (ItemStack s : enlb.getArmorInventoryList()) {
				if (s != null && !s.isEmpty() && !head.isEmpty() && head.getItem() instanceof TinkersHelm) {
					boolean found = false;
					Item handItem = hand.getItem();
					for (String itemName : TinkersDefence.config.armor.visorItemWhitelist) {
						if (handItem.getRegistryName().toString().equals(itemName) ||
							handItem instanceof ItemSword ||
							handItem instanceof ItemAxe ||
							handItem instanceof ItemBow ||
							enlb.isActiveItemStackBlocking()) {
								ArmorTagUtil.setVisor(s, true);
								ArmorTagUtil.setVisorTime(s, 0.0f);
								s.serializeNBT();
								found = true;
						}
					}
					if (!found) {
						ArmorTagUtil.setVisor(s, false);
						ArmorTagUtil.setVisorTime(s, 0.0f);
						s.serializeNBT();
					}
				}
			}
		}
	}

	@Override
	public String getArmorType() {
		return "helm";
	}
}
