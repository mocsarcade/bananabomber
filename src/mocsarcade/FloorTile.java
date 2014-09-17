package mocsarcade;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class FloorTile extends Tile
{
	public FloorTile(GameMap gamemap, int tx, int ty)
	{
		super(gamemap, tx, ty);
		
		this.color = Color.gray;
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
