package bernhard.scharrer.battlemobs.mobs.pig;

import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobStatue;
import bernhard.scharrer.battlemobs.util.Locations;
import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.EntityPig;
import net.minecraft.server.v1_12_R1.WorldServer;

public class PigStatue extends MobStatue {

	public PigStatue(BattleMob mob) {
		super(mob, Locations.pig_statue);
	}

	@Override
	protected EntityInsentient generateEntity(WorldServer world) {
		return new EntityPig(world);
	}
	
}
