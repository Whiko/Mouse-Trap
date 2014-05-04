import java.io.Serializable;

public class InfoPerso implements Serializable
{	
	private static final long serialVersionUID = 1L;
	private Joueur[] joueurs;
	private Map map;
	private int id;
	
	public InfoPerso(Joueur[] joueurs, Map carte)
	{
		this.joueurs = joueurs;
		map = carte;
		id = 0;
	}
	
	public Joueur[] getJoueur()
	{
		return joueurs;
	}

	public Map getMap()
	{
		return map;
	}

	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return id;
	}
}
