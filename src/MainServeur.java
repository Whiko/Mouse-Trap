import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class MainServeur 
{
	public static void main(String[] args) throws IOException
    {
    	Serveur serveur = new Serveur();
    	serveur.acceptJoueur();
    }
}
