import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class JoueurPacman extends Joueur
{
	private int nbObjet;
	private Map fichierCarte;
	private int positionX;
	private int positionY;
	private Image pacmanimg; 
	private Configuration config;
	private int tailleMur, taillePacman, ecartX, ecartY, vitesse;
	private int[][] carte;
	
	public JoueurPacman(String path) throws SlickException, IOException
	{
		nbObjet = 0;
		fichierCarte = new Map(path);
		config = new Configuration("config/config_map.txt");
		tailleMur = config.getValeur("tailleMur");
		taillePacman = config.getValeur("taillePerso");
		ecartX = config.getValeur("ecartX");
		ecartY = config.getValeur("ecartY");
		vitesse = config.getValeur("vitesse");
		pacmanimg = new Image("sprites/sprites_Cyriaque/sprites_Julie/pacman.png");
		carte = fichierCarte.getCarte();
		positionX = ecartX + tailleMur;
		positionY = ecartY + tailleMur;
	}
	
	void setPseudo(String pseudo)
	{
		this.pseudo = pseudo;
	}
	
	public void affichePacman(Graphics pacman)
	{
		pacman.drawImage(pacmanimg, positionX, positionY);
	}

	@Override
	public void seDeplacer(GameContainer container) 
	{
		int position_droit = positionX-ecartX+taillePacman;
		int position_gauche = positionX-ecartX;
		int position_bas = positionY-ecartY+taillePacman;
		int position_haut = positionY-ecartY;
		
		if (container.getInput().isKeyDown(Input.KEY_LEFT))
		{
			if  (carte[(int)((position_gauche-(vitesse+1))/tailleMur)][(int)(position_haut/tailleMur)]!='1'
				&& carte[(int)((position_gauche-(vitesse+1))/tailleMur)][(int)(position_bas/tailleMur)]!='1')
				positionX -= vitesse;
			
			else
				positionX -= position_gauche - ((position_gauche/tailleMur)*tailleMur)-1;
		}
		
		position_droit = positionX-ecartX+taillePacman;
		position_gauche = positionX-ecartX;
		position_bas = positionY-ecartY+taillePacman;
		position_haut = positionY-ecartY;
		
		if (container.getInput().isKeyDown(Input.KEY_RIGHT)) 
		{
			if	(carte[(int)((position_droit+vitesse+1)/tailleMur)][(int)(position_haut/tailleMur)]!='1'
				&& carte[(int)((position_droit+vitesse+1)/tailleMur)][(int)(position_bas/tailleMur)]!='1')
				positionX += vitesse;
			
			else
			{
				if  (carte[(int)((position_droit)/tailleMur)][(int)(position_haut/tailleMur)]!='1'
					&& carte[(int)((position_droit)/tailleMur)][(int)(position_bas/tailleMur)]!='1')
					positionX += ((position_droit/tailleMur)*tailleMur)+tailleMur-1 - position_droit;
			}
		}

		position_droit = positionX-ecartX+taillePacman;
		position_gauche = positionX-ecartX;
		position_bas = positionY-ecartY+taillePacman;
		position_haut = positionY-ecartY;
		
		if (container.getInput().isKeyDown(Input.KEY_UP))
		{
			if	(carte[(int)(position_gauche/tailleMur)][(int)((position_haut-(vitesse+1))/tailleMur)]!='1'
				&& carte[(int)((position_droit)/tailleMur)][(int)((position_haut-(vitesse+1))/tailleMur)]!='1')
				positionY -= vitesse;
			
			else
				positionY -= position_haut - ((position_haut/tailleMur)*tailleMur)-1;
		}	
		
		position_droit = positionX-ecartX+taillePacman;
		position_gauche = positionX-ecartX;
		position_bas = positionY-ecartY+taillePacman;
		position_haut = positionY-ecartY;
		
		if (container.getInput().isKeyDown(Input.KEY_DOWN))
		{
			if	(carte[(int)(position_gauche/tailleMur)][(int)((position_bas+(vitesse+1))/tailleMur)]!='1'
				&& carte[(int)(position_droit/tailleMur)][(int)((position_bas+(vitesse+1))/tailleMur)]!='1')
				positionY += vitesse;
			
			else
				if  (carte[(int)((position_droit)/tailleMur)][(int)(position_bas/tailleMur)]!='1'
				&& carte[(int)((position_gauche)/tailleMur)][(int)(position_bas/tailleMur)]!='1')
				positionY += (((position_bas/tailleMur)+1)*tailleMur)-1 - position_bas;
		}
	}
}