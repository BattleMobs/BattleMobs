package bernhard.scharrer.battlemobs.mobs.skeleton;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import bernhard.scharrer.battlemobs.mobs.MobListener;
import bernhard.scharrer.battlemobs.mobs.pig.PigItems;
import bernhard.scharrer.battlemobs.util.Cooldown;
import bernhard.scharrer.battlemobs.util.Locations;
import bernhard.scharrer.battlemobs.util.Tier;

public class SkeletonListener extends MobListener {
	
	private static Random random = new Random();
	
	private static final double ARROW_DAMAGE = 4;
	private static final double ARROW_BONUS = 2;
	private static final double ARROW_MULTIPLIER = 2;
	private static final String ARROW_TAG_HEADER = "񗉶;";
	private static final PotionEffect FLASH = new PotionEffect(PotionEffectType.BLINDNESS, 20, 0);

	private static final double BONE_BREAKER_RADIUS = 15;
	
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
							event.getProjectile().setCustomName(ARROW_TAG_HEADER+p.getName()+";"+damage+";"+(tier>=Tier.TIER_1_2)+";"+(tier>=Tier.TIER_1_3));
							event.getBow().setDurability((short) (Material.BOW.getMaxDurability()-1));
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
				
				if (event.getDamager().getCustomName().startsWith(ARROW_TAG_HEADER)) {
					
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
					
					if (Boolean.parseBoolean(data[3])) {
						p.addPotionEffect(FLASH);
					}
					
					if (Boolean.parseBoolean(data[4])) {
						rotateHead(p);
					}
					
				}
				
			}
		}
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		if (super.valid(event.getPlayer().getWorld())) {
			
			Player p = event.getPlayer();
			int tier = super.getMobTier(p);
			ItemStack hand = p.getInventory().getItemInMainHand();
			
			if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (tier != -1 && hand!=null && hand.getItemMeta()!=null) {
					
					/*
					 * bone breaker
					 */
					if (hand.getItemMeta().getDisplayName().contains(SkeletonItems.ABILITY_2_NAME)) {
						
						new Cooldown(p, 1, 30);
						
						for (Entity e : p.getNearbyEntities(BONE_BREAKER_RADIUS, BONE_BREAKER_RADIUS, BONE_BREAKER_RADIUS)) {
							
							if (e instanceof Player) {
								Player enemy = (Player) e;
								enemy.damage(arg0);
							}
							
						}
						
					}
					
				}
			}
			
		}
	}
	
	private void rotateHead(Player p) {
		Locations.map_world.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 1);
		int yaw = random.nextInt(360);
		Location newLoc = p.getLocation();
		newLoc.setYaw(yaw-180);
		p.teleport(newLoc);
	}
	
}
