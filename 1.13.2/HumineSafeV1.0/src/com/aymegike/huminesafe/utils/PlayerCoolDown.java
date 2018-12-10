package com.aymegike.huminesafe.utils;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import com.aymegike.huminesafe.HumineSafe;

import net.md_5.bungee.api.ChatColor;

public class PlayerCoolDown {
	
	private Player player;
	private BossBar bb;
	
	private int task;
	private int coolDown = 0;
	private int coolDownMax = 30;
	
	public PlayerCoolDown(Player player) {
		this.player = player;	
		HumineSafe.getPlayerCoolDownManager().addPlayerCoolDown(this);
		bb = Bukkit.createBossBar(ChatColor.DARK_PURPLE+"Tes pieds foules des terres sacrées... "+ChatColor.WHITE+coolDown, BarColor.PURPLE, BarStyle.SOLID, BarFlag.DARKEN_SKY);
		bb.addPlayer(player);
	}
	
	public void startEnter(boolean rebootCoolDown) {
		if (rebootCoolDown)
			coolDown = 0;
		Bukkit.getScheduler().cancelTask(task);
		
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("HumineSafe"), new Runnable() {
			
			@Override
			public void run() {
				
				if (coolDown >= coolDownMax) {
					stop(false);
				}
				
				bb.setTitle(ChatColor.DARK_PURPLE+"Tes pieds foules des terres sacrées... "+ChatColor.WHITE+coolDown);
				bb.setProgress( (float) ((coolDown * 100) / coolDownMax) / 100);
				coolDown++;
				
			}
			
		}, 20, 20);
		
	}
	
	public void startExit(boolean rebootCoolDown) {
		if (rebootCoolDown)
			coolDown = 30;
		Bukkit.getScheduler().cancelTask(task);
		
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Bukkit.getPluginManager().getPlugin("HumineSafe"), new Runnable() {
			
			@Override
			public void run() {
				
				if (coolDown <= 0) {
					stop(false);
				}
				
				bb.setTitle(ChatColor.DARK_PURPLE+"Tes pieds foules des terres sacrées... "+ChatColor.WHITE+coolDown);
				bb.setProgress( (float) ((coolDown * 100) / coolDownMax) / 100);
				coolDown--;
				
			}
			
		}, 20, 20);
		
	}
	
	public void stop(boolean remove) {
		
		Bukkit.getScheduler().cancelTask(task);
		bb.removePlayer(player);
		if (remove) {
			HumineSafe.getPlayerCoolDownManager().removePlayerCoolDown(this);
		}
		
	}
	
	public void addCoolDown(int incress) {
		this.coolDownMax+=incress;
	}
	
	
	public Player getPlayer() {
		return player;
	}

}
