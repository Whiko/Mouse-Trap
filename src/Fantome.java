import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Fantome extends Joueur
{
	private Map fichierCarte;
	private boolean visible;
	private String mvmt;
	private int positionX, positionY, tailleMur, tailleFantome, ecartX, ecartY, vitesse, direction;
	private Configuration config;
	private int carte[][];
	private Image fantomeimg;
	
	public Fantome(String path) throws IOException, SlickException
	{
		visible = true;
		mvmt = "haut"; // indique la direction actuelle du fantome
		fichierCarte = new Map(path);
		config = new Configuration("config/config_map.txt");
		positionX = config.getValeur("pointDepartFantomeX");	//indiques les positions en x et y
		positionY = config.getValeur("pointDepartFantomeY");
		tailleMur = config.getValeur("taillePerso");
		ecartX = config.getValeur("ecartX");	//ecart entre le bord de la fenetre et la map
		ecartY = config.getValeur("ecartY");
		vitesse = config.getValeur("vitesse");
		direction = 0;
		carte = fichierCarte.getCarte();
		fantomeimg = new Image("sprites/sprites_Cyriaque/sprites_Julie/fantome_violet.png");
		
	}
	
	public void afficheFantome(Graphics fantome)
	{
		fantome.drawImage(fantomeimg, positionX, positionY);
	}
	
	@Override
	public void seDeplacer(GameContainer container)
	{
		int position_droit = positionX-ecartX+tailleFantome;
		int position_gauche = positionX-ecartX;
		int position_bas = positionY-ecartY+tailleFantome;
		int position_haut = positionY-ecartY;
		int distance, alea;
		int directions[] = new int[3];
		for(int i=0; i<3; i++)
			directions[i]=0;
		
		//si le fantome se deplace vers la gauche
		if(mvmt == "gauche")
		{
			if((position_gauche-(vitesse+1))/tailleMur != position_gauche/tailleMur)
			{
				distance = position_gauche - (tailleMur * (position_gauche-1)/tailleMur);
				positionX -= distance; 
			}
		}
		
		//si le fantome se deplace vers la droite
		else if(mvmt == "droit")
		{
			if((position_droit-(vitesse+1))/tailleMur != position_droit/tailleMur)
			{
				
				distance = position_droit - (tailleMur * (position_droit-1)/tailleMur);
				positionX += distance;
				
				
			}
			
			else
				positionX += vitesse;
		}
		
		//si le fantome se deplace vers le haut
		else if(mvmt.equals("haut"))
		{
			System.out.println("test");
			//si la case de la position actuelle - 4 est une autre case que celle actuelle
			if((position_haut-(vitesse+1))/tailleMur != position_haut/tailleMur)
			{
				if(carte[(position_gauche-(vitesse+1))/tailleMur][position_haut/tailleMur] != 1)
				{
					direction += 1;
					directions[0] = 1; //1 represente la direction 'gauche'
				}
				
				if(carte[position_gauche/tailleMur][(position_haut-(vitesse+1))/tailleMur] !=1)
				{
					direction += 2;
					directions[1] = 2; //2 la direction 'haut'
				}
				
				if(carte[(position_droit-(vitesse+1))/tailleMur][position_haut/tailleMur] !=1)
				{
					direction += 4;
					directions[2] = 4; //4 la direction 'droit'
				}
				
				//determination de la prochaine direction à suivre, parmi celles possibles
				if(direction == 0)
				{
					mvmt = "bas";
					positionY += vitesse;
				}
				
				System.out.println("test2");
				do
				{
					alea = (int)(Math.random()*2);
					System.out.println(alea);
				}while(directions[alea] == 0);
				
				if(alea == 0) //direction 'gauche'
				{
					distance = position_haut - (tailleMur*(position_haut-1)/tailleMur);
					positionY -= distance;
					mvmt = "gauche";
					positionX -= vitesse;
				}
				
				else if(alea == 1) //direction 'haut'
					positionY -= vitesse;
				
				else if(alea == 2) //direction 'droit'
				{
					distance = position_haut - (tailleMur * (position_haut-1)/tailleMur);
					positionY -= distance;
					mvmt = "droit";
					positionX += vitesse;
				}
				
				
				/*
				else if(direction == 1)
				{
					distance = position_haut - (tailleMur * (position_haut-1)/tailleMur);
					positionY -= distance;
					mvmt = "gauche";
					positionX -= vitesse;
				}
				
				else if(direction == 2)
				{
					positionY -= vitesse;
				}
				
				else if(direction == 4)
				{
					distance = position_haut - (tailleMur * (position_haut-1)/tailleMur);
					positionY -= distance;
					mvmt = "droit";
					positionX += vitesse;
				}
				
				else if(direction == 3)
				{
					alea = (int)(Math.random()*1);
					
					if(alea == 0) //direction 'gauche'
					{
						distance = position_haut - (tailleMur * (position_haut-1)/tailleMur);
						positionY -= distance;
						mvmt = "gauche";
						positionX -= vitesse;
					}
					
					else if(alea == 1) //direction 'haut'
						positionY -= vitesse;
				}
				
				else if(direction == 5)
				{
					alea = (int)(Math.random()*1);
					
					if(alea == 0) //direction 'gauche'
					{
						distance = position_haut - (tailleMur * (position_haut-1)/tailleMur);
						positionY -= distance;
						mvmt = "gauche";
						positionX -= vitesse;
					}
					
					else if(alea == 1) //direction 'droit'
					{
						distance = position_haut - (tailleMur * (position_haut-1)/tailleMur);
						positionY -= distance;
						mvmt = "droit";
						positionX += vitesse;
					}
				}
				
				else if(direction == 6)
				{
					alea = (int)(Math.random()*1);
					
					if(alea == 0) //direction 'droit'
					{
						distance = position_haut - (tailleMur * (position_haut-1)/tailleMur);
						positionY -= distance;
						mvmt = "droit";
						positionX += vitesse;
					}
					
					else if(alea == 1) //direction 'haut'
						positionY -= vitesse;
				}
				
				else if(direction == 7)
				{
					alea = (int)(Math.random()*2);
					
					if(alea == 0) //direction 'gauche'
					{
						distance = position_haut - (tailleMur * (position_haut-1)/tailleMur);
						positionY -= distance;
						mvmt = "gauche";
						positionX -= vitesse;
					}
					
					else if(alea == 1) //direction 'droit'
					{
						distance = position_haut - (tailleMur * (position_haut-1)/tailleMur);
						positionY -= distance;
						mvmt = "droit";
						positionX += vitesse;
					}
					
					else if(alea == 2) //direction 'haut'
						positionY -= vitesse;
				}*/
			}
			
			else
				positionY -= vitesse; 
		}
		
		//si le fantome se deplace vers le bas
		else if(mvmt == "bas")
		{
			if((position_bas-(vitesse+1))/tailleMur != position_bas/tailleMur)
			{
				distance = position_bas - (tailleMur * (position_bas-1)/tailleMur);
				positionY += distance;
			}
			
			else
				positionY += vitesse; 
		}
	}
}
