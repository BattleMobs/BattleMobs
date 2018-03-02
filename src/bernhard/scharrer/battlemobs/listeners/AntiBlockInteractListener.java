package bernhard.scharrer.battlemobs.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class AntiBlockInteractListener extends Listener {
	
	private static final List<Material> ANTI_INTERACT_BLOCKS = new ArrayList<>();
	
	{
		ANTI_INTERACT_BLOCKS.add(Material.BED_BLOCK);
		ANTI_INTERACT_BLOCKS.add(Material.TRAPPED_CHEST);
		ANTI_INTERACT_BLOCKS.add(Material.WOOD_BUTTON);
		ANTI_INTERACT_BLOCKS.add(Material.STONE_BUTTON);
		ANTI_INTERACT_BLOCKS.add(Material.LEVER);
		ANTI_INTERACT_BLOCKS.add(Material.FURNACE);
		ANTI_INTERACT_BLOCKS.add(Material.ENDER_CHEST);
		ANTI_INTERACT_BLOCKS.add(Material.ACACIA_DOOR_ITEM);
		ANTI_INTERACT_BLOCKS.add(Material.BIRCH_DOOR);
		ANTI_INTERACT_BLOCKS.add(Material.DARK_OAK_DOOR);
		ANTI_INTERACT_BLOCKS.add(Material.JUNGLE_DOOR);
		ANTI_INTERACT_BLOCKS.add(Material.SPRUCE_DOOR);
		ANTI_INTERACT_BLOCKS.add(Material.TRAP_DOOR);
		ANTI_INTERACT_BLOCKS.add(Material.WOOD_DOOR);
		ANTI_INTERACT_BLOCKS.add(Material.WOODEN_DOOR);
		ANTI_INTERACT_BLOCKS.add(Material.CHEST);
		ANTI_INTERACT_BLOCKS.add(Material.EYE_OF_ENDER);
		ANTI_INTERACT_BLOCKS.add(Material.TNT);
		ANTI_INTERACT_BLOCKS.add(Material.ANVIL);
		ANTI_INTERACT_BLOCKS.add(Material.BEACON);
		ANTI_INTERACT_BLOCKS.add(Material.BREWING_STAND);
		ANTI_INTERACT_BLOCKS.add(Material.WORKBENCH);
		ANTI_INTERACT_BLOCKS.add(Material.JUKEBOX);
		ANTI_INTERACT_BLOCKS.add(Material.ENDER_PORTAL_FRAME);
		ANTI_INTERACT_BLOCKS.add(Material.DROPPER);
		ANTI_INTERACT_BLOCKS.add(Material.DISPENSER);
		ANTI_INTERACT_BLOCKS.add(Material.OBSERVER);
		ANTI_INTERACT_BLOCKS.add(Material.HOPPER);
		ANTI_INTERACT_BLOCKS.add(Material.ACACIA_FENCE_GATE);
		ANTI_INTERACT_BLOCKS.add(Material.BIRCH_FENCE_GATE);
		ANTI_INTERACT_BLOCKS.add(Material.DARK_OAK_FENCE_GATE);
		ANTI_INTERACT_BLOCKS.add(Material.JUNGLE_FENCE_GATE);
		ANTI_INTERACT_BLOCKS.add(Material.SPRUCE_FENCE_GATE);
		ANTI_INTERACT_BLOCKS.add(Material.FENCE_GATE);
	}
	
	@EventHandler
	public void onTrample(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (event.getClickedBlock() != null) {
				if (event.getPlayer().getGameMode()!=GameMode.CREATIVE) {
					 
					Material type = event.getClickedBlock().getType();
					
					if (ANTI_INTERACT_BLOCKS.contains(type)) {
						event.setCancelled(true);
					}
					
				}
			}
		}
	}

}
