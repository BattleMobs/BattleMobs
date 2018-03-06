package bernhard.scharrer.battlemobs.mobs.spider;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import bernhard.scharrer.battlemobs.mobs.MobListener;
import bernhard.scharrer.battlemobs.mobs.MobType;
import bernhard.scharrer.battlemobs.util.Cooldown;
import bernhard.scharrer.battlemobs.util.DamageHandler;
import bernhard.scharrer.battlemobs.util.Item;
import bernhard.scharrer.battlemobs.util.Task;
import bernhard.scharrer.battlemobs.util.Tier;
import net.minecraft.server.v1_12_R1.EnumParticle;
import net.minecraft.server.v1_12_R1.PacketPlayOutWorldParticles;

public class SpiderListener extends MobListener {
	
	private static final PotionEffect POISON_3S = new PotionEffect(PotionEffectType.POISON, 60, 1);
	private static final PotionEffect POISON_5S = new PotionEffect(PotionEffectType.POISON, 100, 0);
	private static final PotionEffect SPEED_1_15S = new PotionEffect(PotionEffectType.SPEED, 300, 0);
	private static final PotionEffect SPEED_2_15S = new PotionEffect(PotionEffectType.SPEED, 300, 3);
	private static final PotionEffect STRENGTH_15S = new PotionEffect(PotionEffectType.POISON, 300, 2);
	
	private static final int EYE_OF_SPIDER_COOLDOWN = 5;
	private static final float EYE_OF_SPIDER_SPEED = 2.5f;
	private static final double EYE_OF_SPIDER_DAMAGE = 6;
	private static final double EYE_OF_SPIDER_ARROW_DAMAGE = 4;
	private static final double EYE_OF_SPIDER_ARROW_AMOUNT = 3;
	private static final String ARROW_TAG_HEADER = "񗉷;";
	private static final double EYE_OF_SPIDER_KNOCKBACK = 1.2;
	
	private static final int WEB_BOMB_EXPLODE_TIME = 4;
	private static final float WEB_BOMB_DECAY_TIME = 3;
	private static final float WEB_BOMB_SPEED = 1.2f;
	private static final int WEB_BOMB_RADIUS = 8;
	private static final int WEB_BOMB_COOLDOWN = 20;
	private static final double WEB_BOMB_DAMAGE = 5;
	private static final ItemStack WEB_BOMB_ITEM = Item.createIngameItem("", Material.STRING, 0);
	
	private static final int SPIDER_ARMY_COOLDOWN = 30;
	private static final float SPIDER_ARMY_DESPAWN = 5;
	private static final double SPIDER_ARMY_RADIUS = 15;
	private static final int SPIDER_ARMY_SPIDERS = 3;

	@EventHandler (priority=EventPriority.HIGHEST)
	public void onHit(EntityDamageByEntityEvent event) {
		
		Bukkit.broadcastMessage("???");
		
		if (event.getDamager() instanceof Player) {
			Player p = (Player) event.getDamager();
			int tier = super.getMobTier(p);
			if (super.valid(p, MobType.SPIDER) && tier != Tier.UNDEFINED) {
				if (Item.valid(p.getInventory().getItemInMainHand())) {
					/*
					 * eye of the spider melee
					 */
					if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(SpiderItems.ABILITY_1_NAME)) {
						if (event.getEntity() instanceof LivingEntity) {
							p.playSound(p.getLocation(), Sound.ENTITY_SPIDER_AMBIENT, 1, 1);
							event.setCancelled(true);
							LivingEntity lentity = (LivingEntity) event.getEntity();
							lentity.addPotionEffect(POISON_3S);
							DamageHandler.deal(lentity, p, EYE_OF_SPIDER_DAMAGE);
							if (tier >= Tier.TIER_1_3) {
								lentity.setVelocity(lentity.getEyeLocation().toVector().subtract(p.getLocation().toVector()).normalize().multiply(EYE_OF_SPIDER_KNOCKBACK));
							}
						}
					}
					
					event.setCancelled(true);
					
				}
				
			}
		}
		
		Bukkit.broadcastMessage("1");
		
		if (event.getDamager() instanceof Arrow) {
			
			Bukkit.broadcastMessage("2");
			
			if (event.getEntity() instanceof LivingEntity) {
				
				LivingEntity enemy = (LivingEntity) event.getEntity();
				
				/*
				 * eye of the spider ranged
				 */
				Bukkit.broadcastMessage("Name: "+event.getDamager().getCustomName());
				if (event.getDamager().getCustomName()!=null && event.getDamager().getCustomName().startsWith(ARROW_TAG_HEADER)) {
					
					String name = event.getDamager().getCustomName().split(";")[1];
					Player shooter = null;
					
					for (Player current : enemy.getWorld().getPlayers()) if (name.equals(current.getName())) {
						shooter = current;
						break;
					}
					
					enemy.addPotionEffect(POISON_3S);
					DamageHandler.deal(enemy, shooter, EYE_OF_SPIDER_ARROW_DAMAGE);
					Bukkit.broadcastMessage("Damage: "+EYE_OF_SPIDER_ARROW_DAMAGE);
				}
			}
		}
		
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		int tier = super.getMobTier(p);
		if (super.valid(p, MobType.SPIDER) && tier != Tier.UNDEFINED) {
			
			if (Item.valid(p.getInventory().getItemInMainHand())) {
				if (event.getAction()==Action.RIGHT_CLICK_AIR||event.getAction()==Action.RIGHT_CLICK_BLOCK) {
					
					/*
					 * eye of the spider shoot arrows
					 */
					if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(SpiderItems.ABILITY_1_NAME)) {
						
						if (tier>=Tier.TIER_1_2) {
							new Task(0,0.3f) {
								private int amount = 0;
								@Override
								public void run() {
									shotArrow(p);
									if (++amount >= EYE_OF_SPIDER_ARROW_AMOUNT) {
										cancel();
									}
								}
							};
						} else {
							shotArrow(p);
						}
						
						new Cooldown(p, 0, EYE_OF_SPIDER_COOLDOWN);
					}
					
					/*
					 * web bomb
					 */
					if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(SpiderItems.ABILITY_2_NAME)) {
						
						new Cooldown(p, 1, WEB_BOMB_COOLDOWN);
						new WebBomb(p, tier);
						
					}
					
					/*
					 * spider army
					 */
					if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(SpiderItems.ABILITY_3_NAME)) {
						
						
						
						LivingEntity target = null;
						
						for (Entity nearBy : p.getNearbyEntities(SPIDER_ARMY_RADIUS, SPIDER_ARMY_RADIUS, SPIDER_ARMY_RADIUS)) {
							if (nearBy instanceof LivingEntity) {
								target = (LivingEntity) nearBy;
								break;
							}
						}
						
						if (target!=null) {
							new Cooldown(p, 2, (tier>=Tier.TIER_3_3?SPIDER_ARMY_COOLDOWN/2:SPIDER_ARMY_COOLDOWN));
							
							List<CaveSpider> spiders = new ArrayList<>();
							
							for (int n=0;n<(tier>=Tier.TIER_3_3?SPIDER_ARMY_SPIDERS*2:SPIDER_ARMY_SPIDERS);n++) {
								spiders.add((CaveSpider) p.getWorld().spawnEntity(p.getLocation(), EntityType.CAVE_SPIDER));
							}
							
							for (CaveSpider spider : spiders) {
								spider.setHealth(1);
								spider.setTarget(target);
								spider.addPotionEffect(tier >= Tier.TIER_3_2 ? SPEED_2_15S : SPEED_1_15S);
								spider.addPotionEffect(STRENGTH_15S);
							}
							
							Task period = new Task(0, 0.5f) {
								public void run() {
									for (CaveSpider spider :spiders) {
										if (spider != null) {
											LivingEntity newTarget = null;
											if (spider.getTarget()!=null) {
												if (spider.getTarget() == p || spider.getTarget().isDead()) {
													newTarget = getNearBy(spider, p);
												}
											} else {
												newTarget = getNearBy(spider, p);
											}
											if (newTarget!=null) spider.setTarget(newTarget);
										}
									}
								}
							};
							
							new Task(SPIDER_ARMY_DESPAWN) {
								public void run() {
									period.cancel();
									for (CaveSpider spider : spiders) {
										if (spider != null) {
											spider.remove();
										}
									}
								}
							};
						} else {
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_SNARE, 1, 1);
						}

					}
					
				}
			}
			
		}
	}
	
	private LivingEntity getNearBy(CaveSpider spider, Player owner) {
		for (Entity nearBy : spider.getNearbyEntities(SPIDER_ARMY_RADIUS, SPIDER_ARMY_RADIUS, SPIDER_ARMY_RADIUS)) {
			if (nearBy instanceof LivingEntity && nearBy != owner) {
				return (LivingEntity) nearBy;
			}
		}
		spider.remove();
		return null;
	}

	private void shotArrow(Player p) {
		p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1, 1);
		Arrow arrow = p.getWorld().spawn(p.getEyeLocation().add(0,0.1,0), Arrow.class);
		arrow.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(EYE_OF_SPIDER_SPEED));
		arrow.setCustomNameVisible(false);
		arrow.setCustomName(ARROW_TAG_HEADER+";"+p.getName());
		arrow.setShooter(p);
	}
	
	private void createWebBall(org.bukkit.entity.Item item, int size, int tier) {
		
		for (int z = item.getLocation().getBlockZ()-size-1;z<item.getLocation().getBlockZ()+size;z++) {
			for (int y = item.getLocation().getBlockY()-size-1;y<item.getLocation().getBlockY()+size;y++) {
				for (int x = item.getLocation().getBlockX()-size-1;x<item.getLocation().getBlockX()+size;x++) {
					
					if (item.getLocation().distance(new Location(item.getWorld(), x, y, z)) <= size/2) {
						replace(tier, item.getWorld().getBlockAt(x, y, z));
					}
					
				}
			}
		}
		
	}
	
	private void replace(int tier, Block block) {
		if (block!=null) {
			if (block.getType() == Material.AIR) {
				block.setType(Material.WEB);
				new Task(tier >= Tier.TIER_2_2?2*WEB_BOMB_DECAY_TIME:WEB_BOMB_DECAY_TIME) {
					public void run() {
						block.setType(Material.AIR);
					}
				};
			}
		}
		
	}
	
	private class WebBomb {
		
		public WebBomb(Player p, int tier) {
			org.bukkit.entity.Item item = p.getWorld().dropItem(p.getEyeLocation(), WEB_BOMB_ITEM);
			item.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(WEB_BOMB_SPEED));
			item.setCustomNameVisible(false);
			
			new Task(0,0.1f) {
				private int time = 0;
				@Override
				public void run() {
					if (time/5 >= WEB_BOMB_EXPLODE_TIME) {
						explode(p, item, tier);
					} else {
						float x = (float) item.getLocation().getX();
						float y = (float) item.getLocation().getY();
						float z = (float) item.getLocation().getZ();
						PacketPlayOutWorldParticles particles = new PacketPlayOutWorldParticles(EnumParticle.CRIT, true, x, y, z, 0, 0, 0, 0, 0, 0);
						for (Entity nearBy : item.getNearbyEntities(30, 30, 30)) {
							if (nearBy instanceof CraftPlayer) {
								((CraftPlayer) nearBy).getHandle().playerConnection.sendPacket(particles);
							}
						}
						for (Entity nearBy : item.getNearbyEntities(1, 1, 1)) {
							if (nearBy != p && nearBy instanceof LivingEntity) {
								explode(p, item, tier);
							}
						}
					}
					
					time++;
				}
				
				@SuppressWarnings("deprecation")
				private void explode(Player spider, org.bukkit.entity.Item item, int tier) {
					
					if (tier >= Tier.TIER_2_2) {
						for (Entity enemy : item.getNearbyEntities(WEB_BOMB_RADIUS, WEB_BOMB_RADIUS, WEB_BOMB_RADIUS)) {
							if (enemy != spider && enemy instanceof LivingEntity) {
								DamageHandler.deal((LivingEntity) enemy, spider, WEB_BOMB_DAMAGE);
								if (tier >= Tier.TIER_2_3) {
									((LivingEntity) enemy).addPotionEffect(POISON_5S);
								}
							}
						}
					}
					
					createWebBall(item, WEB_BOMB_RADIUS, tier);
					item.remove();
					spider.getWorld().playSound(spider.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
					spider.getWorld().playEffect(item.getLocation(), Effect.EXPLOSION_HUGE, 1);
					cancel();
				}
				
			};
		}
		
	}
	
}
