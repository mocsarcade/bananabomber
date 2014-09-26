package mocsarcade;
import java.io.IOException;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Game extends BasicGame
{
	public static Audio music;
	
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
			
			Game.music = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("./res/music.wav"));
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
		
		Game.music.playAsMusic(1f, 1f, true);
		this.gamemap = new GameMap(this);
	}
	
	public GameMap gamemap;

	public int gameoverTimer;
	public Color gameoverColor;
	public String gameoverMessage;
	
	public void update(GameContainer container, int delta) throws SlickException
	{
		Input input = container.getInput();
		
		if(input.isKeyDown(Input.KEY_ESCAPE))
		{
			System.exit(0);
		}
		
		this.gamemap.update(input, delta);
		
		if(this.gameoverMessage != null)
		{
			this.gameoverTimer -= delta;
			
			if(this.gameoverTimer <= 0)
			{
				Game.music.stop();
				Game.music.playAsMusic(1f, 1f, true);
				
				this.gamemap = new GameMap(this);
				
				this.gameoverMessage = null;
			}
		}
		else
		{
			if(this.gamemap.monkeys.size() == 0)
			{
				this.gameoverTimer = 3 * 1000;
				this.gameoverColor = Color.white;
				this.gameoverMessage = "Everyone loses! :<";
			}
			else if(this.gamemap.monkeys.size() == 1)
			{
				this.gameoverTimer = 3 * 1000;
				this.gameoverColor = this.gamemap.monkeys.get(0).getColor();
				this.gameoverMessage = this.gamemap.monkeys.get(0).getName() + " wins! :D";
			}
		}
	}
	
	public void render(GameContainer container, Graphics graphics) throws SlickException
	{
		this.gamemap.render(graphics);
		
		if(this.gameoverMessage != null)
		{
			graphics.setColor(this.gameoverColor);
			graphics.fillRect(0, (Game.HEIGHT / 2) - 32, Game.WIDTH, 64);
			graphics.setColor(Color.black);
			graphics.drawString(this.gameoverMessage, 48, (Game.HEIGHT / 2) - 8);
		}
	}
	
	public static void main(String[] args) throws SlickException
	{
		AppGameContainer container = new AppGameContainer(new Game());
		container.setDisplayMode(Game.WIDTH, Game.HEIGHT, false);
		//container.setTargetFrameRate(60);
		container.setFullscreen(true);
		container.setShowFPS(false);
		container.start();
	}
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String VERSION = "v0.0.2";
	public static final String TITLE = "Bananabomber";
	
	public static Random randomness = new Random();
}