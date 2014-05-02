import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class Serveur 
{
	private final int port;
	private DatagramPacket paquetRequete, paquetReponse;
	private DatagramSocket serveur;
	private byte[] donneesReponse, donneesRequete; 
	private String requete;
	private InfoPerso perso; 
	
	public Serveur() throws UnknownHostException, SocketException
	{
		port = 8081;
		serveur = new DatagramSocket(port);
		donneesReponse = new byte[500];
		requete = "";
	}
	
	public void envoi(Joueur joueur, Map map) throws IOException
	{
		perso = new InfoPerso (joueur, map);
		try{
			FileOutputStream fileOut = new FileOutputStream("config/donnees_serveur.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(perso);
			out.flush();
			out.close();
			fileOut.close();
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
	}
}
