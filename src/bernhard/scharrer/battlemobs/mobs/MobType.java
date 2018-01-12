package bernhard.scharrer.battlemobs.mobs;

public enum MobType {
	
	PIG (1),
	ZOMBIE (1),
	SKELETON (2),
	SHEEP (3),
	SPIDER (5),
	VILLAGER (5),
	CHICKEN (8),
	WITCH (10),
	SLIME (15),
	MAGMA_SLIME (15),
	COW (20),
	CREEPER (25),
	SILVERFISH (30),
	OCELOT (30),
	BAT (30),
	SQUID (35),
	RABBIT (40),
	ZOMBIE_PIGMAN (45),
	WOLF (50),
	GUARDIAN (50),
	HORSE (75),
	BLAZE (75),
	IRON_GOLEM (100),
	WITHER_SKELETON(100);
	
	private int level;
	
	private MobType(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
}
