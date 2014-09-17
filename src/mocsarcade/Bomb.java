package mocsarcade;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Bomb
{
	private Tile tile;
	
	public Bomb(Tile tile)
	{
		this.tile = tile;
		this.tile.add(this);
	}
	
	public void render(Graphics graphics)
	{
		float x = this.tile.getX() + this.tile.getWidth() * 0.125f;
		float y = this.tile.getY() + this.tile.getHeight() * 0.125f;;
		float width = this.tile.getWidth() * 0.75f;
		float height = this.tile.getHeight() * 0.75f;
		
		graphics.setColor(Color.black);
		graphics.fillOval(x, y, width, height);
	}
}