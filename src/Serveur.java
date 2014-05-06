import java.io.*;
import java.net.*;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


public class Serveur 
{
	private final int port;
	private ServerSocketChannel soc;
	private SocketChannel s;
	private ObjectOutputStream oos;
	private PartieMulti partieMulti;
	
	public Serveur() throws IOException
	{
		port = 8081;
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
}
