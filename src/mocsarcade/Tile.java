package mocsarcade;

import org.newdawn.slick.Graphics;

public abstract class Tile
{
	protected int tx, ty;
	
	public Tile(int tx, int ty)
	{
		this.tx = tx;
		this.ty = ty;
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
	
	public abstract void render(Graphics graphics);
	public abstract boolean isPassable();
	
	public static final int SIZE = 40;
}