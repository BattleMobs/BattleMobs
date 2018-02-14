package bernhard.scharrer.battlemobs.mobs.zombie;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobStatue;
import bernhard.scharrer.battlemobs.mobs.MobType;
import de.robingrether.idisguise.disguise.AgeableDisguise;
import de.robingrether.idisguise.disguise.DisguiseType;
import de.robingrether.idisguise.disguise.MobDisguise;

public class BattleZombie extends BattleMob {

	private static final double ZOMBIE_HEALTH = 40;
	protected static final float ZOMBIE_WALSPEED = 0.28f;
	
	public BattleZombie() {
		super(MobType.ZOMBIE, new ZombieItems(), new ZombieListener());
	}

	@Override
	protected MobStatue generateStatue() {
		return new ZombieStatue(this);
	}

	@Override
	public MobDisguise generateDisguise() {
		return new AgeableDisguise(DisguiseType.ZOMBIE);
	}

	@Override
	public void init(Player p) {
		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(ZOMBIE_HEALTH);
		p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
		p.setWalkSpeed(ZOMBIE_WALSPEED);
	}

}
