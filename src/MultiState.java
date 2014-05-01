import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class MultiState extends BasicGameState
{
	public static final int stateID = 10;
	private Map carte;
	private JoueurPacman cs;
	private Fantome[] fantomes;
	private Configuration config;
	private int nbFantomes, ecartX;
	private Image vie;
	private Client client;
	
	@Override
    public int getID() 
    {
        return stateID;
    }
	
	@Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
    	
    	try{
			config = new Configuration("config/config_multi.txt");
			nbFantomes = config.getValeur("nbFantomes");
			ecartX = config.getValeur("ecartX");
	    	carte = new Map("map/map1.txt");
	    	cs= new JoueurPacman("map/map1.txt", "config/config_multi.txt");
	    	fantomes = new Fantome[nbFantomes];
	    	vie = new Image("sprites/heart.png");
	    	client = new Client();
	    	
	    	for(int i=0; i<nbFantomes; i++)
			{
				fantomes[i] = new Fantome("map/map1.txt", "config/config_multi.txt", i);
			}
	    	
	    } catch (IOException e)	{e.printStackTrace();}
    }
	
	@Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics arg) throws SlickException 
    {
    	try{
    		//affichage entites
	    	carte.afficheMap(arg);
	    	cs.affichePacman(arg);
	    	for(int i=0; i<nbFantomes; i++)
	    		fantomes[i].afficheFantome(arg);
	    	
	    	//bandeau fenetre jeu
	    	arg.drawString("Score PacMan : "+cs.getScore(), ecartX+20, 60);
	    	arg.drawString("Vie PacMan : ", ecartX+20, 80);
	    	for(int i=0; i<cs.getVie(); i++) 
	    	{
				arg.drawImage(vie, ecartX+135+15*i,85);
			}
	    } catch (IOException e)			{e.printStackTrace();}
    	
    	//configuration fin niveau
    	if(cs.getCptPieces()==config.getValeur("nbPoints"))
    	{
    		
    	}
    }
	
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws  SlickException 
    {    	
    	try {
			client.gererClavierClient(gc);
			//client.reception();
		} catch (IOException e) {e.printStackTrace();} catch (ClassNotFoundException e) {e.printStackTrace();}
    	
    	//deplacements persos
    	/*cs.seDeplacer(gc, carte);
    	for(int i=0; i<nbFantomes; i++)
    		fantomes[i].dplcmtMulti(gc, carte.getCarte());*/
    	
    }
}
