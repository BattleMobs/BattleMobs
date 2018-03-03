package bernhard.scharrer.battlemobs.mobs;

import org.bukkit.Location;

import bernhard.scharrer.battlemobs.util.Locations;

public enum MobType {
	
	PIG (1,Locations.pig_statue, 90, new Location(Locations.lobby_world, 4.5f, 50.5f, 25.5f, -45, 0)),
	ZOMBIE (1,Locations.zombie_statue, 54, new Location(Locations.lobby_world, 4.5f, 50.5f, 29.5f, -29, -18)),
	SKELETON (2,Locations.skeleton_statue, 51, new Location(Locations.lobby_world, 2.5f, 50.5f, 29.5f, -1, -10)),
	SHEEP (3,Locations.sheep_statue, 91, new Location(Locations.lobby_world, -1.5f, 50.5f, 29.5f, 0, -6)),
	SPIDER (5,Locations.spider_statue, 52, new Location(Locations.lobby_world, -3.5f, 50.5f, 28.5f, 19, 1)),
	VILLAGER (5,Locations.villager_statue, 120, new Location(Locations.lobby_world, -4.5f, 50.5f, 26.5f, 46, -7)),
	CHICKEN (8,Locations.chicken_statue, 93, new Location(Locations.lobby_world, -25.5f, 50.5f, 5.5f, 42, 4)),
	WITCH (10,Locations.witch_statue, 66, new Location(Locations.lobby_world, -28.5f, 50.5f, 4.5f, 62, -19)),
	SLIME (15,Locations.slime_statue, 55, new Location(Locations.lobby_world, -28.5f, 50.5f, 2.5f, 90, -2)),
	MAGMA_SLIME (15,Locations.pig_statue, 62,new Location(Locations.lobby_world, -4.5f, 50.5f, 26.5f, 46, -7)),
	COW (20,Locations.pig_statue, 92,new Location(Locations.lobby_world, -4.5f, 50.5f, 26.5f, 46, -7)),
	CREEPER (25,Locations.creeper_statue, 50,new Location(Locations.lobby_world, -25.5f, 50.5f, -4.5f, 136, -3)),
	SILVERFISH (30,Locations.pig_statue, 60,new Location(Locations.lobby_world, -4.5f, 50.5f, 26.5f, 46, -7)),
	OCELOT (30,Locations.pig_statue, 98,new Location(Locations.lobby_world, -4.5f, 50.5f, 26.5f, 46, -7)),
	BAT (30,Locations.pig_statue, 65,new Location(Locations.lobby_world, -4.5f, 50.5f, 26.5f, 46, -7)),
	SQUID (35,Locations.pig_statue, 94,new Location(Locations.lobby_world, -4.5f, 50.5f, 26.5f, 46, -7)),
	RABBIT (40,Locations.pig_statue,101,new Location(Locations.lobby_world, -4.5f, 50.5f, 26.5f, 46, -7)),
	ZOMBIE_PIGMAN (45,Locations.pig_statue, 57,new Location(Locations.lobby_world, -4.5f, 50.5f, 26.5f, 46, -7)),
	WOLF (50,Locations.pig_statue, 95,new Location(Locations.lobby_world, -4.5f, 50.5f, 26.5f, 46, -7)),
	GUARDIAN (50,Locations.pig_statue, 68,new Location(Locations.lobby_world, -4.5f, 50.5f, 26.5f, 46, -7)),
	HORSE (75,Locations.pig_statue,100,new Location(Locations.lobby_world, -4.5f, 50.5f, 26.5f, 46, -7)),
	BLAZE (75,Locations.pig_statue, 61,new Location(Locations.lobby_world, -4.5f, 50.5f, 26.5f, 46, -7)),
	IRON_GOLEM (100,Locations.pig_statue, 91,new Location(Locations.lobby_world, -4.5f, 50.5f, 26.5f, 46, -7)),
	WITHER_SKELETON(100,Locations.pig_statue, 58, new Location(Locations.lobby_world, -4.5f, 50.5f, 26.5f, 46, -7));
	
	private int level;
	private Location loc;
	private int egg;
	private Location teleporter;
	
	private MobType(int level, Location loc, int egg, Location teleporter) {
		this.level = level;
		this.loc = loc;
		this.egg = egg;
		this.teleporter = teleporter;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getEgg() {
		return egg;
	}
	
	public String getDisplayName() {
		return toString().replace("_", " ");
	}

	public Location getLocation() {
		return loc;
	}
	
	public Location getTeleporterLocation() {
		return teleporter;
	}
	
}
