package bernhard.scharrer.battlemobs.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class PlayerPickUpListener extends Listener {
	
	@EventHandler
	public void onPickUp(EntityPickupItemEvent event) {
		if (event.getEntity() instanceof Player) {
			event.setCancelled(true);
		}
	}
	
}
