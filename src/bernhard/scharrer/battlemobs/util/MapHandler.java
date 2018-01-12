package bernhard.scharrer.battlemobs.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MapHandler {
	
	private static List<Location> locations = new ArrayList<>();
	private static final Random random = new Random();
	
	public static void loadMap() {
		
		locations.add(new Location(Locations.map_world, 264.5f, 5.5f, -189.5f, 180, 0));
		locations.add(new Location(Locations.map_world, 264.5f, 5.5f, -253.5f, 0, 0));
		
	}
	
	public static void teleportIntoMap(Player p) {
		Location loc = locations.get(random.nextInt(locations.size()));
		p.teleport(loc);
	}
	
}
