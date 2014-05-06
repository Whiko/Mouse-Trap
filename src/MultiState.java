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
	private static Map carte;
	private static Joueur[] joueurs;
	private Configuration config;
	private int nbFantomes, ecartX;
	private Image vie;
	private static Client client;
	private int nbMaxJoueurs;
	private int i=0;
	
	@Override
    public int getID() 
    {
        return stateID;
    }
	
	public static void initClient() throws IOException
	{
    	client = new Client();
    	client.reception();
    	client.start();
	}
	
	@Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
    	
    	try{
    		nbMaxJoueurs = 5;
			config = new Configuration("config/config_multi.txt");
			nbFantomes = config.getValeur("nbFantomes");
			ecartX = config.getValeur("ecartX");
	    	carte = new Map("map/map1.txt");
	    	joueurs = new Joueur[nbMaxJoueurs];
	    	vie = new Image("sprites/heart.png");
	    	
	    } catch (IOException e)	{e.printStackTrace();}
    }
	
	@Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics arg) throws SlickException 
    {
    	GestionGraphismes g = new GestionGraphismes();
    	if (carte != null && joueurs != null)
    	{
	    	try{
	    		//affichage entites
		    	carte.afficheMap(arg, g);
		    	for (int i=0; i<nbMaxJoueurs; i++)
		    	{
		    		if(joueurs[i] instanceof JoueurPacman)
		    			((JoueurPacman)joueurs[i]).affichePacman(arg, g);
		    		
		    		else if(joueurs[i] instanceof Fantome)
		    			((Fantome)joueurs[i]).afficheFantome(arg, g, i);
		    	}
		    	
		    	//bandeau fenetre jeu
		    	arg.drawString("Score PacMan : "+ ((JoueurPacman)joueurs[0]).getScore(), ecartX+20, 60);
		    	arg.drawString("Vie PacMan : ", ecartX+20, 80);
		    	for(int i=0; i<((JoueurPacman)joueurs[0]).getVie(); i++) 
		    	{
					arg.drawImage(vie, ecartX+135+15*i,85);
				}
		    } catch (IOException e)	{e.printStackTrace();}
    	}
    }
	
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws  SlickException 
    {    	
    	try {
			client.gererClavierClient(gc);
		} catch (IOException e) {e.printStackTrace();} catch (ClassNotFoundException e) {e.printStackTrace();}

    	joueurs = client.getJoueur();
    	carte = client.getMap();
    	
    	if (client.getFin())
    		sbg.enterState(MainMenuState.stateID);
    	
    	//game over
    	if(((JoueurPacman)joueurs[0]).getGameOver())
    	{
    		sbg.enterState(GameOverState.stateID);
    	}
    }
}
