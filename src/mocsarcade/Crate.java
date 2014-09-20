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
		float x = this.tile.getX() + 5;
		float y = this.tile.getY() + 5;
		float width = this.tile.getWidth() - 10;
		float height = this.tile.getHeight() - 10;
		
		x -= 5; y -= 5; width += 10; height += 10;
		
		graphics.setColor(this.color);
		graphics.fillRoundRect(x, y, width, height, 5);
	}
	
	private static Color[] colors = {
		new Color(204, 119, 34),
		new Color(184, 115, 51),
		new Color(205, 127, 50)
	};
}