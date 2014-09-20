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
	private float dx, dy;
	private Image image;
	private String color;
	private GameMap gamemap;
	private KeyScheme keyscheme;
	private float deacceleration = 0.002f;
	private float maximumSpeed = 0.2f;
	
	public Monkey(GameMap gamemap, String color)
	{
		this.gamemap = gamemap;
		
		this.color = color;
		
		Random random = new Random();
		this.x = (random.nextInt(9) + 1) * Tile.WIDTH * 2 - (Tile.WIDTH * 0.5f);
		this.y = (random.nextInt(7) + 1) * Tile.WIDTH * 2 - (Tile.WIDTH * 0.5f);
		this.gamemap.getTile(this.x, this.y).explode(Direction.ALL, 2, false);
		this.gamemap.getTile(this.x, this.y).explode(Direction.ALL, 2, false);
		this.speed = 0.025f;
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
			this.dy -= this.getSpeed(delta);
			if(this.dy < -this.maximumSpeed)
				this.dy = -this.maximumSpeed;
		}
		
		if(input.isKeyDown(this.keyscheme.moveSouth))
		{
			this.dy += this.getSpeed(delta);
			if(this.dy > this.maximumSpeed)
				this.dy = this.maximumSpeed;
		}
		
		if(input.isKeyDown(this.keyscheme.moveEast))
		{
			this.dx += this.getSpeed(delta);
			if(this.dx > this.maximumSpeed)
				this.dx = this.maximumSpeed;
		}

		if(input.isKeyDown(this.keyscheme.moveWest))
		{
			this.dx -= this.getSpeed(delta);
			if(this.dx < -this.maximumSpeed)
				this.dx = -this.maximumSpeed;
		}

		if(this.gamemap.canMoveHere(this.getHitbox(), dx, 0))
		{
			this.x += this.dx;
		}
		else
		{
			this.dx = 0;
		}

		if(this.gamemap.canMoveHere(this.getHitbox(), 0, dy))
		{
			this.y += this.dy;
		}
		else
		{
			this.dy = 0;
		}
		
		if(this.dx < 0)
		{
			this.dx += this.deacceleration;
			if(this.dx > 0) {this.dx = 0;}
		}
		else if(this.dx > 0)
		{
			this.dx -= this.deacceleration;
			if(this.dx < 0) {this.dx = 0;}
		}
		if(this.dy < 0)
		{
			this.dy += this.deacceleration;
			if(this.dy > 0) {this.dy = 0;}
		}
		else if(this.dy > 0)
		{
			this.dy -= this.deacceleration;
			if(this.dy < 0) {this.dy = 0;}
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
	
	private float getSpeed(float delta)
	{
		return this.speed * delta;
	}
	
	private final static int WIDTH = 38;
	private final static int HEIGHT = 38;
	private final static int HITBOX_WIDTH = 11;
	private final static int HITBOX_HEIGHT = 22;
	private final static int HITBOX_OFFSET = 5;
	
	public static HashMap<String, Image> images = new HashMap<String, Image>();
}