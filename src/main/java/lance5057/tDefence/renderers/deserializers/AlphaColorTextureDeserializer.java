package lance5057.tDefence.renderers.deserializers;

import lance5057.tDefence.renderers.info.TDMaterialRenderInfo;
import slimeknights.tconstruct.library.client.MaterialRenderInfo;
import slimeknights.tconstruct.library.client.material.deserializers.AbstractRenderInfoDeserializer;

public class AlphaColorTextureDeserializer extends AbstractRenderInfoDeserializer {

	protected String color;

	@Override
	public MaterialRenderInfo getMaterialRenderInfo() {
		return new TDMaterialRenderInfo.AlphaColor(fromHex(color));
	}
}