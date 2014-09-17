package mocsarcade;

import org.newdawn.slick.Input;

public class KeyScheme
{
	public int moveNorth;
	public int moveSouth;
	public int moveWest;
	public int moveEast;
	public int dropBomb;
	
	public KeyScheme(String color)
	{
		if(color == "red")
		{
			this.moveNorth = Input.KEY_UP;
			this.moveSouth = Input.KEY_DOWN;
			this.moveWest = Input.KEY_LEFT;
			this.moveEast = Input.KEY_RIGHT;
			this.dropBomb = Input.KEY_SPACE;
		}
		else if(color == "green")
		{
			this.moveNorth = Input.KEY_W;
			this.moveSouth = Input.KEY_S;
			this.moveWest = Input.KEY_A;
			this.moveEast = Input.KEY_D;
			this.dropBomb = Input.KEY_E;
		}
	}
}