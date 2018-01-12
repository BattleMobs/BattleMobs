package bernhard.scharrer.battlemobs.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener extends Listener {
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (event.getWhoClicked().getGameMode()!=GameMode.CREATIVE) {
			event.setCancelled(true);
		}
	}

}
