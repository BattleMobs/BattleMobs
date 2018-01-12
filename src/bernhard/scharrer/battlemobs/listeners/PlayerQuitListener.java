package bernhard.scharrer.battlemobs.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

import bernhard.scharrer.battlemobs.mobs.MobMaster;

public class PlayerQuitListener extends Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		MobMaster.removePlayer(event.getPlayer());
	}
	
}
