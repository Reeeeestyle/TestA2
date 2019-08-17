package me.Reeeeestyle.A1;

import me.Reeeeestyle.A1.Events.EventsClass;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.plugin.java.JavaPlugin;

import Items.CustomItems;

public class Alpha1 extends JavaPlugin{ 
	private Commands commands;
	public void onEnable(){
		commands = new Commands();
		getCommand(commands.cmd1).setExecutor(commands);
		getCommand(commands.cmd2).setExecutor(commands);
		getCommand(commands.cmd3).setExecutor(commands);
		getCommand(commands.cmd4).setExecutor(commands);
		getCommand(commands.cmd5).setExecutor(commands);
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\nTest A1 has been enabled\n\n");
		getServer().getPluginManager().registerEvents(new EventsClass(), this);
		loadConfig();
		
		CustomItems items = new CustomItems();
		items.customRecipe();
		items.unshaped();
	}
	
	public void onDisable(){
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "\n\nTest A1 has been disabled\n\n");
	}
	
	public void loadConfig(){
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
}
