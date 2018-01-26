package bernhard.scharrer.battlemobs.mobs.sheep;

import org.bukkit.DyeColor;
import org.bukkit.entity.Player;

import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobStatue;
import bernhard.scharrer.battlemobs.mobs.MobType;
import de.robingrether.idisguise.disguise.MobDisguise;
import de.robingrether.idisguise.disguise.SheepDisguise;

public class BattleSheep extends BattleMob {

	public BattleSheep() {
		super(MobType.SHEEP, new SheepItems(), new SheepListener());
	}

	@Override
	protected MobStatue generateStatue() {
		return new SheepStatue(this);
	}

	@Override
	public MobDisguise generateDisguise() {
		return new SheepDisguise(true, DyeColor.WHITE);
	}

	@Override
	public void init(Player p) {
		
	}

}
