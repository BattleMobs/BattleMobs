package bernhard.scharrer.battlemobs.mobs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import bernhard.scharrer.battlemobs.listeners.Listener;
import bernhard.scharrer.battlemobs.util.Locations;
import bernhard.scharrer.battlemobs.util.PlayerUtils;

public class ItemUtilListener extends Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getPlayer().getWorld().getName().equals(Locations.map_world.getName())) {
			if (e.getAction()==Action.RIGHT_CLICK_AIR||e.getAction()==Action.RIGHT_CLICK_BLOCK) {
				if (e.getPlayer().getInventory().getItemInMainHand()!=null) {
					ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
					if (item.getItemMeta()!=null&&item.getItemMeta().getDisplayName()!=null) {
						if (item.getItemMeta().getDisplayName().equals(MobItems.LOBBY.getItemMeta().getDisplayName())) {
							PlayerUtils.teleportToLobby(e.getPlayer());
						}
						if (item.getItemMeta().getDisplayName().equals(MobItems.AUTO_RESPAWN_ON.getItemMeta().getDisplayName())) {
							e.getPlayer().sendMessage("§cThis will be implemented soon...");
						}
					}
				}
			}
		}
	}
	
}
