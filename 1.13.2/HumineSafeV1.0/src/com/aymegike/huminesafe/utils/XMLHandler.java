package com.aymegike.huminesafe.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {
	
	private ZoneSafe zoneSafe;
	
	private ReaderState rs = ReaderState.NONE;
	private ComState cs = ComState.NONE;
	
	private boolean sound = false;
	private boolean message = false;
	
	public void startDocument() {
		System.out.println("[HumineSafe] Parsing document");
	}
	
	public void endDocument() {
		System.out.println("[HumineSafe] End pars document");
	}
	
	public void startElement(String nameSpaceURI, String localName, String qName, Attributes atts) {
		
		if (qName.equalsIgnoreCase("zone-safe")) {
			zoneSafe = new ZoneSafe();
		}
		
		if (qName.equalsIgnoreCase("c1")) {
			rs = ReaderState.C1;
		}
		
		if (qName.equalsIgnoreCase("c2")) {
			rs = ReaderState.C2;
		}
		
		if (qName.equalsIgnoreCase("enter")) {
			cs = ComState.ENTER;
		}
		
		if (qName.equalsIgnoreCase("exit")) {
			cs = ComState.EXIT;
		}
		
		if (qName.equalsIgnoreCase("message")) {
			message = true;
		}
		
		if (qName.equalsIgnoreCase("sound")) {
			sound = true;
		}
		
		if (qName.equalsIgnoreCase("location")) {
			
			String world = atts.getValue("world");
			String x = atts.getValue("x");
			String y = atts.getValue("y");
			String z = atts.getValue("z");
			
			if (world != null && x !=null && y != null && z != null) {
				if (rs == ReaderState.C1)
					zoneSafe.setLocation1(new Location(Bukkit.getWorld(world), Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z)));
				else if (rs == ReaderState.C2)
					zoneSafe.setLocation2(new Location(Bukkit.getWorld(world), Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z)));
				
				System.out.println("[HumineSafe] "+rs.toString()+" "+world+" "+x+" "+y+" "+z);
				
			} else {
				System.out.println("[HumineSage] ERROR Location world x y z");
			}
			
		}
		
	}
	
	public void endElement(String nameSpaceURI, String localName, String qName) {
		
		if (qName.equalsIgnoreCase("zone-safe")) {
			zoneSafe.initSquare();
			zoneSafe.initZone();
			zoneSafe = null;
		}
		
		if (qName.equalsIgnoreCase("c1")) {
			rs = ReaderState.NONE;
		}
		
		if (qName.equalsIgnoreCase("c2")) {
			rs = ReaderState.NONE;
		}
		
		if (qName.equalsIgnoreCase("message")) {
			message = false;
		}
		
		if (qName.equalsIgnoreCase("sound")) {
			sound = false;
		}
		
		if (qName.equalsIgnoreCase("enter")) {
			cs = ComState.NONE;
		}
		
		if (qName.equalsIgnoreCase("exit")) {
			cs = ComState.NONE;
		}
		
	}
	
	public void characters(char[] ch, int start, int length) {
		
		if (message) {
			if (cs == ComState.ENTER)
				zoneSafe.addMessagesEnter(new String(ch, start, length));
			else if (cs == ComState.EXIT)
				zoneSafe.addMessagesExit(new String(ch, start, length));	
		} else if (sound) {
			if (cs == ComState.ENTER)
				zoneSafe.addSoundEnter(Sound.valueOf(new String(ch, start, length)));
			else if (cs == ComState.EXIT)
				zoneSafe.addSoundExit(Sound.valueOf(new String(ch, start, length)));			
		}
		
	}
}
