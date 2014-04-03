import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState
{
	public static final int stateID = 5;
	private Map carte;
	private ControleSouris cs;
   
    @Override
    public int getID() 
    {
        return stateID;
    }
 
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
    	
    	try{
    	carte = new Map("map/map.txt");
    	cs= new ControleSouris("map/map.txt");
	    } catch (IOException e)			{e.printStackTrace();}
	    
	    
    }
 
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics arg) throws SlickException 
    {
    	try{
    	carte.afficheMap(arg);
    	cs.afficheSouris(arg);
    	cs.seDeplacer(gc);
	    } catch (IOException e)			{e.printStackTrace();}
    }
 
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws  SlickException 
    {
    }
}