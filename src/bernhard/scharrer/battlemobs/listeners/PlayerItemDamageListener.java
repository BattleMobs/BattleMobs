package bernhard.scharrer.battlemobs.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerItemDamageListener extends Listener {
	
	private ItemStack item, temp;
	private ItemMeta meta;
	
	@EventHandler
	public void onItemDamage(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (p.getInventory().getItemInMainHand()!=null) {
			item = p.getInventory().getItemInMainHand();
			meta = item.getItemMeta();
			temp = new ItemStack(item.getType(), item.getAmount(), (short) 0);
			temp.setItemMeta(meta);
			p.getInventory().setItemInMainHand(temp);
			p.updateInventory();
		}
	}
	
}
