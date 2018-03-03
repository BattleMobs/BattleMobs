package bernhard.scharrer.battlemobs.mobs.creeper;

import org.bukkit.entity.Player;

import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobStatue;
import bernhard.scharrer.battlemobs.mobs.MobType;
import de.robingrether.idisguise.disguise.CreeperDisguise;
import de.robingrether.idisguise.disguise.MobDisguise;

public class BattleCreeper extends BattleMob {

	public BattleCreeper() {
		super(MobType.CREEPER, new CreeperItems(), new CreeperListener());
	}

	@Override
	protected MobStatue generateStatue() {
		return new CreeperStatue(this);
	}

	@Override
	public MobDisguise generateDisguise() {
		return new CreeperDisguise(false);
	}

	@Override
	public void init(Player p) {
		
	}

}
