import java.io.*;
import java.net.*;

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
	private Joueur joueur;
	private Map map;
	
	public Client(Joueur joueur, Map map) throws UnknownHostException, SocketException
	{
		nomHote = "localhost";
		adresse = InetAddress.getByName(nomHote);
		ip = adresse.getHostAddress();
		port = 8081;
		client = new DatagramSocket();
		donneesReponse = new byte[500];
		donneesRequete = new byte[500];
		this.joueur = joueur;
		this.map = map;
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
		FileInputStream fileIn = new FileInputStream("config/donnees_serveur.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        try {
			perso = (InfoPerso) in.readObject();
		} catch (ClassNotFoundException e) {e.printStackTrace();}
        
        in.close();
        fileIn.close();
        
        joueur = perso.getJoueur();
        map = perso.getMap();
	}
	
	public Joueur getJoueur()
	{
		return joueur;
	}
	
	public Map getMap()
	{
		return map;
	}
}
