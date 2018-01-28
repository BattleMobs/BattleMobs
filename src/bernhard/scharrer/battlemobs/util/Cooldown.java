package bernhard.scharrer.battlemobs.util;

import java.util.List;
import java.util.Vector;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Cooldown {
	
	private static final Material MATERIAL_COOLDOWN = Material.FIREWORK_CHARGE;
	
	private ItemStack item;
	private int task;
	private int cooldown;
	private Cooldown instance;
	
	private static List<Cooldown> cooldowns = new Vector<>();
	
	public Cooldown(Player p, int slot, int cooldown) {
		
		p.playSound(p.getLocation(), Sound.BLOCK_STONE_BUTTON_CLICK_ON, 10, 10);
		
		this.cooldown = cooldown;
		this.item = p.getInventory().getItem(slot);
		this.instance = this;
		
		p.getInventory().setItem(slot, Item.createItem("§3", "", MATERIAL_COOLDOWN, this.cooldown, 0));
		p.updateInventory();
		
		task = Scheduler.schedule(20, 20, ()->{
			
			this.cooldown--;
			
			if (cooldown > 0 && isPlayerValid(p)) {
				
				if (p.getInventory().getItem(slot)!=null && p.getInventory().getItem(slot).getType()==MATERIAL_COOLDOWN) {
					
					p.getInventory().getItem(slot).setAmount(this.cooldown);
					p.updateInventory();
					
				}
			} else {
				cooldowns.remove(instance);
				Scheduler.cancel(task);
			}
			
		});
		
		Scheduler.schedule(cooldown*20, ()->{
			if (isPlayerValid(p)) {
				p.getInventory().setItem(slot, item);
				p.updateInventory();
				p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
			}
			cooldowns.remove(instance);
			Scheduler.cancel(task);
		});
		
		cooldowns.add(this);
		
	}
	
	private boolean isPlayerValid(Player p) {
		return p.getWorld().getName().equals(Locations.map_world.getName()) && p.getGameMode() == GameMode.SURVIVAL;
	}
	
}
