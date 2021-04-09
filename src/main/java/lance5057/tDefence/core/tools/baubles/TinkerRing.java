package lance5057.tDefence.core.tools.baubles;

import java.util.List;

import baubles.api.BaubleType;
import lance5057.tDefence.core.materials.TDMaterials;
import lance5057.tDefence.core.parts.TDParts;
import lance5057.tDefence.core.tools.bases.BaubleTool;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.tools.TinkerMaterials;

public class TinkerRing extends BaubleTool
{
	public TinkerRing()
	{
		super(BaubleType.RING,
				PartMaterialType.head(TDParts.ringShank),
				PartMaterialType.handle(TDParts.filigree),
				PartMaterialType.extra(TDParts.rivets));
		setUnlocalizedName("tinkersring");
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
		if (this.isInCreativeTab(tab)) {
			addDefaultSubItems(subItems, TinkerMaterials.iron, TinkerMaterials.iron, TinkerMaterials.wood);
		}
	}

	@Override
	public void onPlayerBaubleRender(ItemStack stack, EntityPlayer player, RenderType type, float partialTicks) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NBTTagCompound setupTexture(List<Material> materials) {
		// TODO Auto-generated method stub
		return null;
	}

}
