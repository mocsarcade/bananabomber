package mocsarcade;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public abstract class Tile
{
	protected int tx, ty;
	protected Color color;
	protected Bomb bomb;
	
	public Tile(int tx, int ty)
	{
		this.tx = tx;
		this.ty = ty;
	}
	
	public void render(Graphics graphics)
	{
		graphics.setColor(this.color);
		graphics.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		
		if(this.bomb != null)
		{
			this.bomb.render(graphics);
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
	
	public void add(Bomb bomb)
	{
		this.bomb = bomb;
	}
	
	public boolean isPassable()
	{
		return true;
	}
	
	public static final int SIZE = 40;
}