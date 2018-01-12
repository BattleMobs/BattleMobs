package bernhard.scharrer.battlemobs.mobs.skeleton;

import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobStatue;
import bernhard.scharrer.battlemobs.mobs.MobType;
import de.robingrether.idisguise.disguise.AgeableDisguise;
import de.robingrether.idisguise.disguise.DisguiseType;
import de.robingrether.idisguise.disguise.MobDisguise;

public class BattleSkeleton extends BattleMob {

	public BattleSkeleton() {
		super(MobType.SKELETON, new SkeletonItems(), new SkeletonListener());
	}

	@Override
	protected MobStatue generateStatue() {
		return new SkeletonStatue(this);
	}

	@Override
	public MobDisguise generateDisguise() {
		return new AgeableDisguise(DisguiseType.SKELETON);
	}
	
	
	
}
