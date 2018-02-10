package bernhard.scharrer.battlemobs.mobs.villager;

import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import bernhard.scharrer.battlemobs.mobs.MobListener;

public class VillagerListener extends MobListener {
	
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent event) {
		if (event.getRightClicked() instanceof Villager) {
			event.setCancelled(true);
		}
	}
	
}
