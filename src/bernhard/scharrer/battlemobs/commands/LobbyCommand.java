package bernhard.scharrer.battlemobs.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import bernhard.scharrer.battlemobs.util.PlayerUtils;

public class LobbyCommand extends Command {

	public LobbyCommand() {
		super("lobby");
	}

	@Override
	protected void perform(Player p, String cmd) {
		PlayerUtils.teleportToLobby(p);
	}

	@Override
	protected void perform(CommandSender sender, String cmd) {
		sender.sendMessage("You need to be ingame.");
	}

}
