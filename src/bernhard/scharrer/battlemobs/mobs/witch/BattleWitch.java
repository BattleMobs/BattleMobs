package bernhard.scharrer.battlemobs.mobs.witch;

import org.bukkit.entity.Player;

import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobStatue;
import bernhard.scharrer.battlemobs.mobs.MobType;
import de.robingrether.idisguise.disguise.DisguiseType;
import de.robingrether.idisguise.disguise.MobDisguise;

public class BattleWitch extends BattleMob {

	public BattleWitch() {
		super(MobType.WITCH, new WitchItems(), new WitchListener());
	}

	@Override
	protected MobStatue generateStatue() {
		return new WitchStatue(this);
	}

	@Override
	public MobDisguise generateDisguise() {
		return (MobDisguise) DisguiseType.WITCH.newInstance();
	}

	@Override
	public void init(Player p) {}

}
