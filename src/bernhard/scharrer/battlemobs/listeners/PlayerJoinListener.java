package bernhard.scharrer.battlemobs.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import bernhard.scharrer.battlemobs.mobs.MobMaster;
import bernhard.scharrer.battlemobs.util.Item;
import bernhard.scharrer.battlemobs.util.Locations;
import bernhard.scharrer.battlemobs.util.PlayerUtils;
import bernhard.scharrer.battlemobs.util.Scoreboard;

public class PlayerJoinListener extends Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		e.setJoinMessage("§8[§a+§8]" + (p.isOp() ? "§c" : "§7") + " " + p.getName());
		
		PlayerUtils.insertIntoDB(p);
		MobMaster.loadPlayer(p);
		PlayerUtils.reset(p);
		p.getInventory().setItem(1, Item.createItem("§b§lINFO", "", Material.NETHER_STAR, 1, 0));
		p.getInventory().setItem(4, Item.createItem("§b§lTELEPORTER", "", Material.COMPASS, 1, 0));
		p.getInventory().setItem(7, Item.createItem("§b§lComing soon!", "", Material.NETHER_STAR, 1, 0));
		
		p.sendTitle("§6BattleMobs", "§7Willkommen " + p.getName(), 10, 40, 10);
		p.playSound(Locations.lobby, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
		
//		if (data == null) Database.createInsert(p);
//		data = Database.getData(p);
		Scoreboard.updateScoreboard(p);

		
		
		p.teleport(Locations.lobby);
		
	}
	
}
