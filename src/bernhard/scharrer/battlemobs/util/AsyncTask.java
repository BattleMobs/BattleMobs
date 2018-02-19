package bernhard.scharrer.battlemobs.util;

import java.util.Timer;
import java.util.TimerTask;

public abstract class AsyncTask extends TimerTask {
	
	private Timer timer;
	
	public AsyncTask(float start, float period) {
		timer = new Timer();
		timer.schedule(this, (long) (start * 1000), (long) (period * 1000));
	}
	
	public AsyncTask(float seconds) {
		timer = new Timer();
		timer.schedule(this, (long) (seconds * 1000));
	}
	
	@Override
	public boolean cancel() {
		timer.cancel();
		return true;
	}
	
}
