package bernhard.scharrer.battlemobs.mobs.chicken;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import bernhard.scharrer.battlemobs.mobs.MobListener;
import bernhard.scharrer.battlemobs.mobs.zombie.ZombieItems;
import bernhard.scharrer.battlemobs.util.Item;

public class ChickenListener extends MobListener {
	
	private ItemStack SHARPWINGS_ITEM = Item.createIngameItem("", Material.FEATHER, 0);
;
	private int SHARPWINGS_SPEED = 5;

	
	
	@EventHandler
	public void sharpFeather(PlayerInteractEvent event) {
		
		if(super.valid(event.getPlayer())) {
			
			Player p = event.getPlayer();
			int tier = super.getMobTier(p);
			ItemStack hand = p.getInventory().getItemInMainHand();

			
			if (tier != -1 && hand != null && hand.getItemMeta() != null
					&& hand.getItemMeta().getDisplayName().contains(ChickenItems.ABILITY_1_NAME)) {
				
				if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
					
					org.bukkit.entity.Item item = p.getWorld().dropItem(p.getEyeLocation(), SHARPWINGS_ITEM);
					item.setVelocity(p.getEyeLocation().getDirection().normalize().multiply(SHARPWINGS_SPEED));
					item.setCustomNameVisible(false);

					
				}
				
			}
			
		}
		
	}

}
