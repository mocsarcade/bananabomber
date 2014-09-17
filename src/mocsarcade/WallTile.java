package mocsarcade;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class WallTile extends Tile
{
	public WallTile(int tx, int ty)
	{
		super(tx, ty);
		
		this.color = Color.darkGray;
	}

	public boolean isPassable()
	{
		return false;
	}
}