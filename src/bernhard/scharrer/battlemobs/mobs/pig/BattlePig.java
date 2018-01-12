package bernhard.scharrer.battlemobs.mobs.pig;

import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobStatue;
import bernhard.scharrer.battlemobs.mobs.MobType;
import de.robingrether.idisguise.disguise.MobDisguise;
import de.robingrether.idisguise.disguise.PigDisguise;

public class BattlePig extends BattleMob {

	public BattlePig() {
		super(MobType.PIG, new PigItems(), new PigListener());
	}

	@Override
	protected MobStatue generateStatue() {
		return new PigStatue(this);
	}

	@Override
	public MobDisguise generateDisguise() {
		return new PigDisguise(true, true);
	}
	
}
