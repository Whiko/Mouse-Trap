import java.io.Serializable;


public class InfoScores implements Serializable
{
	private String[] pseudos;
	private int[] scores;
	private int taille;
	
	public InfoScores ()
	{
		taille = 5;
		pseudos = new String[taille];
		scores = new int[taille];
	}
	
	public InfoScores (String[] pseudos, int[] scores)
	{
		this.pseudos = pseudos;
		this.scores = scores;
	}
	
	public void put (String pseudo, int score)
	{
		String tmp_pseudo;
		int tmp_score;
		int i=0;
		
		for (i=0; i<taille && scores[i] != 0; i++)
		{
			if (scores[i] < score)
			{
				tmp_pseudo = pseudo;
				pseudo = pseudos[i];
				pseudos[i] = tmp_pseudo;
				
				tmp_score = score;
				score = scores[i];
				scores[i] = tmp_score;
			}
		}
		
		if (i < taille && scores[i] == 0)
		{
			pseudos[i] = pseudo;
			scores[i] = score;
		}
	}
	
	public String toString()
	{
		String str = "";
		for (int i=0; i<taille; i++)
			if (pseudos[i] != null && scores[i] != 0)
				str += "\n" + pseudos[i] + " " + scores[i] + "\n";
			else
				str += "\npas de scores\n";
		
		return str;
	}
}
