
/*
 * Cette classe implémente la boucle infinie du jeu :
 * Crée une nouvelle fenetre
 * Lis les entrées
 * Met à jour
 * 
 */

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame; 

public class WindowGame extends StateBasedGame
{
    private MainMenuState menu;
    private MenuOption option;
    private MultiState multi;
    private GameState game;
    private GameState2 game2;
    private GameState3 game3;
    private GameState4 game4;
    private GameState5 game5;
    private GameOverState gameOver;
    static AppGameContainer container;

	public WindowGame()
    {
        super("PacMan");   	 
    }
    
    //initialise le contenu du jeu, charge les graphismes, les sons
    @Override
    public void initStatesList(GameContainer container) throws SlickException
    {
    	//if (container instanceof AppGameContainer) 
    	{ 
    	      //this.container = (AppGameContainer) container;
    	// on stocke le conteneur du jeu !

       	 menu = new MainMenuState();
       	 multi = new MultiState();
    	 option = new MenuOption();
    	 game= new GameState();
    	 game2= new GameState2();
    	 game3=new GameState3();
    	 game4=new GameState4();
    	 game5=new GameState5();
    	 gameOver= new GameOverState();

    	 addState(menu);
    	 addState(multi);
    	 addState(option);
    	 addState(game);
    	 addState(game2);
    	 addState(game3);
    	 addState(game4);
    	 addState(game5);
    	 addState(gameOver);
    	 container.setShowFPS(false);}
    }
    
   /* //affiche le contenu du jeu
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        
    }
    
    //met à jour les elements en fonction du temps delta passé
    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        
    }*/
    
     @Override
    public void keyReleased(int key, char c) 
    {
        if (Input.KEY_ESCAPE == key)
            container.exit();
    }
}