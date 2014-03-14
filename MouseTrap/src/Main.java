import java.io.IOException;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Main 
{
	public static void main(String[] args) throws IOException, SlickException 
	{
		Map carte = new Map("map/Map_lvl1.txt");
		Graphics g = null;
		carte.afficheMap(g);
	}

}
