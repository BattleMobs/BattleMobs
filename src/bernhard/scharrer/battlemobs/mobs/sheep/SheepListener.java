package bernhard.scharrer.battlemobs.mobs.sheep;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import bernhard.scharrer.battlemobs.mobs.MobListener;
import bernhard.scharrer.battlemobs.util.Item;
import bernhard.scharrer.battlemobs.util.Task;
import bernhard.scharrer.battlemobs.util.Tier;

public class SheepListener extends MobListener {
	
	private static final int WOOL_COUNT_SLOT = 3;
	private static final int WOOL_COUNT_PER_HIT = 1;
	private static final int WOOL_COUNT_PER_KILL = 5;
	private static final int WOOL_COUNT_MAX_BASE = 20;
	private static final double WOOL_COUNT_DAMAGE_MODIFIER = 0.25;
	private static final ItemStack WOOL_COUNTER = Item.createIngameItem("WOOL COUNTER", Material.WOOL, 0);
	private static final Material GRAZE_BLOCK_TYPE = Material.GRASS;
	private static final float GRAZE_TIMEOUT = 3;
	
	private static final List<Material> GRAZE_BANNED_BLOCKS = new ArrayList<>();
	
	private static final double FEEDING_TIME_HEAL = 2;
	
	{
		GRAZE_BANNED_BLOCKS.add(Material.BARRIER);
		GRAZE_BANNED_BLOCKS.add(Material.DEAD_BUSH);
		GRAZE_BANNED_BLOCKS.add(Material.YELLOW_FLOWER);
		GRAZE_BANNED_BLOCKS.add(Material.SAPLING);
		GRAZE_BANNED_BLOCKS.add(Material.RED_MUSHROOM);
		GRAZE_BANNED_BLOCKS.add(Material.BROWN_MUSHROOM);
		GRAZE_BANNED_BLOCKS.add(Material.RED_ROSE);
		GRAZE_BANNED_BLOCKS.add(Material.WATER_LILY);
		GRAZE_BANNED_BLOCKS.add(Material.BED_BLOCK);
		GRAZE_BANNED_BLOCKS.add(Material.CHEST);
		GRAZE_BANNED_BLOCKS.add(Material.TRAPPED_CHEST);
		GRAZE_BANNED_BLOCKS.add(Material.ENDER_CHEST);
		GRAZE_BANNED_BLOCKS.add(Material.LONG_GRASS);
		GRAZE_BANNED_BLOCKS.add(Material.WEB);
		GRAZE_BANNED_BLOCKS.add(Material.GRASS);
		GRAZE_BANNED_BLOCKS.add(Material.LEVER);
		GRAZE_BANNED_BLOCKS.add(Material.WOOD_BUTTON);
		GRAZE_BANNED_BLOCKS.add(Material.STONE_BUTTON);
		GRAZE_BANNED_BLOCKS.add(Material.GRASS);
		GRAZE_BANNED_BLOCKS.add(Material.WALL_BANNER);
		GRAZE_BANNED_BLOCKS.add(Material.WALL_SIGN);
		GRAZE_BANNED_BLOCKS.add(Material.SIGN_POST);
	}
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		
		if (e.getDamager() instanceof Player) {
			Player sheep = (Player) e.getDamager();
			if (super.valid(sheep)) {
				if (sheep.getInventory().getItemInMainHand()!=null) {
					
					ItemStack item = sheep.getInventory().getItemInMainHand();
					if (item.getItemMeta()!=null&&item.getItemMeta().getDisplayName()!=null) {
						if (item.getItemMeta().getDisplayName().contains(SheepItems.ABILITY_1_NAME)) {
							
							e.setCancelled(true);
							
							int tier = super.getMobTier(sheep);
							int count = 1;
							int max = getMaxWool(tier);
							
							if (sheep.getInventory().getItem(WOOL_COUNT_SLOT)!=null) {
								count = sheep.getInventory().getItem(WOOL_COUNT_SLOT).getAmount();
								count = count + WOOL_COUNT_PER_HIT > max ? max : count + WOOL_COUNT_PER_HIT;
								sheep.getInventory().getItem(WOOL_COUNT_SLOT).setAmount(count);
							} else {
								sheep.getInventory().setItem(WOOL_COUNT_SLOT, WOOL_COUNTER);
							}
							
							sheep.playSound(sheep.getLocation(), Sound.ENTITY_SHEEP_SHEAR, 1, 1);
							
							sheep.updateInventory();
							
							if (e.getEntity() instanceof LivingEntity) {
								LivingEntity enemy = (LivingEntity) e.getEntity();
								enemy.damage(WOOL_COUNT_DAMAGE_MODIFIER*count);
							}
							
						}
					}
				}
			}
		}
		
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		
		if (super.valid(e.getPlayer()) && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player sheep = e.getPlayer();
			if (sheep.getInventory().getItemInMainHand()!=null) {
				ItemStack item = sheep.getInventory().getItemInMainHand();
				if (item.getItemMeta()!=null&&item.getItemMeta().getDisplayName()!=null) {
					if (item.getItemMeta().getDisplayName().contains(SheepItems.ABILITY_2_NAME)) {
						
						Block block = e.getClickedBlock();
						
						if (GRAZE_BANNED_BLOCKS.contains(block.getType())) return;
						
						Material type = block.getType();
						byte data = block.getData();
						sheep.getWorld().getBlockAt(block.getLocation()).setType(GRAZE_BLOCK_TYPE);
						
						new Task(GRAZE_TIMEOUT) {
							@Override
							public void run() {
								sheep.getWorld().getBlockAt(block.getLocation()).setType(type);
								sheep.getWorld().getBlockAt(block.getLocation()).setData(data);
							}
						};
						
					}
				}
			}
		}
		
	}
	
	@EventHandler
	public void onLeftclick(PlayerInteractEvent e) {
		if (super.valid(e.getPlayer()) && e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player sheep = e.getPlayer();
			if (sheep.getInventory().getItemInMainHand()!=null) {
				ItemStack item = sheep.getInventory().getItemInMainHand();
				if (item.getItemMeta()!=null&&item.getItemMeta().getDisplayName()!=null) {
					if (item.getItemMeta().getDisplayName().contains(SheepItems.ABILITY_3_NAME)) {
						if (sheep.getHealth()+FEEDING_TIME_HEAL>=sheep.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue()) {
							sheep.setHealth(sheep.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
						} else {
							sheep.setHealth(sheep.getHealth()+FEEDING_TIME_HEAL);
						}
					}
				}
			}
		}
	}
	
	private int getMaxWool(int tier) {
		
		if (tier >= Tier.TIER_1_3) return WOOL_COUNT_MAX_BASE + 10;
		else if (tier >= Tier.TIER_1_2) return WOOL_COUNT_MAX_BASE + 5;
		else return WOOL_COUNT_MAX_BASE;
		
	}
	
}
