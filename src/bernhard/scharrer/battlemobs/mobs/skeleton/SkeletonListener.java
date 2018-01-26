package bernhard.scharrer.battlemobs.mobs.skeleton;

import java.util.Random;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;

import bernhard.scharrer.battlemobs.mobs.MobListener;
import bernhard.scharrer.battlemobs.util.Tier;

public class SkeletonListener extends MobListener {
	
	private static Random random = new Random();
	
	private static final String ARROW_TAG_SINGLE = "§1§r";
	private static final String ARROW_TAG_DOUBLE = "§2§r";
	private static final double ARROW_DAMAGE = 0;
	
	@EventHandler
	public void onLaunch(EntityShootBowEvent event) {
		
		if (event.getBow().getItemMeta()!=null&&event.getBow().getItemMeta().getDisplayName()!=null) {
			if (event.getBow().getItemMeta().getDisplayName().contains(SkeletonItems.ABILITY_1_NAME)) {
				
				if (event.getEntity() instanceof Player) {
					Player p = (Player) event.getEntity();
					int tier = super.getMobTier(p);
					if (super.valid(p)) {
						if (tier!=Tier.UNDEFINED) {
							if (tier>=Tier.TIER_3_2) {
								event.getProjectile().setCustomName(ARROW_TAG_DOUBLE);
							} else {
								event.getProjectile().setCustomName(ARROW_TAG_SINGLE);
							}
							event.getProjectile().setCustomNameVisible(false);
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
				
				if (event.getDamager().getCustomName().equals(ARROW_TAG_SINGLE)) {
					event.setCancelled(true);
					p.damage(ARROW_DAMAGE);
				} else if (event.getDamager().getCustomName().equals(ARROW_TAG_SINGLE)) {
					event.setCancelled(true);
				}
				
			}
		}
	}
	
	private void rotateHead(Player p) {
		p.getLocation().setYaw(random.nextInt(360));
	}
	
}
