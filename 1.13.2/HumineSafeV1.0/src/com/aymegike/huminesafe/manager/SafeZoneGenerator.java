package com.aymegike.huminesafe.manager;

import java.io.File;
import java.util.ArrayList;

import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.aymegike.huminesafe.utils.XMLHandler;
import com.aymegike.huminesafe.utils.ZoneSafe;

public class SafeZoneGenerator {
	
	private ArrayList<ZoneSafe> zones = new ArrayList<ZoneSafe>();
	private File folder;
	
	public SafeZoneGenerator() {
		folder = new File("./plugins/HumineSafe/zones");
		folder.mkdirs();
		if (folder.list() != null) {
			for (int i = 0 ; i < folder.list().length ; i++) {
				
				try {
					
					XMLReader p = XMLReaderFactory.createXMLReader();
					p.setContentHandler(new XMLHandler());
					p.parse(folder.getPath()+"/"+folder.list()[i]);
				
				} catch (Exception e) {e.printStackTrace();}
				
			}
		} else {
			System.out.println("[HumineSafe] aucun fichier xml a charger.");
		}
		
		
		
		
		
	}
	
	public void addZone(ZoneSafe zone) {
		zones.add(zone);
	}
	
	public ArrayList<ZoneSafe> getZones() {
		return this.zones;
	}
	
	public File getFolder() {
		return this.folder;
	}

}
