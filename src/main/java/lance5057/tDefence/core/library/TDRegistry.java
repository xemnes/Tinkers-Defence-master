package lance5057.tDefence.core.library;

import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import akka.japi.Pair;
import gnu.trove.set.hash.TLinkedHashSet;
import lance5057.tDefence.core.tools.armor.renderers.ArmorRenderer;
import lance5057.tDefence.core.tools.bases.ArmorCore;
import net.minecraft.item.Item;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.IToolPart;

public class TDRegistry {
	private static final Set<ArmorCore> armor = new TLinkedHashSet<>();
	//private static final Set<IToolPart> armorParts = new TLinkedHashSet<>();
	private static final Set<ArmorCore> armorRefineryCrafting = Sets.newLinkedHashSet();
	public static List<Pair<Item, ArmorPart>> armorPartPatterns = Lists.newLinkedList();

	public static final Set<ArmorRenderer> finishingAnvilForms = new TLinkedHashSet<>();

	public static void registerTool(ArmorCore tool) {
		armor.add(tool);

		for (PartMaterialType pmt : tool.getRequiredComponents()) {
			for (IToolPart tp : pmt.getPossibleParts()) {
				TinkerRegistry.registerToolPart(tp);
			}
		}
	}

	/**
	 * Adds a armor to the Crafting UI of both the armor Station as well as the
	 * armor Forges
	 */
	public static void registerArmorCrafting(ArmorCore armor) {
		registerArmorRefineryCrafting(armor);
	}

	/** Adds a armor to the Crafting UI of the armor Station */
	public static void registerArmorRefineryCrafting(ArmorCore armor) {
		armorRefineryCrafting.add(armor);
	}

	public static ImmutableSet<ArmorCore> getArmorRefineryCrafting() {
		return ImmutableSet.copyOf(armorRefineryCrafting);
	}

	public static Set<ArmorCore> getArmor() {
		return ImmutableSet.copyOf(armor);
	}

	public static void registerFinishingAnvilForm(ArmorRenderer model) {

	}
}
