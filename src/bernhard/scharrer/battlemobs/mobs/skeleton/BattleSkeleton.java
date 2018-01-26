package bernhard.scharrer.battlemobs.mobs.skeleton;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobStatue;
import bernhard.scharrer.battlemobs.mobs.MobType;
import bernhard.scharrer.battlemobs.util.Item;
import de.robingrether.idisguise.disguise.DisguiseType;
import de.robingrether.idisguise.disguise.MobDisguise;

public class BattleSkeleton extends BattleMob {

	public BattleSkeleton() {
		super(MobType.SKELETON, new SkeletonItems(), new SkeletonListener());
	}

	@Override
	protected MobStatue generateStatue() {
		return new SkeletonStatue(this);
	}

	@Override
	public MobDisguise generateDisguise() {
		return (MobDisguise) DisguiseType.SKELETON.newInstance();
	}

	@Override
	public void init(Player p) {
		p.getInventory().setItem(9, Item.createIngameItem("§c", Material.ARROW, 0));
		p.updateInventory();
	}
	
	
	
}
