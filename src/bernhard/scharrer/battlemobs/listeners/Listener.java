package bernhard.scharrer.battlemobs.listeners;

import bernhard.scharrer.battlemobs.BattleMobs;

public class Listener implements org.bukkit.event.Listener {
	
	public Listener() {
		BattleMobs.getInstance().getServer().getPluginManager().registerEvents(this, BattleMobs.getInstance());
	}
	
}
