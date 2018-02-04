package bernhard.scharrer.battlemobs.mobs;

import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import bernhard.scharrer.battlemobs.data.MobData;
import bernhard.scharrer.battlemobs.data.PlayerData;
import bernhard.scharrer.battlemobs.listeners.Listener;
import bernhard.scharrer.battlemobs.util.Locations;

public class MobListener extends Listener {
	
	protected static final int WRONG_MOB = -1;
	
	public int getMobTier(Player p) {
		
		PlayerData data = MobMaster.getPlayerData(p);
		
		if (data!=null) {
			MobData mob = data.getCurrentData();
			if (mob!=null) {
				return mob.getTier();
			}
		}
		
		return WRONG_MOB;
	}

	public boolean valid(Player p) {
		return valid((Entity)p)&&p.getWorld().getName().equals(Locations.map_world.getName());
	}
	
	public boolean valid(Entity entity) {
		boolean survival = true;
		if (entity instanceof Player) {
			survival = ((Player) entity).getGameMode()==GameMode.SURVIVAL;
		}
		return survival && entity.getWorld().getName().equals(Locations.map_world.getName());
	}
	
	public boolean valid(World world) {
		return world.getName().equals(Locations.map_world.getName());
	}
	
}
