import java.io.*;
import java.net.*;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;


public class Serveur 
{
	private final int port;
	private DatagramPacket paquetRequete, paquetReponse;
	private DatagramSocket serveur;
	private byte[] donneesReponse, donneesRequete; 
	private String partie, reponse;
	private InfoPerso perso; 
	private ServerSocketChannel soc;
	private SocketChannel s;
	private ObjectOutputStream oos;
	//private Vector<PartieMulti> parties;
	private static int nbJoueurs, nbParties;
	private PartieMulti partieMulti;
	
	public Serveur() throws IOException
	{
		nbJoueurs = 0;
		nbParties = 0;
		partie = "non";
		//parties = new Vector<PartieMulti>();
		port = 8081;
		serveur = new DatagramSocket(port);
		donneesReponse = new byte[500];
		reponse = "";
		soc = ServerSocketChannel.open();
		soc.socket().bind(new InetSocketAddress(port));
	}	
	
	public void acceptJoueur() throws IOException
	{
		while(true)
		{
			s = soc.accept();
			oos = new ObjectOutputStream(s.socket().getOutputStream());

			if (partieMulti == null)
			{
				partieMulti = new PartieMulti();
				partieMulti.start();
			}	
			partieMulti.newClient(oos);
			
			/* pour gerer plusieurs parties ... :
			/*if(parties == null)
			{
				partieMulti = new PartieMulti();
				//parties.add(partieMulti);
				nbParties++;
				nbJoueurs++;
				partieMulti.newClient(oos);
				partieMulti.start();
			}
			
			else
			{
				if(partie == "non")
				{
					nbJoueurs++;
					partieMulti.newClient(oos);
				}
				
				else
				{
					partieMulti = new PartieMulti();
					parties.add(partieMulti);
					nbParties++;
					nbJoueurs++;
					partieMulti.newClient(oos);
					partieMulti.start();
				}
			}*/
		}
	}
	
/*	public void envoi(JoueurPacman joueur, Map map) throws IOException
	{
		perso = new InfoPerso (joueur, map);
		try{
			oos.writeObject(perso);
			oos.reset();
			oos.flush();
		}catch(IOException i)
	      {
	          i.printStackTrace();
	      }
		
	}
	
	public String reception() throws IOException
	{
		donneesRequete = new byte[500];
		paquetRequete = new DatagramPacket(donneesRequete, donneesRequete.length);
		serveur.receive(paquetRequete);
		requete = new String(paquetRequete.getData());
		return requete;
	}*/
}
