package lance5057.tDefence.core.workstations.tileentities;

import lance5057.tDefence.core.workstations.gui.armorrefinery.ArmorRefineryContainer;
import lance5057.tDefence.core.workstations.gui.armorrefinery.ArmorRefineryGui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import slimeknights.tconstruct.tools.common.tileentity.TileToolStation;

public class ArmorRefineryTile extends TileToolStation {

  public ArmorRefineryTile()  {
    inventoryTitle = "gui.armorrefinery.name";
  }

  @Override
  @SideOnly(Side.CLIENT)
  public GuiContainer createGui(InventoryPlayer inventoryPlayer, World world, BlockPos pos) {
    return new ArmorRefineryGui(inventoryPlayer, world, pos, this);
  }

  @Override
  public Container createContainer(InventoryPlayer inventoryPlayer, World world, BlockPos pos) {
    return new ArmorRefineryContainer(inventoryPlayer, this);
  }
}
