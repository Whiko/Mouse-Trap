import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class PartieMulti extends Thread
{
	private Map carte;
	private Joueur[] joueurs;
	private ThreadEnvoi envoi;
	private int port;
	private DatagramPacket paquetRequete;
	private DatagramChannel socDatagram;
	private ByteBuffer buf;
	private String requete;
	private SocketChannel s;
	private int nbMaxJoueurs;
	
	public PartieMulti()
	{
    	port = 8082;
		try{
			nbMaxJoueurs = 5;
			joueurs = new Joueur[nbMaxJoueurs];
	    	carte = new Map("map/map1.txt");
	    	socDatagram = DatagramChannel.open();
	    	socDatagram.socket().bind(new InetSocketAddress(port));
			requete = "";
			envoi = new ThreadEnvoi(joueurs, carte, nbMaxJoueurs);
			envoi.setInfoPerso(joueurs, carte);
			envoi.start();
	    } catch (IOException e)	{e.printStackTrace();}
	}
	
	public void newClient(ObjectOutputStream newOOS) throws IOException
	{
		synchronized (joueurs) {
			int i = 0;
			if(joueurs[i] == null)
				joueurs[i] = new JoueurPacman("map/map1.txt", "config/config_multi.txt");
			
			else
			{
				while (joueurs[i] != null && i < nbMaxJoueurs)
					i++;
				joueurs[i] = new Fantome("map/map1.txt", "config/config_multi.txt");
			}
			envoi.newClient(newOOS, i);
		}
	}
	
	public void reception() throws IOException
	{
		socDatagram.configureBlocking(false);
		requete = "";
		buf = ByteBuffer.allocate(50);
		buf.clear();
		socDatagram.receive(buf);
		requete = new String(buf.array());
	}
	
	public void traitementRequete()
	{
		int id = requete.charAt(0) - 48;
		if (id >= 0 && id < nbMaxJoueurs) {
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

			synchronized (joueurs) {
				traitementRequete();
		
				if (joueurs[0] != null)
				{
					Fantome[] fantomes = new Fantome[nbMaxJoueurs-1];
					for (int i=1; i<nbMaxJoueurs; i++)
						fantomes[i-1] = (Fantome) joueurs[i];
					((JoueurPacman)joueurs[0]).gestionContact(fantomes);
				}
			}
			synchronized (carte) {
				carte.spawnEtoile();
			}
			envoi.setInfoPerso(joueurs, carte);
			try {
				sleep(15);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
}
