package bernhard.scharrer.battlemobs.mobs.slime;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.SlimeSplitEvent;

import bernhard.scharrer.battlemobs.mobs.MobListener;

public class SlimeListener extends MobListener {
	
	@EventHandler
	public void onSplit(SlimeSplitEvent event) {
		event.setCancelled(true);
	}
	
	
	
}
