package com.aymegike.huminesafe.manager;

import java.util.ArrayList;

import com.aymegike.huminesafe.utils.PlayerCoolDown;

public class PlayerCoolDownManager {
	
	private ArrayList<PlayerCoolDown> pcds;
	
	
	public PlayerCoolDownManager() {
		this.pcds = new ArrayList<PlayerCoolDown>();
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
	
}
