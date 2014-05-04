/*import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class ServeurState extends BasicGameState
{
	public static final int stateID = 5;
	
   
    @Override
    public int getID() 
    {
        return stateID;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
    	
    	
    }
 
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics arg) throws SlickException 
    {
    	GestionGraphismes g = new GestionGraphismes();
    	try{
    		//affichage entites
	    	carte.afficheMap(arg, g);
	    	cs.affichePacman(arg, g);
	    	for(int i=0; i<nbFantomes; i++)
	    		fantomes[i].afficheFantome(arg, g, i);
	    	
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
    	
    	
    	//renvoit les donnees du jeu
    	//serveur.envoi(cs.getX(), cs.getY());
    	
    }
}*/
