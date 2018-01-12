package bernhard.scharrer.battlemobs.mobs.skeleton;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import bernhard.scharrer.battlemobs.mobs.MobItems;
import bernhard.scharrer.battlemobs.util.Item;

public class SkeletonItems implements MobItems {

	static final String ABILITY_1_NAME = "Cursed Sword";
    static final String ABILITY_1_DESC = "1s slow effect.";
    static final String ABILITY_2_NAME = "Blood Rage";
    static final String ABILITY_2_DESC = "Gives you speed effect for 5s,#with a cooldown of 30s.";
    static final String ABILITY_3_NAME = "Inner Strengh";
    static final String ABILITY_3_DESC = "Turn into a giant for 15s.#During the duration you get resistance.#60s cooldown.";
    static final String ANILITY_1_TIER_1_NAME = "Zombie and Steel";
    static final String ANILITY_1_TIER_1_DESC = "Increase the slowness by 1.5s and#replace stone sword with a iron sword.";
    static final String ANILITY_2_TIER_1_NAME = "Gonna go fast!";
    static final String ANILITY_2_TIER_1_DESC = "Reduce the ability cooldown to 20s.";
    static final String ANILITY_3_TIER_1_NAME = "Inner Health";
    static final String ANILITY_3_TIER_1_DESC = "Get a regeneration effect while in giant form.";
    static final String ANILITY_1_TIER_2_NAME = "Why not diamond?";
    static final String ANILITY_1_TIER_2_DESC = "Slow effect stays now for 2.5s and#turn the iron sword into a diamond sword.";
    static final String ANILITY_2_TIER_2_NAME = "RAGE!";
    static final String ANILITY_2_TIER_2_DESC = "Doubles the damage you deal while rage.";
    static final String ANILITY_3_TIER_2_NAME = "True Giant!";
    static final String ANILITY_3_TIER_2_DESC = "Reduce the cooldown to 40s and#damage the player in a 15 blocks radius.";
	
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
			item.addEnchantment(Enchantment.ARROW_INFINITE, 0);
			return item;
		case 1:
			
			return Item.createIngameItem(ABILITY_2_NAME, Material.BONE, 0);
		case 2:
			return Item.createIngameItem(ABILITY_3_NAME, Material.FIREWORK, 0);
	
		}
		
		return null;
	}

}
