package bernhard.scharrer.battlemobs.mobs.villager;

import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobStatue;
import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.EntityVillager;
import net.minecraft.server.v1_12_R1.WorldServer;

public class VillagerStatue extends MobStatue {

	public VillagerStatue(BattleMob mob) {
		super(mob);
	}

	@Override
	protected EntityInsentient generateEntity(WorldServer world) {
		return new EntityVillager(world);
	}

}
