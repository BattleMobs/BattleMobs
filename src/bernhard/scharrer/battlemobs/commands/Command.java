package bernhard.scharrer.battlemobs.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import bernhard.scharrer.battlemobs.BattleMobs;

public abstract class Command implements CommandExecutor {
	
	public Command(String cmd) {
		BattleMobs.getInstance().getServer().getPluginCommand(cmd).setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String cmd, String[] args) {
		if (sender instanceof Player) perform((Player) sender, cmd);
		else perform((Player) sender, cmd);
		return false;
	}
	
	protected abstract void perform(Player p, String cmd);
	protected abstract void perform(CommandSender sender, String cmd);

}
