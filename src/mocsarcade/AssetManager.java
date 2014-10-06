package mocsarcade;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;

public class AssetManager
{
	private HashMap<String, Image> images = new HashMap<String, Image>();
	private HashMap<String, Sound> sounds = new HashMap<String, Sound>();
	private HashMap<String, Music> musics = new HashMap<String, Music>();
	
	public Image getImage(String source)
	{
		try
		{
			if(this.images.get(source) == null)
			{
				this.images.put(source, new Image(source));
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
		return this.images.get(source);
	}
	
	public Sound getSound(String source)
	{
		try
		{
			if(this.sounds.get(source) == null)
			{
				this.sounds.put(source, new Sound(source));
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
		return this.sounds.get(source);
	}
	
	public Music getMusic(String source)
	{
		try
		{
			if(this.musics.get(source) == null)
			{
				this.musics.put(source, new Music(source));
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
		return this.musics.get(source);
	}
}