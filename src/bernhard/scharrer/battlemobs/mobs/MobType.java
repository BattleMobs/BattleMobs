package bernhard.scharrer.battlemobs.mobs;

import org.bukkit.Location;
import bernhard.scharrer.battlemobs.util.Locations;

public enum MobType {
	
	PIG (1,Locations.pig_statue),
	ZOMBIE (1,Locations.zombie_statue),
	SKELETON (2,Locations.skeleton_statue),
	SHEEP (3,Locations.sheep_statue),
	SPIDER (5,Locations.spider_statue),
	VILLAGER (5,Locations.pig_statue),
	CHICKEN (8,Locations.pig_statue),
	WITCH (10,Locations.pig_statue),
	SLIME (15,Locations.pig_statue),
	MAGMA_SLIME (15,Locations.pig_statue),
	COW (20,Locations.pig_statue),
	CREEPER (25,Locations.pig_statue),
	SILVERFISH (30,Locations.pig_statue),
	OCELOT (30,Locations.pig_statue),
	BAT (30,Locations.pig_statue),
	SQUID (35,Locations.pig_statue),
	RABBIT (40,Locations.pig_statue),
	ZOMBIE_PIGMAN (45,Locations.pig_statue),
	WOLF (50,Locations.pig_statue),
	GUARDIAN (50,Locations.pig_statue),
	HORSE (75,Locations.pig_statue),
	BLAZE (75,Locations.pig_statue),
	IRON_GOLEM (100,Locations.pig_statue),
	WITHER_SKELETON(100,Locations.pig_statue);
	
	private int level;
	private Location loc;
	
	private MobType(int level, Location loc) {
		this.level = level;
		this.loc = loc;
	}
	
	public int getLevel() {
		return level;
	}

	public Location getLocation() {
		return loc;
	}
	
}
