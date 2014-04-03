

import org.newdawn.slick.GameContainer; 
import org.newdawn.slick.Graphics; 
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException; 
import org.newdawn.slick.state.BasicGameState; 
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState 
 { 
	public static final int ID = 1; 
	private Image msp;
	@Override
	public int getID() {
		return ID;
	}
	
	 public void init(GameContainer container, StateBasedGame game) throws SlickException { 
		 msp = new Image ("p.png");
		 //¨pour cette exemple, on a rien à initialiser. 
	 }
	 
	 public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException { 
		 g.drawString("Hello World", 0, 0); // méthode pour afficher un message à l’ecran
		 msp.draw(100,20);
	 } 	
	 
	 public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException { 
		 // pour cette exemple, on a rien à mettre à jour. 
	 } 
}
