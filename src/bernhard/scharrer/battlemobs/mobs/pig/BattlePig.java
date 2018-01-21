package bernhard.scharrer.battlemobs.mobs.pig;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobStatue;
import bernhard.scharrer.battlemobs.mobs.MobType;
import de.robingrether.idisguise.disguise.MobDisguise;
import de.robingrether.idisguise.disguise.PigDisguise;

public class BattlePig extends BattleMob {

	private static final double PIG_HEALTH = 16;

	public BattlePig() {
		super(MobType.PIG, new PigItems(), new PigListener());
	}

	@Override
	protected MobStatue generateStatue() {
		return new PigStatue(this);
	}

	@Override
	public MobDisguise generateDisguise() {
		return new PigDisguise(true, true);
	}

	@Override
	public void init(Player p) {
		
		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(PIG_HEALTH);
		p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
		
	}
	
}
