package bernhard.scharrer.battlemobs;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import bernhard.scharrer.battlemobs.data.Database;
import bernhard.scharrer.battlemobs.listeners.ListenerMaster;
import bernhard.scharrer.battlemobs.mobs.MobMaster;
import bernhard.scharrer.battlemobs.properties.PluginProperties;
import bernhard.scharrer.battlemobs.util.Locations;
import bernhard.scharrer.battlemobs.util.MapHandler;
import de.robingrether.idisguise.api.DisguiseAPI;

public class BattleMobs extends JavaPlugin {
	
	private static BattleMobs instance;
	private static DisguiseAPI api;
	
	@Override
	public void onEnable() {

		instance = this;
		api = getServer().getServicesManager().getRegistration(DisguiseAPI.class).getProvider();
		
		PluginProperties.load();
		Database.loadAuthFile();
		Database.connect();
		Database.createDefaultTables();
		Locations.load();
		MapHandler.loadMap();
		ListenerMaster.register();
		MobMaster.register();
		
		for (Player p : Bukkit.getOnlinePlayers()) p.kickPlayer("§8[§6BattleMobs§8] §7Server is restarting!");
		
	} 
	@Override
	public void onDisable() {
		MobMaster.cleanUp();
	}

	public static BattleMobs getInstance() {
		return instance;
	}
	
	public static DisguiseAPI getAPI() {
		return api;
	}
	
}
