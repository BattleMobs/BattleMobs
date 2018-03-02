package bernhard.scharrer.battlemobs.mobs;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import bernhard.scharrer.battlemobs.data.MobData;
import bernhard.scharrer.battlemobs.util.Item;

public class MobInventory {
	
	private static final String HEADER = "§8§l[§3§l";
	private static final String TRAILER= "/9§8§l]";
	private static final String LEVEL_TOO_LOW = "§4§lNot available yet.";
	
	public MobInventory(MobData data, MobType type) {

		Player p = data.getPlayer();
		
		BattleMob mob = MobMaster.getBattleMob(type);
		
		if (mob != null) {
			String title = HEADER+type.toString()+"§8 Level: §3"+data.getLevel()+" §8Tier: §3"+data.getTier()+TRAILER;
			String play_title = HEADER+type.toString()+"§7 Level§8: §b"+data.getLevel()+" §7Tier§8: §b"+data.getTier()+TRAILER;
			String details = "§7EXP§8: §b"+data.getEXP()+"#§7Kills§8: §b"+data.getKills()+"#§7Deaths§8: §b"+data.getDeaths()+"#§7KDR§8: §b"+String.format("%.3f", ((float)data.getKills()/(data.getDeaths()!=0?data.getDeaths():1)));
			
			Inventory inv = Bukkit.createInventory(null, 54, title);
			
			inv.setItem(4, Item.createItem(play_title, "#"+details+"##§3§lCLICK TO PLAY!", Material.NETHER_STAR, 1, 0));
			
			int tier = 1;
			
			for (tier = 1;tier<= data.getTier();tier++) {
				inv.setItem(16+(tier*3), mob.getItems().getMobInventoryItem(tier));
			}
			
			if (tier<=9) {
				if (data.getLevel()>=(tier*10)-10) {
					inv.setItem(16+(tier*3), MobItems.BUYABLE);
				} else {
					String desc = "§cReach §4"+mob.getType().toString()+" §clevel "+((tier*10)-10)+" first#§cor buy it now for 300$";
					inv.setItem(16+(tier*3), Item.createItem(LEVEL_TOO_LOW,desc, Material.INK_SACK, 1, 8));
				}
			}
			
			tier++;
			
			while (tier<=9) {
				inv.setItem(16+(tier*3), MobItems.LOCKED);
				tier++;
			}
			
			p.closeInventory();
			p.openInventory(inv);
			p.updateInventory();
		}
		
	}
	
}
