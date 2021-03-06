package bernhard.scharrer.battlemobs.listeners;

public class Listeners {
	
	/**
	 * register listeners
	 */
	public static void init() {
		new AntiBlockInteractListener();
		new AntiWheatTramplingListener();
		new AsyncPlayerChatListener();
		new BlockBreakListener();
		new BlockPlaceListener();
//		new EntityDamageListener();
		new FoodLevelChangeListener();
		new InventoryClickListener();
		new LeavesDecayListener();
		new PlayerPickUpListener();
		new PlayerFakeDeathListener();
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
