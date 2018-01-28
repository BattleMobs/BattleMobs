package bernhard.scharrer.battlemobs.mobs.skeleton;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import bernhard.scharrer.battlemobs.mobs.MobItems;
import bernhard.scharrer.battlemobs.util.Item;

public class SkeletonItems implements MobItems {

	static final String ABILITY_1_NAME = "Skeleton Bow";
    static final String ABILITY_1_DESC = "Arrow make 2 hearts damage.";
    static final String ABILITY_2_NAME = "Bone Breaker";
    static final String ABILITY_2_DESC = "#Spins the heads of enemies in a#15 block radius randomly#and deals 1 heart damage per spin.";
    static final String ABILITY_3_NAME = "Ride of Death";
    static final String ABILITY_3_DESC = "Let's you ride a dead horse.#While riding you regenrate a little.";
    static final String ANILITY_1_TIER_1_NAME = "Bow++";
    static final String ANILITY_1_TIER_1_DESC = "Adds a FLASH-Effect and deals#1 heart more damage.";
    static final String ANILITY_2_TIER_1_NAME = "Twice Spins, Twice Fun";
    static final String ANILITY_2_TIER_1_DESC = "Makes a second spin after 1 second.";
    static final String ANILITY_3_TIER_1_NAME = "Better Conditions";
    static final String ANILITY_3_TIER_1_DESC = "While riding you deal twice as much damage.";
    static final String ANILITY_1_TIER_2_NAME = "Little Confusion?";
    static final String ANILITY_1_TIER_2_DESC = "Rotate enemies head randomly#on succesfull hit.";
    static final String ANILITY_2_TIER_2_NAME = "More Spins!";
    static final String ANILITY_2_TIER_2_DESC = "+2 extra spins in a 1s rythmus##+adds a little flash everyetime#enemy is rotating.";
    static final String ANILITY_3_TIER_2_NAME = "U can't see me.";
    static final String ANILITY_3_TIER_2_DESC = "Succesfully hits while riding#blinds your enemies for 3s.";
	
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
			ItemStack item = Item.createIngameItem(ABILITY_1_NAME, Material.BOW, 0);
			item.addEnchantment(Enchantment.ARROW_INFINITE, 1);
			return item;
		case 1:
			return Item.createIngameItem(ABILITY_2_NAME, Material.BONE, 0);
		case 2:
			return Item.createIngameItem(ABILITY_3_NAME, Material.IRON_BARDING, 0);
		}
		
		return null;
	}

}
