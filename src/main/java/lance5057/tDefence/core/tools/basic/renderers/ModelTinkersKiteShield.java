package lance5057.tDefence.core.tools.basic.renderers;


import lance5057.tDefence.core.tools.armor.renderers.ArmorRenderer;
import net.minecraft.client.model.*;
import net.minecraft.item.ItemStack;

public class ModelTinkersKiteShield extends ArmorRenderer {
	private final ModelRenderer left;
	private final ModelRenderer right;
	private final ModelRenderer back_r1;
	private final ModelRenderer front_r1;
	private final ModelRenderer middle;
	private final ModelRenderer handle;

	public ModelTinkersKiteShield(ItemStack stack) {
		super(0.25f, 0, 64, 64, stack);
		textureWidth = 64;
		textureHeight = 64;

		left = new ModelRenderer(this);
		left.setRotationPoint(-2.625F, 24.0F, 0.925F);
		setRotationAngle(left, 0.0F, 0.3752F, 0.0F);
		left.cubeList.add(new ModelBox(left, 0, 48, -5.0F, -16.0F, -1.0F, 5, 16, 0, 0.0F, false));
		left.cubeList.add(new ModelBox(left, 32, 48, -5.0F, -16.0F, 0.0F, 5, 16, 0, 0.0F, false));
		left.cubeList.add(new ModelBox(left, 29, 0, -5.0F, -13.0F, -1.0F, 1, 5, 1, 0.0F, true));
		left.cubeList.add(new ModelBox(left, 32, 0, -4.0F, -14.0F, -1.0F, 1, 8, 1, 0.0F, true));
		left.cubeList.add(new ModelBox(left, 35, 0, -3.0F, -14.0F, -1.0F, 1, 10, 1, 0.0F, true));
		left.cubeList.add(new ModelBox(left, 38, 0, -2.0F, -15.0F, -1.0F, 1, 12, 1, 0.0F, true));
		left.cubeList.add(new ModelBox(left, 41, 0, -1.0F, -15.0F, -1.0F, 1, 13, 1, 0.0F, true));

		right = new ModelRenderer(this);
		right.setRotationPoint(3.7964F, 15.0F, 0.875F);
		setRotationAngle(right, 0.0F, -0.3927F, 0.0F);
		right.cubeList.add(new ModelBox(right, 41, 0, -1.0714F, -6.0F, -0.5F, 1, 13, 1, 0.0F, false));
		right.cubeList.add(new ModelBox(right, 38, 0, -0.0714F, -6.0F, -0.5F, 1, 12, 1, 0.0F, false));
		right.cubeList.add(new ModelBox(right, 35, 0, 0.9286F, -5.0F, -0.5F, 1, 10, 1, 0.0F, false));
		right.cubeList.add(new ModelBox(right, 32, 0, 1.9286F, -5.0F, -0.5F, 1, 8, 1, 0.0F, false));
		right.cubeList.add(new ModelBox(right, 29, 0, 2.9286F, -4.0F, -0.5F, 1, 5, 1, 0.0F, false));

		back_r1 = new ModelRenderer(this);
		back_r1.setRotationPoint(-1.0714F, 9.0F, 0.5F);
		right.addChild(back_r1);
		setRotationAngle(back_r1, 0.0F, 3.1416F, 0.0F);
		back_r1.cubeList.add(new ModelBox(back_r1, 54, 48, -5.0F, -16.0F, 0.0F, 5, 16, 0, 0.0F, false));

		front_r1 = new ModelRenderer(this);
		front_r1.setRotationPoint(-1.0714F, 9.0F, -0.5F);
		right.addChild(front_r1);
		setRotationAngle(front_r1, 0.0F, 3.1416F, 0.0F);
		front_r1.cubeList.add(new ModelBox(front_r1, 22, 48, -5.0F, -16.0F, 0.0F, 5, 16, 0, 0.0F, false));

		middle = new ModelRenderer(this);
		middle.setRotationPoint(1.0F, 24.0F, 0.0F);
		middle.cubeList.add(new ModelBox(middle, 10, 48, -4.0F, -16.0F, 0.0F, 6, 16, 0, 0.0F, false));
		middle.cubeList.add(new ModelBox(middle, 42, 48, -4.0F, -16.0F, 1.0F, 6, 16, 0, 0.0F, false));
		middle.cubeList.add(new ModelBox(middle, 44, 0, -4.0F, -15.0F, 0.0F, 6, 14, 1, 0.0F, false));
		middle.cubeList.add(new ModelBox(middle, 58, 0, -2.0F, -16.0F, 0.0F, 2, 16, 1, 0.0F, false));

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 15.0F, 3.5F);
		setRotationAngle(handle, 0.0F, 0.0F, -0.3927F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -1.0F, -3.0F, -2.5F, 2, 1, 4, 0.0F, false));
		handle.cubeList.add(new ModelBox(handle, 0, 0, -1.0F, 2.0F, -2.5F, 2, 1, 4, 0.0F, false));
		handle.cubeList.add(new ModelBox(handle, 12, 0, -1.0F, -2.0F, 0.5F, 2, 4, 1, 0.0F, false));
	}

	public void render() {
		left.render(0.0625F);
		right.render(0.0625F);
		middle.render(0.0625F);
		handle.render(0.0625F);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}