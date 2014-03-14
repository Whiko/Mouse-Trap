import java.io.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Map 
{
	private int[][] carte;
	private Image mur; 
	
	public Map(String path) throws SlickException, IOException
	{
		mur = new Image("mur.png");
		carte = new int[20][20];	//taille de la map

		int c;
		int x = 20;
		int y = 20;
		FileInputStream fichier = new FileInputStream(path); 
		for(int i=0; i<x; i++)
		{
			for(int j=0; j<y; j++)
			{
				c = fichier.read();
				if(c != 10) //'10' = retour a la ligne
				{
					carte[i][j] = c;
				}
			}		
		}
		fichier.close();
	}
	
	public void afficheMap(Graphics fenetre) throws IOException
	{
		int x = 20;
		int y = 20;
		
		for(int i=0; i<x; i++)
		{
			for(int j=0; j<y; j++)
			{
				if (carte[i][j] == '1')
					fenetre.drawImage(mur, j*16, i*16);
			}
		}		
	}	
}