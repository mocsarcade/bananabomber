package mocsarcade;

import java.awt.Rectangle;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Monkey
{
	private float x, y;
	private float speed;
	private Image image;
	private GameMap gamemap;
	private int power;
	
	public Monkey(GameMap gamemap) throws SlickException
	{
		this.x = 64;
		this.y = 64;
		this.speed = 0.25f;
		this.power = 3;
		
		this.gamemap = gamemap;
		this.image = new Image("./res/monkey.png");
	}
	
	public void update(Input input, int delta)
	{
		float step = this.speed * delta;
		
		if(input.isKeyDown(Input.KEY_UP))
		{
			if(this.gamemap.getTile(this.x, this.y - step).isPassable())
			{
				this.y -= step;
			}
		}
		
		if(input.isKeyDown(Input.KEY_DOWN))
		{
			if(this.gamemap.getTile(this.x, this.y + step).isPassable())
			{
				this.y += step;
			}
		}

		if(input.isKeyDown(Input.KEY_LEFT))
		{
			if(this.gamemap.getTile(this.x - step, this.y).isPassable())
			{
				this.x -= step;
			}
		}
		
		if(input.isKeyDown(Input.KEY_RIGHT))
		{
			if(this.gamemap.getTile(this.x + step, this.y).isPassable())
			{
				this.x += step;
			}
		}
		
		if(input.isKeyDown(Input.KEY_SPACE))
		{
			Tile tile = this.gamemap.getTile(this.x, this.y);
			
			if(!tile.hasBomb())
			{
				tile.addBomb(new Bomb(tile, this.power));
			}
		}
	}
	
	public void render(Graphics graphics)
	{
		float x = this.getX() - this.getHalfWidth();
		float y = this.getY() - this.getHalfHeight();
		
		this.image.draw(x, y);
	}
	
	public float getX()
	{
		return this.x;
	}
	
	public float getY()
	{
		return this.y;
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
	
	public Rectangle getRectangle()
	{
		int x = (int)(this.getX() - this.getHalfWidth());
		int y = (int)(this.getY() - this.getHalfHeight());
		int width = this.getWidth();
		int height = this.getHeight();
		
		return new Rectangle(x, y, width, height);
	}
}