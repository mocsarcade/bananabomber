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
	private float x, y;
	private float dx, dy;
	
	private GameMap gamemap;
	
	private float acceleration = 0.1f;
	private float deacceleration = 0.0075f;
	
	private Image image;
	private String name;
	private Color color;
	private KeyScheme keyscheme;
	
	public int bombCapacity = 1;
	public int bombIntensity = 2;
	
	public Monkey(GameMap gamemap, String name)
	{
		this.gamemap = gamemap;
		this.name = name;
		
		this.x = (Game.randomness.nextInt(9) + 1) * Tile.WIDTH * 2 - (Tile.WIDTH * 0.5f);
		this.y = (Game.randomness.nextInt(7) + 1) * Tile.WIDTH * 2 - (Tile.WIDTH * 0.5f);
		this.gamemap.getTile(this.x, this.y).explode(Direction.ALL, 2, false);
		this.gamemap.getTile(this.x, this.y).explode(Direction.ALL, 2, false);
		
		if(this.name == "red")
		{
			this.color = Color.red;
			this.keyscheme = new KeyScheme("red");
			this.image = Game.assets.getImage("./res/red.monkey.png");
		}
		else if(this.name == "green")
		{
			this.color = Color.green;
			this.keyscheme = new KeyScheme("green");
			this.image = Game.assets.getImage("./res/green.monkey.png");
		}
	}
	
	public void update(Input input, int delta)
	{
		if(input.isKeyDown(this.keyscheme.moveNorth))
		{
			this.dy = -this.getSpeed(delta);
		}
		
		if(input.isKeyDown(this.keyscheme.moveSouth))
		{
			this.dy = this.getSpeed(delta);
		}
		
		if(input.isKeyDown(this.keyscheme.moveEast))
		{
			this.dx = this.getSpeed(delta);
		}

		if(input.isKeyDown(this.keyscheme.moveWest))
		{
			this.dx = -this.getSpeed(delta);
		}

		if(this.gamemap.canMoveHere(this.getHitbox(), dx, 0))
		{
			this.x += this.dx;
		}

		if(this.gamemap.canMoveHere(this.getHitbox(), 0, dy))
		{
			this.y += this.dy;
		}
		
		if(this.dx < 0)
		{
			this.dx += this.deacceleration * delta;
			if(this.dx > 0) {this.dx = 0;}
		}
		if(this.dx > 0)
		{
			this.dx -= this.deacceleration * delta;
			if(this.dx < 0) {this.dx = 0;}
		}
		if(this.dy < 0)
		{
			this.dy += this.deacceleration * delta;
			if(this.dy > 0) {this.dy = 0;}
		}
		if(this.dy > 0)
		{
			this.dy -= this.deacceleration * delta;
			if(this.dy < 0) {this.dy = 0;}
		}
		
		if(input.isKeyDown(this.keyscheme.dropBomb))
		{
			Tile tile = this.gamemap.getTile(this.x, this.y);
			
			if(tile.bomb == null)
			{
				if(this.bombCapacity > 0)
				{
					tile.bomb = new Bomb(this, tile, this.bombIntensity);
					this.bombCapacity -= 1;
				}
			}
		}
	}

	public void render(Graphics graphics)
	{
		float x = this.getX() - (this.getWidth() / 2) - (Tile.WIDTH / 2);
		float y = this.getY() - (this.getHeight() / 2) + (Tile.HEIGHT / 2);
		
		this.image.draw(x, y);
		
		graphics.setColor(this.color);
		String status = this.name + " monkey: " + this.bombCapacity + " bombs with " + this.bombIntensity + " power";
		if(this.name == "red")
		{
			graphics.drawString(status, 38, 12);
		}
		else if(this.name == "green")
		{
			graphics.drawString(status, 38, 28);
		}
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

	public String getName()
	{
		return this.name;
	}
	
	public Color getColor()
	{
		return this.color;
	}
	
	private float getSpeed(float delta)
	{
		return this.acceleration * delta;
	}
	
	private final static int WIDTH = 38;
	private final static int HEIGHT = 38;
	private final static int HITBOX_WIDTH = 11;
	private final static int HITBOX_HEIGHT = 22;
	
	public static HashMap<String, Image> images = new HashMap<String, Image>();
}