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
	
	public Client() throws UnknownHostException, SocketException
	{
		nomHote = "localhost";
		adresse = InetAddress.getByName(nomHote);
		ip = adresse.getHostAddress();
		port = 8081;
		client = new DatagramSocket();
		donneesReponse = new byte[4000];
		donneesRequete = new byte[4000];
	}
	
	public void gererClavierClient(GameContainer container) throws IOException, ClassNotFoundException
	{
		if (container.getInput().isKeyDown(Input.KEY_LEFT))
			envoi("gauche");

		if (container.getInput().isKeyDown(Input.KEY_RIGHT)) 
			envoi("droite");

		if (container.getInput().isKeyDown(Input.KEY_UP))
			envoi("haut");

		if (container.getInput().isKeyPressed(Input.KEY_DOWN))
			envoi("bas");
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
		paquetReponse = new DatagramPacket(donneesReponse, donneesReponse.length);
		client.receive(paquetReponse);
	}
}
