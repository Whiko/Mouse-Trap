import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends BasicGameState
{
	public static final int stateID = 1;
     
    private Image jouer = null;
    private Image multi= null;
    private Image options= null;
    private Image quitter= null;
    private Image fond= null;
    
    //private Sound debut;
    
    private int mouseX = 0;
    private int mouseY = 0;
 
 
    @Override
    public int getID() 
    {
        return stateID;
    }
 
    /**
    * Send the position of the mouse when it clicked.
    *
    * @param gc
    * gc is the game container created by slick
    */
    private void getPosClicked(GameContainer gc) 
    {
        Input input = gc.getInput();
        if (input.isMousePressed(0))
        {
            mouseX = input.getMouseX();
            mouseY = input.getMouseY();
        }
    }
 
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
    {
    	//debut = new Sound("pacman_beginning.wav");
    	//debut.loop();
    	
        jouer = new Image("sprites/menu/bouton1joueur.png");
        multi= new Image("sprites/menu/multi.png");
        options= new Image("sprites/menu/options.png");
        quitter= new Image("sprites/menu/quitter.png");
        fond= new Image("sprites/menu/menu2.png");
    }
 
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics arg) throws SlickException 
    {
    	fond.draw(0,0);
        jouer.draw(80,230);
        multi.draw(380,230);
        options.draw(320,380);
        quitter.draw(530,530);
    }
 
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws  SlickException 
    {
    	//enter(gc,sbg);
    	getPosClicked(gc);
        
        if ((mouseX > 80 && mouseX < jouer.getWidth() + 80) && (mouseY >= 230 && mouseY <= jouer.getHeight() + 230)) 
        {
            //jouer
        	sbg.enterState(GameState.stateID);
        	mouseX = 0;
            mouseY = 0;
        }
        
        if ((mouseX > 380 && mouseX < multi.getWidth() + 380) && (mouseY >= 230 && mouseY <= multi.getHeight() + 230)) 
        {
        	
        	try {
				MultiState.initClient();
			} catch (IOException e) {e.printStackTrace();}
        	sbg.enterState(MultiState.stateID);
        	mouseX = 0;
            mouseY = 0;
        }
        
        if ((mouseX > 320 && mouseX < options.getWidth() + 320) && (mouseY >= 380 && mouseY <= options.getHeight() + 380)) 
        {
        	sbg.enterState(MenuOption.stateID);
        	mouseX = 0;
            mouseY = 0;
        }
        
        if ((mouseX > 530 && mouseX < quitter.getWidth() + 530) && (mouseY >= 530 && mouseY <= quitter.getHeight() + 530)) 
        {
        	gc.exit();
        }    
    }
}
