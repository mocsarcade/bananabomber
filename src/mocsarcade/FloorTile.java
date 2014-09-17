package mocsarcade;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class FloorTile extends Tile
{
	public FloorTile(int tx, int ty)
	{
		super(tx, ty);
		
		this.color = Color.gray;
	}

	public boolean isPassable()
	{
		return true;
	}
}
