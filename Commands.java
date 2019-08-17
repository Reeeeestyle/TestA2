package me.Reeeeestyle.A1;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Commands implements Listener, CommandExecutor {
	
	public String cmd1 = "giveitem";
	public String cmd2 = "inventory";
	public String cmd3 = "warp";
	public String cmd4 = "setwarp";
	public String cmd5 = "delwarp";
	private Alpha1 plugin = Alpha1.getPlugin(Alpha1.class);
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
			if(cmd.getName().equalsIgnoreCase(cmd1)){
				if(sender instanceof Player){
					Material item = Material.getMaterial(args[0].toUpperCase());
					if(args.length != 0)
					{
						if (item != null){
							Inventory inv = ((Player)sender).getInventory();
							inv.addItem(new ItemStack(item, 1));
							return true;
						}
						else{
							sender.sendMessage(ChatColor.RED + "That is not a valid item.");
							return true;
						}
					}
					else{
						sender.sendMessage(ChatColor.RED + "Not enough arguments!");
						return true;
					}
				}
				else{
					sender.sendMessage(ChatColor.RED + "Only players can execute this command.");
					return true;
				}
			}
			if(cmd.getName().equalsIgnoreCase(cmd2)){
				if(sender instanceof Player){
					Player player = (Player) sender;
					CustomInventory i = new CustomInventory();
					i.newInventory(player);
					return true;
				}
				else{
					sender.sendMessage(ChatColor.RED + "Only players can execute this command.");
					return true;
				}
			}
			
			if(cmd.getName().equalsIgnoreCase(cmd3)){
				Player player = (Player) sender;
				if(args.length == 1 && !args[0].equalsIgnoreCase("location")){
					for(Player online : Bukkit.getOnlinePlayers()){
						if(args[0].equalsIgnoreCase(online.getDisplayName())){
							player.teleport(online);
							return true;
						}
						else{
							player.sendMessage(ChatColor.RED + "Please specify a player.");
							return true;
						}
					}
				}
				if(args.length == 2 && args[0].equalsIgnoreCase("location")){
					float yaw = (float) plugin.getConfig().getDouble("Warps."+args[1]+".Yaw");
					float pitch = (float) plugin.getConfig().getDouble("Warps."+args[1]+".Pitch");
					float x = (float) plugin.getConfig().getDouble("Warps."+args[1]+".X");
					float y = (float) plugin.getConfig().getDouble("Warps."+args[1]+".Y");
					float z = (float) plugin.getConfig().getDouble("Warps."+args[1]+".Z");
					if (x == 0 && y == 0 && z == 0 && yaw == 0 && pitch == 0){
						player.sendMessage(ChatColor.RED+ "That warp does not exist!");
						return true;
					}
					else{
						Location loc = new Location(player.getWorld(), x, y, z, yaw, pitch);
						player.teleport(loc);
						player.sendMessage(ChatColor.GREEN+ "Teleported!");
						return true;
					}
				}
				
			}
			if(cmd.getName().equalsIgnoreCase(cmd4)){
				Player player = (Player) sender;
				if(args.length == 1){
					Location loc = player.getLocation();
					plugin.getConfig().set("Warps."+args[0]+".Yaw", loc.getYaw());
					plugin.getConfig().set("Warps."+args[0]+".Pitch", loc.getPitch());
					plugin.getConfig().set("Warps."+args[0]+".X", loc.getX());
					plugin.getConfig().set("Warps."+args[0]+".Y", loc.getY());
					plugin.getConfig().set("Warps."+args[0]+".Z", loc.getZ());
					plugin.saveConfig();
					player.sendMessage(ChatColor.GREEN+ "Warp created!");
					return true;
				}
				else{
					player.sendMessage(ChatColor.RED + "Please specify a name.");
					return true;
				}
			}
			if(cmd.getName().equalsIgnoreCase(cmd5)){
				Player player = (Player) sender;
				if(args.length == 1){
					plugin.getConfig().set("Warps."+args[0], null);
					plugin.saveConfig();
					player.sendMessage(ChatColor.GREEN+ "Warp deleted!");
				}
				else{
					player.sendMessage(ChatColor.RED + "Please specify a name.");
					return true;
				}
			}
		return false;
	}
}
