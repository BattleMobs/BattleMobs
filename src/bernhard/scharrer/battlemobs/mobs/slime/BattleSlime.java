package bernhard.scharrer.battlemobs.mobs.slime;

import org.bukkit.entity.Player;

import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobStatue;
import bernhard.scharrer.battlemobs.mobs.MobType;
import de.robingrether.idisguise.disguise.DisguiseType;
import de.robingrether.idisguise.disguise.MobDisguise;

public class BattleSlime extends BattleMob {

	public BattleSlime() {
		super(MobType.SLIME, new SlimeItems(), new SlimeListener());
	}

	@Override
	protected MobStatue generateStatue() {
		return new SlimeStatue(this);
	}

	@Override
	public MobDisguise generateDisguise() {
		return (MobDisguise) DisguiseType.SLIME.newInstance();
	}

	@Override
	public void init(Player p) {}

}
