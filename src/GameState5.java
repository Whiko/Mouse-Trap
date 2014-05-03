import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState5 extends BasicGameState
{
	public static final int stateID = 9;
	private Map carte;
	private static JoueurPacman cs;
	private Fantome[] fantomes;
	private Configuration config;
	private int nbFantomes, ecartX;
	private Image vie;
	private Image finPartie;
	private Image bienJoue;
    private Fenetre fenetre;
	private Image continuer;
	private static int score;
	private static int csVie;
	private int i=0,j=0;
	
	
    @Override
    public int getID() 
    {
        return stateID;
    }
 
    public static int getScore()
    {
    	int scoreTot=score+GameState.getScore()+GameState2.getScore()+GameState3.getScore()+GameState4.getScore();
    	return scoreTot;
    }
    
    public static int getVie()
    {
    	return csVie;
    }
    
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
    	
    	try{
		config = new Configuration("config/config_map5.txt");
		nbFantomes = config.getValeur("nbFantomes");
		ecartX = config.getValeur("ecartX");
    	carte = new Map("map/map5.txt");
    	cs= new JoueurPacman("map/map5.txt", "config/config_map5.txt");
    	fantomes = new Fantome[nbFantomes];
    	vie = new Image("sprites/heart.png");
    	finPartie= new Image("sprites/menu/mp.png");
    	bienJoue= new Image("sprites/menu/bienJoue.png");
    	continuer= new Image("sprites/menu/continuer.png");
    	
    	for(int i=0; i<nbFantomes; i++)
		{
			fantomes[i] = new Fantome("map/map5.txt", "config/config_map5.txt");
		}
    	
	    } catch (IOException e)	{e.printStackTrace();}
    }
 
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics arg) throws SlickException 
    {
    	GestionGraphismes g = new GestionGraphismes();
    	try{
    		//affichage entites
    		carte.spawnEtoile();
	    	carte.afficheMap(arg, g);
	    	cs.affichePacman(arg, g);
	    	for(int i=0; i<nbFantomes; i++)
	    		fantomes[i].afficheFantome(arg, g, i);
	    	
	    	//bandeau fenetre jeu
	    	arg.drawString("Score : "+cs.getScore(), ecartX+20, 60);
	    	arg.drawString("Vie : ", ecartX+20, 80);
	    	for(int i=0;j!=0 && i<cs.getVie(); i++) 
	    	{
				arg.drawImage(vie, ecartX+75+15*i,85);
			}
	    } catch (IOException e)			{e.printStackTrace();}
    	
    	
    	if(cs.getCptPieces()==config.getValeur("nbPoints"))
    	{
    		finPartie.draw(400,270);
    		bienJoue.draw(321,400);
    		continuer.draw(55,450);
    	}
    }
 
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws  SlickException 
    {
    	if(j==0)
    	{
    		cs.setVie(GameState4.getVie());
			j++;
    	}
    	
    	
    	//deplacements persos
    	if(!cs.getGameOver() && cs.getCptPieces()<config.getValeur("nbPoints"))
    	{
    		cs.gestionContact(fantomes);
    		cs.seDeplacer(gc, carte);
    		for(int i=0; i<nbFantomes; i++)
    			fantomes[i].seDeplacer(gc, carte.getCarte());
    	}
    	
    	//changements d'etats
    	//niveau suivant
    	if(cs.getCptPieces()==config.getValeur("nbPoints") && gc.getInput().isKeyDown((Input.KEY_ENTER)))
    	{
        	if (fenetre==null) 
        	{
        		fenetre = new Fenetre(0);//GameState5.getScore());	
    	    	fenetre.setVisible(true);
        	}
    	}

    	//game over
    	if(fenetre!=null && !fenetre.isVisible())
    	{
    		if(i==0)
    		{
    			score= cs.getScore();
    			csVie=cs.getVie();
    			i++;
    		}
	    	fenetre = null;
	    	try{
	    		cs = new JoueurPacman("map/map5.txt", "config/config_map5.txt");
		    	fantomes = new Fantome[nbFantomes];
		    	for(int i=0; i<nbFantomes; i++)
				{
					fantomes[i] = new Fantome("map/map5.txt", "config/config_map5.txt");
				}
	    		carte.reinitMap("map/map5.txt");
    		} catch (IOException e)	{e.printStackTrace();}
    		i=0;
    		j=0;
    		sbg.enterState(MainMenuState.stateID);
    	}
		
    	if(cs.getGameOver())
    	{
    		if(i==0)
    		{
    			score= cs.getScore();
    			i++;
    		}
    		try{
	    		cs = new JoueurPacman("map/map5.txt", "config/config_map5.txt");
		    	fantomes = new Fantome[nbFantomes];
		    	for(int i=0; i<nbFantomes; i++)
				{
					fantomes[i] = new Fantome("map/map5.txt", "config/config_map5.txt");
				}
	    		carte.reinitMap("map/map5.txt");
    		} catch (IOException e)	{e.printStackTrace();}
    		i=0;
    		j=0;
    		sbg.enterState(GameOverState.stateID);
    	}
    }
}