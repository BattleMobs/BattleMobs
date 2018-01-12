package bernhard.scharrer.battlemobs.mobs;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import bernhard.scharrer.battlemobs.BattleMobs;
import bernhard.scharrer.battlemobs.data.MobData;
import bernhard.scharrer.battlemobs.data.PlayerData;
import bernhard.scharrer.battlemobs.listeners.Listener;
import bernhard.scharrer.battlemobs.util.Locations;
import bernhard.scharrer.battlemobs.util.MapHandler;
import bernhard.scharrer.battlemobs.util.PlayerUtils;
import bernhard.scharrer.battlemobs.util.Scoreboard;

public class MobInventoryHandler extends Listener {
	
	private static final String HEADER = "§8§l[§3§l";
	private static final int PRIZE = 100;
	private static final int SKIP_PRIZE = 300;
	
	private static final String LEVEL_TOO_LOW = "§4§lNot available yet.";
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		Player p = (Player) event.getWhoClicked();
		ItemStack item = event.getCurrentItem();
		
		if (p.getWorld().getName().equals(Locations.lobby_world.getName())) {
			
			if (event.getInventory().getName().length()>HEADER.length()) {
				String part = event.getInventory().getName().substring(HEADER.length(), event.getInventory().getName().length()).split("§")[0];
				try {
					
					BattleMob mob = MobMaster.getBattleMob(MobType.valueOf(part));
					PlayerData data = MobMaster.getPlayerData(p);
					
					if (item!=null) {
						
						/*
						 * buy item normal
						 */
						if (item.getType()==Material.EMERALD) {
							buy(data, data.getMobData(mob.getType()), PRIZE);
						}
						/*
						 * force buy item
						 */
						else if (item.getType()==Material.INK_SACK&&item.getItemMeta().getDisplayName().equals(LEVEL_TOO_LOW)) {
							buy(data, data.getMobData(mob.getType()), SKIP_PRIZE);
						}
						/*
						 * play item
						 */
						else if (item.getType()==Material.NETHER_STAR && item.getItemMeta().getDisplayName().startsWith(HEADER)) {
							
							PlayerUtils.reset(p);
							MapHandler.teleportIntoMap(p);
							data.setCurrent(mob.getType());
							
							int tier = data.getCurrentData().getTier();
							
							for (int n=0;n<3&&n<tier;n++) {
								p.getInventory().setItem(n, mob.getItems().getAbilityItem(n, (tier-(n+1))/3));
							}
							BattleMobs.getAPI().disguise(p, mob.generateDisguise());
							
						}
						
					}
					
				} catch (IllegalArgumentException e) {
					return;
				}
			}
			
		}
		
	}

	private void buy(PlayerData data, MobData mob, int prize) {
		
		Player p = data.getPlayer();
		
		if (data.getMoney()>=prize) {
			
			p.closeInventory();
			data.modifyMoney(-prize);
			mob.incrementTier();
			p.sendTitle("§2§lSuccesfully bought tier!", "§aMoney: "+data.getMoney(), 10, 20, 10);
			p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
			
			Scoreboard.updateScoreboard(p);
			
		} else {
			
			p.closeInventory();
			p.sendTitle("§4§lCan't afford tier!", "§cMoney: "+data.getMoney(), 10, 20, 10);
			p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_DESTROY, 1, 1);
			
		}
	}
	
}
