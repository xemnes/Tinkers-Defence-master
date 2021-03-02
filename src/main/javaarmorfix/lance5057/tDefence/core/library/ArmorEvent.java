package lance5057.tDefence.core.library;

import com.google.common.collect.ImmutableList;

import lance5057.tDefence.core.tools.bases.ArmorBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;
import slimeknights.tconstruct.library.materials.Material;

/**
 * Base class for all tinkers events
 */
public abstract class ArmorEvent extends Event {

  /**
   * Fired when a tool is built.
   * This happens every time a tool is loaded as well as when the player actually builds the tool.
   * You can make changes to the tag compound and it'll land on the resulting tool, but its itemstack is not available.
   */
  public static class OnItemBuilding extends ArmorEvent {

    public NBTTagCompound tag;
    public final ImmutableList<Material> materials;
    public final ArmorBase tool;

    public OnItemBuilding(NBTTagCompound tag, ImmutableList<Material> materials, ArmorBase tool) {
      this.tag = tag;
      this.materials = materials;
      this.tool = tool;
    }

    public static OnItemBuilding fireEvent(NBTTagCompound tag, ImmutableList<Material> materials, ArmorBase tool) {
      OnItemBuilding event = new OnItemBuilding(tag, materials, tool);
      MinecraftForge.EVENT_BUS.post(event);
      return event;
    }
  }

  /**
   * Fired when the player tries to replace a toolpart.
   * You can modify the input items to achieve different results, this will not modify the actual items in the game.
   * If you're modifying the list itself, make sure to put new items into originally empty indices to prevent the usage of other items in the input. Just append to the list.
   * You can not modify the tool that's getting modified
   */
  @Cancelable
  public static class OnToolPartReplacement extends ArmorEvent {

    /** The items in the tool station. Can be manipulated. */
    public NonNullList<ItemStack> replacementParts;
    public ItemStack toolStack;

    public OnToolPartReplacement(NonNullList<ItemStack> replacementParts, ItemStack toolStack) {
      this.replacementParts = replacementParts;
      this.toolStack = toolStack.copy();
    }

    public static boolean fireEvent(NonNullList<ItemStack> replacementParts, ItemStack toolStack) {
      return !MinecraftForge.EVENT_BUS.post(new OnToolPartReplacement(replacementParts, toolStack));
    }
  }
}