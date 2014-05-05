import java.io.IOException;
import java.io.ObjectOutputStream;



public class ThreadEnvoi extends Thread
{
	private InfoPerso infoPerso;
	private ObjectOutputStream[] oos;
	private int nbMaxJoueurs;
	private PartieMulti parent;
	
	public ThreadEnvoi(Joueur joueurs[], Map carte, int nbMaxJoueurs)
	{
		this.nbMaxJoueurs = nbMaxJoueurs;
		oos = new ObjectOutputStream[nbMaxJoueurs];
		infoPerso = new InfoPerso (joueurs, carte);
	}
	
	public void newClient(ObjectOutputStream newOOS, int i) throws IOException
	{
		synchronized (oos) {
			oos[i] = newOOS;
			infoPerso.setId(i);
			try {
				oos[i].writeObject(infoPerso);
				oos[i].reset();
				oos[i].flush();
			} catch(IOException e) {
				System.out.println("connexion perdue avec le joueur "+i);
				oos[i] = null;
			}
		}
	}
	
	public void setInfoPerso(Joueur joueurs[], Map carte)
	{
		synchronized (infoPerso)
		{
			infoPerso = new InfoPerso(joueurs, carte);
		}
	}
	
	public void envoi() throws IOException
	{
		for (int i=0; i<nbMaxJoueurs; i++)
		{
			if(oos[i] != null)
			{
				infoPerso.setId(i);
				synchronized (oos) {
					try {
						oos[i].writeObject(infoPerso);
						oos[i].reset();
						oos[i].flush();
					} catch(IOException e) {
						// si on a un probleme de connexion le joueur i, on le supprime.
						System.out.println("connexion perdue avec le joueur "+i);
						oos[i]=null;
					}
				}
			}
		}
	}
	
	public void run()
	{
		while (true)
		{
			try {
				envoi();
			} catch (IOException e) {e.printStackTrace();}
			try {
				sleep(20);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
}
