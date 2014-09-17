package mocsarcade;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Monkey
{
	private float x, y; 
	private Image image;
	private float speed = 0.25f;
	
	public Monkey() throws SlickException
	{
		image = new Image("./res/monkey.png");
	}
	
	public void update(Input input, int delta)
	{
		if(input.isKeyDown(Input.KEY_UP))
		{
			this.y -= this.speed * delta;
		}
		else if(input.isKeyDown(Input.KEY_DOWN))
		{
			this.y += this.speed * delta;
		}

		if(input.isKeyDown(Input.KEY_LEFT))
		{
			this.x -= this.speed * delta;
		}
		else if(input.isKeyDown(Input.KEY_RIGHT))
		{
			this.x += this.speed * delta;
		}
	}
	
	public void render()
	{
		float x = this.x + this.getHalfWidth();
		float y = this.y + this.getHalfHeight();
		
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