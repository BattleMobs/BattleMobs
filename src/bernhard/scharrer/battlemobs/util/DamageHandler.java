package bernhard.scharrer.battlemobs.util;

import java.util.List;
import java.util.Vector;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class DamageHandler {
	
	private static List<PlayerDamageHandler> handlers = new Vector<>();
	private static List<PlayerDeathListener> listeners = new Vector<>();
	
	public static void bind(Player p) {
		handlers.add(new PlayerDamageHandler(p));
	}
	
	public static void unbind(Player p) {
		handlers.remove(getHandler(p));
	}

	public static void cleanUp() {
		handlers.clear();
		listeners.clear();
	}
	
	private static PlayerDamageHandler getHandler(Player p) {
		for (PlayerDamageHandler handler : handlers) {
			if (handler.p == p) {
				return handler;
			}
		}
		return null;
	}
	
	public static Player getLastDamage(Player p) {
		for (PlayerDamageHandler handler : handlers) {
			if (handler.p == p) {
				return handler.last_damager;
			}
		}
		
		return null;
	}
	
	public static void deal(LivingEntity victim, double damage) {
		deal(victim, null, damage);
	}
	
	public static void deal(LivingEntity victim, Player damager, double damage) {
		
		if (victim instanceof Player) {
			PlayerDamageHandler handler = getHandler((Player) victim);
			if (damager != null) handler.damage(damager);
			else handler.damage();
		}
		
		victim.damage(damage);
	}
	
	private static class PlayerDamageHandler {
		
		private Player p;
		private Player last_damager;
		
		public PlayerDamageHandler(Player p) {
			this.p = p;
		}
		
		public void damage(Player p) {
			last_damager = p;
		}
		
		public void damage() {
			last_damager = null;
		}
		
	}
	
	public static void triggerDeathListeners(Player dead, Player killer) {
		if (dead!=null) {
			for (PlayerDeathListener listener : listeners) {
				listener.onPlayerDeath(dead, killer);
			}
		}
	}

	protected static void registerDeathListener(PlayerDeathListener playerDeathEvent) {
		listeners.add(playerDeathEvent);
	}
	
}
