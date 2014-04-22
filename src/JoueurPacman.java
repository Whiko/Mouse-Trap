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
	private int positionX, positionY, pointDepartX, pointDepartY, tailleMur, taillePacman, ecartX, ecartY, vitesse, nbFantomes, nbPoints;
	private Image pacmanimg; 
	private Configuration config;
	private int[][] carte;
	private Fantome[] fantomes;
	private boolean gameOver, nextLevel;
	private GestionEntite gestion;
	
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
		nbFantomes = config.getValeur("nbFantomes");
		nbPoints = config.getValeur("nbPoints");
		pacmanimg = new Image("sprites/sprites_Cyriaque/sprites_Julie/pacman.png");
		carte = fichierCarte.getCarte();
		positionX = ecartX + tailleMur;
		positionY = ecartY + tailleMur;
		pointDepartX = positionX;
		pointDepartY = positionY;
		gameOver = false;
		nextLevel = false;
		gestion = new GestionEntite();
		fantomes = new Fantome[1];
		
		for(int i=0; i<nbFantomes; i++)
		{
			fantomes[i] = new Fantome("map/map1.txt");
		}
	}
	
	public Fantome[] getFantomes()
	{
		return fantomes;
	}
	
	public void setPseudo(String pseudo)
	{
		this.pseudo = pseudo;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public int getVie()
	{
		return vie;
	}
	
	public void gestionContact()
	{
		int i=0;
		
		while(i<nbFantomes && gameOver != true)
		{
			if((positionX <= fantomes[i].getX()+2 && positionX >= fantomes[i].getX()-2) 
					&& (positionY <= fantomes[i].getY()+2 && positionY >= fantomes[i].getY()-2))
			{
				if(vie > 1)
				{
					vie--;
					positionX = pointDepartX;
					positionY = pointDepartY;
					gestion.timerMort();
				}
				
				else
					gameOver = true;
			}
			
			i++;
		}
	}
	
	public int setScore(int position_gauche, int position_haut, int score)
	{
		if(carte[position_gauche/tailleMur][position_haut/tailleMur] == '3')
		{
			if((position_gauche < ((position_gauche/tailleMur)*tailleMur+tailleMur/4) &&  (position_gauche >= ((position_gauche/tailleMur)*tailleMur)-3)) 
					&& (position_haut < ((position_haut/tailleMur)*tailleMur+tailleMur/2) && (position_haut > ((position_haut/tailleMur)*tailleMur)-3)))
			{
				carte[position_gauche/tailleMur][position_haut/tailleMur] = 0;
				score += 100;
			}
	 	}
			
		return score;
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
		
		for(int j=0; j<nbFantomes; j++)
		{
			fantomes[j].seDeplacer(container);
		}
		
		if (container.getInput().isKeyDown(Input.KEY_LEFT))
		{
			if  (carte[(int)((position_gauche-(vitesse+1))/tailleMur)][(int)(position_haut/tailleMur)]!='1'
				&& carte[(int)((position_gauche-(vitesse+1))/tailleMur)][(int)(position_bas/tailleMur)]!='1')
			{
				positionX -= vitesse;
				gestionContact();
			}
			
			else
				positionX -= position_gauche - ((position_gauche/tailleMur)*tailleMur)-1;
			
			score = setScore(position_gauche, position_haut, score);
		}
	
		position_droit = positionX-ecartX+taillePacman;
		position_gauche = positionX-ecartX;
		position_bas = positionY-ecartY+taillePacman;
		position_haut = positionY-ecartY;
		
		if (container.getInput().isKeyDown(Input.KEY_RIGHT)) 
		{
			if	(carte[(int)((position_droit+vitesse+1)/tailleMur)][(int)(position_haut/tailleMur)]!='1'
				&& carte[(int)((position_droit+vitesse+1)/tailleMur)][(int)(position_bas/tailleMur)]!='1')
			{
				positionX += vitesse;
				gestionContact();
			}
			
			else
			{
				if  (carte[(int)((position_droit)/tailleMur)][(int)(position_haut/tailleMur)]!='1'
					&& carte[(int)((position_droit)/tailleMur)][(int)(position_bas/tailleMur)]!='1')
					positionX += ((position_droit/tailleMur)*tailleMur)+tailleMur-1 - position_droit;
			}
			
			score = setScore(position_gauche, position_haut, score);
		}

		position_droit = positionX-ecartX+taillePacman;
		position_gauche = positionX-ecartX;
		position_bas = positionY-ecartY+taillePacman;
		position_haut = positionY-ecartY;
		
		if (container.getInput().isKeyDown(Input.KEY_UP))
		{
			if	(carte[(int)(position_gauche/tailleMur)][(int)((position_haut-(vitesse+1))/tailleMur)]!='1'
				&& carte[(int)((position_droit)/tailleMur)][(int)((position_haut-(vitesse+1))/tailleMur)]!='1')
			{
				positionY -= vitesse;
				gestionContact();
			}
			
			else
				positionY -= position_haut - ((position_haut/tailleMur)*tailleMur)-1;
			
			score = setScore(position_gauche, position_haut, score);
		}	
		
		position_droit = positionX-ecartX+taillePacman;
		position_gauche = positionX-ecartX;
		position_bas = positionY-ecartY+taillePacman;
		position_haut = positionY-ecartY;
		
		if (container.getInput().isKeyDown(Input.KEY_DOWN))
		{
			if	(carte[(int)(position_gauche/tailleMur)][(int)((position_bas+(vitesse+1))/tailleMur)]!='1'
				&& carte[(int)(position_droit/tailleMur)][(int)((position_bas+(vitesse+1))/tailleMur)]!='1')
			{
				positionY += vitesse;
				gestionContact();
			}
			
			else
				if  (carte[(int)((position_droit)/tailleMur)][(int)(position_bas/tailleMur)]!='1'
				&& carte[(int)((position_gauche)/tailleMur)][(int)(position_bas/tailleMur)]!='1')
				positionY += (((position_bas/tailleMur)+1)*tailleMur)-1 - position_bas;
			
			score = setScore(position_gauche, position_haut, score);
		}
	}
}