package bernhard.scharrer.battlemobs.mobs.zombie;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import bernhard.scharrer.battlemobs.mobs.MobListener;

public class ZombieListener extends MobListener {
	
	private static final PotionEffect SWORD_SLOW_1 = new PotionEffect(PotionEffectType.SLOW, 20, 2);
	private static final PotionEffect SWORD_SLOW_2 = new PotionEffect(PotionEffectType.SLOW, 30, 2);

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
					}
					
				}
				
			}
			
		}
	}
	
}
