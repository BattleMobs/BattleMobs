package bernhard.scharrer.battlemobs.mobs.skeleton;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;

import bernhard.scharrer.battlemobs.mobs.MobListener;
import bernhard.scharrer.battlemobs.util.Locations;
import bernhard.scharrer.battlemobs.util.Tier;

public class SkeletonListener extends MobListener {
	
	private static Random random = new Random();
	
	private static final double ARROW_DAMAGE = 6;
	private static final double ARROW_BONUS = 2;
	private static final double ARROW_MULTIPLIER = 2;
	private static final String ARROW_TAG_HEADER = "񗉶;";
	
	@EventHandler
	public void onLaunch(EntityShootBowEvent event) {
		
		if (event.getBow().getItemMeta()!=null&&event.getBow().getItemMeta().getDisplayName()!=null) {
			if (event.getBow().getItemMeta().getDisplayName().contains(SkeletonItems.ABILITY_1_NAME)) {
				
				if (event.getEntity() instanceof Player) {
					Player p = (Player) event.getEntity();
					int tier = super.getMobTier(p);
					if (super.valid(p)) {
						if (tier!=Tier.UNDEFINED) {
							
							double damage = ARROW_DAMAGE;
							if (tier>=Tier.TIER_1_2) damage += ARROW_BONUS;
							for (Entity e : p.getPassengers()) {
								if (e instanceof Horse) {
									damage *= ARROW_MULTIPLIER;
									break;
								}
							}
							
							event.getProjectile().setCustomNameVisible(false);
							event.getProjectile().setCustomName(p.getName()+";"+damage);
							
						}
					}
				}
				
			}
		}
		
	}
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Arrow) {
			
			if (event.getEntity() instanceof Player) {
				
				Player p = (Player) event.getEntity();
				
				if (event.getDamager().getCustomName().equals(ARROW_TAG_HEADER)) {
					
					event.setCancelled(true);
					
					String[] data = event.getDamager().getCustomName().split(";");
					Player shooter = null;
					for (Player online : Bukkit.getOnlinePlayers()) {
						if (online.getName().equals(data[1])) {
							shooter = online;
							break;
						}
					}
					
					if (shooter != null) p.damage(Double.parseDouble(data[2]), shooter);
					else p.damage(Double.parseDouble(data[2]));
					
				}
				
			}
		}
	}
	
	private void rotateHead(Player p) {
		p.getLocation().setYaw(random.nextInt(360));
	}
	
}
