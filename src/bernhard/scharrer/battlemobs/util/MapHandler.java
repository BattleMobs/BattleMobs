package bernhard.scharrer.battlemobs.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import bernhard.scharrer.battlemobs.BattleMobs;
import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobItems;

public class MapHandler {
	
	private static List<Location> locations = new ArrayList<>();
	private static final Random random = new Random();
	
	public static void loadMap() {
		
		locations.add(new Location(Locations.map_world, 264.5f, 5.5f, -189.5f, 180, 0));
		locations.add(new Location(Locations.map_world, 264.5f, 5.5f, -253.5f, 0, 0));
		
	}
	
	public static void teleportIntoMap(Player p, int tier, BattleMob mob) {
		Location loc = locations.get(random.nextInt(locations.size()));
		p.teleport(loc);
		
		p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
		
		for (int n=0;n<3&&n<tier;n++) {
			p.getInventory().setItem(n, mob.getItems().getAbilityItem(n, (tier-(n+1))/3));
		}
		p.getInventory().setItem(7, MobItems.AUTO_RESPAWN_ON);
		p.getInventory().setItem(8, MobItems.LOBBY);
		mob.init(p);
		BattleMobs.getAPI().disguise(p, mob.generateDisguise());
		
	}
	
}
