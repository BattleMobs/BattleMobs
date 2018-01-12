package bernhard.scharrer.battlemobs.listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileHitListener extends Listener {
	
	@EventHandler
	public void onHit(ProjectileHitEvent event) {
		
		if (event.getEntity() instanceof Arrow) {
			event.getEntity().remove();
		}
		
	}
	
}
