package bernhard.scharrer.battlemobs.mobs.zombie;

import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobStatue;
import bernhard.scharrer.battlemobs.util.Locations;
import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.EntityZombie;
import net.minecraft.server.v1_12_R1.WorldServer;

public class ZombieStatue extends MobStatue {

	public ZombieStatue(BattleMob mob) {
		super(mob, Locations.zombie_statue);
	}

	@Override
	protected EntityInsentient generateEntity(WorldServer world) {
		return new EntityZombie(world);
	}

}
