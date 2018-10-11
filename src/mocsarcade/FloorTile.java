package mocsarcade;

import org.newdawn.slick.Color;

public class FloorTile extends Tile
{
	public FloorTile(GameMap gamemap, int tx, int ty)
	{
		super(gamemap, tx, ty);
		this.color = new Color(200, 175, 150);
		this.crate = new Crate(this);
	}

	public boolean canMoveHere()
	{
		if(this.crate != null
		|| this.bomb != null)
		{
			return false;
		}
		
		return true;
	}
	
	public boolean canExplode()
	{
		return true;
	}
}
