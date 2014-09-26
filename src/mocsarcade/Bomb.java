package mocsarcade;

import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.openal.Audio;

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
		
		Bomb.sounds.get("drop").playAsSoundEffect(1f, 1f, false);
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
		float x = this.tile.getX() + this.tile.getWidth() * 0.125f;
		float y = this.tile.getY() + this.tile.getHeight() * 0.125f;
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
		Bomb.sounds.get("explosion " + (Game.randomness.nextInt(3)+1)).playAsSoundEffect(0.9f, 1f, false);
	}
	
	public static HashMap<String, Audio> sounds = new HashMap<String, Audio>();
}