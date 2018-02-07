package bernhard.scharrer.battlemobs.mobs.skeleton;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.SkeletonHorse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.spigotmc.event.entity.EntityDismountEvent;

import bernhard.scharrer.battlemobs.mobs.MobListener;
import bernhard.scharrer.battlemobs.util.Cooldown;
import bernhard.scharrer.battlemobs.util.Item;
import bernhard.scharrer.battlemobs.util.Locations;
import bernhard.scharrer.battlemobs.util.Task;
import bernhard.scharrer.battlemobs.util.Tier;

public class SkeletonListener extends MobListener {
	
	private static Random random = new Random();
	
	private static final double ARROW_DAMAGE = 4;
	private static final double ARROW_BONUS = 2;
	private static final double ARROW_MULTIPLIER = 2;
	private static final String ARROW_TAG_HEADER = "񗉶;";
	private static final PotionEffect FLASH = new PotionEffect(PotionEffectType.BLINDNESS, 20, 0);

	private static final double BONE_BREAKER_RADIUS = 15;
	private static final double BONE_BREAKER_DAMAGE = 0;
	private static final int BONE_BREAKER_COOLDOWN = 30;

	private static final int RIDE_OF_DEATH_COOLDOWN = 60;
	private static final ItemStack RIDE_OF_DEATH_SADDLE = Item.createIngameItem("Saddle", Material.SADDLE, 0);
	private static final PotionEffect RIDE_OF_DEATH_BLIND = new PotionEffect(PotionEffectType.BLINDNESS, 60, 0);
	private static final double RIDE_OF_DEATH_HEAL = 2;
	
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
							if (p.isInsideVehicle()) {
								if (p.getVehicle() instanceof SkeletonHorse) {
									if (tier >= Tier.TIER_3_2) damage *= ARROW_MULTIPLIER;
								}
							}
							
							event.getProjectile().setCustomNameVisible(false);
							event.getProjectile().setCustomName(ARROW_TAG_HEADER+p.getName()+";"+damage+";"+(tier>=Tier.TIER_1_2)+";"+(tier>=Tier.TIER_1_3)+";"+(tier>=Tier.TIER_3_3));
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
				
				if (event.getDamager().getCustomName()!=null && event.getDamager().getCustomName().startsWith(ARROW_TAG_HEADER)) {
					
					event.setCancelled(true);
					
					String[] data = event.getDamager().getCustomName().split(";");
					
					p.damage(Double.parseDouble(data[2]));
					
					if (Boolean.parseBoolean(data[3]) && !Boolean.parseBoolean(data[5])) {
						p.addPotionEffect(FLASH);
					}
					
					if (Boolean.parseBoolean(data[4])) {
						rotateHead(p);
					}
					
					if (Boolean.parseBoolean(data[5])) {
						p.addPotionEffect(RIDE_OF_DEATH_BLIND);
					}
					
				}
				
			}
			
			if (event.getEntity() instanceof SkeletonHorse) {
				
				if (event.getDamager().getCustomName()!=null && event.getDamager().getCustomName().startsWith(ARROW_TAG_HEADER)) {
					event.setCancelled(true);
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
				if (tier != Tier.UNDEFINED && hand!=null && hand.getItemMeta()!=null && hand.getItemMeta().getDisplayName()!=null) {
					
					/*
					 * bone breaker
					 */
					if (hand.getItemMeta().getDisplayName().contains(SkeletonItems.ABILITY_2_NAME)) {
						
						new Cooldown(p, 1, BONE_BREAKER_COOLDOWN);
						int spins = 1;
						spins += (tier>=Tier.TIER_2_2?1:0);
						spins += (tier>=Tier.TIER_2_3?2:0);
						boolean flash = tier>=Tier.TIER_2_3;
						
						for (Entity e : p.getNearbyEntities(BONE_BREAKER_RADIUS, BONE_BREAKER_RADIUS, BONE_BREAKER_RADIUS)) {
							
							if (e instanceof Player) {
								Player enemy = (Player) e;
								spin(enemy, spins, flash);
							}
							
						}
						
					}
					
					/*
					 * ride of death
					 */
					if (hand.getItemMeta().getDisplayName().contains(SkeletonItems.ABILITY_3_NAME)) {
						
						new Cooldown(p, 2, RIDE_OF_DEATH_COOLDOWN);
						
						SkeletonHorse horse = (SkeletonHorse) Locations.map_world.spawnEntity(p.getLocation(), EntityType.SKELETON_HORSE);
						horse.setAdult();
						horse.setBreed(true);
						horse.setTamed(true);
						horse.getInventory().setItem(0, RIDE_OF_DEATH_SADDLE);
						horse.addPassenger(p);
						
						new Task(0, 1) {
							
							public void run() {
								if (SkeletonListener.this.valid(p) && p.isInsideVehicle() && p.getVehicle() instanceof SkeletonHorse) {
									if (p.getHealth()+RIDE_OF_DEATH_HEAL>=p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()) {
										p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
									} else {
										p.setHealth(p.getHealth()+RIDE_OF_DEATH_HEAL);
									}
								} else {
									cancel();
								}
							}
						};
						
					}
					
				}
			}
			
		}
	}
	
	@EventHandler
	public void onExit(EntityDismountEvent event) {
		if (super.valid(event.getDismounted())) {
			if (event.getDismounted() instanceof SkeletonHorse) {
				event.getDismounted().remove();
			}
		}
	}
	
	private void spin(Player enemy, int amount, boolean flash) {
	
		new Task(0,1) {
			
			private int spins = 0;
			
			public void run() {
				
				if (spins++ < amount) {
					
					if (SkeletonListener.this.valid(enemy)) {
						rotateHead(enemy);
						enemy.damage(BONE_BREAKER_DAMAGE);
						Locations.map_world.playSound(enemy.getLocation(), Sound.BLOCK_GLASS_BREAK, 1, 1);
						if (flash) enemy.addPotionEffect(FLASH);
					}
					
				} else {
					 cancel();
				}
				
			}
		};
		
		
		
	}

	private void rotateHead(Player p) {
		Locations.map_world.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 1);
		int yaw = random.nextInt(360);
		Location newLoc = p.getLocation();
		newLoc.setYaw(yaw-180);
		p.teleport(newLoc);
	}
	
}
