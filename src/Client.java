import java.io.*;
import java.net.*;
import java.nio.channels.*;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;


public class Client 
{
	private String nomHote, requete;
	private InetAddress adresse;
	private String ip;
	private final int port;
	private final int portDatagram;
	private DatagramPacket paquetRequete;
	private DatagramSocket client;
	//private BufferedReader entree;
	private byte[] donneesRequete;
	private InfoPerso perso;
	private Joueur[] joueurs;
	private Map map;
	private SocketChannel soc;
	private ObjectInputStream ois;
	private int id;
	
	public Client() throws IOException
	{
		nomHote = "localhost";
		adresse = InetAddress.getByName(nomHote);
		ip = adresse.getHostAddress();
		port = 8081;
		portDatagram = 8082;
		client = new DatagramSocket();
		donneesRequete = new byte[500];
		joueurs = null;
		map = null;
	    soc = SocketChannel.open();
	    soc.connect(new InetSocketAddress(ip, port));
        ois = new ObjectInputStream(soc.socket().getInputStream());
	}
	
	public void gererClavierClient(GameContainer container) throws IOException, ClassNotFoundException
	{
		String direction = Integer.toString(id);
		
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
		System.out.println(direction);
		requete = direction;
		donneesRequete = requete.getBytes();
		paquetRequete = new DatagramPacket(donneesRequete, donneesRequete.length, adresse, portDatagram);
		client.send(paquetRequete);
	}
	
	public void reception() throws IOException
	{
		perso = null;
        try {
			perso = (InfoPerso) ois.readObject();
		} catch (ClassNotFoundException e) {e.printStackTrace();}

        joueurs = perso.getJoueur();
        map = perso.getMap();
        id = perso.getId();
	}
	
	public Joueur[] getJoueur()
	{
		return joueurs;
	}
	
	public Map getMap()
	{
		return map;
	}
}
