package bernhard.scharrer.battlemobs.mobs;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import bernhard.scharrer.battlemobs.util.Item;

public interface MobItems {
	
	ItemStack LOCKED = Item.createItem("§4§lLOCKED", "§cUnlock lower tiers first.", Material.BARRIER, 1, 0);
	ItemStack BUYABLE = Item.createItem("§2§lUNLOCK TIER", "#§7100$##§aClICK TO BUY", Material.EMERALD, 1, 0);
	ItemStack LOBBY = Item.createItem("§c§lBACK TO LOBBY", "", Material.SLIME_BALL, 1, 0);
	ItemStack AUTO_RESPAWN_ON = Item.createItem("§a§lAUTO RESPAWN", "", Material.EYE_OF_ENDER, 1, 0);
	ItemStack AUTO_RESPAWN_OFF = Item.createItem("§c§lAUTO RESPAWN", "", Material.ENDER_PEARL, 1, 0);
	
	public ItemStack getMobInventoryItem(int tier);
	public ItemStack getAbilityItem(int ability, int upgrades);
	
}
