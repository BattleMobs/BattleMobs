package bernhard.scharrer.battlemobs.mobs.spider;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import bernhard.scharrer.battlemobs.mobs.MobListener;
import bernhard.scharrer.battlemobs.util.Cooldown;
import bernhard.scharrer.battlemobs.util.Item;
import bernhard.scharrer.battlemobs.util.Tier;

public class SpiderListener extends MobListener {
	
	private static final float EYE_OF_SPIDER_SPEED = 2.5f;

	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			Player p = (Player) event.getDamager();
			int tier = super.getMobTier(p);
			if (super.valid(p) && tier != Tier.UNDEFINED) {
				
				if (Item.valid(p.getInventory().getItemInMainHand())) {
					
					/*
					 * eye of the spider melee
					 */
					if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(SpiderItems.ABILITY_1_NAME)) {
						
					}
					
					event.setCancelled(true);
					
				}
				
			}
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		int tier = super.getMobTier(p);
		if (super.valid(p) && tier != Tier.UNDEFINED) {
			
			if (Item.valid(p.getInventory().getItemInMainHand())) {
				if (event.getAction()==Action.RIGHT_CLICK_AIR||event.getAction()==Action.RIGHT_CLICK_BLOCK) {
					
					/*
					 * eye of the spider shoot arrows
					 */
					if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(SpiderItems.ABILITY_1_NAME)) {
						
						Arrow arrow = p.getWorld().spawn(p.getEyeLocation().add(0,0.1,0), Arrow.class);
						arrow.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(EYE_OF_SPIDER_SPEED));
						arrow.setShooter(p);
						
						new Cooldown(p, 0, 10);
					}
					
				}
			}
			
		}
	}
	
}
