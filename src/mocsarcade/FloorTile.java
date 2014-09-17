package mocsarcade;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class FloorTile extends Tile
{
	private Crate crate;
	
	public FloorTile(GameMap gamemap, int tx, int ty)
	{
		super(gamemap, tx, ty);
		this.color = Color.gray;
		this.crate = new Crate(this);
	}
	
	public void render(Graphics graphics)
	{
		super.render(graphics);
		
		if(this.crate != null)
		{
			this.crate.render(graphics);
		}
	}

	public boolean isPassable()
	{
		return true;
	}
	
	public boolean canExplode()
	{
		return true;
	}
}
