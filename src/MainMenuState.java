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
     
    private Image title=null;
    private Image pacM=null;
    private Image MrsPacman = null;
    private Image jouer = null;
    private Image multi= null;
    private Image options= null;
    private Image quitter= null;
    
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
    	
    	title = new Image("sprites/menu/title.png");
    	pacM= new Image ("sprites/menu/pacM.png");
        MrsPacman = new Image("sprites/menu/p.png");
        jouer = new Image("sprites/menu/bouton1joueur.png");
        multi= new Image("sprites/menu/multi.png");
        options= new Image("sprites/menu/options.png");
        quitter= new Image("sprites/menu/quitter.png");
    }
 
    @Override
    public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException 
    {
    	title.draw(140,10);
        MrsPacman.draw(10,285);
    	pacM.draw(175,182);
        jouer.draw(200, 175);
        pacM.draw(280,257);
        multi.draw(305,250);
        pacM.draw(395,327);
        options.draw(420,320);
        quitter.draw(530,430);
    }
 
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws  SlickException 
    {
    	enter(gc,sbg);
    	getPosClicked(gc);
        
        if ((mouseX > 200 && mouseX < jouer.getWidth() + 200) && (mouseY >= 175 && mouseY <= jouer.getHeight() + 175)) 
        {
            //jouer
        	sbg.enterState(GameState.stateID);
        }
        
        if ((mouseX > 305 && mouseX < multi.getWidth() + 305) && (mouseY >= 250 && mouseY <= multi.getHeight() + 250)) 
        {
        	//Reseau
        }
        
        if ((mouseX > 420 && mouseX < options.getWidth() + 420) && (mouseY >= 320 && mouseY <= options.getHeight() + 320)) 
        {
        	sbg.enterState(MenuOption.stateID);
        }
        
        if ((mouseX > 540 && mouseX < quitter.getWidth() + 540) && (mouseY >= 430 && mouseY <= quitter.getHeight() + 430)) 
        {
        	gc.exit();
        }    
    }
}
