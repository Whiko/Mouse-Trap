import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Fantome extends Joueur
{
	private Map fichierCarte;
	private String mvmt;
	private int positionX, positionY, tailleMur, tailleFantome, ecartX, ecartY, vitesse, pointDepartX, pointDepartY;
	private Configuration config;
	private int carte[][];
	private Image fantomeimg;
	
	public Fantome(String path, String configu) throws IOException, SlickException
	{
		mvmt = "haut"; // indique la direction actuelle du fantome
		fichierCarte = new Map(path);
		config = new Configuration(configu);
		positionX = config.getValeur("pointDepartFantomeX");	//indiques les positions en x et y
		positionY = config.getValeur("pointDepartFantomeY");
		pointDepartX = positionX;
		pointDepartY = positionY;
		tailleMur = config.getValeur("tailleMur");
		tailleFantome = config.getValeur("taillePerso");
		ecartX = config.getValeur("ecartX");	//ecart entre le bord de la fenetre et la map
		ecartY = config.getValeur("ecartY");
		vitesse = config.getValeur("vitesse");
		carte = fichierCarte.getCarte();
		fantomeimg = new Image("sprites/sprites_Cyriaque/sprites_Julie/fantome_violet.png");
		
	}
	
	public int getX()
	{
		return positionX;
	}
	
	public int getY()
	{
		return positionY;
	}
	
	public void afficheFantome(Graphics fantome)
	{
		fantome.drawImage(fantomeimg, positionX, positionY);
	}
	
	@Override
	// les fantomes peuvent revenir sur leurs pas a une intersection, si leur vitesse <1... je sais pas pourquoi. A creuser.
	// Les commentaires d'explication du code sont sur le 1er cas : lorsqu'on se dirige vers la gauche, les autres cas sont construits de la meme maniere.
	// fantomes peut etre un peu rapides, meme avec une vitesse =1...?
	public void seDeplacer(GameContainer container)
	{
		int position_droit = positionX-ecartX+tailleFantome;
		int position_gauche = positionX-ecartX;
		int position_bas = positionY-ecartY+tailleFantome;
		int position_haut = positionY-ecartY;
		int distance, alea;
		boolean directions[] = new boolean[3];
		for(int i=0; i<3; i++)
			directions[i]=false;
		

		if(mvmt == "gauche")
		{
			distance = position_gauche % tailleMur;
			if (distance-1 <= vitesse) // Si on est dans une zone ou il va etre possible de tourner...
			{
				// tests des directions possibles
				//direction bas
				if(carte[position_gauche/tailleMur][(position_bas+(vitesse+1))/tailleMur] !='1')
					directions[0] = true;
				
				//direction gauche
				if(carte[(position_gauche-(vitesse+1))/tailleMur][position_haut/tailleMur] !='1')
					directions[1] = true;
				
				//direction haut
				if(carte[(position_gauche)/tailleMur][(position_haut-(vitesse+1))/tailleMur] !='1')
					directions[2] = true;

				
				if(!directions[0] && !directions[1] && !directions[2]) // cul de sac : aucune direction possible, a part revenir sur ses pas
					mvmt = "droit";
				
				else
				{
					do// decision aleatoire de la direction a prendre. On tire un nombre aleatoire tant que le chemin choisis n'est pas disponible...
					{
						alea = (int)(Math.random()*3);
					}while(!directions[alea]); 
					
					if(alea == 0) //direction 'bas'
					{
						positionX -= distance-1;
						positionY += vitesse;
						mvmt = "bas";
					}
					
					else if(alea == 1) //direction 'gauche'
						positionX -= vitesse;
					
					else if(alea == 2) //direction 'haut'
					{
						positionX -= distance-1;
						positionY -= vitesse;
						mvmt = "haut";
					}
				}
			}
			else
			{
				positionX -= vitesse;
				mvmt = "gauche";
			}
		}	
		
		else if(mvmt == "haut")
		{
			distance = position_haut % tailleMur;
			if (distance-1 <= vitesse)
			{
				//direction gauche
				if(carte[(position_gauche-(vitesse+1))/tailleMur][position_haut/tailleMur] !='1')
					directions[0] = true;
				
				//direction haut
				if(carte[position_gauche/tailleMur][(position_haut-(vitesse+1))/tailleMur] !='1')
					directions[1] = true; 
				
				//direction droit
				if(carte[(position_droit+(vitesse+1))/tailleMur][position_haut/tailleMur] !='1')
					directions[2] = true;

				if(!directions[0] && !directions[1] && !directions[2])
					mvmt = "bas";
				
				else
				{
					do
					{
						alea = (int)(Math.random()*3);
					}while(!directions[alea]);

					if(alea == 0) //direction 'gauche'
					{
						positionY -= distance-1;
						positionX -= vitesse;
						mvmt = "gauche";
					}
					
					else if(alea == 1) //direction 'haut'
						positionY -= vitesse;
					
					else if(alea == 2) //direction 'droit'
					{
						positionY -= distance-1;
						positionX += vitesse;
						mvmt = "droit";
					}
				}
			}
			else
			{
				positionY -= vitesse;
				mvmt = "haut";
			}
		}	
		
		else if(mvmt == "bas")
		{
			distance = tailleMur - position_bas % tailleMur;
			if (distance-1 <= vitesse)
			{
				//direction droit
				if(carte[(position_droit+(vitesse+1))/tailleMur][position_bas/tailleMur] !='1')
					directions[0] = true;
				
				//direction bas
				if(carte[(position_gauche)/tailleMur][(position_bas+(vitesse+1))/tailleMur] !='1')
					directions[1] = true;
				
				//direction gauche
				if(carte[(position_gauche-(vitesse+1))/tailleMur][position_bas/tailleMur] !='1')
					directions[2] = true;

				if(!directions[0] && !directions[1] && !directions[2])
					mvmt = "haut";
				
				else
				{
					do
					{
						alea = (int)(Math.random()*3);
					}while(!directions[alea]);

					if(alea == 0) //direction 'droit'
					{
						positionY += distance-1;
						positionX += vitesse;
						mvmt = "droit";
					}
					
					else if(alea == 1) //direction 'bas'
						positionY += vitesse;
					
					else if(alea == 2) //direction 'gauche'
					{
						positionY += distance-1;
						positionX -= vitesse;
						mvmt = "gauche";
					}
				}
			}
			else
			{
				positionY += vitesse;
				mvmt = "bas";
			}
		}
		
		else if(mvmt == "droit")
		{
			distance = tailleMur - position_droit % tailleMur;
			if (distance-1 <= vitesse)
			{
				//direction haut
				if(carte[position_droit/tailleMur][(position_haut-(vitesse+1))/tailleMur] !='1')
					directions[0] = true;
				
				//direction droit
				if(carte[(position_droit+(vitesse+1))/tailleMur][position_haut/tailleMur] !='1')
					directions[1] = true;
				
				//direction bas
				if(carte[position_droit/tailleMur][(position_bas+(vitesse+1))/tailleMur] !='1')
					directions[2] = true;

				if(!directions[0] && !directions[1] && !directions[2])
					mvmt = "gauche";
				
				else
				{
					do
					{
						alea = (int)(Math.random()*3);
					}while(!directions[alea]);

					if(alea == 0) //direction 'haut'
					{
						positionX += distance-1;
						positionY -= vitesse;
						mvmt = "haut";
					}
					
					else if(alea == 1) //direction 'droit'
						positionX += vitesse;
					
					else if(alea == 2) //direction 'bas'
					{
						positionX += distance-1;
						positionY += vitesse;
						mvmt = "bas";
					}
				}
			}
			else
			{
				positionX += vitesse;
				mvmt = "droit";
			}
		}	
	}
}
