package mocsarcade;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Banana
{
	private Tile tile;
	private Image image;
	
	public Banana(Tile tile)
	{
		this.tile = tile;
		if(new Random().nextInt(2) == 1)
		{
			this.image = Banana.images.get("intensity");
		}
		else
		{
			this.image = Banana.images.get("capacity");
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
				
				if(this.image == Banana.images.get("capacity banana"))
				{
					System.out.println("AMOUNT");
					monkey.bombCapacity += 1;
				}
				else if(this.image == Banana.images.get("intensity banana"))
				{
					System.out.println("POWER");
					monkey.bombIntensity += 1;
				}
			}
		}
	}

	public void render(Graphics graphics)
	{
		float x = this.tile.getX();
		float y = this.tile.getY();
		
		this.image.draw(x, y);
	}
	
	public static HashMap<String, Image> images = new HashMap<String, Image>();
}