package bernhard.scharrer.battlemobs.mobs.slime;

import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobStatue;
import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.EntitySlime;
import net.minecraft.server.v1_12_R1.WorldServer;

public class SlimeStatue extends MobStatue {

	public SlimeStatue(BattleMob mob) {
		super(mob);
	}

	@Override
	protected EntityInsentient generateEntity(WorldServer world) {
		EntitySlime slime = new EntitySlime(world);
		slime.setSize(2, true);
//		((Slime)slime.getBukkitEntity()).setSize(2);
		return slime;
	}
	
}
