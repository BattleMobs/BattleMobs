package bernhard.scharrer.battlemobs.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
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
			inv.setItem(n++, Item.createItem("§8§l[§a"+type.getDisplayName()+"§7 Level§8: §2"+type.getLevel()+"§8§l]§r", "", Material.MONSTER_EGG, 1, type.getEgg()));
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
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getInventory().getTitle().equals(TELEPORTER_TITLE)) {
			if (e.getCurrentItem()!=null && e.getCurrentItem().getItemMeta()!=null&&e.getCurrentItem().getItemMeta().getDisplayName()!=null) {
				ItemStack item = e.getCurrentItem();
				for (MobType type : MobType.values()) {
					if (item.getItemMeta().getDisplayName().contains(type.getDisplayName())) {
						e.getWhoClicked().closeInventory();
						e.getWhoClicked().teleport(type.getTeleporterLocation());
						break;
					}
				}
			}
		}
	}
	
}
