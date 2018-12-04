package com.aymegike.huminesafe.event;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import com.aypi.Aypi;
import com.aypi.utils.Zone;

public class Spawn implements Listener {
	
	@EventHandler
	public void spawnEntity(CreatureSpawnEvent e) {
		if (e.getEntityType() == EntityType.WITHER) {
			
			for (Zone zone : Aypi.getZoneManager().getZones()) {
				if (zone.entityIsInZone(e.getEntity())) {
					e.setCancelled(true);
				}
			}
			
		}
	}
	
}
