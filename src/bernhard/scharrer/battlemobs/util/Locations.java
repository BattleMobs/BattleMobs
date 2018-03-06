package bernhard.scharrer.battlemobs.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class Locations {
	
	public static World lobby_world;
	public static World map_world;
	
	public static Location lobby;
	public static Location waste_paper_basket;
	
	public static Location pig_statue;
	public static Location zombie_statue;
	public static Location skeleton_statue;
	public static Location sheep_statue;
	public static Location spider_statue;
	public static Location villager_statue;
	public static Location chicken_statue;
	public static Location witch_statue;
	public static Location slime_statue;
//	public static Location magma_slime_location;
//	public static Location cow_location;
	public static Location creeper_statue;
	
	public static void load() {
		
		lobby_world = Bukkit.getWorld("Lobby");
		map_world = Bukkit.getWorld("Map");
		
		lobby = new Location(lobby_world, 0.5f, 50.5f, 0.5f, 0, 0);
		waste_paper_basket = new Location(lobby_world, 7.5f, 40.5f, 0.5f, 91, -6);
		
		pig_statue = new Location(lobby_world, 7.5f, 51f, 28.5f, 135, 0);
		zombie_statue = new Location(lobby_world, 5.5f, 51f, 31.5f, 165, 0);
		skeleton_statue = new Location(lobby_world, 2.5f, 51f, 32.5f, 180, 0);
		sheep_statue = new Location(lobby_world, -1.5f, 51f, 32.5f, 180, 0);
		spider_statue = new Location(lobby_world, -4.5f, 51f, 31.5f, 195, 0);
		villager_statue = new Location(lobby_world, -6.5f, 51f, 28.5f, 225, 0);
		
		chicken_statue = new Location(lobby_world, -27.5f, 51f, 7.5f, 225, 0);
		witch_statue = new Location(lobby_world, -30.5f, 51f, 5.5f, 255, 0);
		slime_statue = new Location(lobby_world, -31.5f, 51f, 2.5f, 270, 0);
		

		creeper_statue = new Location(lobby_world, -27.5f, 51f, -6.5f, 315, 0);
	}
	
}
