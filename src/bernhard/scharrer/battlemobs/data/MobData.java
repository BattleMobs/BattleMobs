package bernhard.scharrer.battlemobs.data;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import bernhard.scharrer.battlemobs.mobs.MobType;
import bernhard.scharrer.battlemobs.util.Leveling;

public class MobData {
	
	private MobType type;
	private Player p;
	private int tier;
	private int exp;
	private int kills;
	private int deaths;
	
	public MobData(MobType type, Player p) {
		this.type = type;
		this.p = p;
		this.tier = Database.getInt("MOBS", "TIER", "MOB LIKE '"+type.toString()+"' AND UUID LIKE '"+p.getUniqueId().toString()+"'");
		this.exp = Database.getInt("MOBS", "EXP", "MOB LIKE '"+type.toString()+"' AND UUID LIKE '"+p.getUniqueId().toString()+"'");
		this.kills = Database.getInt("MOBS", "KILLS", "MOB LIKE '"+type.toString()+"' AND UUID LIKE '"+p.getUniqueId().toString()+"'");
		this.deaths = Database.getInt("MOBS", "DEATHS", "MOB LIKE '"+type.toString()+"' AND UUID LIKE '"+p.getUniqueId().toString()+"'");
	}

	public MobType getType() {
		return type;
	}

	public Player getPlayer() {
		return p;
	}

	public int getTier() {
		return tier;
	}
	
	public int getEXP() {
		return exp;
	}
	
	public int getLevel() {
		return Leveling.getLevel(exp);
	}

	public int getKills() {
		return kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void addEXP(int exp) {
		int level = getLevel();
		this.exp += exp;
		Database.execute("UPDATE MOBS SET EXP="+this.exp+" WHERE MOB LIKE '"+type.toString()+"' AND UUID LIKE '"+p.getUniqueId().toString()+"'");
		// level up
		if (getLevel()>level) {
			
			p.sendMessage("§8[§6BattleMobs§8] §aYou have reached §2"+type.toString()+"§a level §2"+(level+1)+"§a!");
			p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
			
		}
	}
	
	public void incrementKills() {
		this.kills++;
		Database.execute("UPDATE MOBS SET KILLS="+this.kills+" WHERE MOB LIKE '"+type.toString()+"' AND UUID LIKE '"+p.getUniqueId().toString()+"'");
	}
	
	public void incrementDeaths() {
		this.deaths++;
		Database.execute("UPDATE MOBS SET DEATHS="+this.deaths+" WHERE MOB LIKE '"+type.toString()+"' AND UUID LIKE '"+p.getUniqueId().toString()+"'");
	}
	
	public void incrementTier() {
		this.tier++;
		Database.execute("UPDATE MOBS SET TIER="+this.tier+" WHERE MOB LIKE '"+type.toString()+"' AND UUID LIKE '"+p.getUniqueId().toString()+"'");
	}
	
}
