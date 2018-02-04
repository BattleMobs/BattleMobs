package bernhard.scharrer.battlemobs.mobs.spider;

import org.bukkit.entity.Player;

import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobStatue;
import bernhard.scharrer.battlemobs.mobs.MobType;
import de.robingrether.idisguise.disguise.DisguiseType;
import de.robingrether.idisguise.disguise.MobDisguise;

public class BattleSpider extends BattleMob {

	public BattleSpider() {
		super(MobType.SPIDER, new SpiderItems(), new SpiderListener());
	}

	@Override
	protected MobStatue generateStatue() {
		return new SpiderStatue(this);
	}

	@Override
	public MobDisguise generateDisguise() {
		return (MobDisguise) DisguiseType.SPIDER.newInstance();
	}

	@Override
	public void init(Player p) {
		
	}

}
