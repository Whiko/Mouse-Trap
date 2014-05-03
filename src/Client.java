import java.io.*;
import java.net.*;
import java.nio.channels.*;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;


public class Client 
{
	private String nomHote, requete, reponse;
	private InetAddress adresse;
	private String ip;
	private final int port;
	private DatagramPacket paquetRequete, paquetReponse;
	private DatagramSocket client;
	//private BufferedReader entree;
	private byte[] donneesReponse, donneesRequete;
	private InfoPerso perso;
	private JoueurPacman joueur;
	private Map map;
	private SocketChannel soc;
	private ObjectInputStream ois;
	
	public Client() throws IOException
	{
		nomHote = "localhost";
		adresse = InetAddress.getByName(nomHote);
		ip = adresse.getHostAddress();
		port = 8081;
		client = new DatagramSocket();
		donneesReponse = new byte[500];
		donneesRequete = new byte[500];
		joueur = null;
		map = null;
	    soc = SocketChannel.open();
	    soc.connect(new InetSocketAddress(ip, port));
        ois = new ObjectInputStream(soc.socket().getInputStream());
	}
	
	public void gererClavierClient(GameContainer container) throws IOException, ClassNotFoundException
	{
		String direction = "";
		
		if (container.getInput().isKeyDown(Input.KEY_LEFT))
			direction += "gauche"; 

		if (container.getInput().isKeyDown(Input.KEY_RIGHT)) 
			direction += "droite"; 

		if (container.getInput().isKeyDown(Input.KEY_UP))
			direction += "haut"; 

		if (container.getInput().isKeyDown(Input.KEY_DOWN))
			direction += "bas"; 
		
		envoi(direction);
	}
	
	public void envoi(String direction) throws IOException
	{
		requete = direction;
		donneesRequete = requete.getBytes();
		paquetRequete = new DatagramPacket(donneesRequete, donneesRequete.length, adresse, port);
		client.send(paquetRequete);
	}
	
	public void reception() throws IOException
	{
		perso = null;
        try {
			perso = (InfoPerso) ois.readObject();
		} catch (ClassNotFoundException e) {e.printStackTrace();}

        joueur = perso.getJoueur();
        map = perso.getMap();
	}
	
	public JoueurPacman getJoueur()
	{
		return joueur;
	}
	
	public Map getMap()
	{
		return map;
	}
}
