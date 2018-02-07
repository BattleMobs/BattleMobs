package bernhard.scharrer.battlemobs.mobs.zombie;

import javax.annotation.Generated;

import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
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
import bernhard.scharrer.battlemobs.util.Scheduler;
import bernhard.scharrer.battlemobs.util.Tier;
import de.robingrether.idisguise.api.DisguiseAPI;
import de.robingrether.idisguise.disguise.AgeableDisguise;
import de.robingrether.idisguise.disguise.Disguise;
import de.robingrether.idisguise.disguise.DisguiseType;
import de.robingrether.idisguise.disguise.MobDisguise;

public class ZombieListener extends MobListener {

	private static final PotionEffect SWORD_SLOW_1 = new PotionEffect(PotionEffectType.SLOW, 30, 2);
	private static final PotionEffect SWORD_SLOW_2 = new PotionEffect(PotionEffectType.SLOW, 60, 2);
	private static final int SWORD_DAMAGE_TIER1 = 4;
	private static final int SWORD_DAMAGE_TIER2 = 5;
	private static final int SWORD_DAMAGE_TIER3 = 6;

	private static final float BLOODRAGE_SPEED = 1f;
	private static final int BLOODRAGE_COOLDOWN_TIER1 = 30;
	private static final int BLOODRAGE_COOLDOWN_TIER2 = 20;
	private static final int BLOODRAGE_DURATION = 5;
	private static int BLOODRAGE_ON = 0;

	private static final int INNERSTRENGH_DURATION = 15;
	private static final int INNERSTRENGH_COOLDOWN_TIER1 = 60;
	private static final int INNERSTRENGH_COOLDOWN_TIER2 = 40;
	private static final int INNERSTRENGH_DEMAGE = 4;
	private static final int INNERSTRENGH_KNOCKBACK = 9001; // keinen plan wie du des daun sklaierst berni :D
	private static final PotionEffect INNERSTRENGH_RESISTANCE = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 900, 2);
	private static final PotionEffect INNERSTRENGH_Regeneration = new PotionEffect(PotionEffectType.REGENERATION, 900, 2);

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
							lentity.damage(BLOODRAGE_ON >= 1? SWORD_DAMAGE_TIER1*2:SWORD_DAMAGE_TIER1);
						}
						else if (tier >= Tier.TIER_2_2) {
							lentity.damage(BLOODRAGE_ON >= 1? SWORD_DAMAGE_TIER2*2:SWORD_DAMAGE_TIER2);
						}
						else if (tier >= Tier.TIER_1_2) {
							lentity.damage(BLOODRAGE_ON >= 1? SWORD_DAMAGE_TIER3*2:SWORD_DAMAGE_TIER3);
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

						new Cooldown(p, 1, tier >= Tier.TIER_2_2 ? BLOODRAGE_COOLDOWN_TIER2 : BLOODRAGE_COOLDOWN_TIER1);

						p.setWalkSpeed(BLOODRAGE_SPEED);
						p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 1);
						BLOODRAGE_ON = 1;

						Scheduler.schedule(BLOODRAGE_DURATION * 20, () -> {

							if (super.valid(p)) {
								p.setWalkSpeed(BattleZombie.ZOMBIE_WALSPEED);
								p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1, 1);
								BLOODRAGE_ON  =0;
							}

						});

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

						new Cooldown(p, 2,
								tier >= Tier.TIER_3_2 ? INNERSTRENGH_COOLDOWN_TIER2 : INNERSTRENGH_COOLDOWN_TIER1);

						p.playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 1, 1);
						BattleMobs.getAPI().disguise(p, DisguiseType.GIANT.newInstance());
						p.addPotionEffect(INNERSTRENGH_RESISTANCE);

						if (tier >= Tier.TIER_2_3) {p.addPotionEffect(INNERSTRENGH_Regeneration);}
						
						event.setCancelled(true);

						Scheduler.schedule(INNERSTRENGH_DURATION * 20, () -> {
							if (super.valid(p)) {
								BattleMobs.getAPI().disguise(p, new AgeableDisguise(DisguiseType.ZOMBIE));
							}
						});

					}
				}
			}
		}
	}

}
