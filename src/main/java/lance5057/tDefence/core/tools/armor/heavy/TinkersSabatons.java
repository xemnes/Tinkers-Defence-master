package lance5057.tDefence.core.tools.armor.heavy;

import java.util.List;

import c4.conarm.common.ConstructsRegistry;
import lance5057.tDefence.core.library.ArmorNBT;
import lance5057.tDefence.core.library.ArmorTags;
import lance5057.tDefence.core.library.ArmorTextureBuilder;
import lance5057.tDefence.core.materials.TDMaterials;
import lance5057.tDefence.core.materials.stats.ArmorMaterialStats;
import lance5057.tDefence.core.materials.stats.FabricMaterialStats;
import lance5057.tDefence.core.materials.stats.FeetMaterialStats;
import lance5057.tDefence.core.parts.TDParts;
import lance5057.tDefence.core.tools.armor.renderers.heavy.ModelTinkersSabatons;
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

public class TinkersSabatons extends ArmorCore {
	public TinkersSabatons() {
		super(EntityEquipmentSlot.FEET,
				new PartMaterialType(ConstructsRegistry.armorPlate, FeetMaterialStats.TYPE),
				PartMaterialType.handle(TDParts.rivets),
				PartMaterialType.extra(TDParts.filigree),
				new PartMaterialType(ConstructsRegistry.bootsCore, FeetMaterialStats.TYPE),
				new PartMaterialType(TDParts.fabric, FabricMaterialStats.TYPE));
		setUnlocalizedName("tinkerssabatons");
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
		if (this.isInCreativeTab(tab)) {
			addDefaultSubItems(subItems, null, null, null, null, TDMaterials.white.mat);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public NBTTagCompound setupTexture(List<Material> materials) {
		NBTTagCompound base = new NBTTagCompound();

		ResourceLocation rc = ArmorTextureBuilder.createArmorTexture("sabatons",
				new String[] { "plates", "rivets", "trim", "caps", "soles" }, materials, 96, 96);

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
		return new ModelTinkersSabatons(itemStack);
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
	public float durabilityMultiplier() {
		// TODO Auto-generated method stub
		return 2.1f;
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

		ArmorMaterialStats head2 = materials.get(0).getStatsOrUnknown(FeetMaterialStats.TYPE);
		ArmorMaterialStats head = materials.get(1).getStatsOrUnknown(FeetMaterialStats.TYPE);
		HandleMaterialStats handle = materials.get(2).getStatsOrUnknown(MaterialTypes.HANDLE);
		ExtraMaterialStats extra = materials.get(3).getStatsOrUnknown(MaterialTypes.EXTRA);
		// start with head
		data.head(this, head, head2);
		data.extra(extra);
		data.handle(handle);

		data.modifiers = 5;

		return data;
	}

	@Override
	public String getArmorType() {
		return "sabatons";
	}
}
