package lance5057.tDefence.core.addons.botania;

import lance5057.tDefence.TinkersDefence;
import lance5057.tDefence.core.addons.botania.modifiers.ModMana;
import lance5057.tDefence.core.library.ModuleBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.mantle.util.RecipeMatch;

public class AddonBotania extends ModuleBase {
	public AddonBotania() {
	}

	public static Item manasteelCore;
	public static Item elementiumCore;
	public static Item terrasteelCore;
	public static Item corpseIvy;

//	public static ModScabbing scabbing = new ModScabbing();
//	public static ModWill will = new ModWill();
	
	public static ModMana mod_mana = new ModMana();
//	public static ModPixies mod_pixies = new ModPixies();
//	public static ModBeam mod_beam = new ModBeam();
//	public static ModCorpseIvy mod_corpseIvy = new ModCorpseIvy();

//	public static MaterialHelper blankslate = new MaterialHelper("blankslate", 0x5f3d3d,
//			new HeadMaterialStats(120, 4.00f, 3.00f, HarvestLevels.STONE), new HandleMaterialStats(0.50f, -50),
//			new ExtraMaterialStats(20), new ShieldMaterialStats(120 / 4, 30), DefenceMaterials.whyWouldYouMakeABowOutOfThis,
//			new HelmMaterialStats(120, 2, 0, 0), new ChestMaterialStats(120, 4, 0, 0),
//			new LegsMaterialStats(120, 3, 0, 0), new FeetMaterialStats(120, 2, 0, 0));
//
//	public static MaterialHelper reinforcedslate = new MaterialHelper("reinforcedslate", 0x5f3d3d,
//			new HeadMaterialStats(240, 4.50f, 3.50f, HarvestLevels.IRON), new HandleMaterialStats(0.60f, 0),
//			new ExtraMaterialStats(40), new ShieldMaterialStats(240 / 4, 40), DefenceMaterials.whyWouldYouMakeABowOutOfThis,
//			new HelmMaterialStats(240, 2, 0, 5), new ChestMaterialStats(240, 5, 0, 5),
//			new LegsMaterialStats(240, 3, 0, 5), new FeetMaterialStats(240, 2, 0, 5));
//
//	public static MaterialHelper imbuedslate = new MaterialHelper("imbuedslate", 0x5f3d3d,
//			new HeadMaterialStats(360, 5.0f, 4.0f, HarvestLevels.DIAMOND), new HandleMaterialStats(0.70f, 50),
//			new ExtraMaterialStats(60), new ShieldMaterialStats(360 / 4, 50), DefenceMaterials.whyWouldYouMakeABowOutOfThis,
//			new HelmMaterialStats(360, 3, 0, 10), new ChestMaterialStats(360, 6, 0, 10),
//			new LegsMaterialStats(360, 6, 0, 10), new FeetMaterialStats(360, 3, 0, 10));
//
//	public static MaterialHelper demonicslate = new MaterialHelper("demonicslate", 0x5f3d3d,
//			new HeadMaterialStats(480, 5.50f, 5.50f, HarvestLevels.OBSIDIAN), new HandleMaterialStats(0.80f, 100),
//			new ExtraMaterialStats(80), new ShieldMaterialStats(480 / 4, 60), DefenceMaterials.whyWouldYouMakeABowOutOfThis,
//			new HelmMaterialStats(480, 3, 0, 15), new ChestMaterialStats(480, 6, 1, 15),
//			new LegsMaterialStats(480, 6, 1, 15), new FeetMaterialStats(480, 3, 0, 15));
//
//	public static MaterialHelper etherealslate = new MaterialHelper("etherealslate", 0x5f3d3d,
//			new HeadMaterialStats(600, 6.0f, 6.0f, HarvestLevels.COBALT), new HandleMaterialStats(0.90f, 150),
//			new ExtraMaterialStats(100), new ShieldMaterialStats(600 / 4, 70), DefenceMaterials.whyWouldYouMakeABowOutOfThis,
//			new HelmMaterialStats(600, 2, 1, 20), new ChestMaterialStats(600, 5, 2, 20),
//			new LegsMaterialStats(600, 3, 2, 20), new FeetMaterialStats(600, 2, 1, 20));

	@Override
	public void preInit(FMLPreInitializationEvent e) {
//		bloodyBandages = new Item();
		
		manasteelCore = new Item();
		elementiumCore = new Item();
		terrasteelCore = new Item();
		corpseIvy = new Item();
		
//		this.setupItem(manasteelCore, "manasteelcore");
//		this.setupItem(elementiumCore, "elementiumcore");
//		this.setupItem(terrasteelCore, "terrasteelcore");
//		this.setupItem(corpseIvy, "corpseivy");
//
//		this.setupItem(bloodyBandages, "bloodybandages");

		// DefenceMaterials.itemList.add(bloodyBandages);

//		DefenceMaterials.materials.add(this.blankslate);
//		DefenceMaterials.materials.add(this.reinforcedslate);
//		DefenceMaterials.materials.add(this.imbuedslate);
//		DefenceMaterials.materials.add(this.demonicslate);
//		DefenceMaterials.materials.add(this.etherealslate);

		// TinkerRegistry.registerModifier(scabbing);

		TinkersDefence.proxy.registerItemRenderer(manasteelCore, 0, "manasteelcore");
		TinkersDefence.proxy.registerItemRenderer(elementiumCore, 0, "elementiumcore");
		TinkersDefence.proxy.registerItemRenderer(terrasteelCore, 0, "terrasteelcore");
		TinkersDefence.proxy.registerItemRenderer(corpseIvy, 0, "corpseivy");

		TinkersDefence.proxy.registerModifierModel(mod_mana);
//		TinkersDefence.proxy.registerModifierModel(elementiumCore);
//		TinkersDefence.proxy.registerModifierModel(terrasteelCore);
//		TinkersDefence.proxy.registerModifierModel(corpseIvy);
	}

	@Override
	public void init(FMLInitializationEvent e) {
		mod_mana.addRecipeMatch(new RecipeMatch.Item(new ItemStack(this.manasteelCore), 1));
//		will.addRecipeMatch(new RecipeMatch.Item(new ItemStack(RegistrarBloodMagicItems.SENTIENT_SWORD), 1));
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		// TODO Auto-generated method stub

	}

//	@Override
//	public void registerItems(Register<Item> event) {
//		IForgeRegistry r = event.getRegistry();
//		r.register(this.manasteelCore);
//		r.register(this.elementiumCore);
//		r.register(this.terrasteelCore);
//		r.register(this.corpseIvy);
//	}
//
//	@Override
//	public void registerBlocks(Register<Block> event) {
//		// TODO Auto-generated method stub
//
//	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {

	}

}
