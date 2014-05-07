import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuCommandes extends BasicGameState
{	
	public static final int stateID = 11;
	
	private Image fond= null;
    private Image retour=null;
    private int mouseX = 0;
    private int mouseY = 0;
    

	@Override
    public int getID() 
	{
        return stateID;
    }
    
    private void getPosClicked(GameContainer gc) 
    {
        Input input = gc.getInput();
        if (input.isMouseButtonDown(0)) {
            mouseX = input.getMouseX();
            mouseY = input.getMouseY();
        }
    }

    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
    {
        fond= new Image("sprites/menu/menuCommandes.png");
        retour= new Image("sprites/menu/retour.png");
    }
    
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics arg) throws SlickException 
    {
    	fond.draw(0,0);
        retour.draw(670,530);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
    {
        getPosClicked(gc);
        
        if ((mouseX > 670 && mouseX < retour.getWidth() + 670) && (mouseY >= 530 && mouseY <= retour.getHeight() + 530)) 
        {
        	sbg.enterState(MenuOption.stateID);  
        	mouseX = 0;
            mouseY = 0;
        }
    }
}
