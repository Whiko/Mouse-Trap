import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;


public class Client extends Thread
{
	private String nomHote;
	private InetSocketAddress adresse;
	private String ip;
	private final int port;
	private final int portDatagram;
	private DatagramChannel socDatagram;
	private InfoPerso perso;
	private Joueur[] joueurs;
	private Map map;
	private SocketChannel soc;
	private ObjectInputStream ois;
	private int id;
	private ByteBuffer buf;
	private boolean fin;
	
	public Client() throws IOException
	{
		fin = false;
		port = 8081;
		portDatagram = 8082;
		nomHote = "192.168.1.11";
		adresse = new InetSocketAddress(nomHote, portDatagram);
		ip = adresse.getAddress().getHostAddress();
		socDatagram = DatagramChannel.open();
		joueurs = null;
		map = null;
	    soc = SocketChannel.open();
	    soc.connect(new InetSocketAddress(ip, port));
        ois = new ObjectInputStream(soc.socket().getInputStream());
        buf = ByteBuffer.allocate(50);
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
		if (direction.length() >= 3) {
	        buf = ByteBuffer.allocate(50);
	        buf.clear();
	        buf.put(direction.getBytes());
	        buf.flip();
			socDatagram.send(buf, adresse);
		}
	}
	
	public void reception() throws IOException
	{
		perso = null;
        try {
			perso = (InfoPerso) ois.readObject();
        } catch (Exception e) {
    		System.out.println("connexion avec le serveur perdue...");
        	fin = true;
        }
        if (perso != null)
        {
    		joueurs = perso.getJoueur();
    		map = perso.getMap();
    		id = perso.getId();
        }
	}
	
	public boolean getFin()
	{
		return fin;
	}
	
	public Joueur[] getJoueur()
	{
		return joueurs;
	}
	
	public Map getMap()
	{
		return map;
	}
	
	public void run()
	{
		while (!fin)
		{
			try {
				reception();
			} catch (IOException e1) {e1.printStackTrace();}
			try {
				sleep(15);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
}
