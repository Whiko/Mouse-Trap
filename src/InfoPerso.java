import java.io.Serializable;

public class InfoPerso implements Serializable
{	
	private static final long serialVersionUID = 1L;
	private JoueurPacman perso;
	private Map map;
	
	public InfoPerso(JoueurPacman joueur, Map carte)
	{
		perso = joueur;
		map = carte;
	}
	
	public JoueurPacman getJoueur()
	{
		return perso;
	}
	
	public Map getMap()
	{
		return map;
	}
}
