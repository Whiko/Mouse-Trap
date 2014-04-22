import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuOption extends BasicGameState
{	
	public static final int stateID = 4;
	
	private Image fond= null;
    private Image son=null;
    private Image activer=null;
    private Image desactiver=null;
    private Image commandes=null;
    private Image MScores=null;
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
        fond= new Image("sprites/menu/menu2.png");
        son= new Image("sprites/menu/son.png");
        activer= new Image("sprites/menu/activer.png");
        desactiver=new Image("sprites/menu/desactiver.png");
        commandes=new Image("sprites/menu/commandes.png");
        MScores=new Image("sprites/menu/MScores.png");
        retour= new Image("sprites/menu/retour.png");
    }
    
    
    @Override
    public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException 
    {
    	fond.draw(0,0);
        son.draw(270, 170);
        activer.draw(370,170);
        desactiver.draw(370,210);
        commandes.draw(270,300);
        MScores.draw(270,400);
        retour.draw(670,530);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
    {
        getPosClicked(gc);
        
 
        if ((mouseX > 670 && mouseX < retour.getWidth() + 670) && (mouseY >= 530 && mouseY <= retour.getHeight() + 530)) 
        {
        	sbg.enterState(MainMenuState.stateID);      
        }
        
        if ((mouseX > 370 && mouseX < activer.getWidth() + 370) && (mouseY >= 170 && mouseY <= activer.getHeight() + 170)) 
        {
        	//activer son
        }
        
        if ((mouseX > 370 && mouseX < desactiver.getWidth() + 370) && (mouseY >= 210 && mouseY <= desactiver.getHeight() + 210)) 
        {
        	//desactiver son
        }
        
        if ((mouseX > 270 && mouseX < desactiver.getWidth() + 270) && (mouseY >= 300 && mouseY <= desactiver.getHeight() + 300)) 
        {
        	//commandes
        }
        
        if ((mouseX > 270 && mouseX < desactiver.getWidth() + 270) && (mouseY >= 400 && mouseY <= desactiver.getHeight() + 400)) 
        {
        	//Meilleurs scores
        }
        
    }
}