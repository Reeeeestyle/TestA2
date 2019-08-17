package me.Reeeeestyle.A1;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class CustomInventory {
	private Plugin plugin = Alpha1.getPlugin(Alpha1.class);
	
	public void newInventory(Player player){
		Inventory i = plugin.getServer().createInventory(null, 27, ChatColor.DARK_AQUA + "Test A1 Inventory");
		int healthint = (int) player.getHealth();
		int foodint = player.getFoodLevel();
		ItemStack empty = new ItemStack(Material.STAINED_GLASS_PANE,1,(byte)15);
		ItemMeta emptymeta = empty.getItemMeta();
		emptymeta.setDisplayName("");
		empty.setItemMeta(emptymeta);
		ItemStack health = new ItemStack(Material.INK_SACK,healthint,(byte)1);
		ItemMeta healthmeta = empty.getItemMeta();
		healthmeta.setDisplayName(ChatColor.YELLOW + "Health");
		health.setItemMeta(healthmeta);
		ItemStack food = new ItemStack(Material.APPLE,foodint);
		ItemMeta foodmeta = empty.getItemMeta();
		foodmeta.setDisplayName(ChatColor.YELLOW + "Food");
		food.setItemMeta(foodmeta);
		i.setItem(12,health);
		i.setItem(14,food);
		player.openInventory(i);
	}
}
