package bernhard.scharrer.battlemobs.mobs.spider;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
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
import bernhard.scharrer.battlemobs.util.Item;
import bernhard.scharrer.battlemobs.util.Task;
import bernhard.scharrer.battlemobs.util.Tier;

public class SpiderListener extends MobListener {
	
	private static final float EYE_OF_SPIDER_SPEED = 2.5f;
	private static final PotionEffect POISON_3S = new PotionEffect(PotionEffectType.POISON, 60, 0);
	private static final double EYE_OF_SPIDER_DAMAGE = 6;
	private static final double EYE_OF_SPIDER_ARROW_DAMAGE = 4;
	private static final double EYE_OF_SPIDER_ARROW_AMOUNT = 3;
	private static final String ARROW_TAG_HEADER = "񗉷;";
	private static final int WEB_BOMB_EXPLODE_TIME = 4;
	private static final float WEB_BOMB_SPEED = 1.2f;
	private static final ItemStack WEB_BOMB_ITEM = Item.createIngameItem("", Material.STRING, 0);
	private static final double EYE_OF_SPIDER_KNOCKBACK = 1.2;

	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			Player p = (Player) event.getDamager();
			int tier = super.getMobTier(p);
			if (super.valid(p) && tier != Tier.UNDEFINED) {
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
							lentity.damage(EYE_OF_SPIDER_DAMAGE);
							if (tier >= Tier.TIER_1_3) {
								lentity.setVelocity(lentity.getEyeLocation().toVector().subtract(p.getLocation().toVector()).normalize().multiply(EYE_OF_SPIDER_KNOCKBACK));
							}
						}
					}
					
					event.setCancelled(true);
					
				}
				
			}
		}
		
		if (event.getDamager() instanceof Arrow) {
			if (event.getEntity() instanceof LivingEntity) {
				
				LivingEntity p = (LivingEntity) event.getEntity();
				event.setCancelled(true);
				
				/*
				 * eye of the spider ranged
				 */
				if (event.getDamager().getCustomName()!=null && event.getDamager().getCustomName().startsWith(ARROW_TAG_HEADER)) {
					p.addPotionEffect(POISON_3S);
					p.damage(EYE_OF_SPIDER_ARROW_DAMAGE);
				}
			}
		}
		
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		int tier = super.getMobTier(p);
		if (super.valid(p) && tier != Tier.UNDEFINED) {
			
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
						
						new Cooldown(p, 0, 10);
					}
					
					/*
					 * web bomb
					 */
					if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(SpiderItems.ABILITY_2_NAME)) {
						
						new WebBomb(p);
						
					}
					
				}
			}
			
		}
	}

	private void shotArrow(Player p) {
		p.playSound(p.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1, 1);
		Arrow arrow = p.getWorld().spawn(p.getEyeLocation().add(0,0.1,0), Arrow.class);
		arrow.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(EYE_OF_SPIDER_SPEED));
		arrow.setCustomNameVisible(false);
		arrow.setCustomName(ARROW_TAG_HEADER);
		arrow.setShooter(p);
	}
	
	private class WebBomb {
		
		public WebBomb(Player p) {
			org.bukkit.entity.Item item = p.getWorld().dropItem(p.getEyeLocation(), WEB_BOMB_ITEM);
			item.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(WEB_BOMB_SPEED));
			item.setCustomNameVisible(false);
			
			new Task(0,0.2f) {
				private int time = 0;
				@SuppressWarnings("deprecation")
				@Override
				public void run() {
					if (time >= WEB_BOMB_EXPLODE_TIME) {
						item.remove();
						p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
						p.getWorld().playEffect(item.getLocation(), Effect.EXPLOSION_HUGE, 1);
						cancel();
					} else {
						// TODO
					}
					
					time++;
				}
			};
		}
		
	}
	
}
