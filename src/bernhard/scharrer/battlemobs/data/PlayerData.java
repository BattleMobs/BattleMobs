package bernhard.scharrer.battlemobs.data;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import bernhard.scharrer.battlemobs.mobs.MobType;
import bernhard.scharrer.battlemobs.util.Leveling;

public class PlayerData {
	
	private Player p;
	private List<MobData> mob_data = new ArrayList<>();
	private MobData current;
	private String rank;
	private int exp;
	private int kills;
	private int deaths;
	private int money;
	
	public PlayerData(Player p) {
		long ago = System.currentTimeMillis();
		this.p = p;
		this.current = null;
		this.rank = Database.getString("PLAYERS", "RANK", "UUID LIKE '"+p.getUniqueId().toString()+"'");
		this.exp = Database.getInt("PLAYERS", "EXP", "UUID LIKE '"+p.getUniqueId().toString()+"'");
		this.kills = Database.getInt("PLAYERS", "KILLS", "UUID LIKE '"+p.getUniqueId().toString()+"'");
		this.deaths = Database.getInt("PLAYERS", "DEATHS", "UUID LIKE '"+p.getUniqueId().toString()+"'");
		this.money = Database.getInt("PLAYERS", "MONEY", "UUID LIKE '"+p.getUniqueId().toString()+"'");
		
		for (MobType type : MobType.values()) mob_data.add(new MobData(type, p));
		Bukkit.getConsoleSender().sendMessage("Time to load data: "+(System.currentTimeMillis()-ago)+"ms");
	}

	public Player getPlayer() {
		return p;
	}

	public MobData getMobData(MobType type) {
		for (MobData data : mob_data) if (data.getType()==type) return data;
		return null;
	}

	public MobData getCurrentData() {
		return current;
	}

	public String getRank() {
		return rank;
	}

	public int getExp() {
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

	public int getMoney() {
		return money;
	}
	
	public void setCurrent(MobType current) {
		this.current = getMobData(current);
	}

	public void modifyMoney(int money) {
		this.money += money;
		Database.execute("UPDATE PLAYERS SET MONEY = '"+this.money+"' WHERE UUID LIKE '"+p.getUniqueId().toString()+"'");
	}
	
	public void addEXP(int exp) {
		int level = getLevel();
		this.exp += exp;
		Database.execute("UPDATE PLAYERS SET EXP = '"+this.exp+"' WHERE UUID LIKE '"+p.getUniqueId().toString()+"'");
		// level up
		if (getLevel()>level) {
			
			p.sendMessage("§8[§6BattleMobs§8] §aYou have reached level §2"+(level+1)+"§a!");
			p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_TWINKLE, 1, 1);
			p.resetTitle();
			p.sendTitle("§2§lLEVEL UP", "§aLevel: "+(level+1),10,60,10);
			
		}
	}
	
	public void incrementKills() {
		this.kills++;
		Database.execute("UPDATE PLAYERS SET KILLS = '"+this.kills+"' WHERE UUID LIKE '"+p.getUniqueId().toString()+"'");
	}
	
	public void incrementDeaths() {
		this.deaths++;
		Database.execute("UPDATE PLAYERS SET DEATHS = '"+this.deaths+"' WHERE UUID LIKE '"+p.getUniqueId().toString()+"'");
	}

}
