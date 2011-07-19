package me.CAR145.LoginMessages;

import java.util.logging.Logger;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;



public class LoginMessages extends JavaPlugin {
	public static LoginMessages plugin;

	public final Logger log = Logger.getLogger("Minecraft");
	private final LoginMessagesPlayerListener playerListener = new LoginMessagesPlayerListener(this);
	String rules;


	@Override
	public void onDisable() {
		this.log.info("Login Messages Disabled");

	}
	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_JOIN, this.playerListener, Event.Priority.Normal, this);
		PluginDescriptionFile pdfFile = this.getDescription();
		this.log.info("[" + pdfFile.getName() + "]" + " Version " + pdfFile.getVersion() + " Is Enabled! ");
		loadConfig();
	}



	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		String[] split = args;
		String commandName = command.getName().toLowerCase();
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (commandName.equalsIgnoreCase("Rules")) {
				if (split.length == 0) {
					player.sendMessage(ChatColor.GOLD + "Server Rules: " + rules); 
				}

			}
		}
		return false;
	}

	public void loadConfig()
	{
		Configuration config = getConfiguration();
		config.load();


		rules = config.getString("rules-message", " <Replace with server rules>");


		config.setProperty("rules-message", rules);

		config.save();
	}






}