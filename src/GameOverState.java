import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOverState extends BasicGameState
{
	public static final int stateID = 2;
     
    private Image fond;
    private Fenetre fenetreOptions;
	
     
 
    @Override
    public int getID() 
    {
        return stateID;
    }
 
    
 
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
    {
    	fond =new Image("sprites/menu/gameOver.png");
    }
 
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics arg2) throws SlickException 
    {
    	fond.draw(0,0);
    	if (gc.getInput().isKeyDown((Input.KEY_ENTER))) 
    	{
    		fenetreOptions = new Fenetre();
    		fenetreOptions.setVisible(true);
    	}
    		
    }
 
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws  SlickException 
    {
    	if (gc.getInput().isKeyDown((Input.KEY_ENTER)) && !fenetreOptions.isVisible()) 
    			sbg.enterState(MainMenuState.stateID);
    }
}
