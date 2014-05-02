import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState
{
	public static final int stateID = 5;
	private Map carte;
	private static JoueurPacman cs;
	private Fantome[] fantomes;
	private Configuration config;
	private int nbFantomes, ecartX;
	private Image vie;
	private Image finPartie;
	private Image bienJoue;
	private Image continuer;
	private static int score;
	private int i=0;
   
    @Override
    public int getID() 
    {
        return stateID;
    }
    
    
    public static int getScore()
    {
    	return score;
    }
 
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
    	
    	try{
			config = new Configuration("config/config_map.txt");
			nbFantomes = config.getValeur("nbFantomes");
			ecartX = config.getValeur("ecartX");
	    	carte = new Map("map/map1.txt");
	    	cs = new JoueurPacman("map/map1.txt", "config/config_map.txt");
	    	fantomes = new Fantome[nbFantomes];
	    	vie = new Image("sprites/heart.png");
	    	finPartie= new Image("sprites/menu/mp.png");
	    	bienJoue= new Image("sprites/menu/bienJoue.png");
	    	continuer= new Image("sprites/menu/continuer.png");

			for(int i=0; i<nbFantomes; i++)
			{
				fantomes[i] = new Fantome("map/map1.txt", "config/config_map.txt", i);
			}
			
	    } catch (IOException e)	{e.printStackTrace();}
    }
 
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics arg) throws SlickException 
    {
    	try{
    		//affichage entites
    		carte.spawnEtoile();
	    	carte.afficheMap(arg);
	    	cs.affichePacman(arg);
	    	for(int i=0; i<nbFantomes; i++)
	    		fantomes[i].afficheFantome(arg);
	    	
	    	//bandeau fenetre jeu
	    	arg.drawString("Score : "+cs.getScore(), ecartX+20, 60);
	    	arg.drawString("Vie : ", ecartX+20, 80);
	    	for(int i=0; i<cs.getVie(); i++) 
	    	{
				arg.drawImage(vie, ecartX+75+15*i,85);
			}
	    } catch (IOException e)			{e.printStackTrace();}
    	
    	//configuration fin niveau
    	if(cs.getCptPieces()==config.getValeur("nbPoints"))
    	{
    		
    		finPartie.draw(400,200);
    		bienJoue.draw(321,350);
    		continuer.draw(55,450);
    	}
    }
 
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws  SlickException 
    {  
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
    		if(i==0)
    		{
    			score= cs.getScore();
    			i++;
    		}
    		try{
        		cs = new JoueurPacman("map/map1.txt", "config/config_map.txt");
        		carte.reinitMap("map/map1.txt");
        		fantomes = new Fantome[nbFantomes];
        		for(int i=0; i<nbFantomes; i++)
    			{
    				fantomes[i] = new Fantome("map/map1.txt", "config/config_map.txt", i);
    			}
        		} catch (IOException e)	{e.printStackTrace();}
    		sbg.enterState(GameState2.stateID);
    		i=0;
    	}
    	
    	//game over
    	if(cs.getGameOver())
    	{
    		if(i==0)
    		{
    			score= cs.getScore();
    			i++;
    		}
    		try{
	    		fantomes = new Fantome[nbFantomes];
	    		for(int i=0; i<nbFantomes; i++)
				{
					fantomes[i] = new Fantome("map/map1.txt", "config/config_map.txt", i);
				}
	    		carte.reinitMap("map/map1.txt");
	    		cs = new JoueurPacman("map/map1.txt", "config/config_map.txt");
    		} catch (IOException e)	{e.printStackTrace();}
    		sbg.enterState(GameOverState.stateID);
    		i=0;
    	}
    }
}