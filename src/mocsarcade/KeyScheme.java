package mocsarcade;

import org.newdawn.slick.Input;
import java.io.*;
import java.util.Properties;

public class KeyScheme
{
	public int moveNorth;
	public int moveSouth;
	public int moveWest;
	public int moveEast;
	public int dropBomb;
	
	public KeyScheme(String color)
	{
	    try{
	    	//Load ini file
	        Properties p = new Properties();
	        p.load(new FileInputStream("controls.ini"));
	        //Each key of the file has a string for a key to press. Map to them
			this.moveNorth = Utility.convertChar(p.getProperty(color + "up"));
			this.moveSouth = Utility.convertChar(p.getProperty(color + "down"));
			this.moveWest = Utility.convertChar(p.getProperty(color + "left"));
			this.moveEast = Utility.convertChar(p.getProperty(color + "right"));
			this.dropBomb = Utility.convertChar(p.getProperty(color + "bomb"));
	    }
	    catch (Exception e) {
	      System.out.println(e);
	    }
	    
	}
}