import java.io.Serializable;

public class InfoPerso implements Serializable
{	
	private static final long serialVersionUID = 1L;
	private Joueur perso;
	private Map map;
	
	public InfoPerso(Joueur joueur, Map carte)
	{
		perso = joueur;
		map = carte;
	}
	
	public Joueur getJoueur()
	{
		return perso;
	}
	
	public Map getMap()
	{
		return map;
	}
}
