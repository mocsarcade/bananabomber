package mocsarcade;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Monkey
{
	public int power;
	private float speed;
	public int bombcount;
	
	private float x, y;
	private Image image;
	private String color;
	private GameMap gamemap;
	private KeyScheme keyscheme;
	
	public Monkey(GameMap gamemap, String color)
	{
		this.gamemap = gamemap;
		
		this.color = color;
		
		Random random = new Random();
		this.x = (random.nextInt(9) + 1) * Tile.WIDTH * 2 - (Tile.WIDTH * 0.5f);
		this.y = (random.nextInt(7) + 1) * Tile.WIDTH * 2 - (Tile.WIDTH * 0.5f);
		this.gamemap.getTile(this.x, this.y).explode(Direction.ALL, 2, false);
		this.gamemap.getTile(this.x, this.y).explode(Direction.ALL, 2, false);
		this.speed = 0.25f;
		this.power = 2;
		this.bombcount = 1;
		this.image = Monkey.images.get(this.color);
		this.keyscheme = new KeyScheme(this.color);
	}
	
	public void update(Input input, int delta)
	{
		float step = this.getSpeed();
		
		if(input.isKeyDown(this.keyscheme.moveNorth))
		{
			Rectangle hitbox = this.getHitbox();
			hitbox.translate(0, (int)((step+1)*-1));
			if(this.gamemap.canMoveHere(hitbox))
			{
				this.y -= step;
			}
		}
		
		if(input.isKeyDown(this.keyscheme.moveSouth))
		{
			Rectangle hitbox = this.getHitbox();
			hitbox.translate(0, (int)(step+1));
			if(this.gamemap.canMoveHere(hitbox))
			{
				this.y += step;
			}
		}
		
		if(input.isKeyDown(this.keyscheme.moveEast))
		{
			Rectangle hitbox = this.getHitbox();
			hitbox.translate((int)(step+1), 0);
			if(this.gamemap.canMoveHere(hitbox))
			{
				this.x += step;
			}
		}

		if(input.isKeyDown(this.keyscheme.moveWest))
		{
			Rectangle nextHitbox = this.getHitbox();
			nextHitbox.translate((int)((step+1)*-1), 0);
			if(this.gamemap.canMoveHere(nextHitbox))
			{
				this.x -= step;
			}
			
			//if(this.gamemap.getTiles(this.getHitbox()).equals(this.gamemap.getTiles(nextHitbox))
		}
		
		if(input.isKeyDown(this.keyscheme.dropBomb))
		{
			Tile tile = this.gamemap.getTile(this.x, this.y);
			
			if(!tile.hasBomb())
			{
				if(this.bombcount > 0)
				{
					tile.addBomb(new Bomb(tile, this.power, this));
					this.bombcount -= 1;
				}
			}
		}
	}

	public void render(Graphics graphics)
	{
		float x = this.getX() - (this.getWidth() / 2);
		float y = this.getY() - (this.getHeight() / 2);
		
		//this.image.draw(x, y);
		
		Rectangle hitbox = this.getHitbox();
		graphics.setColor(Color.orange);
		graphics.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
	}
	
	public float getX()
	{
		return this.x;
	}
	
	public float getY()
	{
		return this.y;
	}

	public int getTileyX()
	{
		return (int)(Math.floor(this.getX() / Tile.WIDTH)); 
	}

	public int getTileyY()
	{
		return (int)(Math.floor(this.getY() / Tile.HEIGHT)); 
	}
	
	public int getWidth()
	{
		return Monkey.WIDTH;
	}
	
	public int getHeight()
	{
		return Monkey.HEIGHT;
	}
	
	public int getHitboxWidth()
	{
		return Monkey.HITBOX_WIDTH;
	}
	
	public int getHitboxHeight()
	{
		return Monkey.HITBOX_HEIGHT;
	}
	
	public Rectangle getHitbox()
	{
		int x = (int)(this.getX() - (this.getHitboxWidth() / 2));
		int y = (int)(this.getY() - (this.getHitboxHeight() / 4));
		
		int width = this.getHitboxWidth();
		int height = this.getHitboxHeight();
		
		return new Rectangle(x, y, width, height);
	}

	public String getColor()
	{
		return this.color;
	}
	
	private float getSpeed()
	{
		return this.speed;
	}
	
	private final static int WIDTH = 38;
	private final static int HEIGHT = 38;
	private final static int HITBOX_WIDTH = 11;
	private final static int HITBOX_HEIGHT = 22;
	private final static int HITBOX_OFFSET = 5;
	
	public static HashMap<String, Image> images = new HashMap<String, Image>();
}