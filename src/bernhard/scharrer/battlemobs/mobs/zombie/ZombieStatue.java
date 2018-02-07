package bernhard.scharrer.battlemobs.mobs.zombie;

import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobStatue;
import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.EntityZombie;
import net.minecraft.server.v1_12_R1.WorldServer;

public class ZombieStatue extends MobStatue {

	public ZombieStatue(BattleMob mob) {
		super(mob);
	}

	@Override
	protected EntityInsentient generateEntity(WorldServer world) {
		EntityZombie mob = new EntityZombie(world);
		mob.setBaby(false );		
		return mob;
	}

}
