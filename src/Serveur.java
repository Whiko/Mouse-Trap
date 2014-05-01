import java.io.IOException;
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
	
	public Serveur() throws UnknownHostException, SocketException
	{
		port = 8081;
		serveur = new DatagramSocket(port);
		donneesReponse = new byte[4000];
		requete = "";
	}
	
	/*public void envoi(int positionX, int positionY) throws IOException
	{
		
		donneesReponse = reponse.getBytes();
		paquetReponse = new DatagramPacket(donneesReponse, donneesReponse.length, paquetRequete.getAddress(), paquetRequete.getPort());
		serveur.send(paquetRequete);
	}*/
	
	public String reception() throws IOException
	{
		donneesRequete = new byte[4000];
		paquetRequete = new DatagramPacket(donneesRequete, donneesRequete.length);
		serveur.receive(paquetRequete);
		requete = new String(paquetRequete.getData());
		return requete;
	}
}
