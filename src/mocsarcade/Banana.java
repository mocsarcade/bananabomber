package mocsarcade;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.openal.Audio;

public class Banana
{
	private Tile tile;
	private String type;
	
	public Banana(Tile tile)
	{
		this.tile = tile;
		
		if(Game.randomness.nextBoolean())
		{
			this.type = "intensity";
		}
		else
		{
			this.type = "capacity";
		}
	}
	
	public void update(int delta)
	{
		for(Monkey monkey : this.tile.gamemap.getMonkies())
		{
			if(monkey.getTileyX() == this.tile.getTileyX()
			&& monkey.getTileyY() == this.tile.getTileyY())
			{
				this.tile.powerup = null;
				
				if(this.type == "capacity")
				{
					monkey.bombCapacity += 1;
				}
				else if(this.type == "intensity")
				{
					monkey.bombIntensity += 1;
				}
				
				Banana.sounds.get("powerup").playAsSoundEffect(1f, 1f, false);
			}
		}
	}

	public void render(Graphics graphics)
	{
		float x = this.tile.getX() - (Tile.WIDTH / 2);
		float y = this.tile.getY() + (Tile.HEIGHT / 2);
		
		Banana.images.get(this.type).draw(x, y);
	}
	
	public static HashMap<String, Image> images = new HashMap<String, Image>();
	public static HashMap<String, Audio> sounds = new HashMap<String, Audio>();
}