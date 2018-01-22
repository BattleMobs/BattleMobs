package bernhard.scharrer.battlemobs.mobs.skeleton;

import java.util.Random;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import bernhard.scharrer.battlemobs.mobs.MobListener;

public class SkeletonListener extends MobListener {
	
	private static Random random = new Random();
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Arrow) {
			
		}
	}
	
	private void rotateHead(Player p) {
		p.getLocation().setYaw(random.nextInt(360));
	}
	
}
