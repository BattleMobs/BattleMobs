package bernhard.scharrer.battlemobs.mobs.skeleton;

import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobStatue;
import bernhard.scharrer.battlemobs.util.Locations;
import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.EntitySkeleton;
import net.minecraft.server.v1_12_R1.WorldServer;

public class SkeletonStatue extends MobStatue {

	public SkeletonStatue(BattleMob mob) {
		super(mob, Locations.skeleton_statue);
	}

	@Override
	protected EntityInsentient generateEntity(WorldServer world) {
		return new EntitySkeleton(world);
	}

}
