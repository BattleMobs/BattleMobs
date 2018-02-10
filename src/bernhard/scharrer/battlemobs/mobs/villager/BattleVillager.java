package bernhard.scharrer.battlemobs.mobs.villager;

import org.bukkit.entity.Player;

import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobStatue;
import bernhard.scharrer.battlemobs.mobs.MobType;
import de.robingrether.idisguise.disguise.MobDisguise;
import de.robingrether.idisguise.disguise.VillagerDisguise;
import de.robingrether.idisguise.disguise.VillagerDisguise.Profession;

public class BattleVillager extends BattleMob {

	public BattleVillager() {
		super(MobType.VILLAGER, new VillagerItems(), new VillagerListener());
	}

	@Override
	protected MobStatue generateStatue() {
		return new VillagerStatue(this);
	}

	@Override
	public MobDisguise generateDisguise() {
		return new VillagerDisguise(true, Profession.PRIEST);
	}

	@Override
	public void init(Player p) {
		
	}

}
