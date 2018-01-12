package bernhard.scharrer.battlemobs.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChangeListener extends Listener {

	@EventHandler
	public void onWeatherToggle(WeatherChangeEvent e) {
		e.setCancelled(true);
	}

}
