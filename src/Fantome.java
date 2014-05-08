import java.io.IOException;
import java.io.Serializable;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class Fantome extends Joueur implements Serializable
{
	private String mvmt;
	private int positionX, positionY, tailleMur, tailleFantome, ecartX, ecartY, vitesse, position_droit, position_gauche, position_haut,
				position_bas, pointDepartX, pointDepartY;
	private Configuration config;
	
	public Fantome(String path, String configu) throws IOException
	{
		mvmt = "haut"; // indique la direction actuelle du fantome
		config = new Configuration(configu);
		positionX = config.getValeur("pointDepartFantomeX");
		positionY = config.getValeur("pointDepartFantomeY");
		pointDepartX = positionX;
		pointDepartY = positionY;
		tailleMur = config.getValeur("tailleMur");
		tailleFantome = config.getValeur("taillePerso");
		ecartX = config.getValeur("ecartX");	//ecart entre le bord de la fenetre et la map
		ecartY = config.getValeur("ecartY");
		vitesse = config.getValeur("vitesse");
		position_droit = positionX-ecartX+tailleFantome;
		position_gauche = positionX-ecartX;
		position_bas = positionY-ecartY+tailleFantome;
		position_haut = positionY-ecartY;
	}
	
	public int getGauche()
	{
		return position_gauche;
	}
	
	public int getDroit()
	{
		return position_droit;
	}
	
	public int getHaut()
	{
		return position_haut;
	}
	
	public int getBas()
	{
		return position_bas;
	}
	public void resetPosition()
	{
		positionY = pointDepartY;
		positionX = pointDepartX;
	}
	
	public void afficheFantome(Graphics fantome, GestionGraphismes g, int i)
	{
		fantome.drawImage(g.getImage("fantomeimg"+((i%6)+1)), positionX, positionY);
	}
	
	public void seDeplacer(GameContainer container, int carte[][])
	{

		position_droit = positionX-ecartX+tailleFantome;
		position_gauche = positionX-ecartX;
		position_bas = positionY-ecartY+tailleFantome;
		position_haut = positionY-ecartY;
		
		int distance, alea;
		boolean directions[] = new boolean[3];
		for(int i=0; i<3; i++)
			directions[i]=false;
		

		if(mvmt == "gauche")
		{
			distance = position_gauche % tailleMur;
			if (distance-1 <= vitesse) // Si on est dans une zone ou il va etre possible de tourner
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
					do// decision aleatoire de la direction a prendre. On tire un nombre aleatoire tant que le chemin choisi n'est pas disponible
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
	
	public void seDeplacerServeur(Map carte, String requete)
	{
		position_droit = positionX-ecartX+tailleFantome;
		position_gauche = positionX-ecartX;
		position_bas = positionY-ecartY+tailleFantome;
		position_haut = positionY-ecartY;
		
		if (requete.contains("gauche"))
		{
			if (carte.getCase((int)((position_gauche-(vitesse+1))/tailleMur), (int)(position_haut/tailleMur))!='1'
				&& carte.getCase((int)((position_gauche-(vitesse+1))/tailleMur), (int)(position_bas/tailleMur))!='1')
			{
				positionX -= vitesse;
			}
			
			else
				positionX -= position_gauche - ((position_gauche/tailleMur)*tailleMur)-1;
			
		}
	
		position_droit = positionX-ecartX+tailleFantome;
		position_gauche = positionX-ecartX;
		position_bas = positionY-ecartY+tailleFantome;
		position_haut = positionY-ecartY;
		
		if (requete.contains("droite"))
		{
			if	(carte.getCase((int)((position_droit+vitesse+1)/tailleMur), (int)(position_haut/tailleMur))!='1'
				&& carte.getCase((int)((position_droit+vitesse+1)/tailleMur), (int)(position_bas/tailleMur))!='1')
			{
				positionX += vitesse;
			}
			
			else
			{
				if  (carte.getCase((int)((position_droit)/tailleMur), (int)(position_haut/tailleMur))!='1'
					&& carte.getCase((int)((position_droit)/tailleMur), (int)(position_bas/tailleMur))!='1')
					positionX += ((position_droit/tailleMur)*tailleMur)+tailleMur-1 - position_droit;
			}
			
		}

		position_droit = positionX-ecartX+tailleFantome;
		position_gauche = positionX-ecartX;
		position_bas = positionY-ecartY+tailleFantome;
		position_haut = positionY-ecartY;
		
		if (requete.contains("haut"))
		{			
			if	(carte.getCase((int)(position_gauche/tailleMur), (int)((position_haut-(vitesse+1))/tailleMur))!='1'
				&& carte.getCase((int)((position_droit)/tailleMur), (int)((position_haut-(vitesse+1))/tailleMur))!='1')
			{
				positionY -= vitesse;
			}
			
			else
				positionY -= position_haut - ((position_haut/tailleMur)*tailleMur)-1;
			
		}	
		
		position_droit = positionX-ecartX+tailleFantome;
		position_gauche = positionX-ecartX;
		position_bas = positionY-ecartY+tailleFantome;
		position_haut = positionY-ecartY;
		
		if (requete.contains("bas"))
		{
			if	(carte.getCase((int)(position_gauche/tailleMur), (int)((position_bas+(vitesse+1))/tailleMur))!='1'
				&& carte.getCase((int)(position_droit/tailleMur),(int)((position_bas+(vitesse+1))/tailleMur))!='1')
			{
				positionY += vitesse;
			}
			
			else
				if  (carte.getCase((int)((position_droit)/tailleMur), (int)(position_bas/tailleMur))!='1'
				&& carte.getCase((int)((position_gauche)/tailleMur),(int)(position_bas/tailleMur))!='1')
					positionY += (((position_bas/tailleMur)+1)*tailleMur)-1 - position_bas;
		}
	}
}
