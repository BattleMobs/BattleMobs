package bernhard.scharrer.battlemobs.mobs.pig;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import bernhard.scharrer.battlemobs.mobs.MobItems;
import bernhard.scharrer.battlemobs.util.Item;

public class PigItems implements MobItems {

	static final String ABILITY_1_NAME = "Bacon Power";
	static final String ABILITY_1_DESC = "20%-lifesteal-effect.";
	static final String ABILITY_2_NAME = "Pig Hook";
	static final String ABILITY_2_DESC = "Hook all players in a 15 block radius towards you.";
	static final String ABILITY_3_NAME = "Saddle Down";
	static final String ABILITY_3_DESC = "Picks up an enemy by RIGHT-clicking him/her.#As long as the enemy remains on you#he/she gets 2 hearts damage/s.";
	static final String ANILITY_1_TIER_1_NAME = "Not enough?";
	static final String ANILITY_1_TIER_1_DESC = "Increases lifesteal-effect by 20%.#Total: 40%";
	static final String ANILITY_2_TIER_1_NAME = "Pain? Why not.";
	static final String ANILITY_2_TIER_1_DESC = "Every hooked enemy gets 2 hearts damage.";
	static final String ANILITY_3_TIER_1_NAME = "Blind Rush";
	static final String ANILITY_3_TIER_1_DESC = "Saddled enemies get a blinding effect.";
	static final String ANILITY_1_TIER_2_NAME = "Fire makes things better!";
	static final String ANILITY_1_TIER_2_DESC = "Adds a 3s burn effect to your enemies.";
	static final String ANILITY_2_TIER_2_NAME = "Power Hook";
	static final String ANILITY_2_TIER_2_DESC = "Half the cooldown, double the power.";
	static final String ANILITY_3_TIER_2_NAME = "Burn in hell!";
	static final String ANILITY_3_TIER_2_DESC = "2 hearts more damage over time#and the target gets a 5s burn effect#when he/she saddles down.";

	@Override
	public ItemStack getMobInventoryItem(int tier) {
		switch (tier) {
		case 1:
			return Item.createAbilityItem(ABILITY_1_NAME, ABILITY_1_DESC, Material.END_CRYSTAL, 1, 0);
		case 2:
			return Item.createAbilityItem(ABILITY_2_NAME, ABILITY_2_DESC, Material.END_CRYSTAL, 1, 0);
		case 3:
			return Item.createAbilityItem(ABILITY_3_NAME, ABILITY_3_DESC, Material.END_CRYSTAL, 1, 0);
		case 4:
			return Item.createAbilityItem(ANILITY_1_TIER_1_NAME, ANILITY_1_TIER_1_DESC, Material.ENCHANTED_BOOK, 1, 0);
		case 5:
			return Item.createAbilityItem(ANILITY_2_TIER_1_NAME, ANILITY_2_TIER_1_DESC, Material.ENCHANTED_BOOK, 1, 0);
		case 6:
			return Item.createAbilityItem(ANILITY_3_TIER_1_NAME, ANILITY_3_TIER_1_DESC, Material.ENCHANTED_BOOK, 1, 0);
		case 7:
			return Item.createAbilityItem(ANILITY_1_TIER_2_NAME, ANILITY_1_TIER_2_DESC, Material.ENCHANTED_BOOK, 1, 0);
		case 8:
			return Item.createAbilityItem(ANILITY_2_TIER_2_NAME, ANILITY_2_TIER_2_DESC, Material.ENCHANTED_BOOK, 1, 0);
		case 9:
			return Item.createAbilityItem(ANILITY_3_TIER_2_NAME, ANILITY_3_TIER_2_DESC, Material.ENCHANTED_BOOK, 1, 0);
		}
		return null;
	}

	@Override
	public ItemStack getAbilityItem(int ability, int upgrade) {
		
		switch(ability) {
		
		case 0:
			return Item.createIngameItem(ABILITY_1_NAME, upgrade<2?Material.PORK:Material.GRILLED_PORK, 0);
		case 1:
			return Item.createIngameItem(ABILITY_2_NAME, Material.CARROT_STICK, 0);
		case 2:
			return Item.createIngameItem(ABILITY_3_NAME, Material.SADDLE, 0);
	
		}
		
		return null;
	}
	
}
