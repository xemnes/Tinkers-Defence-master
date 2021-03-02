package lance5057.tDefence.core.workstations;

import lance5057.tDefence.Reference;
import lance5057.tDefence.core.workstations.gui.armorstation.ArmorStationContainer;
import lance5057.tDefence.core.workstations.gui.armorstation.ArmorStationGui;
import lance5057.tDefence.core.workstations.tileentities.ArmorStationTile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class TDWorkstations {

	public static final int ArmorStationID = 0;



	public void preInit(FMLPreInitializationEvent e) {


	}


	public void postInit(FMLPostInitializationEvent e) {

	}

	public void registerItems(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry registry = event.getRegistry();

	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {

	}

	public static Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);

		switch (ID) {
			case ArmorStationID:
				return new ArmorStationContainer(player.inventory, (ArmorStationTile) world.getTileEntity(pos));
		}
		return null;
	}

	public static Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);

		switch (ID) {
			case ArmorStationID:
				return new ArmorStationGui(player.inventory, world, pos, (ArmorStationTile) world.getTileEntity(pos));
		}
		return null;
	}

}