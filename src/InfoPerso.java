
public class InfoPerso 
{	
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
