package lance5057.tDefence.core.tools.armor.chain;

import java.util.List;

import lance5057.tDefence.core.library.ArmorNBT;
import lance5057.tDefence.core.library.ArmorTags;
import lance5057.tDefence.core.library.ArmorTextureBuilder;
import lance5057.tDefence.core.materials.TDMaterials;
import lance5057.tDefence.core.materials.stats.*;
import lance5057.tDefence.core.parts.TDParts;
import lance5057.tDefence.core.tools.armor.renderers.chain.ModelTinkersChainboots;
import lance5057.tDefence.core.tools.bases.ArmorCore;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import c4.conarm.common.ConstructsRegistry;

public class TinkersChainboots extends ArmorCore {
	public TinkersChainboots() {
		super(EntityEquipmentSlot.FEET,
				new PartMaterialType(TDParts.chainmail, FeetMaterialStats.TYPE),
				PartMaterialType.handle(ConstructsRegistry.bootsCore),
				new PartMaterialType(TDParts.fabric, FabricMaterialStats.TYPE),
				PartMaterialType.extra(TDParts.rivets));
		setUnlocalizedName("tinkerschainboots");
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
		if (this.isInCreativeTab(tab)) {
			addDefaultSubItems(subItems, null, null, TDMaterials.white.mat, null);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public NBTTagCompound setupTexture(List<Material> materials) {
		NBTTagCompound base = new NBTTagCompound();

		ResourceLocation rc = ArmorTextureBuilder.createArmorTexture("chainboots",
				new String[] { "chain", "plate", "cloth", "string" }, materials, 32, 32);

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
		return new ModelTinkersChainboots(armorSlot);
	}

	@Override
	public NBTTagCompound buildTag(List<Material> materials) {
		ArmorNBT data = buildDefaultTag(materials);
		return data.get();
	}

	@Override
	public EntityEquipmentSlot getArmorSlot(ItemStack stack, EntityEquipmentSlot armorType) {
		return EntityEquipmentSlot.FEET;
	}

	@Override
	protected ArmorNBT buildDefaultTag(List<Material> materials) {
		ArmorNBT data = new ArmorNBT();

		ArmorMaterialStats head = materials.get(0).getStatsOrUnknown(FeetMaterialStats.TYPE);
		HandleMaterialStats handle = materials.get(1).getStatsOrUnknown(MaterialTypes.HANDLE);
		ExtraMaterialStats extra = materials.get(2).getStatsOrUnknown(MaterialTypes.EXTRA);
		// start with head
		data.head(this, head);
		data.extra(extra);
		data.handle(handle);

		data.modifiers = 5;

		return data;
	}

	@Override
	public String getArmorType() {
		return "chainboots";
	}

	@Override
	public float durabilityMultiplier() {
		// TODO Auto-generated method stub
		return 1.0f;
	}

	@Override
	public float armorMultiplier() {
		// TODO Auto-generated method stub
		return 0.8f;
	}

	@Override
	public float potencyMultiplier() {
		// TODO Auto-generated method stub
		return 2.0f;
	}
}
