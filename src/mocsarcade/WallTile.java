package mocsarcade;

import org.newdawn.slick.Color;

public class WallTile extends Tile
{
	public WallTile(GameMap gamemap, int tx, int ty)
	{
		super(gamemap, tx, ty);
		
		this.color = new Color(80, 80, 80);
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