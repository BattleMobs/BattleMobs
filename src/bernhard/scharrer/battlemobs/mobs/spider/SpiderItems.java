package bernhard.scharrer.battlemobs.mobs.spider;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import bernhard.scharrer.battlemobs.mobs.MobItems;
import bernhard.scharrer.battlemobs.util.Item;

public class SpiderItems implements MobItems {
	
	static final String ABILITY_1_NAME = "Eye Of The Spider";
    static final String ABILITY_1_DESC = "Left click to poison your enemy by 3s.#Deals 3 hearts damage.##Right click to shoot a toxic arrow.#Arrow deals 2 hearts damage#and posions your enemy#for 3s.";
    static final String ABILITY_2_NAME = "Web Bomb";
    static final String ABILITY_2_DESC = "Throws a web bomb. After 2s#a web ball gets summoned.#If you hit a enemy with it,#the ball summons immidiately.#Don't get into your own ball!";
    static final String ABILITY_3_NAME = "Spider Army";
    static final String ABILITY_3_DESC = "Summons 3 little cave spiders#which support you against your enemies.#Spiders despawn after 5s.";
    static final String ABILITY_1_TIER_1_NAME = "More arrows, more hits!";
    static final String ABILITY_1_TIER_1_DESC = "Shoot 3 times instead of only one time.";
    static final String ABILITY_2_TIER_1_NAME = "Web Cluster";
    static final String ABILITY_2_TIER_1_DESC = "Doubles decay time of web ball.#Deals 3 hearts damage to#everyone who is inside the ball.";
    static final String ABILITY_3_TIER_1_NAME = "Run my friends, run!";
    static final String ABILITY_3_TIER_1_DESC = "Increases speed of cave spiders.#(2,5x as fast as before)";
    static final String ABILITY_1_TIER_2_NAME = "Not even close.";
    static final String ABILITY_1_TIER_2_DESC = "Adds a super annoying knockback#effect when you hit your#enemy during combat.";
    static final String ABILITY_2_TIER_2_NAME = "Attention please!";
    static final String ABILITY_2_TIER_2_DESC = "Poisons your enemies for 5s#when they get caught into the ball.";
    static final String ABILITY_3_TIER_2_NAME = "Efficent Reproduction";
    static final String ABILITY_3_TIER_2_DESC = "Half cooldown, twice#as much spiders.";
	
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
			return Item.createIngameItem(ABILITY_1_NAME, Material.SPIDER_EYE, 0);
		case 1:
			return Item.createIngameItem(ABILITY_2_NAME, Material.STRING, 0);
		case 2:
			return Item.createIngameItem(ABILITY_3_NAME, Material.FERMENTED_SPIDER_EYE, 0);
	
		}
		
		return null;
	}

	
}
