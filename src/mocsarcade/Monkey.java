package mocsarcade;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Monkey
{
	private float x, y;
	private float speed;
	private Image image;
	private Gamemap gamemap;
	
	public Monkey(Gamemap gamemap) throws SlickException
	{
		this.x = 64;
		this.y = 64;
		this.speed = 0.25f;
		
		this.gamemap = gamemap;
		this.image = new Image("./res/monkey.png");
	}
	
	public void update(Input input, int delta)
	{
		float step = this.speed * delta;
		
		if(input.isKeyDown(Input.KEY_UP))
		{
			if(this.gamemap.isNotBlocked(this.x, this.y - step))
			{
				this.y -= step;
			}
		}
		
		if(input.isKeyDown(Input.KEY_DOWN))
		{
			if(this.gamemap.isNotBlocked(this.x, this.y + step))
			{
				this.y += step;
			}
		}

		if(input.isKeyDown(Input.KEY_LEFT))
		{
			if(this.gamemap.isNotBlocked(this.x - step, this.y))
			{
				this.x -= step;
			}
		}
		
		if(input.isKeyDown(Input.KEY_RIGHT))
		{
			if(this.gamemap.isNotBlocked(this.x + step, this.y))
			{
				this.x += step;
			}
		}
	}
	
	public void render()
	{
		float x = this.x - this.getHalfWidth();
		float y = this.y - this.getHalfHeight();
		
		this.image.draw(x, y);
	}
	
	public int getWidth()
	{
		return this.image.getWidth();
	}
	
	public int getHeight()
	{
		return this.image.getHeight();
	}
	
	public int getHalfWidth()
	{
		return this.getWidth() / 2;
	}
	
	public int getHalfHeight()
	{
		return this.getHeight() / 2;
	}
}