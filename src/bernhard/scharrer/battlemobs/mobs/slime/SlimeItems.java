package bernhard.scharrer.battlemobs.mobs.slime;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import bernhard.scharrer.battlemobs.mobs.MobItems;
import bernhard.scharrer.battlemobs.util.Item;

public class SlimeItems implements MobItems {

	static final String ABILITY_1_NAME = "Slowball";
    static final String ABILITY_1_DESC = "Shoots slime balls which#slow your enemies on#succesfull hit (1 heart damage).";
    static final String ABILITY_2_NAME = "Slimearmy";
    static final String ABILITY_2_DESC = "Spawns 2 new slimes next to you.#They will fight for you.#(They despawn after 30s)";
    static final String ABILITY_3_NAME = "Crazy Jump";
    static final String ABILITY_3_DESC = "You perform an amazing jump#in the air. Nearby enemies get a really#bad slow (for 3s).";
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
			return Item.createIngameItem(ABILITY_1_NAME, Material.SLIME_BALL, 0);
		case 1:
			return Item.createIngameItem(ABILITY_2_NAME, Material.MONSTER_EGG, 55);
		case 2:
			return Item.createIngameItem(ABILITY_3_NAME, Material.SLIME_BLOCK, 0);
	
		}
		
		return null;
	}

}
