package lance5057.tDefence.core.materials;

import lance5057.tDefence.core.materials.stats.ChestMaterialStats;
import lance5057.tDefence.core.materials.stats.FeetMaterialStats;
import lance5057.tDefence.core.materials.stats.HelmMaterialStats;
import lance5057.tDefence.core.materials.stats.LegsMaterialStats;
import lance5057.tDefence.core.materials.stats.ShieldMaterialStats;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.traits.ITrait;

public class ArmorMaterialAPI {
	
	//Traits
	public void addHelmetTrait(Material mat, ITrait trait)
	{
		mat.addTrait(trait, HelmMaterialStats.TYPE);
	}
	
	public void addChestTrait(Material mat, ITrait trait)
	{
		mat.addTrait(trait, ChestMaterialStats.TYPE);
	}
	
	public void addLeggingsTrait(Material mat, ITrait trait)
	{
		mat.addTrait(trait, LegsMaterialStats.TYPE);
	}
	
	public void addFeetTrait(Material mat, ITrait trait)
	{
		mat.addTrait(trait, FeetMaterialStats.TYPE);
	}
	
	public void addAllArmorTrait(Material mat, ITrait trait)
	{
		mat.addTrait(trait, HelmMaterialStats.TYPE);
		mat.addTrait(trait, ChestMaterialStats.TYPE);
		mat.addTrait(trait, LegsMaterialStats.TYPE);
		mat.addTrait(trait, FeetMaterialStats.TYPE);
	}
	
	public void addShieldTrait(Material mat, ITrait trait)
	{
		mat.addTrait(trait, ShieldMaterialStats.TYPE);
	}
	
	//Stats
	public void addHelmetStats(Material mat, int durability, int defence, int toughness, float potency)
	{
		HelmMaterialStats h = new HelmMaterialStats(durability, defence, toughness, potency);
		TinkerRegistry.addMaterialStats(mat, h);
	}
	
	public void addChestStats(Material mat, int durability, float defence, float toughness, float potency)
	{
		ChestMaterialStats c = new ChestMaterialStats(durability, defence, toughness, potency);
		TinkerRegistry.addMaterialStats(mat, c);
	}
	
	public void addLeggingsStats(Material mat, int durability, float defence, float toughness, float potency)
	{
		LegsMaterialStats l = new LegsMaterialStats(durability, defence, toughness, potency);
		TinkerRegistry.addMaterialStats(mat, l);
	}
	
	public void addFeetStats(Material mat, int durability, float defence, float toughness, float potency)
	{
		FeetMaterialStats f = new FeetMaterialStats(durability, defence, toughness, potency);
		TinkerRegistry.addMaterialStats(mat, f);
	}
	
	public void addShieldStats(Material mat, int durability, float percentBlocked)
	{
		ShieldMaterialStats s = new ShieldMaterialStats(durability, percentBlocked);
		TinkerRegistry.addMaterialStats(mat, s);
	}
}
