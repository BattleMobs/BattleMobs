package bernhard.scharrer.battlemobs.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import bernhard.scharrer.battlemobs.data.PlayerData;
import bernhard.scharrer.battlemobs.mobs.MobMaster;

public class AsyncPlayerChatListener extends Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		e.setCancelled(true);
		Player p = e.getPlayer();
		Location loc = p.getLocation();
		if (p.isOp()) e.setMessage(e.getMessage().replace('&', '§'));
		if (e.getMessage().equalsIgnoreCase("loc")) Bukkit.getConsoleSender().sendMessage("  new Location(Locations.lobby_world, "+(float)loc.getX()+"f, "+(int)loc.getY()+"f, "+(float)loc.getZ()+"f, "+(int)loc.getYaw()+", "+(int)loc.getPitch()+");");
		if (e.getMessage().equalsIgnoreCase("rloc")) Bukkit.getConsoleSender().sendMessage("  new Location(Locations.lobby_world, "+(int)loc.getX()+".5f, "+(int)loc.getY()+".5f, "+(int)loc.getZ()+".5f, "+(int)loc.getYaw()+", "+(int)loc.getPitch()+");");
		else {
			
			String color = (p.isOp() ? "§c" : "§a");
			PlayerData data = MobMaster.getPlayerData(p);
			
			Bukkit.broadcastMessage("§8[§7"+data.getLevel()+"§8][" +color+ "" + p.getName() + "§8] §7" + e.getMessage());
		}
	}
	
}
