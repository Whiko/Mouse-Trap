import java.io.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Map 
{
	private int[][] carte;
	private Image mur; 
	private GestionConstantes x = new GestionConstantes("config/config_map.txt");	//va chercher la largeur de la map x dans le fichier .txt
	private GestionConstantes y = new GestionConstantes("config/config_map.txt");	//va chercher la hauteur de la map y dans le fichier .txt
	private GestionConstantes tMur = new GestionConstantes("config/config_map.txt"); //taille du mur
	private GestionConstantes ecX = new GestionConstantes("config/config_map.txt");	//ecart en x entre la fenetre et la map
	private GestionConstantes ecY = new GestionConstantes("config/config_map.txt");	//ecart en y entre le fenetre et la map
	private int height, width, tailleMur, ecartX, ecartY;

	
	public Map(String path) throws SlickException, IOException
	{	
		height = x.getValeur("height");
		width = y.getValeur("width");
		tailleMur = tMur.getValeur("tailleMur");
		ecartX = ecX.getValeur("ecartX");
		ecartY = ecY.getValeur("ecartY");
		mur = new Image("mur.png");
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