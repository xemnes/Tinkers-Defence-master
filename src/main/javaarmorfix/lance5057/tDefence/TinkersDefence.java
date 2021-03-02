package lance5057.tDefence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;

import lance5057.tDefence.core.addons.actuallyadditions.AddonActuallyAdditions;
import lance5057.tDefence.core.addons.bloodmagic.AddonBloodMagic;
import lance5057.tDefence.core.addons.botania.AddonBotania;
import lance5057.tDefence.core.addons.ebwizardry.AddonEBWizardry;
import lance5057.tDefence.core.addons.toolleveling.AddonToolLeveling;
import lance5057.tDefence.core.events.TDEvents;
import lance5057.tDefence.core.library.ModuleBase;
import lance5057.tDefence.core.library.OutputWikiPages;
import lance5057.tDefence.core.library.book.TDBook;
import lance5057.tDefence.core.materials.TDMaterials;
import lance5057.tDefence.core.materials.TDTraits;
import lance5057.tDefence.core.modifiers.TDModifiers;
import lance5057.tDefence.core.parts.TDParts;
import lance5057.tDefence.core.tools.TDTools;
import lance5057.tDefence.core.workstations.TDWorkstations;
import lance5057.tDefence.core.worldgen.SpawnArmorOnMobs;
import lance5057.tDefence.proxy.CommonProxy;
import net.minecraft.item.Item;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import scala.reflect.internal.Trees.Modifiers;

@Mod(modid = Reference.MOD_ID,
		version = Reference.VERSION,
		name = Reference.MOD_NAME,
		dependencies = "required-after:tconstruct@[1.12-2.7.2.15,);" +
						"required-after:conarm@[1.2.5.9-F,);")
public class TinkersDefence {

	// public static int modGuiIndex = 0;
	// public static final int GUI_CREST_INV = modGuiIndex++;
	// public static final int GUI_ANVIL_INV = modGuiIndex++;
	// public static final int GUI_GUIDEBOOK = modGuiIndex++;
	// public static final int GUI_STRAPS_INV = modGuiIndex++;

	@Mod.Instance(Reference.MOD_ID)
	public static TinkersDefence instance = new TinkersDefence();

	public static Logger logger;

	PacketHandler phandler = new PacketHandler();

	public static TDConfig config;

	public static final SimpleNetworkWrapper networkInstance = NetworkRegistry.INSTANCE
			.newSimpleChannel(Reference.MOD_ID);

	public static Modifiers mods;

	public static TDItems items;
	public static TDBlocks blocks;
	public static TDParts parts;
	public static TDTools tools;
	public static TDMaterials mats;
	public static TDTraits traits;
	public static TDWorkstations workstations;
	public static TDModifiers modifiers;
	public static TDEvents events;

	SpawnArmorOnMobs mobs = new SpawnArmorOnMobs();

	public static List<ModuleBase> addons = new ArrayList<ModuleBase>();

	public static AddonBloodMagic bloodmagic;
	public static AddonBotania botania;
	public static AddonToolLeveling leveling;
	public static AddonEBWizardry wizardry;
	public static AddonActuallyAdditions actadd;

	@SidedProxy(clientSide = "lance5057.tDefence.proxy.ClientProxy", serverSide = "lance5057.tDefence.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static Item book;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		logger = e.getModLog();

		blocks = new TDBlocks();
		items = new TDItems();
		parts = new TDParts();
		mats = new TDMaterials();
		tools = new TDTools();
		events = new TDEvents();
		traits = new TDTraits();
		modifiers = new TDModifiers();
		workstations = new TDWorkstations();
		config = new TDConfig();

		MinecraftForge.EVENT_BUS.register(mobs);

		if (Loader.isModLoaded("bloodmagic") && TDConfig.addons.BloodMagic)
			addons.add(bloodmagic = new AddonBloodMagic());
		if (Loader.isModLoaded("botania") && TDConfig.addons.Botania)
			addons.add(botania = new AddonBotania());
		if (Loader.isModLoaded("tinkertoolleveling") && TDConfig.addons.ToolLeveling)
			addons.add(leveling = new AddonToolLeveling());
		if (Loader.isModLoaded("ebwizardry") && TDConfig.addons.EBWizardry)
			addons.add(wizardry = new AddonEBWizardry());
		// if (TCConfig.addons.ActuallyAdditions)
		addons.add(actadd = new AddonActuallyAdditions());

		for (ModuleBase m : addons) {
			m.preInit(e);
		}

		parts.preInit(e);
		mats.preInit(e);
		tools.preInit(e);
		traits.preInit();
		events.preInit();

//		if(bloodmagic != null)
//			bloodmagic.preInit(e);
//		if(botania != null)
//			botania.preInit(e);
//		if(wizardry != null)
//			wizardry.preInit(e);

		blocks.preInit(e);

		proxy.preInit();

		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
			TDBook.init();
		}
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent e) {
		NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);

		parts.init(e);
		mats.init(e);
		tools.init(e);
		traits.init();
		modifiers.init();
		events.init();


//		if(bloodmagic != null)
//			bloodmagic.init(e);
//		if(botania != null)
//			botania.init(e);
//		if(leveling != null)
//			leveling.init(e);
//		if(wizardry != null)
//			wizardry.init(e);

		for (ModuleBase m : addons) {
			m.init(e);
		}

		items.init(e);
		blocks.init(e);

		proxy.init();

		phandler.init();

	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		parts.postInit(e);
		mats.postInit(e);
		tools.postInit(e);
		traits.postInit();
		modifiers.postInit();
		events.postInit();

//		if(bloodmagic != null)
//			bloodmagic.postInit(e);		
//		if(botania != null)
//			botania.postInit(e);	
//		if(leveling != null)
//			leveling.postInit(e);
//		if(wizardry != null)
//			wizardry.postInit(e);

		for (ModuleBase m : addons) {
			m.postInit(e);
		}

		items.postInit(e);
		blocks.postInit(e);

		proxy.postInit();

		if (TDConfig.debug) {
			dumpBiomeInfo();
		}

		if (TinkersDefence.config.developerFeatures) {
			OutputWikiPages.outputWikiSidebar(mats.materials);
		}

	}

	// public static List<MaterialHelper.oreGen> biomeCheck = new
	// ArrayList<MaterialHelper.oreGen>();

	void dumpBiomeInfo() {
		File f = new File(Loader.instance().getConfigDir(), "BiomeDump.txt");
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(f));

			for (Biome b : ForgeRegistries.BIOMES) {
				output.write(b.getBiomeName());
				output.newLine();

				output.write("Elevation:" + Float.toString(b.getBaseHeight()));
				output.newLine();

				output.write("Temperature:" + Float.toString(b.getDefaultTemperature()));
				output.newLine();

				output.write("Humidity:" + Float.toString(b.getRainfall()));
				output.newLine();
				output.newLine();

//				for (MaterialHelper.oreGen ore : biomeCheck) {
//					if ((ore.oreBiomeWhite == null || checkBiome(b, ore.oreBiomeWhite))
//							&& (ore.oreBiomeBlack == null || !checkBiome(b, ore.oreBiomeBlack))) {
//						float temp = b.getDefaultTemperature();
//						float elevation = b.getBaseHeight();
//						float humidity = b.getRainfall();
//
//						// -2 = null
//						if (ore.biomeTempMax == -2 || ore.biomeTempMin == -2
//								|| (temp >= ore.biomeTempMin && temp <= ore.biomeTempMax))
//							if (ore.biomeElevationMax == -2 || ore.biomeElevationMin == -2
//									|| (elevation >= ore.biomeElevationMin && elevation <= ore.biomeElevationMax))
//								if (ore.biomeHumidityMax == -2 || ore.biomeHumidityMin == -2
//										|| (humidity >= ore.biomeHumidityMin && humidity <= ore.biomeHumidityMax)) {
//									output.write(ore.getName());
//									output.newLine();
//								}
//					}
//				}

				output.write("------------------");
				output.newLine();
			}

			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private boolean checkBiome(Biome current, Biome[] biomes) {
		if (biomes != null) {
			for (Biome b : biomes) {
				if (current == b)
					return true;
			}
		} else
			return true;
		return false;
	}

}
