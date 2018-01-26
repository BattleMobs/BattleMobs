package bernhard.scharrer.battlemobs.mobs.sheep;

import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobStatue;
import bernhard.scharrer.battlemobs.util.Locations;
import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.EntitySheep;
import net.minecraft.server.v1_12_R1.WorldServer;

public class SheepStatue extends MobStatue {

	public SheepStatue(BattleMob mob) {
		super(mob, Locations.sheep_statue);
	}

	@Override
	protected EntityInsentient generateEntity(WorldServer world) {
		return new EntitySheep(world);
	}

}
