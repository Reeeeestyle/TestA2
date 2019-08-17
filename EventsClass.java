package me.Reeeeestyle.A1.Events;

import me.Reeeeestyle.A1.Alpha1;
import me.Reeeeestyle.A1.CustomInventory;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;

import Items.CustomItems;

public class EventsClass implements Listener{
	Plugin plugin = Alpha1.getPlugin(Alpha1.class);
	public String prefix = (ChatColor.BLUE + "TestA2>> ");
	private CustomItems ci = new CustomItems();
	private int count;
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove (PlayerMoveEvent event){
		//System.out.print(1);
		
		/*Player player = event.getPlayer();
		Inventory pInven = player.getInventory();
		ItemStack item = new ItemStack(Material.GOLDEN_APPLE, 1);
		
		if (player.isOnGround()){
			player.sendMessage(ChatColor.RED + "You are moving!");
			pInven.addItem(item);
		}
		if (!player.isOnGround()){
			player.sendMessage(ChatColor.RED + "You are not on the ground!");
		}
		*/
	}
	@EventHandler
	public void onInteract (PlayerInteractEvent event){
		/*Action action = event.getAction();
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		if (action.equals(Action.LEFT_CLICK_BLOCK)){
			if(block.getType().equals(Material.LOG)){
				if (player.getHealth() != 20){
					player.setHealth(player.getHealth()+1);
					player.sendMessage(ChatColor.GREEN + "You have been healed with the power of the tree!");
				}
			}
		}*/
	}
	@EventHandler
	public void onBreak (BlockBreakEvent event){
		/*Block block = event.getBlock();
		Player player = event.getPlayer();
		Location loc = block.getLocation();
		player.sendMessage(ChatColor.GOLD +  "You broke: " + block.getType().toString() + " at location: " + loc.toString());
		player.sendMessage(ChatColor.BLUE +  "" + loc.getBlockX());
		player.sendMessage(ChatColor.BLUE +  "" + loc.getBlockY());
		player.sendMessage(ChatColor.BLUE +  "" + loc.getBlockZ());*/
	}
	@EventHandler
	public void onPlace (BlockPlaceEvent event){
		/*Block block = event.getBlock();
		Player player = event.getPlayer();
		Location loc = block.getLocation();
		player.sendMessage(ChatColor.GOLD +  "You placed: " + block.getType().toString() + " at location: " + loc.toString());
		player.sendMessage(ChatColor.BLUE +  "" + loc.getBlockX());
		player.sendMessage(ChatColor.BLUE +  "" + loc.getBlockY());
		player.sendMessage(ChatColor.BLUE +  "" + loc.getBlockZ());
		
		if(block.getType().equals(Material.LOG)){
			plugin.getServer().broadcastMessage(ChatColor.RED + "" + player.getName()+ " placed a log.");
			plugin.getConfig().set("Users."+player.getUniqueId() + ".Block_" + count + ".World", loc.getWorld().getName());
			plugin.getConfig().set("Users."+player.getUniqueId() + ".Block_" + count + ".X", loc.getBlockX());
			plugin.getConfig().set("Users."+player.getUniqueId() + ".Block_" + count + ".Y", loc.getBlockY());
			plugin.getConfig().set("Users."+player.getUniqueId() + ".Block_" + count + ".Z", loc.getBlockZ());
			plugin.saveConfig();
			count += 1;
		}*/
		
		
	}
	@EventHandler
	public void onPunch (PlayerInteractEvent event){
		Player player = event.getPlayer();
		ci.giveItems(player);
	}
	@EventHandler
	public void onJoin (PlayerJoinEvent event){

		Player player = event.getPlayer();
		event.setJoinMessage("");
		player.sendMessage(prefix + ChatColor.GOLD + "Welcome back, " + player.getName());
	}
	@EventHandler
	public void onClick (PlayerInteractEvent event){

		
		Player player = event.getPlayer();
		Firework fw = player.getWorld().spawn(player.getLocation(), Firework.class);
		FireworkMeta fwm = fw.getFireworkMeta();
		Builder builder = FireworkEffect.builder();
		
		fwm.addEffect(builder.flicker(true).withColor(Color.BLUE).build());
		fwm.addEffect(builder.trail(true).build());
		fwm.addEffect(builder.withFade(Color.ORANGE).build());
		fwm.setPower(2);
		fw.setFireworkMeta(fwm);
	}
	@EventHandler
	public void InvenClick(InventoryClickEvent event){
		Player player = (Player) event.getWhoClicked();
		ClickType click = event.getClick();
		Inventory open = event.getClickedInventory();
		ItemStack item = event.getCurrentItem();
		
		if (open == null){
			return;
		}
		if (open.getName().equals(ChatColor.DARK_AQUA + "Test A1 Inventory")){
			if(item.equals(null) || !item.hasItemMeta()){
				return;
			}
			event.setCancelled(true);
			//System.out.println(1);
			if (item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Health")){
				player.closeInventory();
				player.setHealth(20);
				CustomInventory ci = new CustomInventory();
				ci.newInventory(player);
			}
			if (item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Food")){
				player.closeInventory();
				player.setFoodLevel(20);
				CustomInventory ci = new CustomInventory();
				ci.newInventory(player);
			}
		}
	}
}
