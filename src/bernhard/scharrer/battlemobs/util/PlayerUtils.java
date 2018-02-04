package bernhard.scharrer.battlemobs.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import bernhard.scharrer.battlemobs.data.Database;
import bernhard.scharrer.battlemobs.data.PlayerData;
import bernhard.scharrer.battlemobs.mobs.MobMaster;
import bernhard.scharrer.battlemobs.mobs.MobType;

public class PlayerUtils {
	
	public static final float DEFAULT_WALKSPEED = 0.4f;
	
	public static void reset(Player p) {
		p.getInventory().clear();
		p.getInventory().setHelmet(null);
		p.getInventory().setChestplate(null);
		p.getInventory().setLeggings(null);
		p.getInventory().setBoots(null);
		p.getInventory().setHeldItemSlot(4);
		p.setExp(0);
		p.setGameMode(GameMode.SURVIVAL);
		p.setExhaustion(0);
		p.setLevel(0);
		p.setFireTicks(0);
		p.setFlying(false);
		p.setWalkSpeed(DEFAULT_WALKSPEED);
		p.setFoodLevel(20);
		p.closeInventory();
		changeHealth(p, 20.0);
		
		
		updateEXP(p);
		
	}
	
	public static void receiveLobbyItems(Player p) {
		p.getInventory().setItem(1, Item.createItem("§b§lINFO", "", Material.NETHER_STAR, 1, 0));
		p.getInventory().setItem(4, Item.createItem("§b§lTELEPORTER", "", Material.COMPASS, 1, 0));
		p.getInventory().setItem(7, Item.createItem("§b§lComing soon!", "", Material.NETHER_STAR, 1, 0));
	}
	
	public static void teleportToLobby(Player p) {
		reset(p);
		p.sendMessage("§aYou have been teleported to the lobby.");
		p.teleport(Locations.lobby);
		receiveLobbyItems(p);
	}
	
	public static void updateEXP(Player p) {
		PlayerData data = MobMaster.getPlayerData(p);
		p.setLevel(data.getLevel());
		
		int nextLevel = (Leveling.getTotalEXPNeeded(data.getLevel()+1));
		int currentLevel = Leveling.getTotalEXPNeeded(data.getLevel());
		
		int delta_exp = data.getExp() - currentLevel;
		int span_exp = nextLevel - currentLevel;
		
		float exp = (delta_exp*1.0f)/span_exp;
		if (exp<0) exp = 0;
		
		p.setExp(exp);
	}

	public static void changeHealth(Player p, double health) {
		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
		p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
	}
	
	public static void heal(Player p, double heal) {
		if (heal+p.getHealth()<p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) {
			p.setHealth(p.getHealth()+heal);
		} else p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
	}
	
	public static void insertIntoDB(Player p) {
		
		ResultSet already_in_db = Database.query("SELECT * FROM PLAYERS WHERE UUID LIKE '"+p.getUniqueId().toString()+"'");
		
		try {
			/*
			 * new player
			 */
			if (!already_in_db.next()) {
				Database.execute("INSERT INTO PLAYERS (UUID, NAME, EXP, MONEY, RANK, KILLS, DEATHS) VALUES ('"
						+p.getUniqueId().toString()+"','"
						+p.getName()+"',0,100,"
						+(p.isOp()?"'OWNER'":"'MEMBER'")+",0,0)");
				
				for (MobType type : MobType.values()) {
					Database.execute("INSERT INTO MOBS (UUID, MOB, EXP, TIER, KILLS, DEATHS) VALUES ('"
							+p.getUniqueId().toString()+"','"
							+type.toString()+"',0,1,0,0)");
				}
				
			}
			
			/*
			 * already in db
			 */
			else {
				
				Database.execute("UPDATE PLAYERS SET NAME = '"+p.getName()+"' WHERE UUID LIKE '"+p.getUniqueId().toString()+"'");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
