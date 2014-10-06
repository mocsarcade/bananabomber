package mocsarcade;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Banana
{
	private Tile tile;
	private String type;
	private Image image;
	
	public Banana(Tile tile)
	{
		this.tile = tile;
		
		if(Game.randomness.nextBoolean())
		{
			this.type = "intensity";
			this.image = Game.assets.getImage("./res/intensity.banana.png");
		}
		else
		{
			this.type = "capacity";
			this.image = Game.assets.getImage("./res/capacity.banana.png");
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
				
				Game.assets.getSound("./res/banana.powerup.wav").play();
			}
		}
	}

	public void render(Graphics graphics)
	{
		float x = this.tile.getX() - (Tile.WIDTH / 2);
		float y = this.tile.getY() + (Tile.HEIGHT / 2);

		this.image.draw(x, y);
	}
	
	public static HashMap<String, Image> images = new HashMap<String, Image>();
	public static HashMap<String, Audio> sounds = new HashMap<String, Audio>();
}