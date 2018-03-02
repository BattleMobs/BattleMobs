package bernhard.scharrer.battlemobs.util;

import org.bukkit.entity.Player;

public abstract class PlayerDeathListener {
	
	public PlayerDeathListener() {
		DamageHandler.registerDeathListener(this);
	}
	
	public abstract void onPlayerDeath(Player dead, Player killer);

}
