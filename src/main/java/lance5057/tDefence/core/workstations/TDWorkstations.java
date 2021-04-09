package lance5057.tDefence.core.workstations;

import lance5057.tDefence.Reference;
import lance5057.tDefence.core.workstations.gui.armorrefinery.ArmorRefineryContainer;
import lance5057.tDefence.core.workstations.gui.armorrefinery.ArmorRefineryGui;
import lance5057.tDefence.core.workstations.tileentities.ArmorRefineryTile;
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

	public static final int ArmorRefineryID = 0;



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
			case ArmorRefineryID:
				return new ArmorRefineryContainer(player.inventory, (ArmorRefineryTile) world.getTileEntity(pos));
		}
		return null;
	}

	public static Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);

		switch (ID) {
			case ArmorRefineryID:
				return new ArmorRefineryGui(player.inventory, world, pos, (ArmorRefineryTile) world.getTileEntity(pos));
		}
		return null;
	}

}