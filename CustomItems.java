package Items;

import java.util.ArrayList;

import me.Reeeeestyle.A1.Alpha1;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class CustomItems implements Listener{
	public ItemStack item = new ItemStack(Material.DIAMOND_AXE, 1);
	public ItemMeta meta = item.getItemMeta();
	private Plugin plugin = Alpha1.getPlugin(Alpha1.class);
	public void giveItems(Player player){
		
		meta.setDisplayName(ChatColor.AQUA + "Axe of Zeus");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.WHITE + "Used by Zeus in the great war");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		
		//player.getInventory().addItem(item);
	}
	public void customRecipe(){
		ShapedRecipe zeusaxe = new ShapedRecipe(item);
		zeusaxe.shape("#~ ","#@ "," @ ");
		zeusaxe.setIngredient('#', Material.DIAMOND);
		zeusaxe.setIngredient('~', Material.IRON_INGOT);
		zeusaxe.setIngredient('@', Material.STICK);
		plugin.getServer().addRecipe(zeusaxe);
	}
	public void unshaped(){
		ItemStack item2 = new ItemStack(Material.BLAZE_POWDER,1);
		
		ShapelessRecipe slr = new ShapelessRecipe(item2);
		slr.addIngredient(3,Material.LAVA_BUCKET);
		slr.addIngredient(2, Material.SULPHUR);
		plugin.getServer().addRecipe(slr);
	}
}
