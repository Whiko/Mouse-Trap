import java.io.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Map 
{
	private int[][] carte;
	private Image mur, piece; 
	private Configuration config = new Configuration("config/config_map.txt");
	private int height, width, tailleMur, ecartX, ecartY;
	

	public Map(String path) throws SlickException, IOException
	{	
		height = config.getValeur("height");		//height = 20
		width = config.getValeur("width");		//width = 35
		tailleMur = config.getValeur("tailleMur");	//16
		ecartX = config.getValeur("ecartX");	//40
		ecartY = config.getValeur("ecartY");	//100
		mur = new Image("sprites/sprites_Cyriaque/sprites_Julie/mur.png");
		piece = new Image("sprites/sprites_Cyriaque/originales/piece.png");
		carte = new int[width][height];	//taille de la map
		int c;
		
		FileInputStream fichier = new FileInputStream(path); 
		//on lit chaque caractère du fichier .txt qui contient la map et on les stocke de le tableau carte[][]

		for(int j=0; j<height; j++)	
		{
			for(int i=0; i<width; i++)
			{
				c = fichier.read();
				if(c != 10) 	//10 = '\n' =retour a la ligne
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
		for(int j=0; j<height; j++)		//j de 0 à 34
		{
			for(int i=0; i<width; i++)		//i de 0 à 19
			{
				if (carte[i][j] == '1')		//pour chaque caractère '1', on affiche la texture du mur
					fenetre.drawImage(mur, i*tailleMur+ecartX, j*tailleMur+ecartY);
			}
		}		
	}
	
	public void affichePiece(Graphics fenetre) throws IOException
	{
		for(int j=0; j<height; j++)		//j de 0 à 34
		{
			for(int i=0; i<width; i++)		//i de 0 à 19
			{
				if(carte[i][j] == '3')
					fenetre.drawImage(piece, i*tailleMur+ecartX+tailleMur/2, j*tailleMur+ecartY+tailleMur/3);
			
				/*if(carte[i][j] == '0')
					fenetre.drawImage(, i*tailleMur+ecartX+tailleMur/2, j*tailleMur+ecartY+tailleMur/3);*/
			}
		}	
	}
}
