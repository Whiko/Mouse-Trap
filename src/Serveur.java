import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


public class Serveur 
{
	private final int port;
	private DatagramPacket paquetRequete, paquetReponse;
	private DatagramSocket serveur;
	private byte[] donneesReponse, donneesRequete; 
	private String requete;
	private InfoPerso perso; 
	private ServerSocketChannel soc;
	private SocketChannel s;
	private ObjectOutputStream oos;
	
	public Serveur() throws IOException
	{
		port = 8081;
		serveur = new DatagramSocket(port);
		donneesReponse = new byte[500];
		requete = "";
		soc = ServerSocketChannel.open();
		soc.socket().bind(new InetSocketAddress(port));
		s = soc.accept();
		oos = new ObjectOutputStream(s.socket().getOutputStream());
	}
	
	public void envoi(JoueurPacman joueur, Map map) throws IOException
	{
        System.out.println(joueur.getX());
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
	}
}
