package bernhard.scharrer.battlemobs.mobs.slime;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.SlimeSplitEvent;
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

public class SlimeListener extends MobListener {

	private static final ItemStack SLOWBALL_ITEM = Item.createIngameItem("", Material.SLIME_BALL, 0);
	private static final double SLOWBALL_SPEED = 1.2;
	private static final float SLOWBALL_DECAY = 5;
	private static final float SLOWBALL_TRACKER_PERIOD = 0.1f;
	private static final double SLOWBALL_TRACKER_RADIUS = 1.2;
	private static final PotionEffect SLOWBALL_SLOW_EFFECT = new PotionEffect(PotionEffectType.SLOW, 80, 4);
	private static final List<Player> slowball_shooters = new ArrayList<>();
	private static final float SLOWBALL_TIME_BETWEEN_BURST = 0.4f;
	private static final double SLOWBALL_DAMAGE = 2;
	
	private static final int SLIMEARMY_COUNT = 2;
	
	@EventHandler
	public void onSplit(SlimeSplitEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {

		if (valid(event.getPlayer(), MobType.SLIME)) {

			Player p = event.getPlayer();
			int tier = super.getMobTier(p);
			
			if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR)

			if (Item.valid(p.getInventory().getItemInMainHand())) {
				if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(SlimeItems.ABILITY_1_NAME)) {
					
					new SlowBall(p, tier);
					
				}
				
				if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(SlimeItems.ABILITY_2_NAME)) {
					event.setCancelled(true);
					
					for (int n=0;n<SLIMEARMY_COUNT;n++) {
						
						new SlimeFighter(p, tier);
						
					}
					
				}
				
				if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(SlimeItems.ABILITY_3_NAME)) {
					
					// do crazy jump
					
				}
				
			}

		}

	}
	
	private class SlowBall {

		public SlowBall(Player slime, int tier) {
			
			if (slowball_shooters.contains(slime)) return;
			
			slowball_shooters.add(slime);
			
			org.bukkit.entity.Item item = slime.getWorld().dropItem(slime.getEyeLocation(), SLOWBALL_ITEM);
			item.setVelocity(slime.getEyeLocation().getDirection().normalize().multiply(SLOWBALL_SPEED));
			item.setCustomNameVisible(false);
			slime.playSound(slime.getLocation(), Sound.ENTITY_SLIME_SQUISH, 1, 1);
			
			Task period = new Task(SLOWBALL_TRACKER_PERIOD,SLOWBALL_TRACKER_PERIOD) {
				public void run() {
					
					if (item != null && !item.isOnGround()) {
						
						for (Entity nearBy : item.getNearbyEntities(SLOWBALL_TRACKER_RADIUS, SLOWBALL_TRACKER_RADIUS, SLOWBALL_TRACKER_RADIUS)) {
							if (nearBy instanceof LivingEntity && nearBy != slime) {
								
								LivingEntity enemy = (LivingEntity) nearBy;
								enemy.addPotionEffect(SLOWBALL_SLOW_EFFECT);
								DamageHandler.deal(enemy, slime, SLOWBALL_DAMAGE);
								
								enemy.getWorld().playSound(enemy.getLocation(), Sound.ENTITY_SLIME_ATTACK, 1, 1);
								
								break;
								
							}
						}
						
					} else {
						item.remove();
						cancel();
					}
				}
			};
			
			new Task(SLOWBALL_DECAY) {
				public void run() {
					item.remove();
					period.cancel();
				}
			};
			
			new Task(SLOWBALL_TIME_BETWEEN_BURST) {
				public void run() {
					slowball_shooters.remove(slime);
				}
			};
			
		}
		
	}
	
	private class SlimeFighter {
		
		private static final double SLIMEARMY_TRACK_RADIUS = 25;
		private static final int SLIMEARMY_SIZE = 5;
		private static final double SLIMEARMY_HEALTH = 8;
		private static final float SLIMEARMY_TRACKER = 1.0f;
		private static final float SLIMEARMY_DECAY = 30.0f;
		
		private Slime slime;
		private Player p;
		private Task enemy_updater;

		public SlimeFighter(Player p, int tier) {
			
			this.p = p;
			
			if (!trackNewEnemy(p)) {
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_SNARE, 1, 1);
			} else {
				
				if (!Cooldown.hasCooldown(p, 1)) new Cooldown(p, 1, 20);
				
				this.slime = p.getWorld().spawn(p.getLocation(), Slime.class);
				this.slime.setSize(SLIMEARMY_SIZE);
				this.slime.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(SLIMEARMY_HEALTH);
				this.slime.setHealth(SLIMEARMY_HEALTH);
				
				if (!trackNewEnemy(slime)) {
					kill();
				}
				
				this.enemy_updater = new Task(SLIMEARMY_TRACKER,SLIMEARMY_TRACKER) {
					public void run() {
						if (!trackNewEnemy(slime)) {
							kill();
						}
					}
				};
				
				new Task(SLIMEARMY_DECAY) {
					public void run() {
						kill();
					}
				};
				
			}
			
		}
		
		private void kill() {
			enemy_updater.cancel();
			slime.remove();
		}
		
		private boolean valid() {
			return slime != null && !slime.isDead();
		}
		
		private boolean trackNewEnemy(Entity relativ) {
			
			if (relativ instanceof Slime) {
				
				if (valid()) {
					if (slime.getTarget() == p || slime.getTarget() == null || slime.getTarget().isDead()) {
						for (Entity nearBy : relativ.getNearbyEntities(SLIMEARMY_TRACK_RADIUS, SLIMEARMY_TRACK_RADIUS, SLIMEARMY_TRACK_RADIUS)) {
							if (nearBy instanceof LivingEntity && nearBy != p) {
								if (relativ instanceof Slime) {
									((Slime) relativ).setTarget((LivingEntity) nearBy);
								}
								return true;
							}
						}
					}
					return true;
				} 
				
			} else {
				for (Entity nearBy : relativ.getNearbyEntities(SLIMEARMY_TRACK_RADIUS, SLIMEARMY_TRACK_RADIUS, SLIMEARMY_TRACK_RADIUS)) {
					if (nearBy instanceof LivingEntity && nearBy != p) {
						return true;
					}
				}
			}
			
			return false;
			
		}

		private boolean find(Entity relativ) {
			
			return false;
		}
		
	}

}
