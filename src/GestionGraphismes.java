import java.util.Hashtable;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class GestionGraphismes 
{
	private Hashtable<String, Image> sprites, pacman_img;
	private Image mur, piece, etoile, fantomeimg1, fantomeimg2, fantomeimg3, fantomeimg4, fantomeimg5, fantomeimg6;
	
	public GestionGraphismes () throws SlickException
	{
		sprites = new Hashtable<String, Image>();
		sprites.put("mur", new Image("sprites/sprites_Cyriaque/sprites_Julie/mur.png"));
		sprites.put("piece", new Image("sprites/sprites_Cyriaque/originales/piece.png"));
		sprites.put("etoile", new Image("sprites/sprites_Cyriaque/originales/star.png"));
		sprites.put("fantomeimg1", new Image("sprites/sprites_Cyriaque/sprites_Julie/fantome_1.png"));
		sprites.put("fantomeimg2", new Image("sprites/sprites_Cyriaque/sprites_Julie/fantome_2.png"));
		sprites.put("fantomeimg3", new Image("sprites/sprites_Cyriaque/sprites_Julie/fantome_3.png"));
		sprites.put("fantomeimg4", new Image("sprites/sprites_Cyriaque/sprites_Julie/fantome_4.png"));
		sprites.put("fantomeimg5", new Image("sprites/sprites_Cyriaque/sprites_Julie/fantome_5.png"));
		sprites.put("fantomeimg6", new Image("sprites/sprites_Cyriaque/sprites_Julie/fantome_6.png"));
		sprites.put("fond", new Image("sprites/menu/gameOver.png"));
		sprites.put("vie", new Image("sprites/heart.png"));
		sprites.put("finPartie", new Image("sprites/menu/mp.png"));
		sprites.put("bienJoue", new Image("sprites/menu/bienJoue.png"));
		sprites.put("continuer", new Image("sprites/menu/continuer.png"));
		
		pacman_img = new Hashtable<String, Image>();
		pacman_img.put("pacman_right_ferme", new Image("sprites/sprites_Cyriaque/sprites_Julie/pacman_right_ferme.png"));
		pacman_img.put("pacman_left_ferme", new Image("sprites/sprites_Cyriaque/sprites_Julie/pacman_left_ferme.png"));
		pacman_img.put("pacman_up_ferme", new Image("sprites/sprites_Cyriaque/sprites_Julie/pacman_up_ferme.png"));
		pacman_img.put("pacman_down_ferme", new Image("sprites/sprites_Cyriaque/sprites_Julie/pacman_down_ferme.png"));
		pacman_img.put("pacman_right", new Image("sprites/sprites_Cyriaque/sprites_Julie/pacman_right.png"));
		pacman_img.put("pacman_left", new Image("sprites/sprites_Cyriaque/sprites_Julie/pacman_left.png"));
		pacman_img.put("pacman_up", new Image("sprites/sprites_Cyriaque/sprites_Julie/pacman_up.png"));
		pacman_img.put("pacman_down", new Image("sprites/sprites_Cyriaque/sprites_Julie/pacman_down.png"));
	}
	
	public Image getImage(String nom)
	{
		return sprites.get(nom);
	}
	
	public Image getPacman(String nom)
	{
		return pacman_img.get(nom);
	}
}
