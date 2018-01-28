package bernhard.scharrer.battlemobs.util;

import org.bukkit.Bukkit;

import bernhard.scharrer.battlemobs.BattleMobs;

public abstract class Task implements Runnable {
	
	private int task;
	
	public Task(float start, float period) {
		this.task = Bukkit.getScheduler().scheduleSyncRepeatingTask(BattleMobs.getInstance(), this, (long) (20.0f*start), (long) (20.0f*period));
	}
	
	public Task(float seconds) {
		this.task = Bukkit.getScheduler().scheduleSyncDelayedTask(BattleMobs.getInstance(), this, (long) (20.0f*seconds));
	}
	
	protected void cancel() {
		Scheduler.cancel(task);
	}
	
}
