package mocsarcade;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame
{
	GameMap gamemap;
	
	public Game()
	{
		super(Game.TITLE + " " + Game.VERSION);
	}
	
	public void init(GameContainer container) throws SlickException
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
	
	public void reset()
	{
		try
		{
			this.gamemap = new GameMap(this);
		}
		catch(SlickException exception)
		{
			exception.printStackTrace();
		}
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
	public static final String VERSION = "v0.0.1";
	public static final String TITLE = "Bananabomber";
}