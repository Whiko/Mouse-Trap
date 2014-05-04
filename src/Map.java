import java.io.*;

import org.newdawn.slick.Graphics;


public class Map implements Serializable
{
	private int[][] carte;
	private Configuration config = new Configuration("config/config_map.txt");
	private int height, width, tailleMur, ecartX, ecartY, timer;
	

	public Map(String path) throws IOException
	{	
		height = config.getValeur("height");		
		width = config.getValeur("width");		
		tailleMur = config.getValeur("tailleMur");
		ecartX = config.getValeur("ecartX");	
		ecartY = config.getValeur("ecartY");	
		carte = new int[width][height];	//taille de la map
		timer = 1000;
		int c;
		
		FileInputStream fichier = new FileInputStream(path); 
		//on lit chaque caractere du fichier .txt qui contient la map et on les stocke de le tableau carte[][]

		for(int j=0; j<height; j++)	
		{
			for(int i=0; i<width; i++)
			{
				c = fichier.read();
				if(c != 10 && c != 13) 	//10 = '\n' =retour a la ligne
					carte[i][j] = c;
				else
					i--;
			}		
		}
		fichier.close();
	}
	
	public void reinitMap(String path) throws IOException
	{
		int c;
		timer = 1000;
		FileInputStream fichier = new FileInputStream(path); 
		//on lit chaque caractere du fichier .txt qui contient la map et on les stocke de le tableau carte[][]
		
		for(int j=0; j<height; j++)	
		{
			for(int i=0; i<width; i++)
			{
				c = fichier.read();
				if(c != 10 && c != 13) 	//10 = '\n' =retour a la ligne
					carte[i][j] = c;
				else
					i--;
			}		
		}
		fichier.close();
	}
	
	public int getCase(int abscisse, int ordonnee)
	{
		return carte[abscisse][ordonnee];
	}
	
	public void setCase(int abscisse, int ordonnee, int valeur)
	{
		carte[abscisse][ordonnee] = valeur;
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
	
	public void spawnEtoile()
	{
		if(timer > 0)
			timer --;
		
		else
		{
			int x = 0;
			int y = 0;
			while(carte[x][y] == '1' || carte[x][y] == '4' || carte[x][y] == '2' || carte[x][y] == '5')
			{
				x = (int)(Math.random()*(width-2)+1);
				y = (int)(Math.random()*(height-2)+1);
			}
			if(carte[x][y]=='3')
				carte[x][y] = '2';
			if(carte[x][y] == '0')
				carte[x][y]='5';
			timer = 2000;
		}
	}
	
	public void afficheMap(Graphics fenetre, GestionGraphismes g) throws IOException
	{
		for(int j=0; j<height; j++)
		{
			for(int i=0; i<width; i++)
			{
				if (carte[i][j] == '1')		//pour chaque caractere '1', on affiche la texture du mur
					fenetre.drawImage(g.getImage("mur"), i*tailleMur+ecartX, j*tailleMur+ecartY);
				
				else if(carte[i][j] == '3')
					fenetre.drawImage(g.getImage("piece"), i*tailleMur+ecartX+tailleMur/2, j*tailleMur+ecartY+tailleMur/3);
				
				else if(carte[i][j] == '2')
					fenetre.drawImage(g.getImage("etoile"), i*tailleMur+ecartX, j*tailleMur+ecartY);
				
				else if(carte[i][j] == '5')
					fenetre.drawImage(g.getImage("etoile"), i*tailleMur+ecartX, j*tailleMur+ecartY);
			}
		}	
	}
}
