package lance5057.tDefence.core.library.materialutilities;

import java.io.PrintWriter;

import org.apache.commons.lang3.StringUtils;

import lance5057.tDefence.Reference;
import lance5057.tDefence.TDFluids;
import lance5057.tDefence.TinkersDefence;
import lance5057.tDefence.core.library.OutputWikiPages;
import lance5057.tDefence.core.materials.stats.ShieldMaterialStats;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.fluid.FluidMolten;
import slimeknights.tconstruct.library.materials.BowMaterialStats;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;

public class MeltableMaterial implements MaterialBase {

	public FluidMolten fluid;
	public HeadMaterialStats head;
	public HandleMaterialStats handle;
	public ShieldMaterialStats shield;
	public ExtraMaterialStats extra;
	public BowMaterialStats bow;

	int temp;

	public Item ingot;
	public Item nugget;

	public Block block;
	public Item itemBlock;
	boolean hasBlockTexture;

	public MeltableMaterial(int temp, HeadMaterialStats head, HandleMaterialStats handle, ExtraMaterialStats extra,
			ShieldMaterialStats shield, BowMaterialStats bow) {
		this(temp, head, handle, extra, shield, bow, false);
	}

	public MeltableMaterial(int temp, HeadMaterialStats head, HandleMaterialStats handle, ExtraMaterialStats extra,
			ShieldMaterialStats shield, BowMaterialStats bow, boolean hasBlockTexture) {
		this.temp = temp;
		this.head = head;
		this.handle = handle;
		this.shield = shield;
		this.extra = extra;
		this.bow = bow;

		this.hasBlockTexture = hasBlockTexture;
	}

	@Override
	public void setupPre(MaterialHelper mat) {

		fluid = TDFluids.fluidMetal(mat.mat.getIdentifier(), mat.color, temp);

		mat.mat.setCraftable(false).setCastable(true);
		mat.mat.setFluid(fluid);

		if (head != null)
			TinkerRegistry.addMaterialStats(mat.mat, head);
		if (handle != null)
			TinkerRegistry.addMaterialStats(mat.mat, handle);
		if (extra != null)
			TinkerRegistry.addMaterialStats(mat.mat, extra);
		if (shield != null)
			TinkerRegistry.addMaterialStats(mat.mat, shield);
		if (bow != null)
			TinkerRegistry.addMaterialStats(mat.mat, bow);
	}

	@Override
	public void setupPost(MaterialHelper mat) {
		OreDictionary.registerOre("ingot" + StringUtils.capitalize(mat.mat.identifier), new ItemStack(ingot));
		OreDictionary.registerOre("nugget" + StringUtils.capitalize(mat.mat.identifier), new ItemStack(nugget));
		OreDictionary.registerOre("block" + StringUtils.capitalize(mat.mat.identifier), new ItemStack(block));

		mat.mat.addItem(nugget, 1, Material.VALUE_Nugget);
		mat.mat.addItem(ingot, 1, Material.VALUE_Ingot);
		mat.mat.addItem(block, Material.VALUE_Block); 
	}

	@Override
	public void setupClient(MaterialHelper mat) {
		TinkersDefence.proxy.registerItemColorHandler(mat.color, ingot);
		TinkersDefence.proxy.registerItemColorHandler(mat.color, nugget);
		if (!hasBlockTexture) {
			TinkersDefence.proxy.registerBlockColorHandler(mat.color, block);
			TinkersDefence.proxy.registerItemColorHandler(mat.color, Item.getItemFromBlock(block));
		}
	}

	

	public <T extends Block> T registerBlock(T block, String name) {
		block.setUnlocalizedName(Reference.MOD_ID + "." + name);
		block.setRegistryName(Reference.MOD_ID + "." + name);
		Item ib = new ItemBlock(block).setRegistryName(block.getRegistryName());
		ForgeRegistries.BLOCKS.register(block);
		ForgeRegistries.ITEMS.register(ib);
		return block;
	}

	@Override
	public void setupIntegration(MaterialIntegration mi) {
		mi.fluid = fluid;

	}

	@Override
	public void setupModels(MaterialHelper mat) {
		TinkersDefence.proxy.registerItemRenderer(ingot, 0, "ingot");
		TinkersDefence.proxy.registerItemRenderer(nugget, 0, "nugget");
		if (!hasBlockTexture) {
			TinkersDefence.proxy.registerBlockRenderer(block, "block");
			TinkersDefence.proxy.registerItemBlockRenderer(block, 0, "componentblock");
		}
	}

	@Override
	public void setupInit(MaterialHelper mat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setupWiki(MaterialHelper mat, PrintWriter out) {
		out.write("### Material Info \n\n");
		out.write("Melting Temp: " + temp + "\n\n");

		OutputWikiPages.createMaterialOutput(head, handle, shield, extra, bow, out);
	}
}
