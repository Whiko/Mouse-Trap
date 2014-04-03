import java.io.*;
import java.util.Hashtable;

public class Configuration
{	
	private Hashtable<String, Integer> constantes;

	public Configuration()
	{
		super();
	}

	public Configuration(String path) throws IOException 
	{
		constantes = new Hashtable<String, Integer>();
		FileReader c = new FileReader(path);
        BufferedReader r = new BufferedReader(c);
 
        String line = r.readLine();
             
        while (line != null) 
        {
            String[] decompose = line.split("=");
            String nomConst = decompose[0];
            int valeurConst = Integer.parseInt(decompose[1]);
            
            constantes.put(nomConst, valeurConst);

            line = r.readLine();
        }

        r.close();
    }

	public int getValeur(String nom)
	{
		Integer valeur = constantes.get(nom);
		return valeur;
	}
}