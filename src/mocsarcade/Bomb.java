package mocsarcade;

import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Bomb
{
	private Tile tile;
	private int timer;
	private int intensity;
	private Monkey monkey;
	
	public Bomb(Monkey monkey, Tile tile, int intensity)
	{
		this.monkey = monkey;
		this.tile = tile;
		this.intensity = intensity;
		this.timer = 3 * 1000;
		
		Game.assets.getSound("./res/bomb.drop.wav").play();
	}
	
	public void update(int delta)
	{
		this.timer -= delta;
		
		if(this.timer < 0)
		{
			this.explode();
		}
	}
	
	public void render(Graphics graphics)
	{
		float x = this.tile.getX() + this.tile.getWidth() * 0.125f - (Tile.WIDTH / 2);
		float y = this.tile.getY() + this.tile.getHeight() * 0.125f + (Tile.HEIGHT / 2);
		float width = this.tile.getWidth() * 0.75f;
		float height = this.tile.getHeight() * 0.75f;
		Color color = new Color(this.timer % 255, 0, 0);
		
		graphics.setColor(color);
		graphics.fillOval(x, y, width, height);
	}
	
	public void explode()
	{
		this.monkey.bombCapacity += 1;
		this.tile.bomb = null;
		this.tile.explode(Direction.ALL, this.intensity, true);
		
		Game.assets.getSound("./res/bomb.explosion." + (Game.randomness.nextInt(3) + 1) + ".wav").play();
	}
}