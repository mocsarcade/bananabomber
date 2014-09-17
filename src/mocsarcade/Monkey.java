package mocsarcade;

import java.awt.Rectangle;
import java.util.Random;

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
	private String color;
	private KeyScheme keyscheme;
	
	public Monkey(GameMap gamemap, String color) throws SlickException
	{
		this.gamemap = gamemap;
		this.color = color;
		Random random = new Random();
		this.x = (random.nextInt(9) + 1) * Tile.WIDTH * 2 - (Tile.WIDTH * 0.5f);
		this.y = (random.nextInt(7) + 1) * Tile.WIDTH * 2 - (Tile.WIDTH * 0.5f);
		this.gamemap.getTile(this.x, this.y).explode(Direction.ALL, 2, false);
		this.gamemap.getTile(this.x, this.y).explode(Direction.ALL, 2, false);
		this.speed = 0.25f;
		this.power = 3;
		this.image = new Image("./res/" + this.color + ".monkey.png");
		this.keyscheme = new KeyScheme(this.color);
	}
	
	public void update(Input input, int delta)
	{
		float step = this.speed * delta;
		
		if(input.isKeyDown(this.keyscheme.moveNorth))
		{
			if(this.gamemap.getTile(this.x, this.y - step).isPassable())
			{
				this.y -= step;
			}
		}
		
		if(input.isKeyDown(this.keyscheme.moveSouth))
		{
			if(this.gamemap.getTile(this.x, this.y + step).isPassable())
			{
				this.y += step;
			}
		}

		if(input.isKeyDown(this.keyscheme.moveWest))
		{
			if(this.gamemap.getTile(this.x - step, this.y).isPassable())
			{
				this.x -= step;
			}
		}
		
		if(input.isKeyDown(this.keyscheme.moveEast))
		{
			if(this.gamemap.getTile(this.x + step, this.y).isPassable())
			{
				this.x += step;
			}
		}
		
		if(input.isKeyDown(this.keyscheme.dropBomb))
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

	public String getColor()
	{
		return this.color;
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