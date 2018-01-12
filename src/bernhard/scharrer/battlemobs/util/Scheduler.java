package bernhard.scharrer.battlemobs.util;

import org.bukkit.Bukkit;
import bernhard.scharrer.battlemobs.BattleMobs;

public class Scheduler {
	
	public static int schedule(int ticks, Runnable action) {
		return Bukkit.getScheduler().scheduleSyncDelayedTask(BattleMobs.getInstance(), action, ticks);
	}
	
	public static int schedule(int start, int ticks, Runnable action) {
		return Bukkit.getScheduler().scheduleSyncRepeatingTask(BattleMobs.getInstance(), action, start, ticks);
	}
	
	public static void cancel(int scheduler) {
		Bukkit.getScheduler().cancelTask(scheduler);
	}
	
}
