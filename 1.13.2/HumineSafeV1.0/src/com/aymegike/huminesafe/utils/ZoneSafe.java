package com.aymegike.huminesafe.utils;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Wither;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import com.aymegike.huminesafe.HumineSafe;
import com.aypi.utils.Square;
import com.aypi.utils.Zone;
import com.aypi.utils.inter.ZoneListener;

public class ZoneSafe {
	
	private Zone zone;
	private Square square;
	private ZoneListener zoneListener;
	
	private Location location1;
	private Location location2;
	
	private ArrayList<Sound> soundsEnter = new ArrayList<Sound>();
	private ArrayList<String> messagesEnter = new ArrayList<String>();
	private ArrayList<Sound> soundsExit = new ArrayList<Sound>();
	private ArrayList<String> messagesExit = new ArrayList<String>();
	
	
	public ZoneSafe() {
		
		this.zoneListener = new ZoneListener() {

			@Override
			public void onDamage(Entity entity, EntityDamageEvent e) {
				
			}

			@Override
			public void onPlayerEnterZone(Player player) {
				for (String message : messagesEnter) {
					player.sendMessage(message.replace("&", "§").replaceAll("%PLAYER%", player.getName()));
				}
				for (Sound sound : soundsEnter) {
					player.playSound(player.getLocation(), sound, 5, 1);
				}
				
				PlayerCoolDown pc = null;
				
				for (PlayerCoolDown pcd : HumineSafe.getPlayerCoolDownManager().getPCDS()) {
					if (pcd.getPlayer().getName().equalsIgnoreCase(player.getName())) {
						pc = pcd;
					}
				}
				
				if (pc != null) {
					pc.startEnter(false);
				} else {
					PlayerCoolDown pcd = new PlayerCoolDown(player);
					pcd.startEnter(true);
				}
				
				
			}

			@Override
			public void onPlayerExitZone(Player player) {
				for (String message : messagesExit) {
					player.sendMessage(message.replace("&", "§").replaceAll("%PLAYER%", player.getName()));
				}
				for (Sound sound : soundsExit) {
					player.playSound(player.getLocation(), sound, 5, 1);
				}
				
				PlayerCoolDown pc = null;
				
				for (PlayerCoolDown pcd : HumineSafe.getPlayerCoolDownManager().getPCDS()) {
					if (pcd.getPlayer().getName().equalsIgnoreCase(player.getName())) {
						pc = pcd;
					}
				}
				
				if (pc != null) {
					pc.startExit(false);
				} else {
					PlayerCoolDown pcd = new PlayerCoolDown(player);
					pcd.startExit(true);
					
				}
				
				
			}

			@Override
			public void onPlayerMoveInZone(Player player, PlayerMoveEvent e) {
				
			}

			@Override
			public void onPlayerTryToInteractEvent(Player player, PlayerInteractEvent e) {
				
			}

			@Override
			public void onPlayerTryToPlaceBlock(Player player, Block block, BlockPlaceEvent e) {
				
			}

			@Override
			public void onPlayerTryToRemoveBlock(Player player, Block block, BlockBreakEvent e) {
				
			}

			@Override
			public void onDamageByEntity(Entity entity, EntityDamageByEntityEvent e) {
				if (entity instanceof Player) {
					if (e.getDamager() instanceof Player) {
						e.setCancelled(true);
					}
					if (e.getDamager() instanceof Projectile) {
						Projectile p = (Projectile) e.getDamager();
						if (p.getShooter() instanceof Player) {
							e.setCancelled(true);
						}
					}
				}
				
				else if (entity instanceof Wither) {
					
					((Wither) entity).setHealth(0);
					
				}
			}
			
			
		};
		
	}
	
	public void addSoundEnter(Sound sound) {
		soundsEnter.add(sound);
	}
	
	public void addSoundExit(Sound sound) {
		soundsExit.add(sound);
	}
	
	public void addMessagesEnter(String message) {
		messagesEnter.add(message);
	}
	
	public void addMessagesExit(String message) {
		messagesExit.add(message);
	}
	
	public void setLocation1(Location location1) {
		this.location1 = location1;
	}
	
	public void setLocation2(Location location2) {
		this.location2 = location2;
	}
	
	public void initSquare() {
		if (location1 != null && location2 != null)
			this.square = new Square(this.location1, this.location2);
	}
	
	public void initZone() {
		if (square != null)
			this.zone = new Zone(this.square, this.zoneListener, Zone.PRIORITY_HIGH);
	}
	
	public Zone getZone() {
		return this.zone;
	}
	
	
	
	

}
