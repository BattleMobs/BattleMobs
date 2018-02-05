package bernhard.scharrer.battlemobs.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import bernhard.scharrer.battlemobs.listeners.Listener;
import bernhard.scharrer.battlemobs.mobs.MobType;

public class Teleporter extends Listener {
	
	protected static final ItemStack TELEPORTER_ITEM = Item.createItem("§b§lTELEPORTER", "", Material.COMPASS, 1, 0);
	private static final String TELEPORTER_TITLE = "Teleporter";

	private static Inventory inv;
	
	public static void init() {
		new Teleporter();
		inv = Bukkit.createInventory(null, 54, TELEPORTER_TITLE);
		
		int n = 0;
		
		for (MobType type : MobType.values()) {
			inv.setItem(n++, Item.createItem("§8§l[§a"+type.getDisplayName()+"§7 Level§8: §2"+type.getLevel()+"§8§l]", "", Material.MONSTER_EGG, 1, type.getEgg()));
		}
		
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getPlayer().getWorld().getName().equals(Locations.lobby_world.getName())) {
			if (e.getAction()==Action.RIGHT_CLICK_AIR||e.getAction()==Action.RIGHT_CLICK_BLOCK) {
				if (e.getPlayer().getInventory().getItemInMainHand()!=null) {
					ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
					if (item.getItemMeta()!=null&&item.getItemMeta().getDisplayName()!=null) {
						if (item.equals(TELEPORTER_ITEM)) {
							
							e.getPlayer().openInventory(inv);
							
						}
					}
				}
			}
		}
	}
	
}
