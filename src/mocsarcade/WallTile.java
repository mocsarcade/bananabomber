package mocsarcade;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

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