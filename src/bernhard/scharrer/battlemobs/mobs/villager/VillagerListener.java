package bernhard.scharrer.battlemobs.mobs.villager;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.util.Vector;

import bernhard.scharrer.battlemobs.mobs.MobListener;
import bernhard.scharrer.battlemobs.mobs.MobType;
import bernhard.scharrer.battlemobs.util.Item;
import bernhard.scharrer.battlemobs.util.Tier;

public class VillagerListener extends MobListener {
	
	private static final double BAD_TRADE_DAMAGE = 4;
	private static final Vector X_DIR = new Vector(1, 0, 0);

	@EventHandler
	public void onInteract(PlayerInteractEntityEvent event) {
		if (event.getRightClicked() instanceof Villager) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player && event.getEntity() instanceof LivingEntity) {
			Player p = (Player) event.getDamager();
			LivingEntity enemy = (LivingEntity) event.getEntity();
			if (valid(p, MobType.VILLAGER)) {
				int tier = super.getMobTier(p);
				if (tier!=Tier.UNDEFINED && Item.valid(p.getInventory().getItemInMainHand())) {
					if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(VillagerItems.ABILITY_1_NAME)) {
						
						event.setCancelled(true);
						enemy.damage(BAD_TRADE_DAMAGE);
						Location ploc = p.getLocation();
						Location eloc = enemy.getLocation();
						enemy.teleport(ploc);
						
						float angle = 0;
						
						double dx = eloc.getX() - ploc.getX();
						double dy = eloc.getZ() - ploc.getZ();
						
						if (dx > 0 && dy > 0) {
							angle = (float) (270 - Math.toDegrees(Math.atan(dx/dy)));
						} else if (dx < 0 && dy > 0) {
							angle = (float) (270 + Math.toDegrees(Math.atan(dx/dy)));
						} else if (dx > 0 && dy < 0) {
							angle = (float) (90 + Math.toDegrees(Math.atan(dx/dy)));
						} else if (dx < 0 && dy < 0) {
							angle = (float) (90 - Math.toDegrees(Math.atan(dx/dy)));
						}
						
						p.sendMessage("Angle:"+angle);
						eloc.setYaw(angle);
						p.teleport(eloc);
						
						p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_TRADING, 1, 1);
						if (enemy instanceof Player) {
							((Player)enemy).playSound(enemy.getLocation(), Sound.ENTITY_VILLAGER_TRADING, 1, 1);
						}
						
					}
				}
			}
		}
	}
	
}
