package mocsarcade;

import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public abstract class Tile
{
	protected GameMap gamemap;
	protected int tx, ty;
	protected Color color;
	protected Bomb bomb;
	protected Explosion explosion;
	protected Rectangle rectangle;
	
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
	}
	
	public void render(Graphics graphics)
	{
		float x = this.getX();
		float y = this.getY();
		float width = this.getWidth();
		float height = this.getHeight();
		
		graphics.setColor(this.color);
		graphics.fillRect(x, y, width, height);
		
		if(this.bomb != null)
		{
			this.bomb.render(graphics);
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
		return Tile.SIZE;
	}
	
	public int getHeight()
	{
		return Tile.SIZE;
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

	public void explode(Direction direction, int power)
	{
		if(power > 0)
		{
			if(this.canExplode())
			{
				this.explosion = new Explosion(this, power);
				
				if(this.hasBomb())
				{
					this.removeBomb();
				}
				
				if(direction == Direction.NORTH || direction == Direction.ALL)
				{
					this.gamemap.getTile(this.tx, this.ty - 1).explode(Direction.NORTH, power - 1);
				}
				if(direction == Direction.SOUTH || direction == Direction.ALL)
				{
					this.gamemap.getTile(this.tx, this.ty + 1).explode(Direction.SOUTH, power - 1);
				}
				if(direction == Direction.WEST || direction == Direction.ALL)
				{
					this.gamemap.getTile(this.tx - 1, this.ty).explode(Direction.WEST, power - 1);
				}
				if(direction == Direction.EAST || direction == Direction.ALL)
				{
					this.gamemap.getTile(this.tx + 1, this.ty).explode(Direction.EAST, power - 1);
				}
				
				for(Monkey monkey : this.gamemap.getMonkies())
				{
					if(monkey.getRectangle().intersects(this.getRectangle()))
					{
						this.gamemap.removeMonkey(monkey);
					}
				}
			}
		}
	}
	
	public abstract boolean isPassable();
	public abstract boolean canExplode();
	
	public static final int SIZE = 40;
}