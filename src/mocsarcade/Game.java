package mocsarcade;
import java.io.IOException;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Game extends BasicGame
{
	public Game()
	{
		super(Game.TITLE + " " + Game.VERSION);
	}
	
	public void init(GameContainer container)
	{
		try
		{
			Banana.images.put("intensity", new Image("./res/intensity.banana.png"));
			Banana.images.put("capacity", new Image("./res/capacity.banana.png"));
			Monkey.images.put("green", new Image("./res/green.monkey.png"));
			Monkey.images.put("red", new Image("./res/red.monkey.png"));

			Bomb.sounds.put("drop", AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("./res/bomb.drop.wav")));
			Bomb.sounds.put("explosion 1", AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("./res/bomb.explosion.1.wav")));
			Bomb.sounds.put("explosion 2", AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("./res/bomb.explosion.2.wav")));
			Bomb.sounds.put("explosion 3", AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("./res/bomb.explosion.3.wav")));
			Banana.sounds.put("powerup",  AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("./res/banana.powerup.wav")));
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		this.initiate();
	}
	
	public GameMap gamemap;
	
	public void initiate()
	{
		this.gamemap = new GameMap(this);
	}
	
	public void update(GameContainer container, int delta) throws SlickException
	{
		Input input = container.getInput();
		
		if(input.isKeyDown(Input.KEY_ESCAPE))
		{
			System.exit(0);
		}
		
		this.gamemap.update(input, delta);
	}
	
	public void render(GameContainer container, Graphics graphics) throws SlickException
	{
		this.gamemap.render(graphics);
	}
	
	public static void main(String[] args) throws SlickException
	{
		AppGameContainer container = new AppGameContainer(new Game());
		container.setDisplayMode(Game.WIDTH, Game.HEIGHT, false);
		//container.setTargetFrameRate(60);
		//container.setFullscreen(true);
		container.start();
	}
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String VERSION = "v0.0.2";
	public static final String TITLE = "Bananabomber";
	
	public static Random randomness = new Random();
}