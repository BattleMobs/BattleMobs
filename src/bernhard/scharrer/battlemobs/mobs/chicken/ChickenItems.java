package bernhard.scharrer.battlemobs.mobs.chicken;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import bernhard.scharrer.battlemobs.mobs.MobItems;
import bernhard.scharrer.battlemobs.util.Item;
import bernhard.scharrer.battlemobs.util.Tier;

public class ChickenItems implements MobItems {

	static final String ABILITY_1_NAME = "Sharp Wings";
    static final String ABILITY_1_DESC = "Schoot a feather for long#distance and deal demage.";
    static final String ABILITY_2_NAME = "Egg Bomb";
    static final String ABILITY_2_DESC = "Throw a exploding egg.";
    static final String ABILITY_3_NAME = "Flappy jump";
    static final String ABILITY_3_DESC = "Jump in the air and deal demage on#the landing.";
    static final String ANILITY_1_TIER_1_NAME = "More Sharper Wings";
    static final String ANILITY_1_TIER_1_DESC = "Deal more demage.";
    static final String ANILITY_2_TIER_1_NAME = "Dangerous Yellow";
    static final String ANILITY_2_TIER_1_DESC = "Make a greater explosion.";
    static final String ANILITY_3_TIER_1_NAME = "Vacuum Jump";
    static final String ANILITY_3_TIER_1_DESC = "Suck the enemy in the center of your jump.";
    static final String ANILITY_1_TIER_2_NAME = "Three for one Feather";
    static final String ANILITY_1_TIER_2_DESC = "Shoot three feathers at once.";
    static final String ANILITY_2_TIER_2_NAME = "Great Egg!";
    static final String ANILITY_2_TIER_2_DESC = "Gives the player knockback on impact.";
    static final String ANILITY_3_TIER_2_NAME = "Double Jump";
    static final String ANILITY_3_TIER_2_DESC = "Deal at the beginning of the Jump#demage as well.";
	
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
			return Item.createIngameItem(ABILITY_1_NAME, upgrade>=Tier.TIER_2_1?(upgrade>=Tier.TIER_3_1?Material.STONE_SWORD:Material.IRON_SWORD):Material.DIAMOND_SWORD, 0);
		case 1:
			return Item.createIngameItem(ABILITY_2_NAME, Material.ROTTEN_FLESH, 0);
		case 2:
			return Item.createIngameItem(ABILITY_3_NAME, Material.MONSTER_EGG, 54);
	
		}
		
		return null;
	}

}
