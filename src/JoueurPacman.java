import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class JoueurPacman extends Joueur
{
	private Map fichierCarte;
	private int positionX = 1;
	private int positionY = 1;
	private Image mur; 
	private Configuration config = new Configuration("config/config_map.txt");
	private int tailleMur, ecartX, ecartY;
	private int[][] carte;
	
	public JoueurPacman(String path) throws SlickException, IOException
	{
		fichierCarte = new Map(path);
		tailleMur = config.getValeur("tailleMur");
		ecartX = config.getValeur("ecartX");
		ecartY = config.getValeur("ecartY");
		mur = new Image("mur.png");
		carte = fichierCarte.getCarte();
	}
	
	public void afficheSouris(Graphics pacman)
	{
		pacman.drawImage(mur, positionX*tailleMur+ecartX, positionY*tailleMur+ecartY);
	}

	@Override
	public void seDeplacer(GameContainer container) 
	{
		if (container.getInput().isKeyDown(Input.KEY_LEFT))
			if(positionX>1)
				if(carte[positionX-1][positionY] != '1')
					positionX--;
			
		if (container.getInput().isKeyDown(Input.KEY_RIGHT)) 
			if(positionX<fichierCarte.getWidth()-1)
				if(carte[positionX+1][positionY] != '1')	
					positionX++;

		if (container.getInput().isKeyDown(Input.KEY_UP))
			if(positionY>1)	
				if(carte[positionX][positionY-1] != '1')
					positionY--;
			
		if (container.getInput().isKeyDown(Input.KEY_DOWN))
			if(positionY<fichierCarte.getHeight()-1)
				if(carte[positionX][positionY+1] != '1')	
					positionY++;
	}
}