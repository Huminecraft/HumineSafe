package com.aymegike.huminesafe.manager;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import com.aymegike.huminesafe.utils.PlayerCoolDown;

public class PlayerCoolDownManager {
	
	private ArrayList<PlayerCoolDown> pcds;	
	//private ArrayList<Player> pls;
	
	public PlayerCoolDownManager() {
		this.pcds = new ArrayList<PlayerCoolDown>();
		//this.pls = new ArrayList<Player>(); 
	}
	
	public void addPlayerCoolDown(PlayerCoolDown playerCoolDown) {
		pcds.add(playerCoolDown);	
	}
	
	public void removePlayerCoolDown(PlayerCoolDown playerCoolDown) {
		pcds.remove(playerCoolDown);
	}	
	
	public ArrayList<PlayerCoolDown> getPCDS() {
		return pcds;
	}
	
	/*public void addPlayer(Player player) {
		pls.add(player);
	}
	
	public void removePlayer(Player player) {
		pls.remove(player);
	}
	
	public ArrayList<Player> getPlayers() {
		return pls;
	}	
	
	public boolean containPlayer(Player player) {
		return pls.contains(player);
	}*/
	
	/*public void addCooldownForPlayer(Player player)
	{
		pls.contains(player);
	}*/

	public PlayerCoolDown getPlayerCoolDownForPlayer(Player player) {
		for (PlayerCoolDown pcd : pcds)
		{
			if (pcd.getPlayer().getUniqueId().equals(player.getUniqueId()))
			{
				return pcd;
			}
		}
		return null;
	}
	
	public boolean containPlayerCoolDown(Player player)
	{
		return getPlayerCoolDownForPlayer(player) != null;
	}
}
