package bernhard.scharrer.battlemobs.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {
	
	private static ItemStack item;
	private static ItemMeta meta;
	private static String[] description;
	private static List<String> lore = new ArrayList<>();
	private static int n;
	
	public static ItemStack createItem(String name, String desc, Material mat, int amount, int data) {
		item = new ItemStack(mat, amount, (short) data);
		meta = item.getItemMeta();
		meta.setDisplayName(name);
		lore.clear();
		description = desc.split("#");
		for (n=0;n<description.length;n++) lore.add(description[n]);
		if (!desc.isEmpty()) meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack createAbilityItem(String name, String desc, Material mat, int amount, int data) {
		item = new ItemStack(mat, amount, (short) data);
		meta = item.getItemMeta();
		meta.setDisplayName("§3§l"+name);
		lore.clear();
		description = desc.split("#");
		for (n=0;n<description.length;n++) lore.add("§7"+description[n]);
		if (!desc.isEmpty()) meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack createIngameItem(String name, Material mat, int data) {
		item = new ItemStack(mat, 1, (short) data);
		meta = item.getItemMeta();
		meta.setDisplayName("§3§l"+name);
		item.setItemMeta(meta);
		return item;
	}
	
	public static boolean valid(ItemStack item) {
		return (item != null && item.getItemMeta() != null && item.getItemMeta().getDisplayName() != null);
	}
	
}
