import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class ControleSouris extends Joueur
{
	private Map fichierCarte;
	private int positionX = 1;
	private int positionY = 1;
	private Image mur; 
	private GestionConstantes tMur = new GestionConstantes("config/config_map.txt");
	private GestionConstantes ecX = new GestionConstantes("config/config_map.txt");	//ecart en x entre la fenetre et la map
	private GestionConstantes ecY = new GestionConstantes("config/config_map.txt");
	int tailleMur, ecartX, ecartY;
	private int[][] carte;
	
	public ControleSouris(String path) throws SlickException, IOException
	{
		fichierCarte = new Map(path);
		tailleMur = tMur.getValeur("tailleMur");
		ecartX = ecX.getValeur("ecartX");
		ecartY = ecY.getValeur("ecartY");
		mur = new Image("mur.png");
		carte = fichierCarte.getCarte();
	}
	
	public void afficheSouris(Graphics souris)
	{
		souris.drawImage(mur, positionX*tailleMur+ecartX, positionY*tailleMur+ecartY);
	}

	@Override
	public void seDeplacer(GameContainer container) 
	{
		if (container.getInput().isKeyDown(Input.KEY_LEFT))
			if(positionX>1)
				if(carte[positionY][positionX-1] != 1)
					positionX--;
			
		if (container.getInput().isKeyDown(Input.KEY_RIGHT)) 
			if(positionX<fichierCarte.getWidth()-1)
				if(carte[positionY][positionX+1] != 1)	
					positionX++;

		if (container.getInput().isKeyDown(Input.KEY_UP))
			if(positionY>1)	
				if(carte[positionY-1][positionX] != 1)
					positionY--;
			
		if (container.getInput().isKeyDown(Input.KEY_DOWN))
			if(positionY<fichierCarte.getHeight()-1)
				if(carte[positionY+1][positionX] != 1)	
					positionY++;
	}
}
