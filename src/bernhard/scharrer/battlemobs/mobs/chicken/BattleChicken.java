package bernhard.scharrer.battlemobs.mobs.chicken;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobStatue;
import bernhard.scharrer.battlemobs.mobs.MobType;
import de.robingrether.idisguise.disguise.AgeableDisguise;
import de.robingrether.idisguise.disguise.DisguiseType;
import de.robingrether.idisguise.disguise.MobDisguise;

public class BattleChicken extends BattleMob {

	private static final double CHICKEN_HEALTH = 16;
	
	public BattleChicken() {
		super(MobType.CHICKEN, new ChickenItems(), new ChickenListener());
	}

	@Override
	protected MobStatue generateStatue() {
		return new ChickenStatue(this);
	}

	@Override
	public MobDisguise generateDisguise() {
		return new AgeableDisguise(DisguiseType.CHICKEN, true);
	}

	@Override
	public void init(Player p) {
		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(CHICKEN_HEALTH);
		p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
	}
	
	
}
