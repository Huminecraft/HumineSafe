package com.aymegike.huminesafe.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.aymegike.huminesafe.HumineSafe;

public class PlayerLeft implements Listener {
	
	@EventHandler
	public void onPlayerLeft(PlayerQuitEvent e) {
		
		Player player = e.getPlayer();
		
		HumineSafe.getPlayerCoolDownManager().removePlayer(player);
		
	}

}
