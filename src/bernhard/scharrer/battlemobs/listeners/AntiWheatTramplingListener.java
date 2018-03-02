package bernhard.scharrer.battlemobs.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class AntiWheatTramplingListener extends Listener {
	
	@EventHandler
	public void onTrample(PlayerInteractEvent event) {
		if(event.getAction().equals(Action.PHYSICAL) && event.getClickedBlock().getType().equals(Material.SOIL)){
			event.setCancelled(true);
		}
	}

}
