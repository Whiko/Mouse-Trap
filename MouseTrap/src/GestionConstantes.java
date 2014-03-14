import java.io.*;

//UTILISER HASHTABLE !


public class GestionConstantes 
{	
	String nom;
	int valeur;
	
	public GestionConstantes()
	{
		nom = "Null";
		valeur = 0;
	}
	
	public GestionConstantes(String nomC, int valeurC)
	{
		nom = nomC;
		valeur = valeurC;
	}
	
	public GestionConstantes getConst(String path) 
	{
        try
        {
            FileReader c = new FileReader(path);
            BufferedReader r = new BufferedReader(c);
 
            String line = r.readLine();
             
            while (line != null) 
            {
                String[] decompose = line.split(";");
                String nomConst = decompose[0];
                int valeurConst = Integer.parseInt(decompose[1]);

                line = r.readLine();
            }

            r.close();
 
        } catch (Exception e) {throw new Error(e);}
        
        GestionConstantes constante = new GestionConstantes(nomConst, valeurConst);
        return constante;
    }
}
