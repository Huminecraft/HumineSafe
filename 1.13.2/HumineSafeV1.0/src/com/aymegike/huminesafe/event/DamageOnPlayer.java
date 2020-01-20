package com.aymegike.huminesafe.event;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.aymegike.huminesafe.HumineSafe;
import com.aymegike.huminesafe.utils.CoolDownState;
import com.aymegike.huminesafe.utils.PlayerCoolDown;

public class DamageOnPlayer implements Listener
{

	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e)
	{
		Entity entity = e.getEntity();
		Entity damager = e.getDamager();
		if (damager instanceof Projectile && ((Projectile)damager).getShooter() instanceof Entity)
		{
			damager = (Entity) ((Projectile)damager).getShooter();
		}		

		//If a player is hit..
		if (entity instanceof Player && damager instanceof Player)
		{
			Player player = (Player)entity;
			Player damagerPlayer = (Player)damager;
			
			if (player.getUniqueId().equals(damagerPlayer.getUniqueId()))
			{
				return;
			}
			
			PlayerCoolDown victimPcd = HumineSafe.getPlayerCoolDownManager().getPlayerCoolDownForPlayer(player);
			PlayerCoolDown damagerPcd = HumineSafe.getPlayerCoolDownManager().getPlayerCoolDownForPlayer(damagerPlayer);
			//..and that this player has a cool down..
			if (victimPcd != null)
			{
				//..if the cool down decrease (so the player leave the area)
				if (victimPcd.getCoolDownState() == CoolDownState.DECRESS)
				{
					//cancel the damage
					e.setCancelled(true);
				}
				//else the player is entering the area
				else if (victimPcd.getCoolDownState() == CoolDownState.INCRESS)
				{
					victimPcd.addCoolDown(5);
					return;
				}						
			}
			
			if (damagerPcd != null)
			{
				//..if the cool down decrease (so the player leave the area)
				if (damagerPcd.getCoolDownState() == CoolDownState.DECRESS)
				{
					//cancel the damage
					e.setCancelled(true);
				}
				//else the player is entering the area
				else if (damagerPcd.getCoolDownState() == CoolDownState.INCRESS)
				{
					damagerPcd.addCoolDown(5);
					return;
				}					
			}			
		}
	}
}
