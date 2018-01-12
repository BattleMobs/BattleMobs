package bernhard.scharrer.battlemobs.util;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

import bernhard.scharrer.battlemobs.data.PlayerData;
import bernhard.scharrer.battlemobs.mobs.MobMaster;

public class Scoreboard {
	
	private static PlayerData data;
	
	public static void updateScoreboard(Player p) {
		org.bukkit.scoreboard.Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = scoreboard.registerNewObjective("...", "...");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName("§8[§6§lSTATS§8]");
		data = MobMaster.getPlayerData(p);
		
		addLine(obj,6,"§7Level§8: §e" + data.getLevel());
		addLine(obj,5,"§7EXP: §e" + data.getExp());
		addLine(obj,5,"§7EXP to level: §e" + (Leveling.getTotalEXPNeeded(data.getLevel()+1)-data.getExp()));
		addLine(obj,4,"§7Money§8: §e$" + data.getMoney());
		addLine(obj,3,"§7Kills§8: §e" + data.getKills());
		addLine(obj,2,"§7Deaths§8: §e" + data.getDeaths());
		addLine(obj,1,"§7KDR§8: §e" + String.format("%.3f", ((float)data.getKills()/(data.getDeaths()!=0?data.getDeaths():1))));
		addLine(obj,0,"§7Ping§8: §e" + ((CraftPlayer) p).getHandle().ping + "ms");
		p.setScoreboard(scoreboard);
	}
	
	private static void addLine(Objective obj, int n, String data) {
		Score s = obj.getScore(data);
		s.setScore(n);
	}
	
}