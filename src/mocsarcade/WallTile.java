package mocsarcade;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class WallTile extends Tile
{
	public WallTile(GameMap gamemap, int tx, int ty)
	{
		super(gamemap, tx, ty);
		
		this.color = Color.darkGray;
	}

	public boolean canMoveHere()
	{
		return false;
	}
	
	public boolean canExplode()
	{
		return false;
	}
}