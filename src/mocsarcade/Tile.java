package mocsarcade;

import java.awt.Rectangle;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public abstract class Tile
{
	protected GameMap gamemap;
	protected int tx, ty;
	protected Color color;
	protected Bomb bomb;
	protected Crate crate;
	protected Explosion explosion;
	protected Rectangle rectangle;
	public Banana powerup;
	
	public Tile(GameMap gamemap, int tx, int ty)
	{
		this.gamemap = gamemap;
		this.tx = tx;
		this.ty = ty;

		int x = this.getX();
		int y = this.getY();
		int width = this.getWidth();
		int height = this.getHeight();
		this.rectangle = new Rectangle(x, y, width, height); 
	}
	
	public void update(int delta)
	{
		if(this.bomb != null)
		{
			this.bomb.update(delta);
		}
		
		if(this.explosion != null)
		{
			this.explosion.update(delta);
			
			if(this.explosion.isDone())
			{
				this.explosion = null;
			}
		}
		
		if(this.powerup != null)
		{
			this.powerup.update(delta);
		}
	}
	
	public void render(Graphics graphics)
	{
		float x = this.getX() - (Tile.WIDTH / 2);
		float y = this.getY() + (Tile.HEIGHT / 2);
		float width = this.getWidth();
		float height = this.getHeight();
		
		graphics.setColor(this.color);
		graphics.fillRect(x, y, width, height);
		
		if(this.bomb != null)
		{
			this.bomb.render(graphics);
		}
		
		if(this.crate != null)
		{
			this.crate.render(graphics);
		}
		
		if(this.powerup != null)
		{
			this.powerup.render(graphics);
		}
		
		if(this.explosion != null)
		{
			this.explosion.render(graphics);
		}
	}
	
	public int getX()
	{
		return this.getTileyX() * this.getWidth();
	}
	
	public int getY()
	{
		return this.getTileyY() * this.getHeight();
	}
	
	public int getTileyX()
	{
		return this.tx;
	}
	
	public int getTileyY()
	{
		return this.ty;
	}
	
	public int getWidth()
	{
		return Tile.WIDTH;
	}
	
	public int getHeight()
	{
		return Tile.HEIGHT;
	}
	
	public Rectangle getRectangle()
	{
		return this.rectangle;
	}
	
	public void addBomb(Bomb bomb)
	{
		this.bomb = bomb;
	}

	public boolean hasBomb()
	{
		return this.bomb != null;
	}
	
	public void removeBomb()
	{
		this.bomb = null;
	}

	public void explode(Direction direction, int power, boolean active)
	{
		if(power > 0)
		{
			if(this.canExplode())
			{
				if(active)
				{
					this.explosion = new Explosion(this, power);
					
					for(Monkey monkey : this.gamemap.getMonkies())
					{
						if(monkey.getHitbox().intersects(this.getRectangle()))
						{
							this.gamemap.removeMonkey(monkey);
						}
					}
				}
				
				if(this.bomb != null)
				{
					this.bomb.explode();
					this.bomb = null;
				}
				
				if(this.powerup != null)
				{
					this.powerup = null;
				}
				
				if(this.crate != null)
				{
					this.crate = null;
					power = 0;
					
					if(active)
					{
						if(new Random().nextInt(10) <= 3)
						{
							this.powerup = new Banana(this);
						}
					}
				}
				
				if(direction == Direction.NORTH || direction == Direction.ALL)
				{
					this.gamemap.getTile(this.tx, this.ty - 1).explode(Direction.NORTH, power - 1, active);
				}
				if(direction == Direction.SOUTH || direction == Direction.ALL)
				{
					this.gamemap.getTile(this.tx, this.ty + 1).explode(Direction.SOUTH, power - 1, active);
				}
				if(direction == Direction.WEST || direction == Direction.ALL)
				{
					this.gamemap.getTile(this.tx - 1, this.ty).explode(Direction.WEST, power - 1, active);
				}
				if(direction == Direction.EAST || direction == Direction.ALL)
				{
					this.gamemap.getTile(this.tx + 1, this.ty).explode(Direction.EAST, power - 1, active);
				}
			}
		}
	}
	
	public abstract boolean canMoveHere();
	public abstract boolean canExplode();

	public static final int WIDTH = 40;
	public static final int HEIGHT = 40;
}