package mocsarcade;

import java.awt.Rectangle;
import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class GameMap
{
	private Game game;
	
	private static int TILEY_WIDTH = Game.WIDTH / Tile.WIDTH;
	private static int TILEY_HEIGHT = Game.HEIGHT / Tile.HEIGHT;
	
	private Tile[][] tiles = new Tile[GameMap.TILEY_WIDTH][GameMap.TILEY_HEIGHT];
	private LinkedList<Monkey> monkies = new LinkedList<Monkey>();

	private int gameoverTimer;
	private String gameoverMessage;
	
	public GameMap(Game game)
	{
		this.game = game;
		
		for(int tx = 0; tx < GameMap.TILEY_WIDTH; tx++)
		{
			for(int ty = 0; ty < GameMap.TILEY_HEIGHT; ty++)
			{
				if((tx < 1 || tx >= this.getTileyWidth() - 1
				|| ty < 1 || ty >= this.getTileyHeight() - 1)
				|| (tx % 2 == 0 && ty % 2 == 0))
				{
					this.tiles[tx][ty] = new WallTile(this, tx, ty);
				}
				else
				{
					this.tiles[tx][ty] = new FloorTile(this, tx, ty);
				}
			}
		}

		this.monkies.add(new Monkey(this, "red"));
		this.monkies.add(new Monkey(this, "green"));
	}
	
	public void update(Input input, int delta)
	{
		for(int tx = 0; tx < GameMap.TILEY_WIDTH; tx++)
		{
			for(int ty = 0; ty < GameMap.TILEY_HEIGHT; ty++)
			{
				this.tiles[tx][ty].update(delta);
			}
		}
		
		for(Monkey monkey : this.getMonkies())
		{
			monkey.update(input, delta);
		}
		
		if(this.gameoverMessage != null)
		{
			this.gameoverTimer -= delta;
			System.out.println(gameoverMessage);
			
			if(this.gameoverTimer <= 0)
			{
				this.game.initiate();
			}
		}
		else
		{
			if(this.monkies.size() == 0)
			{
				this.gameoverTimer = 3 * 1000;
				this.gameoverMessage = "Everyone loses!";
			}
			else if(this.monkies.size() == 1)
			{
				this.gameoverTimer = 3 * 1000;
				this.gameoverMessage = this.monkies.get(0).getColor() + " wins!";
			}
		}
	}
	
	public void render(Graphics graphics)
	{
		for(int tx = 0; tx < GameMap.TILEY_WIDTH; tx++)
		{
			for(int ty = 0; ty < GameMap.TILEY_HEIGHT; ty++)
			{
				this.tiles[tx][ty].render(graphics);
			}
		}
		
		for(Monkey monkey : this.getMonkies())
		{
			monkey.render(graphics);
		}
		
		if(this.gameoverMessage != null)
		{
			graphics.setColor(Color.white);
			graphics.drawString(this.gameoverMessage, 48, Game.HEIGHT - 28);
		}
	}
	
	public Tile getTile(int tx, int ty)
	{
		return this.tiles[tx][ty];
	}
	
	public Tile getTile(float x, float y)
	{
		int tx = (int)(Math.floor(x / Tile.WIDTH));
		int ty = (int)(Math.floor(y / Tile.HEIGHT));
		
		return this.tiles[tx][ty];
	}

	public LinkedList<Tile> getTiles(Rectangle hitbox)
	{
		LinkedList<Tile> tiles = new LinkedList<Tile>();
		
		for(int tx = hitbox.x / Tile.WIDTH; tx <= (hitbox.x + hitbox.width) / Tile.WIDTH; tx += 1)
		{
			for(int ty = hitbox.y / Tile.HEIGHT; ty <= (hitbox.y + hitbox.height) / Tile.HEIGHT; ty += 1)
			{
				tiles.add(this.tiles[tx][ty]);
			}
		}
		
		return tiles;
	}
	
	public int getTileyWidth()
	{
		return GameMap.TILEY_WIDTH;
	}
	
	public int getTileyHeight()
	{
		return GameMap.TILEY_HEIGHT;
	}
	
	public boolean canMoveHere(Rectangle initialPosition, Direction direction, float velocity)
	{
		Rectangle finalPosition = new Rectangle(initialPosition);

		if(direction == Direction.NORTH)
		{
			finalPosition.translate(0, (int)((velocity+1)*-1));
		}
		else if(direction == Direction.SOUTH)
		{
			finalPosition.translate(0, (int)(velocity+1));
		}
		else if(direction == Direction.EAST)
		{
			finalPosition.translate((int)(velocity+1), 0);
		}
		else if(direction == Direction.WEST)
		{
			finalPosition.translate((int)((velocity+1)*-1), 0);
		}
		
		LinkedList<Tile> initialPositionTiles = this.getTiles(initialPosition);
		LinkedList<Tile> finalPositionTiles = this.getTiles(finalPosition);
		
		for(Tile finalPositionTile : finalPositionTiles)
		{
			if(!initialPositionTiles.contains(finalPositionTile))
			{
				if(finalPositionTile.canMoveHere() == false)
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	public LinkedList<Monkey> getMonkies()
	{
		return this.monkies;
	}

	public void removeMonkey(Monkey monkey)
	{
		this.monkies.remove(monkey);
	}
}