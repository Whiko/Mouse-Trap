import java.io.*;
import java.util.Scanner;

public class Map 
{
	private int[][] carte;
	
	public void afficheMap(String path) throws IOException
	{
		int x = 20;
		int y = 20;
		carte = new int [20][20];	//taille de la map
		
		InputStream fichier = new FileInputStream(path); 
		InputStreamReader ipsr=new InputStreamReader(fichier);
		BufferedReader br=new BufferedReader(ipsr);
		String ligne;
		
		/*while ((ligne=br.readLine())!=null)		//ça va pas.
		{	
			for(int i=0; i<y; i++)
			{
				for(int j=0; j<x; j++)
				{
					carte[i][j] = ligne.charAt(j);
					if(carte[i][j]==1)
					{
						
					}
				}
			}
		}	*/
		
		int i=0;
		int j=0;
		Scanner sc = null;
        try {
		    sc = new Scanner(fichier);
		    while (sc.hasNextLine()) 
		    {
		        for (char c : sc.next().toCharArray()) 
		        {
		            carte[i][j] = (int)c;
		            
		            if(carte[i][j]==1)
		            {
		            	
		            }
		            
		            i++;
		        	//affiche les blocs
		        }
		        
		        j++;
		    }
		} 
		finally 
		{
		    if (sc != null)
		        sc.close();
		}
		
	}
}
