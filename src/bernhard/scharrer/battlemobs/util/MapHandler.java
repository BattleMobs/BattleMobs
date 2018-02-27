package bernhard.scharrer.battlemobs.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
	
	private static List<Location> locations = new ArrayList<>();
	private static final float TIME_BEFORE_SPAWNING = 5;
	
	private static List<Spawnpoint> spawnpoints = new ArrayList<>();
	
	public static void loadMap() {
		
		locations.add(new Location(Locations.map_world, 264.5f, 5.5f, -189.5f, 180, 0));
		locations.add(new Location(Locations.map_world, 264.5f, 5.5f, -253.5f, 0, 0));
		
		new Task(TIME_BEFORE_SPAWNING) {
			
			@Override
			public void run() {
				spawnpoints.add(new Spawnpoint(new Location(Locations.map_world, 264.5f, 5.5f, -189.5f, 180, 0)));
				spawnpoints.add(new Spawnpoint(new Location(Locations.map_world, 264.5f, 5.5f, -253.5f, 0, 0)));
			}
		};
		
	}
	
	public static void teleportIntoMap(Player p, int tier, BattleMob mob) {
		
		Collections.sort(spawnpoints);
		Spawnpoint sp = spawnpoints.get(0);
		System.out.println(sp.count);
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
						System.out.println("Count: "+count);
					} else {
						spawnItem();
					}
				}

			};
			
		}
		
		private void spawnItem() {
			System.out.println("Spawnpoint was set! ("+loc.toString()+")");
			this.item = loc.getWorld().dropItemNaturally(loc.clone().add(0.5, -0.5, 0.5), Item.createItem("", "", Material.TORCH, 1, 0));
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
