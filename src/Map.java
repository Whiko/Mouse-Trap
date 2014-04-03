import java.io.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Map 
{
	private int[][] carte;
	private Image mur; 
	private Configuration config = new Configuration("config/config_map.txt");
	private int height, width, tailleMur, ecartX, ecartY;
	
	public Map(String path) throws SlickException, IOException
	{	
		height = config.getValeur("height");
		width = config.getValeur("width");
		tailleMur = config.getValeur("tailleMur");
		ecartX = config.getValeur("ecartX");
		ecartY = config.getValeur("ecartY");
		mur = new Image("sprites/menu/mur.png");
		carte = new int[height][width];	//taille de la map
		int c;
		
		FileInputStream fichier = new FileInputStream(path); 
		//on lit chaque caractère du fichier .txt qui contient la map et on les stocke de le tableau carte[][]
		for(int i=0; i<height; i++)
		{
			for(int j=0; j<width; j++)
			{
				c = fichier.read();
				if(c != 10) 	//'10' = retour a la ligne
				{
					carte[i][j] = c;
				}
			}		
		}
		fichier.close();
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int[][] getCarte()
	{
		return carte;
	}
	
	public void afficheMap(Graphics fenetre) throws IOException
	{		
		for(int i=0; i<height; i++)
		{
			for(int j=0; j<width; j++)
			{
				if (carte[i][j] == '1')		//pour chaque caractère '1', on affiche la texture du mur
					fenetre.drawImage(mur, j*tailleMur+ecartX, i*tailleMur+ecartY);
			}
		}		
	}
}