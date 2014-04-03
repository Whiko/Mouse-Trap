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
    private Image pacM=null;
    private Image fantome = null;
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
    	pacM= new Image ("sprites/menu/pacM.png");
        fantome = new Image("sprites/menu/fantomebleu.png");
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
        fantome.draw(10,285);
        son.draw(150, 100);
        pacM.draw(275,107);
        activer.draw(300,100);
        pacM.draw(275,142);
        desactiver.draw(300,135);
        pacM.draw(175,237);
        commandes.draw(200,230);
        pacM.draw(200,327);
        MScores.draw(225,320);
        retour.draw(540,405);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException 
    {//enter(gc,sbg);
        getPosClicked(gc);
        
 
        if ((mouseX > 540 && mouseX < retour.getWidth() + 540) && (mouseY >= 405 && mouseY <= retour.getHeight() + 405)) 
        {
        	sbg.enterState(MainMenuState.stateID);      
        }
        
        if ((mouseX > 305 && mouseX < activer.getWidth() + 305) && (mouseY >= 250 && mouseY <= activer.getHeight() + 250)) 
        {
        	//activer son
        }
        
        if ((mouseX > 420 && mouseX < desactiver.getWidth() + 420) && (mouseY >= 320 && mouseY <= desactiver.getHeight() + 320)) 
        {
        	//desactiver son
        }
        
    }
}