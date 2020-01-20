package com.aymegike.huminesafe.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.aymegike.huminesafe.HumineSafe;
import com.aymegike.huminesafe.utils.ZoneSafe;

public class AskZoneCommand implements CommandExecutor   {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!(sender instanceof Player))
		{
			System.out.println("Cette commande doit etre entrée par un joueur !");
			return false;
		}
		
		Player player = (Player) sender;
		boolean isInZone = false;
		for (ZoneSafe zone : HumineSafe.getSafeZoneGenerator().getZones())
		{
			if (zone.getZone().containLocation(player.getLocation()))
			{
				isInZone = true;
			}
		}
		
		if (isInZone)
		{
			System.out.println("COUCOU1");
			player.sendMessage("Tu es en zone safe, pas de danger à l'horizon ! ");
		}
		else
		{
			System.out.println("COUCOU2");
			player.sendMessage("Tu es en zone hostile, fais attention à toi !");				
		}
		
		
		return true;
	}
}