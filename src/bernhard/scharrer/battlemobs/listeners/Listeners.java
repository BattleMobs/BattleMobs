package bernhard.scharrer.battlemobs.listeners;

public class Listeners {
	
	/**
	 * register listeners
	 */
	public static void init() {
		new AsyncPlayerChatListener();
		new BlockBreakListener();
		new BlockPlaceListener();
//		new EntityDamageListener();
		new FoodLevelChangeListener();
		new InventoryClickListener();
		new LeavesDecayListener();
		new PlayerDeathListener();
		new PlayerDropItemListener();
//		new PlayerInteractEntityListener());
//		new PlayerInteractListener());
		new PlayerItemDamageListener();
		new PlayerJoinListener();
//		new PlayerMoveListener();
		new PlayerQuitListener();
		new ProjectileHitListener();
		new WeatherChangeListener();
	}
	
}
