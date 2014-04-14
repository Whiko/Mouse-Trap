import java.io.IOException;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Fantome extends Joueur
{
	private Map fichierCarte;
	private boolean visible;
	private int positionX, positionY, tailleMur, tailleFantome;
	private Configuration config;
	private int carte[][];
	private Image fantomeimg;
	
	public Fantome(String path) throws IOException, SlickException
	{
		visible = true;
		fichierCarte = new Map(path);
		config = new Configuration("config/config_map.txt");
		positionX = config.getValeur("pointDepartFantomeX");
		positionY = config.getValeur("pointDepartFantomeY");
		tailleMur = config.getValeur("taillePerso");
		carte = fichierCarte.getCarte();
		fantomeimg = new Image("sprites/sprites_Cyriaque/sprites_Julie/pacman.png");
		
	}
	
	public void afficheFantome(Graphics fantome)
	{
		fantome.drawImage(fantomeimg, positionX, positionY);
	}
	
	/*public void deplacement()
	{
		if  (carte[(int)((position_gauche-3)/tailleMur)][(int)(position_haut/tailleMur)]!='1'
				&& carte[(int)((position_gauche-3)/tailleMur)][(int)(position_bas/tailleMur)]!='1')
	}*/
}
