
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
    private GameState game;
    private GameState2 game2;
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
    	 option = new MenuOption();
    	 game= new GameState();
    	 game2= new GameState2();
    	 gameOver= new GameOverState();
    	 
    	 addState(menu);
    	 addState(option);
    	 addState(game);
    	 addState(game2);
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