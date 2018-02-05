package bernhard.scharrer.battlemobs.mobs.sheep;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import bernhard.scharrer.battlemobs.mobs.MobItems;
import bernhard.scharrer.battlemobs.util.Item;

public class SheepItems implements MobItems {

	static final String ABILITY_1_NAME = "Shearing Is Caring";
	static final String ABILITY_1_DESC = "Wool Counter increases 1 wool/hit and 5 wool/kill.#Damage is linked to the Wool Counter.#Max. Wool: 20 ";
	static final String ABILITY_2_NAME = "Graze";
	static final String ABILITY_2_DESC = "Click on the floor to change a block to dirt and spawn grass on it.#Grass slows enemies.";
	static final String ABILITY_3_NAME = "Feeding Time";
	static final String ABILITY_3_DESC = "Heal.";
	static final String ABILITY_1_TIER_1_NAME = "Universal Shears";
	static final String ABILITY_1_TIER_1_DESC = "Other Abilities also get stronger, when the Wool Counter increases.#Ability 2: Increased Slow.#Ability 3: Increased Heal.#Max. Wool: 25 ";
	static final String ABILITY_2_TIER_1_NAME = "Roses Are Red";
	static final String ABILITY_2_TIER_1_DESC = "Instead of Grass a rose gets spawned.#It has thorns!";
	static final String ABILITY_3_TIER_1_NAME = "Bread?";
	static final String ABILITY_3_TIER_1_DESC = "Just a better heal.";
	static final String ABILITY_1_TIER_2_NAME = "Wool God";
	static final String ABILITY_1_TIER_2_DESC = "Right click your Shears when you have max. Wool#to transform into a Wool God!#Max. Wool: 30 ";
	static final String ABILITY_2_TIER_2_NAME = "Get Away From My Lawn!";
	static final String ABILITY_2_TIER_2_DESC = "Roses transform to rosebushes and they spray their thorns!";
	static final String ABILITY_3_TIER_2_NAME = "Cake Heaven";
	static final String ABILITY_3_TIER_2_DESC = "Adds a Life regeneration.";

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
			return Item.createIngameItem(ABILITY_1_NAME, Material.SHEARS, 0);
		case 1:
			return Item.createIngameItem(ABILITY_2_NAME, Material.LONG_GRASS, 1);
		case 2:
			return Item.createIngameItem(ABILITY_3_NAME, Material.WHEAT, 0);
	
		}
		
		return null;
	}
	
}
