package com.aymegike.huminesafe;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.aymegike.huminesafe.event.Spawn;
import com.aymegike.huminesafe.manager.SafeZoneGenerator;

public class HumineSafe extends JavaPlugin{
	
	public static final String VERSION = "1.0";
	
	private static SafeZoneGenerator szg;
	
	public void onEnable() {
		System.out.println("------------------------------------------------------------");
		System.out.println("[HumineSafe] HumineSafe est un bon plugin (enfin je crois) !");
		System.out.println("------------------------------------------------------------");
		
		//EVENTMANAGER
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Spawn(), this);
		
		
		////////////////////////////////
		szg = new SafeZoneGenerator();	
	}
	
	public void onDisable() {
		System.out.println("[HumineSafe] Des bisous !");
	}
	
	public static SafeZoneGenerator getSafeZoneGenerator() {
		return szg;
	}

}
