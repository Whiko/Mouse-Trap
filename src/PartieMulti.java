import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class PartieMulti extends Thread
{
	private Map carte;
	private Joueur[] joueurs;
	private ObjectOutputStream[] oos;
	private int port;
	private DatagramPacket paquetRequete;
	private DatagramSocket socDatagram;
	private byte[] donneesRequete; 
	private String requete;
	private InfoPerso perso; 
	private SocketChannel s;
	private int nbMaxJoueurs;
	
	public PartieMulti()
	{
    	port = 8082;
		try{
			nbMaxJoueurs = 10;
			joueurs = new Joueur[nbMaxJoueurs];
			oos = new ObjectOutputStream[nbMaxJoueurs];
	    	carte = new Map("map/map1.txt");
	    	socDatagram = new DatagramSocket(port);
	    	//socDatagram.getChannel().configureBlocking(false);
			requete = "";
	    } catch (IOException e)	{e.printStackTrace();}
	}
	
	public void newClient(ObjectOutputStream newOOS) throws IOException
	{/*
		if(hashtable == null)
		hashtable.put(oos, new JoueurPacman("map/map1.txt", "config/config_multi.txt"));
	
	else
		hashtable.put(oos, new Fantome("map/map1.txt", "config/config_multi.txt"));
	
	identifiant.put(id, oos);*/

		synchronized (joueurs) {
			synchronized (oos) {
				int i = 0;
				if(joueurs[i] == null)
					joueurs[i] = new JoueurPacman("map/map1.txt", "config/config_multi.txt");
				
				else
				{
					while (joueurs[i] != null && i < nbMaxJoueurs)
						i++;
					joueurs[i] = new Fantome("map/map1.txt", "config/config_multi.txt");
				}
				oos[i] = newOOS;
				
				perso = new InfoPerso (joueurs, carte);
				perso.setId(i);
				try {
					oos[i].writeObject(perso);
					oos[i].reset();
					oos[i].flush();
				} catch(IOException e) {
					System.out.println("connexion perdue avec le joueur "+i);
				}
			}
		}
	}
	
	public void reception() throws IOException
	{
		requete = "";
		donneesRequete = new byte[500];
		paquetRequete = new DatagramPacket(donneesRequete, donneesRequete.length);
		socDatagram.receive(paquetRequete);
		requete = new String(paquetRequete.getData());
	}
	
	public void traitementRequete()
	{
		if (requete.length() >= 3) {
			int id = requete.charAt(0) - 48;
			joueurs[id].seDeplacerServeur(carte, requete);
		}
	}
	
	public void run()
	{
		//reception
		while (true)
		{
			try {
				reception();
			} catch (IOException e1) {e1.printStackTrace();}
			traitementRequete();
	
			synchronized (joueurs) {
				Fantome[] fantomes = {(Fantome)joueurs[1],(Fantome)joueurs[2],(Fantome)joueurs[3],(Fantome)joueurs[4],(Fantome)joueurs[5],(Fantome)joueurs[6],(Fantome)joueurs[7],(Fantome)joueurs[8],(Fantome)joueurs[9]};
		    	((JoueurPacman)joueurs[0]).gestionContact(fantomes);
			}
	    	try {
	    		envoi();
			} catch (IOException e) {e.printStackTrace();}
		}
		/*
    	//deplacements persos
		for (int i=0; i<nbMaxJoueurs; i++)
	    {
	    	JoueurPacman j;
	    	Fantome f;
	    	
	    	if(joueurs[i] instanceof JoueurPacman)
	    	{
	    		j = (JoueurPacman)itr.next();
	    		j.seDeplacerServeur(carte, requete);
	    		j.gestionContact()
	    	}
	    	
	    	else
	    	{
	    		f = (Fantome) itr.next();
	    		f.seDeplacerServeur(carte, requete);
	    	}
	    }*/
	}
	
	public void envoi() throws IOException
	{
		synchronized (joueurs) {
			synchronized (oos) {
				for (int i=0; i<nbMaxJoueurs; i++)
				{
					perso = new InfoPerso (joueurs, carte);
					if(joueurs[i] != null && oos[i] != null)
					{
						perso.setId(i);
						try {
							oos[i].writeObject(perso);
							oos[i].reset();
							oos[i].flush();
						} catch(IOException e) {
							System.out.println("connexion perdue avec le joueur "+i);
							oos[i]=null;
							joueurs[i]=null;
						}
					}
				}
			}
		}
	}
}
