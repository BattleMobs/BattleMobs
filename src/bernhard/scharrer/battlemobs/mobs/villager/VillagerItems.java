package bernhard.scharrer.battlemobs.mobs.villager;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import bernhard.scharrer.battlemobs.mobs.MobItems;
import bernhard.scharrer.battlemobs.util.Item;

public class VillagerItems implements MobItems {

	static final String ABILITY_1_NAME = "Bad Trade";
    static final String ABILITY_1_DESC = "Deals 2 hearts damage and#swaps positions of you and#your enemy.##Always sets your head perfectly#aiming towards your enemy.";
    static final String ABILITY_2_NAME = "Payback";
    static final String ABILITY_2_DESC = "Curses your enemy on hit.#Your enemy gets 3 hearts#damage after 3s and you#steal 1$ from him on succesfully#execution of the curse.#(only if your enemy has the money)";
    static final String ABILITY_3_NAME = "Metalworking";
    static final String ABILITY_3_DESC = "Throws a anvil. Enemies nearby#get blasted away and get#damaged by 5 hearts.";
    static final String ABILITY_1_TIER_1_NAME = "";
    static final String ABILITY_1_TIER_1_DESC = "";
    static final String ABILITY_2_TIER_1_NAME = "";
    static final String ABILITY_2_TIER_1_DESC = "";
    static final String ABILITY_3_TIER_1_NAME = "";
    static final String ABILITY_3_TIER_1_DESC = "";
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
			return Item.createIngameItem(ABILITY_1_NAME, Material.EMERALD, 0);
		case 1:
			return Item.createIngameItem(ABILITY_2_NAME, Material.PAPER, 0);
		case 2:
			return Item.createIngameItem(ABILITY_3_NAME, Material.ANVIL, 0);
	
		}
		
		return null;
	}

}
