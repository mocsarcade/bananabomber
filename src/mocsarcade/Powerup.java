package mocsarcade;

import java.awt.Rectangle;
import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Powerup
{
	private Tile tile;
	private Image image;
	
	public Powerup(Tile tile)
	{
		this.tile = tile;
		if(new Random().nextInt(2) == 1)
		{
			this.image = Powerup.POWER_IMAGE;
		}
		else
		{
			this.image = Powerup.AMOUNT_IMAGE;
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
				
				if(this.image == Powerup.AMOUNT_IMAGE)
				{
					System.out.println("AMOUNT");
					monkey.bombcount += 1;
				}
				else if(this.image == Powerup.POWER_IMAGE)
				{
					System.out.println("POWER");
					monkey.power += 1;
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
	
	public static Image POWER_IMAGE;
	public static Image AMOUNT_IMAGE;
}