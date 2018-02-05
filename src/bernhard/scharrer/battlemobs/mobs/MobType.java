package bernhard.scharrer.battlemobs.mobs;

import org.bukkit.Location;

import bernhard.scharrer.battlemobs.util.Locations;

public enum MobType {
	
	PIG (1,Locations.pig_statue, 90),
	ZOMBIE (1,Locations.zombie_statue, 54),
	SKELETON (2,Locations.skeleton_statue, 51),
	SHEEP (3,Locations.sheep_statue, 91),
	SPIDER (5,Locations.spider_statue, 52),
	VILLAGER (5,Locations.pig_statue, 120),
	CHICKEN (8,Locations.pig_statue, 93),
	WITCH (10,Locations.pig_statue, 66),
	SLIME (15,Locations.pig_statue, 55),
	MAGMA_SLIME (15,Locations.pig_statue, 62),
	COW (20,Locations.pig_statue, 92),
	CREEPER (25,Locations.pig_statue, 50),
	SILVERFISH (30,Locations.pig_statue, 60),
	OCELOT (30,Locations.pig_statue, 98),
	BAT (30,Locations.pig_statue, 65),
	SQUID (35,Locations.pig_statue, 94),
	RABBIT (40,Locations.pig_statue,101),
	ZOMBIE_PIGMAN (45,Locations.pig_statue, 57),
	WOLF (50,Locations.pig_statue, 95),
	GUARDIAN (50,Locations.pig_statue, 68),
	HORSE (75,Locations.pig_statue,100),
	BLAZE (75,Locations.pig_statue, 61),
	IRON_GOLEM (100,Locations.pig_statue, 91),
	WITHER_SKELETON(100,Locations.pig_statue, 58);
	
	private int level;
	private Location loc;
	private int egg;
	
	private MobType(int level, Location loc, int egg) {
		this.level = level;
		this.loc = loc;
		this.egg = egg;
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
	
}
