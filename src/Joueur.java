import java.io.Serializable;

import org.newdawn.slick.GameContainer;

public abstract class Joueur implements Serializable
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
	
	public void seDeplacerServeur(Map carte, String requete) {}
}
