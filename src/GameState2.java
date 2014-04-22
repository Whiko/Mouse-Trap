import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState2 extends BasicGameState
{
	public static final int stateID = 6;
	private Map carte;
	private JoueurPacman cs;
	private Fantome[] fantomes;
	private Configuration config;
	private int nbFantomes, ecartX;
	private Image vie;
	private Image finPartie;
	private Image bienJoue;
   
	
    @Override
    public int getID() 
    {
        return stateID;
    }
 
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
    	
    	try{
		config = new Configuration("config/config_map2.txt");
		nbFantomes = config.getValeur("nbFantomes");
		ecartX = config.getValeur("ecartX");
    	carte = new Map("map/map2.txt");
    	cs= new JoueurPacman("map/map2.txt");
    	fantomes = cs.getFantomes();
    	vie = new Image("sprites/heart.png");
    	finPartie= new Image("sprites/menu/mp.png");
    	bienJoue= new Image("sprites/menu/bienJoue.png");
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
    	carte.affichePiece(arg);
    	for(int i=0; i<nbFantomes; i++)
    		fantomes[i].seDeplacer(gc);
    	
    	//bandeau fenetre jeu
    	arg.drawString("Score : "+cs.getScore(), ecartX+20, 60);
    	arg.drawString("Vie : ", ecartX+20, 80);
    	for(int i=0; i<cs.getVie(); i++) 
    	{
			arg.drawImage(vie, ecartX+75+15*i,85);
		}
    	
	    } catch (IOException e)			{e.printStackTrace();}
    	
    	if(cs.getCptPieces()==134)
    	{
    		finPartie.draw(400,270);
    		bienJoue.draw(321,400);
    	}
    }
 
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws  SlickException 
    {
    	
    }
}