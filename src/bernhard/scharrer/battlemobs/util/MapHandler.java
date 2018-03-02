package bernhard.scharrer.battlemobs.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import bernhard.scharrer.battlemobs.BattleMobs;
import bernhard.scharrer.battlemobs.mobs.BattleMob;
import bernhard.scharrer.battlemobs.mobs.MobItems;

public class MapHandler {
	
	private static final float TIME_BEFORE_SPAWNING = 5;
	
	private static List<Spawnpoint> spawnpoints = new ArrayList<>();
	
	public static void loadMap() {
		
		new Task(TIME_BEFORE_SPAWNING) {
			
			@Override
			public void run() {
				spawnpoints.add(new Spawnpoint(new Location(Locations.map_world,-29.287207f,39f,28.76683f,317,-1)));
				spawnpoints.add(new Spawnpoint(new Location(Locations.map_world,-48.240223f,39f,74.63556f,292,6)));
				spawnpoints.add(new Spawnpoint(new Location(Locations.map_world,-43.37002f,32f,99.421486f,235,8)));
				spawnpoints.add(new Spawnpoint(new Location(Locations.map_world,-11.459639f,27f,115.42925f,204,4)));
				spawnpoints.add(new Spawnpoint(new Location(Locations.map_world,24.404724f,27f,105.49045f,138,0)));
				spawnpoints.add(new Spawnpoint(new Location(Locations.map_world,39.942154f,27f,69.41202f,87,-2)));
				spawnpoints.add(new Spawnpoint(new Location(Locations.map_world,29.502686f,38f,40.511993f,47,0)));
				spawnpoints.add(new Spawnpoint(new Location(Locations.map_world,0.5215505f,28f,27.7152f,12,3)));
			}
		};
		
	}
	
	public static void teleportIntoMap(Player p, int tier, BattleMob mob) {
		
		Collections.sort(spawnpoints);
		Spawnpoint sp = spawnpoints.get(0);
		Collections.shuffle(spawnpoints);
		
		p.teleport(sp.getLocation());
		
		p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
		
		for (int n=0;n<3&&n<tier;n++) {
			p.getInventory().setItem(n, mob.getItems().getAbilityItem(n, (tier-(n+1))/3));
		}
		p.getInventory().setItem(7, MobItems.AUTO_RESPAWN_ON);
		p.getInventory().setItem(8, MobItems.LOBBY);
		mob.init(p);
		BattleMobs.getAPI().disguise(p, mob.generateDisguise());
		
	}
	
	public static void cleanUp() {
		for (Spawnpoint spawnpoint : spawnpoints) {
			spawnpoint.cleanUp();
		}
	}
	
	private static class Spawnpoint implements Comparable<Spawnpoint>{
		
		private static final double SPAWNPOINT_RADIUS = 25;
		private Location loc;
		private org.bukkit.entity.Item item;
		private Task period;
		private int count;
		
		public Spawnpoint(Location loc) {
			
			this.loc = loc;
			this.spawnItem();
			this.item.setInvulnerable(true);
			
			period = new Task(0, 2) {

				public void run() {
					if (item != null && !item.isDead()) {
						item.teleport(loc);
						count = 0;
						for (Entity nearBy : item.getNearbyEntities(SPAWNPOINT_RADIUS, SPAWNPOINT_RADIUS, SPAWNPOINT_RADIUS)) {
							if (nearBy instanceof LivingEntity) {
								count++;
							}
						}
					} else {
						spawnItem();
					}
				}

			};
			
		}
		
		private void spawnItem() {
			this.item = loc.getWorld().dropItemNaturally(loc.clone().add(0.5, -0.5, 0.5), Item.createItem("", "", Material.SEEDS, 1, 0));
		}

		public void cleanUp() {
			period.cancel();
			item.remove();
		}
		
		public Location getLocation() {
			return loc;
		}

		@Override
		public int compareTo(Spawnpoint other) {
			return Integer.compare(this.count, other.count);
		}
		
	}
	
}
