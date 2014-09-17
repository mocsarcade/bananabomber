package mocsarcade;

public class Gamemap
{
	private int UNIT = 48;
	
	public boolean isNotBlocked(float x, float y)
	{
		if(x < UNIT || x > Game.WIDTH - UNIT
		|| y < UNIT || y > Game.HEIGHT - UNIT)
		{
			return false;
		}
		
		if(Math.floor(x / UNIT) % 2 == 0
		&& Math.floor(y / UNIT) % 2 == 0)
		{
			return false;
		}
		
		//if(is a bomb here
		//or a crate here)
		
		return true;
	}
}