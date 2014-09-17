package mocsarcade;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class GameMap
{
	private static int TILEY_WIDTH = Game.WIDTH / Tile.SIZE;
	private static int TILEY_HEIGHT = Game.HEIGHT / Tile.SIZE;
	
	private Tile[][] tiles = new Tile[GameMap.TILEY_WIDTH][GameMap.TILEY_HEIGHT];
	
	public GameMap() throws SlickException
	{
		for(int tx = 0; tx < GameMap.TILEY_WIDTH; tx++)
		{
			for(int ty = 0; ty < GameMap.TILEY_HEIGHT; ty++)
			{
				if((tx < 1 || tx >= this.getTileyWidth() - 1
				|| ty < 1 || ty >= this.getTileyHeight() - 1)
				|| (tx % 2 == 0 && ty % 2 == 0))
				{
					this.tiles[tx][ty] = new WallTile(tx, ty);
				}
				else
				{
					this.tiles[tx][ty] = new FloorTile(tx, ty);
				}
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
	}
	
	public Tile getTile(int tx, int ty)
	{
		return this.tiles[tx][ty];
	}
	
	public Tile getTile(float x, float y)
	{
		int tx = (int)(Math.floor(x / Tile.SIZE));
		int ty = (int)(Math.floor(y / Tile.SIZE));
		
		return this.tiles[tx][ty];
	}
	
	public int getTileyWidth()
	{
		return GameMap.TILEY_WIDTH;
	}
	
	public int getTileyHeight()
	{
		return GameMap.TILEY_HEIGHT;
	}
}