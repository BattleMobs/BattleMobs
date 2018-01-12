package bernhard.scharrer.battlemobs.mobs.zombie;

import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobStatue;
import bernhard.scharrer.battlemobs.mobs.MobType;
import de.robingrether.idisguise.disguise.AgeableDisguise;
import de.robingrether.idisguise.disguise.DisguiseType;
import de.robingrether.idisguise.disguise.MobDisguise;
import de.robingrether.idisguise.disguise.VillagerDisguise.Profession;
import de.robingrether.idisguise.disguise.ZombieVillagerDisguise;

public class BattleZombie extends BattleMob {

	public BattleZombie() {
		super(MobType.ZOMBIE, new ZombieItems(), new ZombieListener());
	}

	@Override
	protected MobStatue generateStatue() {
		return new ZombieStatue(this);
	}

	@Override
	public MobDisguise generateDisguise() {
		return new AgeableDisguise(DisguiseType.ZOMBIE);
	}

}
