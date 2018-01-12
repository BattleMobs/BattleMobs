package bernhard.scharrer.battlemobs.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.LeavesDecayEvent;

public class LeavesDecayListener extends Listener {
	
	@EventHandler
	public void onDecay(LeavesDecayEvent e) {
		e.setCancelled(true);
	}
	
}
