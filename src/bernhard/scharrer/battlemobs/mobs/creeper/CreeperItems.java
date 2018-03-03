package bernhard.scharrer.battlemobs.mobs.creeper;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import bernhard.scharrer.battlemobs.mobs.MobItems;
import bernhard.scharrer.battlemobs.util.Item;

public class CreeperItems implements MobItems {

	static final String ABILITY_1_NAME = "Creeper Charger";
    static final String ABILITY_1_DESC = "For every succesful hit your#charge counter increases by 1.#(max. charge count: 10)";
    static final String ABILITY_2_NAME = "Bolt Rain";
    static final String ABILITY_2_DESC = "Spawn a lightning for every#enemy in a 5 block radius.#+ you get charged up!";
    static final String ABILITY_3_NAME = "Creeper Explosion";
    static final String ABILITY_3_DESC = "Creates an explosion that deals#damage depending on your charge#counter.";
    static final String ABILITY_1_TIER_1_NAME = "";
    static final String ABILITY_1_TIER_1_DESC = "";
    static final String ABILITY_2_TIER_1_NAME = "";
    static final String ABILITY_2_TIER_1_DESC = "";
    static final String ABILITY_3_TIER_1_NAME = "Lightning Voltage";
    static final String ABILITY_3_TIER_1_DESC = "Explosions deals 1.3x damage#when you are charged";
    static final String ABILITY_1_TIER_2_NAME = "";
    static final String ABILITY_1_TIER_2_DESC = "";
    static final String ABILITY_2_TIER_2_NAME = "";
    static final String ABILITY_2_TIER_2_DESC = "";
    static final String ABILITY_3_TIER_2_NAME = "";
    static final String ABILITY_3_TIER_2_DESC = "";
	
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
			return Item.createAbilityItem(ABILITY_1_TIER_1_NAME, ABILITY_1_TIER_1_DESC, Material.ENCHANTED_BOOK, 1, 0);
		case 5:
			return Item.createAbilityItem(ABILITY_2_TIER_1_NAME, ABILITY_2_TIER_1_DESC, Material.ENCHANTED_BOOK, 1, 0);
		case 6:
			return Item.createAbilityItem(ABILITY_3_TIER_1_NAME, ABILITY_3_TIER_1_DESC, Material.ENCHANTED_BOOK, 1, 0);
		case 7:
			return Item.createAbilityItem(ABILITY_1_TIER_2_NAME, ABILITY_1_TIER_2_DESC, Material.ENCHANTED_BOOK, 1, 0);
		case 8:
			return Item.createAbilityItem(ABILITY_2_TIER_2_NAME, ABILITY_2_TIER_2_DESC, Material.ENCHANTED_BOOK, 1, 0);
		case 9:
			return Item.createAbilityItem(ABILITY_3_TIER_2_NAME, ABILITY_3_TIER_2_DESC, Material.ENCHANTED_BOOK, 1, 0);
		}
		return null;
	}

	@Override
	public ItemStack getAbilityItem(int ability, int upgrade) {
		
		switch(ability) {
		
		case 0:
			return Item.createIngameItem(ABILITY_1_NAME, Material.SKULL, 4);
		case 1:
			return Item.createIngameItem(ABILITY_2_NAME, Material.FLINT, 0);
		case 2:
			return Item.createIngameItem(ABILITY_3_NAME, Material.SULPHUR, 0);
	
		}
		
		return null;
	}

}
