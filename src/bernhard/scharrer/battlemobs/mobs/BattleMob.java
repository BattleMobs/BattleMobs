package bernhard.scharrer.battlemobs.mobs;

import org.bukkit.entity.Player;

import de.robingrether.idisguise.disguise.MobDisguise;

public abstract class BattleMob {
	
	private MobType type;
	private MobStatue statue;
	private MobListener listener;
	private MobItems items;
	
	public BattleMob(MobType type, MobItems items, MobListener listener) {
		this.type = type;
		this.items = items;
		this.listener = listener;
		this.statue = generateStatue();
	}

	protected abstract MobStatue generateStatue();
	public abstract MobDisguise generateDisguise();
	public abstract void init(Player p);
	
	public MobType getType() {
		return type;
	}
	
	public MobItems getItems() {
		return items;
	}
	
	public MobListener getListener() {
		return listener;
	}
	
	public void cleanUp() {
		statue.cleanUp();
	}

}
