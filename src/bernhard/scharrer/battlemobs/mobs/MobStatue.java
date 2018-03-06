package bernhard.scharrer.battlemobs.mobs;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import bernhard.scharrer.battlemobs.data.PlayerData;
import bernhard.scharrer.battlemobs.listeners.Listener;
import bernhard.scharrer.battlemobs.util.Locations;
import net.minecraft.server.v1_12_R1.EntityInsentient;
import net.minecraft.server.v1_12_R1.WorldServer;

public abstract class MobStatue extends Listener {
	
	private static final String HEADER = "§8§l[§b§l";
	private static final String TRAILER = "§8§l]";
	
	private EntityInsentient entity;
	private BattleMob mob;

	public MobStatue(BattleMob mob) {

		this.mob = mob;
		Location loc = mob.getType().getLocation();
		entity = generateEntity(((CraftWorld)(loc.getWorld())).getHandle());
		entity.setCustomName(HEADER+mob.getType().toString()+"§7 Level: "+mob.getType().getLevel()+TRAILER);
		entity.setCustomNameVisible(true);
		entity.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
		entity.setInvulnerable(true);
		entity.setGoalTarget(null);
		entity.setOnFire(0);
		entity.setNoAI(true);
		entity.setSilent(true);
		entity.setHeadRotation(loc.getYaw());
		if (entity.getBukkitEntity() instanceof LivingEntity) {
			((LivingEntity) entity.getBukkitEntity()).setCollidable(false);
		}
		((CraftWorld)(loc.getWorld())).addEntity(entity, SpawnReason.CUSTOM);
		
	}
	
	protected abstract EntityInsentient generateEntity(WorldServer world);

	public void cleanUp() {
		if (entity!=null) entity.killEntity();
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEntityEvent event) {
		
		Player p = event.getPlayer();
		Entity e = event.getRightClicked();
		PlayerData data = MobMaster.getPlayerData(p);
		
		if (p.getWorld().getName().equals(Locations.lobby_world.getName())) {
			
			if (event.getRightClicked().getType() == entity.getBukkitEntity().getType()) {
				
				if (e.getCustomName().startsWith(HEADER)) {
					
					if (data.getLevel() >= mob.getType().getLevel()) {
						
						new MobInventory(data.getMobData(mob.getType()), mob.getType());
						
					} else {
						p.sendMessage("§cYou need to be at least level "+mob.getType()+" to play this mob!");
					}
					
				}
				
			}
			
		} 
		
	}
	
}
