package bernhard.scharrer.battlemobs.util;

public class Leveling {
	
	public static int getTotalEXPNeeded(int level) {
		int total = -20;
		for (int n = 1;n<=level;n++) {
			total+=getEXPNeeded(n);
		}
		return total;
	}
	
	public static int getEXPNeeded(int level) {
		return (int)((level*level*3)/100.0f +20);
	}
	
	public static int getLevel(int exp) {
		int level;
		for (level=1;level<100;level++) {
			exp-=getEXPNeeded(level);
			if (exp<0) break;
		}
		return level;
	}
	
}
