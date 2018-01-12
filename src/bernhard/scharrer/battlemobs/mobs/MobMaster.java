package bernhard.scharrer.battlemobs.mobs;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import bernhard.scharrer.battlemobs.data.PlayerData;
import bernhard.scharrer.battlemobs.mobs.pig.BattlePig;
import bernhard.scharrer.battlemobs.mobs.skeleton.BattleSkeleton;
import bernhard.scharrer.battlemobs.mobs.zombie.BattleZombie;

public class MobMaster {
	
	private static List<BattleMob> battlemobs = new ArrayList<>();
	private static List<PlayerData> players = new ArrayList<>();
	
	/**
	 * 
	 */
	public static void register() {
		
		new MobInventoryHandler();
		
		battlemobs.add(new BattlePig());
		battlemobs.add(new BattleZombie());
		battlemobs.add(new BattleSkeleton());
		
	}
	
	/**
	 * 
	 * @param p
	 */
	public static void loadPlayer(Player p) {
		players.add(new PlayerData(p));
	}
	
	/**
	 * 
	 * @param p
	 */
	public static void removePlayer(Player p) {
		players.remove(getPlayerData(p));
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	public static BattleMob getBattleMob(MobType type) {
		for (BattleMob  mob : battlemobs) if (mob.getType().equals(type)) return mob;
		return null;
	}
	
	/**
	 * @param p
	 * @return
	 */
	public static PlayerData getPlayerData(Player p) {
		for (PlayerData data : players) if (data.getPlayer().getUniqueId().equals(p.getUniqueId())) return data;
		return null;
	}

	/**
	 * 
	 */
	public static void cleanUp() {
		for (BattleMob mob : battlemobs) mob.cleanUp();
	}
	
}
