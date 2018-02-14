package bernhard.scharrer.battlemobs.mobs.zombie;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import bernhard.scharrer.battlemobs.BattleMobs;
import bernhard.scharrer.battlemobs.mobs.MobListener;
import bernhard.scharrer.battlemobs.util.Cooldown;
import bernhard.scharrer.battlemobs.util.Item;
import bernhard.scharrer.battlemobs.util.Task;
import bernhard.scharrer.battlemobs.util.Tier;
import de.robingrether.idisguise.disguise.AgeableDisguise;
import de.robingrether.idisguise.disguise.DisguiseType;

public class ZombieListener extends MobListener {

	private static final PotionEffect SWORD_SLOW_1 = new PotionEffect(PotionEffectType.SLOW, 30, 2);
	private static final PotionEffect SWORD_SLOW_2 = new PotionEffect(PotionEffectType.SLOW, 60, 2);
	private static final int SWORD_DAMAGE_TIER1 = 4;
	private static final int SWORD_DAMAGE_TIER2 = 5;
	private static final int SWORD_DAMAGE_TIER3 = 6;

	private static final float BLOODRAGE_SPEED = 0.6f;
	private static final int BLOODRAGE_COOLDOWN_TIER1 = 30;
	private static final int BLOODRAGE_COOLDOWN_TIER2 = 20;
	private static final int BLOODRAGE_DURATION = 5;
	
	private static List<Player> bloodrage = new ArrayList<>();
	private static List<Player> inner_strength = new ArrayList<>();

	private static final int INNERSTRENGH_DURATION = 15;
	private static final int INNERSTRENGH_COOLDOWN_TIER1 = 60;
	private static final int INNERSTRENGH_COOLDOWN_TIER2 = 40;
	protected static final double INNER_STRENGTH_DAMAGE = 2;
	private static final PotionEffect INNERSTRENGH_RESISTANCE = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20+INNERSTRENGH_DURATION*20, 2);
	private static final PotionEffect INNERSTRENGH_REGENERATION = new PotionEffect(PotionEffectType.REGENERATION, 20+INNERSTRENGH_DURATION*20, 2);
	private static final double INNERSTRENGH_RADIUS = 2;
	private static final double INNERSTRENGH_FORCE = 1.3;
	private static final PotionEffect FLASH = new PotionEffect(PotionEffectType.BLINDNESS, 20, 0);

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		if (super.valid(event.getEntity()) && super.valid(event.getDamager())) {

			if (event.getDamager() instanceof Player) {

				Player p = (Player) event.getDamager();
				int tier = super.getMobTier(p);
				ItemStack hand = p.getInventory().getItemInMainHand();

				if (tier != -1 && hand != null && hand.getItemMeta() != null
						&& hand.getItemMeta().getDisplayName().contains(ZombieItems.ABILITY_1_NAME)) {

					event.setCancelled(true);

					if (event.getEntity() instanceof LivingEntity) {
						LivingEntity lentity = (LivingEntity) event.getEntity();
						lentity.addPotionEffect(tier >= 4 ? SWORD_SLOW_2 : SWORD_SLOW_1);
						if (tier >= Tier.TIER_3_1) {
							lentity.damage(bloodrage.contains(p)? SWORD_DAMAGE_TIER1*0.4:SWORD_DAMAGE_TIER1);
						}
						else if (tier >= Tier.TIER_2_2) {
							lentity.damage(bloodrage.contains(p)? SWORD_DAMAGE_TIER2*0.4:SWORD_DAMAGE_TIER2);
						}
						else if (tier >= Tier.TIER_1_2) {
							lentity.damage(bloodrage.contains(p)? SWORD_DAMAGE_TIER3*0.4:SWORD_DAMAGE_TIER3);
						}

					}

				}

			}

		}

	}

	@EventHandler
	public void bloodRage(PlayerInteractEvent event) {

		if (super.valid(event.getPlayer())) {

			Player p = event.getPlayer();
			int tier = super.getMobTier(p);
			ItemStack hand = p.getInventory().getItemInMainHand();

			if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

				if (tier != -1 && hand != null && hand.getItemMeta() != null
						&& hand.getItemMeta().getDisplayName() != null) {

					if (hand.getItemMeta().getDisplayName().contains(ZombieItems.ABILITY_2_NAME)) {
						
						if (inner_strength.contains(p)) {
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_SNARE, 1, 1);
							return;
						}
						
						bloodrage.add(p);
						
						new Cooldown(p, 1, tier >= Tier.TIER_2_2 ? BLOODRAGE_COOLDOWN_TIER2 : BLOODRAGE_COOLDOWN_TIER1);

						p.setWalkSpeed(BLOODRAGE_SPEED);
						p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 1);

						Task period = new Task(0, 0.1f) {
							public void run() {
								if (ZombieListener.this.valid(p)) {
									p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1, 1.5f);
								}
							}
						};
						
						new Task(BLOODRAGE_DURATION) {
							public void run() {
								if (ZombieListener.this.valid(p)) {
									p.setWalkSpeed(BattleZombie.ZOMBIE_WALSPEED);
									p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1, 0.5f);
									bloodrage.remove(p);
									p.addPotionEffect(FLASH);
								}
								period.cancel();
							}
						};

					}
				}
			}
		}
	}

	@EventHandler
	public void innerStrengh(PlayerInteractEvent event) {

		if (super.valid(event.getPlayer())) {

			Player p = event.getPlayer();
			int tier = super.getMobTier(p);
			ItemStack hand = p.getInventory().getItemInMainHand();
			
			if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

				if (tier != Tier.UNDEFINED && Item.valid(hand)) {

					if (hand.getItemMeta().getDisplayName().contains(ZombieItems.ABILITY_3_NAME)) {
						
						if (bloodrage.contains(p)) {
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_SNARE, 1, 1);
							return;
						}
						
						inner_strength.add(p);
						
						new Cooldown(p, 2,
								tier >= Tier.TIER_3_2 ? INNERSTRENGH_COOLDOWN_TIER2 : INNERSTRENGH_COOLDOWN_TIER1);

						p.playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 1, 1);
						BattleMobs.getAPI().disguise(p, DisguiseType.GIANT.newInstance());
						p.addPotionEffect(INNERSTRENGH_RESISTANCE);

						if (tier >= Tier.TIER_2_3) {p.addPotionEffect(INNERSTRENGH_REGENERATION);}
						
						event.setCancelled(true);

						Task period = new Task(0,0.2f) {
							
							
							@Override
							public void run() {
								
								if (ZombieListener.this.valid(p)) {
									for (Entity e : p.getNearbyEntities(INNERSTRENGH_RADIUS, INNERSTRENGH_RADIUS*5, INNERSTRENGH_RADIUS)) {
										
										if (e instanceof LivingEntity) {
											
											LivingEntity enemy = (LivingEntity) e;
											
											if (enemy instanceof Player) {
												if (((Player) enemy).getGameMode() == GameMode.SURVIVAL) {
													((Player) enemy).playSound(enemy.getLocation(), Sound.ENTITY_ZOMBIE_AMBIENT, 1, 0.5f);
												} else continue;
											}
											enemy.setVelocity(enemy.getEyeLocation().toVector().subtract(p.getLocation().toVector()).normalize().multiply(INNERSTRENGH_FORCE));
											enemy.damage(INNER_STRENGTH_DAMAGE);
											
										}
									}
								}
							}
						}; 
						
						new Task(INNERSTRENGH_DURATION) {
							
							@Override
							public void run() {
								if (ZombieListener.this.valid(p)) {
									BattleMobs.getAPI().disguise(p, new AgeableDisguise(DisguiseType.ZOMBIE));
									inner_strength.remove(p);
									p.addPotionEffect(FLASH);
								}
								period.cancel();
							}
						};
						
						

					}
				}
			}
		}
	}

}
