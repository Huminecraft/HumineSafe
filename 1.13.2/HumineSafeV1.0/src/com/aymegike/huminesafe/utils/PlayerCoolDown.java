package com.aymegike.huminesafe.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import com.aymegike.huminesafe.HumineSafe;

public class PlayerCoolDown {
	
	private Player player;
	private BossBar bossBar;
	
	private int task;
	private int coolDown = 0;
	private int coolDownMax = 30;
	
	private CoolDownState cds = CoolDownState.NONE;
	
	public PlayerCoolDown(Player player, boolean isEntering) {
		this.player = player;	
		HumineSafe.getPlayerCoolDownManager().addPlayerCoolDown(this);
		if (isEntering)
		{
			bossBar = Bukkit.createBossBar(ChatColor.DARK_PURPLE+"Tu entres en zone sécurisée... "+ChatColor.WHITE+coolDown+"/"+coolDownMax, BarColor.PURPLE, BarStyle.SOLID);
		}
		else
		{
			bossBar = Bukkit.createBossBar(ChatColor.DARK_PURPLE+"Tu quittes la zone sécurisée... "+ChatColor.WHITE+coolDown+"/"+coolDownMax, BarColor.PURPLE, BarStyle.SOLID);
		}
		bossBar.addPlayer(player);
	}
	
	public void startEnter(boolean rebootCoolDown) {
		cds = CoolDownState.INCRESS;
		if (rebootCoolDown)
			coolDown = 0;
		Bukkit.getScheduler().cancelTask(task);
		
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("HumineSafe"), new Runnable() {
			
			@Override
			public void run() {
				
				if (coolDown >= coolDownMax)
				{
					player.sendMessage(ChatColor.GREEN+"Te voilà maintenant protégé(e) !");
					stop(true);
				}
				
				bossBar.setTitle(ChatColor.DARK_PURPLE+"Tu entres en zone sécurisée... "+ChatColor.WHITE+coolDown+"/"+coolDownMax);
				bossBar.setProgress( (float) ((coolDown * 100) / coolDownMax) / 100);
				coolDown++;
				
			}
			
		}, 20, 20);
		
	}
	
	public void startExit(boolean rebootCoolDown) {
		cds = CoolDownState.DECRESS;
		if (rebootCoolDown)
			coolDown = 30;
		Bukkit.getScheduler().cancelTask(task);
		
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("HumineSafe"), new Runnable() {
			
			@Override
			public void run() {
				
				if (coolDown <= 0)
				{
					player.sendMessage(ChatColor.RED+"Tu n'es désormais plus protégé(e), bonne chance !");
					stop(true);
				}
				bossBar.setTitle(ChatColor.DARK_PURPLE+"Tu quittes la zone sécurisée... "+ChatColor.WHITE+coolDown+"/"+coolDownMax);
				bossBar.setProgress( (float) ((coolDown * 100) / coolDownMax) / 100);
				coolDown--;
				
			}
			
		}, 20, 20);
		
	}
	
	public void stop(boolean remove) {
		
		Bukkit.getScheduler().cancelTask(task);
		bossBar.removePlayer(player);
		if (remove) {
			HumineSafe.getPlayerCoolDownManager().removePlayerCoolDown(this);
		}
		
		cds = CoolDownState.NONE;
		
	}
	
	public void addCoolDown(int incress) {
			this.coolDownMax+=incress;
			if (this.coolDownMax > 60 )
			{
				this.coolDownMax = 60;
			}
	}
	
	
	public Player getPlayer() {
		return player;
	}
	
	
	public CoolDownState getCoolDownState() {
		return cds;
	}
	

}
