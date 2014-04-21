import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState
{
	public static final int stateID = 5;
	private Map carte;
	private JoueurPacman cs;
	private Fantome[] fantomes;
	private Configuration config;
	private int nbFantomes, ecartX;
   
    @Override
    public int getID() 
    {
        return stateID;
    }
 
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
    	
    	try{
		config = new Configuration("config/config_map.txt");
		nbFantomes = config.getValeur("nbFantomes");
		ecartX = config.getValeur("ecartX");
    	carte = new Map("map/map1.txt");
    	cs= new JoueurPacman("map/map1.txt");
    	fantomes = cs.getFantomes();
	    } catch (IOException e)	{e.printStackTrace();}
    }
 
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics arg) throws SlickException 
    {
    	try{
    	carte.afficheMap(arg);
    	cs.affichePacman(arg);
    	for(int i=0; i<nbFantomes; i++)
    		fantomes[i].afficheFantome(arg);
    	cs.seDeplacer(gc);
    	for(int i=0; i<nbFantomes; i++)
    		fantomes[i].seDeplacer(gc);
    	
    	//bandeau fenetre jeu
    	arg.drawString("Score : "+cs.getScore(), ecartX+20, 60);
    	arg.drawString("Vie : ", ecartX+20, 80);
    	
	    } catch (IOException e)			{e.printStackTrace();}
    }
 
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws  SlickException 
    {
    	
    }
}