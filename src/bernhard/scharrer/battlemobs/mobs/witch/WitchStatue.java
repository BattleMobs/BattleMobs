package bernhard.scharrer.battlemobs.mobs.witch;

import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobStatue;
import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.EntityWitch;
import net.minecraft.server.v1_12_R1.WorldServer;

public class WitchStatue extends MobStatue {

	public WitchStatue(BattleMob mob) {
		super(mob);
	}

	@Override
	protected EntityInsentient generateEntity(WorldServer world) {
		return new EntityWitch(world);
	}

}
