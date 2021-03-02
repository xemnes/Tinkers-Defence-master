package lance5057.tDefence.core.tools.armor.renderers.chain;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

import lance5057.tDefence.core.tools.armor.renderers.ArmorRenderer;

/**
 * ModelBiped - Either Mojang or a mod author Created using Tabula 7.0.0
 */
public class ModelTinkersCoif extends ArmorRenderer {
	public ModelRenderer Coif;
	public ModelRenderer Head;

	public ModelTinkersCoif(EntityEquipmentSlot slotIn) {
		super(slotIn);
		
		this.textureWidth = 48;
		this.textureHeight = 48;
		
		this.Coif = new ModelRenderer(this, 0, 0);
		this.Coif.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Coif.addBox(-4.0F, -8.0F, -4.0F, 8, 10, 8, 0.5F);
		
		
		this.Head = new ModelRenderer(this, 0, 18);
		this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		
		this.bipedHead = this.Head;
		this.Head.addChild(Coif);
		
		this.bipedHeadwear.isHidden = true;
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
