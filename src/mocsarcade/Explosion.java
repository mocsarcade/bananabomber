package mocsarcade;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Explosion
{
	private Tile tile;
	private int timer;
	
	public Explosion(Tile tile, int power)
	{
		this.tile = tile;
		this.timer = 255 + (power * 50);
	}
	
	public void update(int delta)
	{
		this.timer -= delta;
	}
	
	public void render(Graphics graphics)
	{
		float x = this.tile.getX() - (Tile.WIDTH / 2);
		float y = this.tile.getY() + (Tile.HEIGHT / 2);
		float width = this.tile.getWidth();
		float height = this.tile.getHeight();
		Color color = new Color(255, 0, 0, this.timer);
		
		graphics.setColor(color);
		graphics.fillRect(x, y, width, height);
	}
	
	public boolean isDone()
	{
		return this.timer <= 0;
	}
}