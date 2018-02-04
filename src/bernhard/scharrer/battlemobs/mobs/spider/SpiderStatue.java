package bernhard.scharrer.battlemobs.mobs.spider;

import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobStatue;
import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.EntitySpider;
import net.minecraft.server.v1_12_R1.WorldServer;

public class SpiderStatue extends MobStatue {

	public SpiderStatue(BattleMob mob) {
		super(mob);
	}

	@Override
	protected EntityInsentient generateEntity(WorldServer world) {
		return new EntitySpider(world);
	}

}
