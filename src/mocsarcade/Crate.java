package mocsarcade;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Crate
{
	private Tile tile;
	private Color color;

	public Crate(Tile tile)
	{
		this.tile = tile;
		this.color = Crate.colors[new Random().nextInt(colors.length)];
	}
	
	public void render(Graphics graphics)
	{
		float x = this.tile.getX() + 1 - (Tile.WIDTH / 2);
		float y = this.tile.getY() + 1 + (Tile.HEIGHT / 2);
		float width = this.tile.getWidth() - 2;
		float height = this.tile.getHeight() - 2;
		
		graphics.setColor(this.color);
		graphics.fillRoundRect(x, y, width, height, 8);
	}
	
	private static Color[] colors = {
		new Color(204, 119, 34),
		new Color(184, 115, 51),
		new Color(205, 127, 50)
	};
}