import java.io.IOException;
import java.util.Hashtable;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class JoueurPacman extends Joueur
{
	private int nbObjet;
	private int positionX, positionY, pointDepartX, pointDepartY,tailleMur, taillePacman, ecartX, ecartY, vitesse, nbFantomes, nbPieces, cptPieces;
	private Hashtable <String, Image> pacman_img; 
	private Configuration config;
	private boolean gameOver, invincible, invisible;
	private int ferme, timer, timerInvincible=1000;
	private String mvmt;
	
	public JoueurPacman(String path, String configu) throws SlickException, IOException
	{
		nbObjet = 0;
		cptPieces = 0;
		config = new Configuration(configu);
		tailleMur = config.getValeur("tailleMur");
		taillePacman = config.getValeur("taillePerso");
		ecartX = config.getValeur("ecartX");
		ecartY = config.getValeur("ecartY");
		vitesse = config.getValeur("vitesse");
		nbFantomes = config.getValeur("nbFantomes");
		nbPieces = config.getValeur("nbPoints");
		pacman_img = new Hashtable<String, Image>();
		pacman_img.put("pacman_right_ferme", new Image("sprites/sprites_Cyriaque/sprites_Julie/pacman_right_ferme.png"));
		pacman_img.put("pacman_left_ferme", new Image("sprites/sprites_Cyriaque/sprites_Julie/pacman_left_ferme.png"));
		pacman_img.put("pacman_up_ferme", new Image("sprites/sprites_Cyriaque/sprites_Julie/pacman_up_ferme.png"));
		pacman_img.put("pacman_down_ferme", new Image("sprites/sprites_Cyriaque/sprites_Julie/pacman_down_ferme.png"));
		pacman_img.put("pacman_right", new Image("sprites/sprites_Cyriaque/sprites_Julie/pacman_right.png"));
		pacman_img.put("pacman_left", new Image("sprites/sprites_Cyriaque/sprites_Julie/pacman_left.png"));
		pacman_img.put("pacman_up", new Image("sprites/sprites_Cyriaque/sprites_Julie/pacman_up.png"));
		pacman_img.put("pacman_down", new Image("sprites/sprites_Cyriaque/sprites_Julie/pacman_down.png"));
		positionX = ecartX + tailleMur;
		positionY = ecartY + tailleMur;
		pointDepartX = positionX;
		pointDepartY = positionY;
		gameOver = false;
		invincible = false;
		invisible = true;
		timer = 150;
		mvmt = "right";
	}
	
	public void setPseudo(String pseudo)
	{
		this.pseudo = pseudo;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public void setScore (int score)
	{
		this.score=score;
	}
	
	public int getVie()
	{
		return vie;
	}
	
	public void setVie(int vie)
	{
		this.vie=vie;
	}
	
	public int getCptPieces()
	{
		return cptPieces;
	}
	
	public boolean getGameOver()
	{
		return gameOver;
	}
		
	
	public boolean getInvincible()
	{
		return invincible;
	}
	
	public int getX()
	{
		return positionX;
	}
	
	public int getY()
	{
		return positionY;
	}
	
	public int setScore(Map carte, int position_gauche, int position_haut, int score)
	{
		if(carte.getCase(position_gauche/tailleMur, position_haut/tailleMur) == '3')
		{
			if((position_gauche < ((position_gauche/tailleMur)*tailleMur+tailleMur/4) &&  (position_gauche >= ((position_gauche/tailleMur)*tailleMur)-3)) 
					&& (position_haut < ((position_haut/tailleMur)*tailleMur+tailleMur/2) && (position_haut > ((position_haut/tailleMur)*tailleMur)-3)))
			{
				carte.setCase(position_gauche/tailleMur, position_haut/tailleMur, '0');
				score += 100;
				cptPieces +=1;
			}
	 	}
		
		if(carte.getCase(position_gauche/tailleMur, position_haut/tailleMur) == '2')
		{
			if((position_gauche < ((position_gauche/tailleMur)*tailleMur+tailleMur/4) &&  (position_gauche >= ((position_gauche/tailleMur)*tailleMur)-3)) 
					&& (position_haut < ((position_haut/tailleMur)*tailleMur+tailleMur/2) && (position_haut > ((position_haut/tailleMur)*tailleMur)-3)))
			{
				carte.setCase(position_gauche/tailleMur, position_haut/tailleMur, '0');
				invincible=true;
				score += 200;
				cptPieces +=1;
			}
	 	}
		
		if(carte.getCase(position_gauche/tailleMur, position_haut/tailleMur) == '5')
		{
			if((position_gauche < ((position_gauche/tailleMur)*tailleMur+tailleMur/4) &&  (position_gauche >= ((position_gauche/tailleMur)*tailleMur)-3)) 
					&& (position_haut < ((position_haut/tailleMur)*tailleMur+tailleMur/2) && (position_haut > ((position_haut/tailleMur)*tailleMur)-3)))
			{
				carte.setCase(position_gauche/tailleMur, position_haut/tailleMur, '0');
				invincible=true;
				score += 200;
			}
	 	}
			
		return score;
	}
	
	public void setEtoile(Map carte,int position_gauche, int position_haut)
	{
		if(carte.getCase(position_gauche/tailleMur, position_haut/tailleMur) == '2')
		{
			if((position_gauche < ((position_gauche/tailleMur)*tailleMur+tailleMur/4) &&  (position_gauche >= ((position_gauche/tailleMur)*tailleMur)-3)) 
					&& (position_haut < ((position_haut/tailleMur)*tailleMur+tailleMur/2) && (position_haut > ((position_haut/tailleMur)*tailleMur)-3)))
			{
				carte.setCase(position_gauche/tailleMur, position_haut/tailleMur, '0');
				nbObjet += 1;
				cptPieces +=1;
			}
	 	}
		
		if(carte.getCase(position_gauche/tailleMur, position_haut/tailleMur) == '5')
		{
			if((position_gauche < ((position_gauche/tailleMur)*tailleMur+tailleMur/4) &&  (position_gauche >= ((position_gauche/tailleMur)*tailleMur)-3)) 
					&& (position_haut < ((position_haut/tailleMur)*tailleMur+tailleMur/2) && (position_haut > ((position_haut/tailleMur)*tailleMur)-3)))
			{
				carte.setCase(position_gauche/tailleMur, position_haut/tailleMur, '0');
				nbObjet += 1;
			}
	 	}
	}
	
	public void affichePacman(Graphics pacman)
	{
		String img = "pacman_"+mvmt;
		if (ferme > 15)
			img = img+"_ferme";
		
		if (timer % 15 < 10)
		pacman.drawImage(pacman_img.get(img), positionX, positionY);
		
		if (timerInvincible >0 && timerInvincible <1000)
			pacman.drawImage(pacman_img.get(img), positionX, positionY);
	}

	public void gestionContact(Fantome[] fantomes)
	{
		int i=0;
		
		
		int position_droit = positionX-ecartX+taillePacman;
		int position_gauche = positionX-ecartX;
		int position_bas = positionY-ecartY+taillePacman;
		int position_haut = positionY-ecartY;
		
		if (!invisible)
		{
			if(invincible)
			{
				if(timerInvincible > 0)
				{	
					timerInvincible --;
					for(i=0; i<nbFantomes; i++)
					{
						if  (  position_gauche >= fantomes[i].getGauche()-config.getValeur("taillePerso")+7
								&& position_droit  <= fantomes[i].getDroit()+config.getValeur("taillePerso")-7
								&& position_haut   >= fantomes[i].getHaut()-config.getValeur("taillePerso")+7
								&& position_bas    <= fantomes[i].getBas()+config.getValeur("taillePerso")-7)
							{
								fantomes[i].resetPosition();
								score += 300;
								
							}
					}
				}
				if(timerInvincible==0)
				{
					timerInvincible=1000;
					invincible=false;
				}
				
			}
			
			else		
			{
				while(i<nbFantomes && !gameOver)
				{
					if  (  position_gauche >= fantomes[i].getGauche()-config.getValeur("taillePerso")+7
						&& position_droit  <= fantomes[i].getDroit()+config.getValeur("taillePerso")-7
						&& position_haut   >= fantomes[i].getHaut()-config.getValeur("taillePerso")+7
						&& position_bas    <= fantomes[i].getBas()+config.getValeur("taillePerso")-7)
					{
						if(vie > 1)
						{
							
							vie--;
							positionX = pointDepartX;
							positionY = pointDepartY;
							timer = 150;
							invisible = true;
						}
						
						else
							gameOver = true;
					}
					
					i++;
				}
			}
		}
		else
		{
			if(timer > 0)
				timer--;
			else
				invisible = false;
		}
	}

	public void seDeplacer(GameContainer container, Map carte) 
	{
		ferme = (ferme + 1) % 30;
		
		int position_droit = positionX-ecartX+taillePacman;
		int position_gauche = positionX-ecartX;
		int position_bas = positionY-ecartY+taillePacman;
		int position_haut = positionY-ecartY;
		
		if (container.getInput().isKeyDown(Input.KEY_LEFT))
		{
			mvmt = "left";
			if (carte.getCase((int)((position_gauche-(vitesse+1))/tailleMur), (int)(position_haut/tailleMur))!='1'
				&& carte.getCase((int)((position_gauche-(vitesse+1))/tailleMur), (int)(position_bas/tailleMur))!='1')
			{
				positionX -= vitesse;
			}
			
			else
				positionX -= position_gauche - ((position_gauche/tailleMur)*tailleMur)-1;
			
			score = setScore(carte, position_gauche, position_haut, score);
		}
	
		position_droit = positionX-ecartX+taillePacman;
		position_gauche = positionX-ecartX;
		position_bas = positionY-ecartY+taillePacman;
		position_haut = positionY-ecartY;
		
		if (container.getInput().isKeyDown(Input.KEY_RIGHT)) 
		{
			mvmt = "right";
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
			
			score = setScore(carte, position_gauche, position_haut, score);
		}

		position_droit = positionX-ecartX+taillePacman;
		position_gauche = positionX-ecartX;
		position_bas = positionY-ecartY+taillePacman;
		position_haut = positionY-ecartY;
		
		if (container.getInput().isKeyDown(Input.KEY_UP))
		{
			if (position_haut - ((position_haut/tailleMur)*tailleMur)-1 != 0)
				mvmt = "up";
			
			if	(carte.getCase((int)(position_gauche/tailleMur), (int)((position_haut-(vitesse+1))/tailleMur))!='1'
				&& carte.getCase((int)((position_droit)/tailleMur), (int)((position_haut-(vitesse+1))/tailleMur))!='1')
			{
				positionY -= vitesse;
			}
			
			else
				positionY -= position_haut - ((position_haut/tailleMur)*tailleMur)-1;
			
			score = setScore(carte, position_gauche, position_haut, score);
		}	
		
		position_droit = positionX-ecartX+taillePacman;
		position_gauche = positionX-ecartX;
		position_bas = positionY-ecartY+taillePacman;
		position_haut = positionY-ecartY;
		
		if (container.getInput().isKeyDown(Input.KEY_DOWN))
		{
			if ((((position_bas/tailleMur)+1)*tailleMur)-1 - position_bas != 0)
				mvmt = "down";
			
			if	(carte.getCase((int)(position_gauche/tailleMur), (int)((position_bas+(vitesse+1))/tailleMur))!='1'
				&& carte.getCase((int)(position_droit/tailleMur),(int)((position_bas+(vitesse+1))/tailleMur))!='1')
			{
				positionY += vitesse;
			}
			
			else
				if  (carte.getCase((int)((position_droit)/tailleMur), (int)(position_bas/tailleMur))!='1'
				&& carte.getCase((int)((position_gauche)/tailleMur),(int)(position_bas/tailleMur))!='1')
				positionY += (((position_bas/tailleMur)+1)*tailleMur)-1 - position_bas;
			
			score = setScore(carte, position_gauche, position_haut, score);
		}
	}
	
	public void seDeplacerServeur(GameContainer container, Map carte, String requete) 
	{		
		ferme = (ferme + 1) % 30;
		
		int position_droit = positionX-ecartX+taillePacman;
		int position_gauche = positionX-ecartX;
		int position_bas = positionY-ecartY+taillePacman;
		int position_haut = positionY-ecartY;
		
		if (requete.contains("gauche"))
		{
			mvmt = "left";
			if (carte.getCase((int)((position_gauche-(vitesse+1))/tailleMur), (int)(position_haut/tailleMur))!='1'
				&& carte.getCase((int)((position_gauche-(vitesse+1))/tailleMur), (int)(position_bas/tailleMur))!='1')
			{
				positionX -= vitesse;
			}
			
			else
				positionX -= position_gauche - ((position_gauche/tailleMur)*tailleMur)-1;
			
			score = setScore(carte, position_gauche, position_haut, score);
		}
	
		position_droit = positionX-ecartX+taillePacman;
		position_gauche = positionX-ecartX;
		position_bas = positionY-ecartY+taillePacman;
		position_haut = positionY-ecartY;

		if (requete.contains("droite"))
		{
			mvmt = "right";
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
			
			score = setScore(carte, position_gauche, position_haut, score);
		}

		position_droit = positionX-ecartX+taillePacman;
		position_gauche = positionX-ecartX;
		position_bas = positionY-ecartY+taillePacman;
		position_haut = positionY-ecartY;
		
		if (requete.contains("haut"))
		{
			if (position_haut - ((position_haut/tailleMur)*tailleMur)-1 != 0)
				mvmt = "up";
			
			if	(carte.getCase((int)(position_gauche/tailleMur), (int)((position_haut-(vitesse+1))/tailleMur))!='1'
				&& carte.getCase((int)((position_droit)/tailleMur), (int)((position_haut-(vitesse+1))/tailleMur))!='1')
			{
				positionY -= vitesse;
			}
			
			else
				positionY -= position_haut - ((position_haut/tailleMur)*tailleMur)-1;
			
			score = setScore(carte, position_gauche, position_haut, score);
		}	
		
		position_droit = positionX-ecartX+taillePacman;
		position_gauche = positionX-ecartX;
		position_bas = positionY-ecartY+taillePacman;
		position_haut = positionY-ecartY;

		if (requete.contains("bas"))
		{
			if ((((position_bas/tailleMur)+1)*tailleMur)-1 - position_bas != 0)
				mvmt = "down";
			
			if	(carte.getCase((int)(position_gauche/tailleMur), (int)((position_bas+(vitesse+1))/tailleMur))!='1'
				&& carte.getCase((int)(position_droit/tailleMur),(int)((position_bas+(vitesse+1))/tailleMur))!='1')
			{
				positionY += vitesse;
			}
			
			else
				if  (carte.getCase((int)((position_droit)/tailleMur), (int)(position_bas/tailleMur))!='1'
				&& carte.getCase((int)((position_gauche)/tailleMur),(int)(position_bas/tailleMur))!='1')
					positionY += (((position_bas/tailleMur)+1)*tailleMur)-1 - position_bas;
			
			score = setScore(carte, position_gauche, position_haut, score);
		}
	}
}