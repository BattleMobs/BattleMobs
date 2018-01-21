package bernhard.scharrer.battlemobs.mobs.zombie;

import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import bernhard.scharrer.battlemobs.mobs.MobListener;
import bernhard.scharrer.battlemobs.util.Cooldown;
import bernhard.scharrer.battlemobs.util.Scheduler;
import bernhard.scharrer.battlemobs.util.Tier;

public class ZombieListener extends MobListener {
	
	private static final PotionEffect SWORD_SLOW_1 = new PotionEffect(PotionEffectType.SLOW, 20, 2);
	private static final PotionEffect SWORD_SLOW_2 = new PotionEffect(PotionEffectType.SLOW, 30, 2);

	private static final float BLOODRAGE_SPEED = 1f;
	private static final int BLOODRAGE_COOLDOWN_TIER1 = 30;
	private static final int BLOODRAGE_COOLDOWN_TIER2 = 20;
	private static final int BLOODRAGE_DURATION = 5;
	
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		if (super.valid(event.getEntity()) && super.valid(event.getDamager())) {
			
			if (event.getDamager() instanceof Player) {
				
				Player p = (Player) event.getDamager();
				int tier = super.getMobTier(p);
				ItemStack hand = p.getInventory().getItemInMainHand();
				
				if (tier != -1 && hand!=null && hand.getItemMeta()!=null&&hand.getItemMeta().getDisplayName().contains(ZombieItems.ABILITY_1_NAME)) {
					
					if (event.getEntity() instanceof LivingEntity) {
						LivingEntity lentity = (LivingEntity) event.getEntity();
						lentity.addPotionEffect(tier>=4?SWORD_SLOW_2:SWORD_SLOW_1);
						p.sendMessage("Zombie schlägt Spieler! " + p.getName());
					}
					
				}
				
			}
			
		}
		
	}
	
	@EventHandler
	public void bloodRage(PlayerInteractEvent event) {
		
		if(super.valid(event.getPlayer())) {
			
			Player p = event.getPlayer();
			int tier = super.getMobTier(p);
			ItemStack hand = p.getInventory().getItemInMainHand();

			if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				
				if (tier != -1 && hand!=null && hand.getItemMeta()!=null) {
					
					if (hand.getItemMeta().getDisplayName().contains(ZombieItems.ABILITY_2_NAME)) {
						
						new Cooldown(p, 1, tier >= Tier.TIER_2_2? BLOODRAGE_COOLDOWN_TIER1:BLOODRAGE_COOLDOWN_TIER2);
						
						p.setWalkSpeed(BLOODRAGE_SPEED);
						p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 1);
						
						Scheduler.schedule(5*20, ()-> {
							
							if (super.valid(p)) {
								p.setWalkSpeed(BattleZombie.ZOMBIE_WALSPEED);
								p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1, 1);
							}
							
						});

						
						
					}
					
				}
				
			}
			
		}
		
		
	}
	
	
}
