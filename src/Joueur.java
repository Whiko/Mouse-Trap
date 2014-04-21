import org.newdawn.slick.GameContainer;


public abstract class Joueur 
{
	protected String pseudo;
	protected int score;
	protected int vie;
	
	public Joueur()
	{
		pseudo = "";
		score = 0;
		vie = 3;
	}

	public void seDeplacer(GameContainer container){}
}
