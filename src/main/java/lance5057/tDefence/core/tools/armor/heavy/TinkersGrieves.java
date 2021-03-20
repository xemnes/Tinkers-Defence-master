package lance5057.tDefence.core.tools.armor.heavy;

import java.util.List;

import c4.conarm.common.ConstructsRegistry;
import lance5057.tDefence.core.library.ArmorNBT;
import lance5057.tDefence.core.library.ArmorTags;
import lance5057.tDefence.core.library.ArmorTextureBuilder;
import lance5057.tDefence.core.materials.TDMaterials;
import lance5057.tDefence.core.materials.stats.ArmorMaterialStats;
import lance5057.tDefence.core.materials.stats.FabricMaterialStats;
import lance5057.tDefence.core.materials.stats.LegsMaterialStats;
import lance5057.tDefence.core.parts.TDParts;
import lance5057.tDefence.core.tools.armor.renderers.heavy.ModelTinkersGrieves;
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
import slimeknights.tconstruct.tools.TinkerMaterials;

public class TinkersGrieves extends ArmorCore {
	public TinkersGrieves() {
		super(EntityEquipmentSlot.LEGS,
				new PartMaterialType(ConstructsRegistry.leggingsCore, LegsMaterialStats.TYPE),
				new PartMaterialType(TDParts.chainmail, LegsMaterialStats.TYPE),
				PartMaterialType.handle(TDParts.filigree),
				PartMaterialType.extra(ConstructsRegistry.armorTrim),
				new PartMaterialType(TDParts.fabric, FabricMaterialStats.TYPE));
		setUnlocalizedName("tinkersgrieves");
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
		if (this.isInCreativeTab(tab)) {
			addDefaultSubItems(subItems, TinkerMaterials.iron, TinkerMaterials.iron, TinkerMaterials.iron, TinkerMaterials.iron,
					TDMaterials.brown.mat);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public NBTTagCompound setupTexture(List<Material> materials) {
		NBTTagCompound base = new NBTTagCompound();

		ResourceLocation rc = ArmorTextureBuilder.createArmorTexture("grieves",
				new String[] { "plate", "chain", "trim", "clasp", "cloth" }, materials, 96, 96);

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
		return new ModelTinkersGrieves(itemStack);
	}

	@Override
	public NBTTagCompound buildTag(List<Material> materials) {
		ArmorNBT data = buildDefaultTag(materials);
		return data.get();
	}

	@Override
	public EntityEquipmentSlot getArmorSlot(ItemStack stack, EntityEquipmentSlot armorType) {
		return EntityEquipmentSlot.LEGS;
	}

	@Override
	public float durabilityMultiplier() {
		// TODO Auto-generated method stub
		return 2.3f;
	}

	@Override
	public float armorMultiplier() {
		// TODO Auto-generated method stub
		return 1.7f;
	}

	@Override
	public float potencyMultiplier() {
		// TODO Auto-generated method stub
		return 0.5f;
	}

	@Override
	protected ArmorNBT buildDefaultTag(List<Material> materials) {
		ArmorNBT data = new ArmorNBT();

		ArmorMaterialStats head2 = materials.get(0).getStatsOrUnknown(LegsMaterialStats.TYPE);
		ArmorMaterialStats head = materials.get(1).getStatsOrUnknown(LegsMaterialStats.TYPE);
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
		return "grieves";
	}
}
