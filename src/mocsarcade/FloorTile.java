package mocsarcade;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class FloorTile extends Tile
{
	public FloorTile(int tx, int ty)
	{
		super(tx, ty);
	}

	public void render(Graphics graphics)
	{
		graphics.setColor(Color.lightGray);
		graphics.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

	public boolean isPassable()
	{
		return true;
	}
}
