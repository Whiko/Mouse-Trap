import java.io.*;

public class Map 
{
	private int[][] carte;
	
	public void afficheMap(String path) throws IOException
	{
		int x = 20;
		int y = 20;
		int caractere = 0;
		carte = new int [20][20];	//taille de la map
		
		FileInputStream fichier = new FileInputStream(path); 
		caractere = fichier.read();
		for(int i=0; i<x; i++)
		{
			for(int j=0; j<y; j++)
			{
				if(caractere != 10) //'10' = retour a la ligne
				{
					carte[i][j] = caractere;
					System.out.println(carte[i][j]+" ");
				}
				
				else
					System.out.println("\n");
			}		
		}
		fichier.close();
	}
}