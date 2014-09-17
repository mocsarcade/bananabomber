package mocsarcade;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Bomb
{
	private Tile tile;
	private int timer;
	private Direction direction;
	private int power;
	
	public Bomb(Tile tile, int power)
	{
		this.tile = tile;
		this.timer = 3 * 1000;
		this.direction = Direction.ALL;
		this.power = power;
	}
	
	public void update(int delta)
	{
		this.timer -= delta;
		
		if(this.timer < 0)
		{
			this.tile.explode(this.direction, this.power);
		}
	}
	
	public void render(Graphics graphics)
	{
		float x = this.tile.getX() + this.tile.getWidth() * 0.125f;
		float y = this.tile.getY() + this.tile.getHeight() * 0.125f;;
		float width = this.tile.getWidth() * 0.75f;
		float height = this.tile.getHeight() * 0.75f;
		Color color = new Color(this.timer % 255, 0, 0);
		
		graphics.setColor(color);
		graphics.fillOval(x, y, width, height);
	}
}