package lance5057.tDefence.core.workstations.gui.armorrefinery;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;

import javax.annotation.Nonnull;

public class ArmorRefinerySlotOut extends Slot {

  public ArmorRefineryContainer parent;

  public ArmorRefinerySlotOut(int index, int xPosition, int yPosition, ArmorRefineryContainer container) {
    super(new InventoryCraftResult(), index, xPosition, yPosition);

    this.parent = container;
  }

  @Override
  public boolean isItemValid(ItemStack stack) {
    return false;
  }

  @Nonnull
  @Override
  public ItemStack onTake(EntityPlayer playerIn, @Nonnull ItemStack stack) {
    FMLCommonHandler.instance().firePlayerCraftingEvent(playerIn, stack, parent.getTile());
    parent.onResultTaken(playerIn, stack);
    stack.onCrafting(playerIn.getEntityWorld(), playerIn, 1);

    return super.onTake(playerIn, stack);
  }
}