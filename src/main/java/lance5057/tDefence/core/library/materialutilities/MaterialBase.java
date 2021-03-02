package lance5057.tDefence.core.library.materialutilities;

import java.io.PrintWriter;

import slimeknights.tconstruct.library.MaterialIntegration;

public abstract interface MaterialBase {

	public abstract void setupPre(MaterialHelper mat);

	public abstract void setupInit(MaterialHelper mat);

	public abstract void setupIntegration(MaterialIntegration mi);

	public abstract void setupPost(MaterialHelper mat);

	public abstract void setupClient(MaterialHelper mat);

	public abstract void setupModels(MaterialHelper mat);

	public abstract void setupWiki(MaterialHelper mat, PrintWriter out);
}
