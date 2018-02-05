package bernhard.scharrer.battlemobs.mobs.pig;

import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
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
import org.spigotmc.event.entity.EntityDismountEvent;

import bernhard.scharrer.battlemobs.mobs.MobListener;
import bernhard.scharrer.battlemobs.util.Cooldown;
import bernhard.scharrer.battlemobs.util.Scheduler;
import bernhard.scharrer.battlemobs.util.Tier;
import de.robingrether.idisguise.api.PlayerInteractDisguisedPlayerEvent;

public class PigListener extends MobListener {

	private static final int PORK_BURN_SECONDS = 3;
	private static final double PORK_DAMAGE = 6;
	private static final float PORK_LIFESTEAL_2 = 0.4f;
	private static final float PORK_LIFESTEAL_1 = 0.2f;
	
	private static final double HOOK_RADIUS = 15.0;
	private static final double HOOK_POWER = 1.0;
	private static final double HOOK_DAMAGE = 4.0;
	private static final int HOOK_COOLDOWN = 15;
	
	private static final PotionEffect SADDLE_EFFECT = new PotionEffect(PotionEffectType.BLINDNESS, 40, 0);
	private static final double SADDLE_DAMAGE = 1;
	private static final double SADDLE_BONUS = 1;
	private static final int SADDLE_FIRE = 5;
	private static final double SADDLE_DOWN_RADIUS = 4;

	@EventHandler (priority=EventPriority.HIGHEST)
	public void onDamage(EntityDamageByEntityEvent event) {
		
		if (super.valid(event.getEntity().getWorld())) {
			
			if (event.getDamager() instanceof Player) {
				
				Player p = (Player) event.getDamager();
				int tier = super.getMobTier(p);
				ItemStack hand = p.getInventory().getItemInMainHand();
				
				if (tier != Tier.UNDEFINED && hand!=null && hand.getItemMeta()!=null&&hand.getItemMeta().getDisplayName().contains(PigItems.ABILITY_1_NAME)) {
					
					event.setCancelled(true);
					if (event.getEntity() instanceof LivingEntity) {
						LivingEntity lentity = (LivingEntity) event.getEntity();
						lentity.damage(PORK_DAMAGE);
					}
					
					double max = p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
					double heal = PORK_DAMAGE*(tier>=Tier.TIER_1_2?PORK_LIFESTEAL_2:PORK_LIFESTEAL_1);
					
					if (p.getHealth()+heal>=max) {
						p.setHealth(max);
					} else {
						p.setHealth(p.getHealth()+heal);
					}
					
					if (tier >= Tier.TIER_1_3) {
						event.getEntity().setFireTicks(20*PORK_BURN_SECONDS);	
					}
					
				} else event.setCancelled(true);
				
			}
			
		}
		
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		
		if (super.valid(event.getPlayer().getWorld())) {
			
			Player p = event.getPlayer();
			int tier = super.getMobTier(p);
			ItemStack hand = p.getInventory().getItemInMainHand();
			
			if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (tier != -1 && hand!=null && hand.getItemMeta()!=null) {
					
					if (hand!=null&&hand.getItemMeta()!=null&&hand.getItemMeta().getDisplayName()!=null) {
						/*
						 * pig hook
						 */
						if (hand.getItemMeta().getDisplayName().contains(PigItems.ABILITY_2_NAME)) {
							hook(p, tier);
						}
						
						/*
						 * saddle down
						 */
						if (hand.getItemMeta().getDisplayName().contains(PigItems.ABILITY_3_NAME)) {
							
							Player tracked = null;
							
							for (Entity e : p.getNearbyEntities(SADDLE_DOWN_RADIUS, SADDLE_DOWN_RADIUS, SADDLE_DOWN_RADIUS)) {
								if (e instanceof Player) {
									tracked = (Player) e;
									break;
								}
							}
							
							if (tracked!=null) {
								if (super.valid(event.getPlayer().getWorld())) {
									
									event.setCancelled(true);
									
									if (hand!=null&&hand.getItemMeta()!=null&&hand.getItemMeta().getDisplayName().contains(PigItems.ABILITY_3_NAME)) {
										
										p.addPassenger(tracked);
										new RidingEffect(this, tier, p, tracked);
										new Cooldown(p, 2, 30);
										
									}
									
									if (hand!=null&&hand.getItemMeta()!=null&&hand.getItemMeta().getDisplayName().contains(PigItems.ABILITY_2_NAME)) {
										hook(p, tier);
									}
									
								}
							} else {
								
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_SNARE, 1, 1);
								
							}

						}
					}
					
				}
			}
			
		}
		
	}
	
	private void hook(Player p, int tier) {
		for (Entity e : p.getNearbyEntities(HOOK_RADIUS, HOOK_RADIUS, HOOK_RADIUS)) {
			
			if (e instanceof Damageable) {
				
				e.setVelocity(p.getEyeLocation().toVector().subtract(e.getLocation().toVector()).normalize().multiply(tier>=8?HOOK_POWER*2:HOOK_POWER));
				
				if (tier >= 5) {
					Damageable d = (Damageable) e;
					d.damage(HOOK_DAMAGE);
				}
				
				if (e instanceof Player) {
					Player enemy = (Player) e;
					enemy.playSound(enemy.getLocation(), Sound.BLOCK_DISPENSER_LAUNCH, 1, 1);
				}
			}
			
		}
		
		p.playSound(p.getLocation(), Sound.BLOCK_FENCE_GATE_OPEN, 1, 1);
		new Cooldown(p, 1, tier>=8?HOOK_COOLDOWN/2:HOOK_COOLDOWN);
	}

	@EventHandler
	public void onRideTry(PlayerInteractDisguisedPlayerEvent event) {
	}
	
	@EventHandler
	public void onExit(EntityDismountEvent event) {
		if (super.valid(event.getDismounted())) {
			if (event.getDismounted() instanceof Player) {
				Player p = (Player) event.getDismounted();
				int tier = super.getMobTier(p);
				if (tier>=9) event.getEntity().setFireTicks(SADDLE_FIRE*20);
			}
		}
	}
	
	private class RidingEffect {
		
		private int task;
		
		public RidingEffect(MobListener context, int tier, Player pig, Entity target) {
			
			this.task = Scheduler.schedule(20, 5, ()->{
				
				if (context.valid(pig) && context.valid(target) && pig.getPassengers().contains(target)) {
					
					if (target instanceof LivingEntity) {
						LivingEntity lentity = (LivingEntity) target;
						
						lentity.damage(SADDLE_DAMAGE+(tier>=9?SADDLE_BONUS:0));
						if (tier>=6) lentity.addPotionEffect(SADDLE_EFFECT);
					}
					
				} else {
					Scheduler.cancel(task);
				}
				
			});
			
		}
		
	}
	
}
