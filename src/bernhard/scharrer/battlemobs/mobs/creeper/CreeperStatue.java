package bernhard.scharrer.battlemobs.mobs.creeper;

import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobStatue;
import net.minecraft.server.v1_12_R1.EntityCreeper;
import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.WorldServer;

public class CreeperStatue extends MobStatue {

	public CreeperStatue(BattleMob mob) {
		super(mob);
	}

	@Override
	protected EntityInsentient generateEntity(WorldServer world) {
		EntityCreeper creeper = new EntityCreeper(world);
		creeper.setPowered(false);
		return creeper;
	}

}
