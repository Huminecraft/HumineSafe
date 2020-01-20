package com.aymegike.huminesafe;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.aymegike.humineclaim.listener.command.ShowMenuCommand;
import com.aymegike.huminesafe.commands.AskZoneCommand;
import com.aymegike.huminesafe.event.DamageOnPlayer;
import com.aymegike.huminesafe.event.PlayerJoin;
import com.aymegike.huminesafe.event.Spawn;
import com.aymegike.huminesafe.manager.PlayerCoolDownManager;
import com.aymegike.huminesafe.manager.SafeZoneGenerator;

public class HumineSafe extends JavaPlugin{
	
	public static final String VERSION = "1.0";
	
	private static SafeZoneGenerator szg;
	private static PlayerCoolDownManager pcdms;
	
	public void onEnable() {
		System.out.println("------------------------------------------------------------");
		System.out.println("[HumineSafe] HumineSafe est un bon plugin (enfin je crois) !");
		System.out.println("------------------------------------------------------------");
		
		//EVENTMANAGER
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Spawn(), this);
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new DamageOnPlayer(), this);
		
		this.getCommand("zone").setExecutor(new AskZoneCommand());
		
		////////////////////////////////
		szg = new SafeZoneGenerator();
		pcdms = new PlayerCoolDownManager();
	}
	
	public void onDisable() {
		System.out.println("[HumineSafe] Disabled !");
	}
	
	public static SafeZoneGenerator getSafeZoneGenerator() {
		return szg;
	}
	
	public static PlayerCoolDownManager getPlayerCoolDownManager() {
		return pcdms;
	}

}
