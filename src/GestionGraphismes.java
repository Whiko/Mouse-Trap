import java.util.Hashtable;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class GestionGraphismes 
{
	private Hashtable<String, Image> sprites, pacman_img;
	
	public GestionGraphismes () throws SlickException
	{
		sprites = new Hashtable<String, Image>();
		sprites.put("mur", new Image("sprites/jeu/mur.png"));
		sprites.put("piece", new Image("sprites/jeu/piece.png"));
		sprites.put("etoile", new Image("sprites/jeu/star.png"));
		sprites.put("fantomeimg1", new Image("sprites/jeu/fantome_1.png"));
		sprites.put("fantomeimg2", new Image("sprites/jeu/fantome_2.png"));
		sprites.put("fantomeimg3", new Image("sprites/jeu/fantome_3.png"));
		sprites.put("fantomeimg4", new Image("sprites/jeu/fantome_4.png"));
		sprites.put("fantomeimg5", new Image("sprites/jeu/fantome_5.png"));
		sprites.put("fantomeimg6", new Image("sprites/jeu/fantome_6.png"));
		sprites.put("fond", new Image("sprites/menu/gameOver.png"));
		sprites.put("vie", new Image("sprites/heart.png"));
		sprites.put("finPartie", new Image("sprites/menu/mp.png"));
		sprites.put("bienJoue", new Image("sprites/menu/bienJoue.png"));
		sprites.put("continuer", new Image("sprites/menu/continuer.png"));
		
		pacman_img = new Hashtable<String, Image>();
		pacman_img.put("pacman_right_ferme", new Image("sprites/jeu/pacman_right_ferme.png"));
		pacman_img.put("pacman_left_ferme", new Image("sprites/jeu/pacman_left_ferme.png"));
		pacman_img.put("pacman_up_ferme", new Image("sprites/jeu/pacman_up_ferme.png"));
		pacman_img.put("pacman_down_ferme", new Image("sprites/jeu/pacman_down_ferme.png"));
		pacman_img.put("pacman_right", new Image("sprites/jeu/pacman_right.png"));
		pacman_img.put("pacman_left", new Image("sprites/jeu/pacman_left.png"));
		pacman_img.put("pacman_up", new Image("sprites/jeu/pacman_up.png"));
		pacman_img.put("pacman_down", new Image("sprites/jeu/pacman_down.png"));

		pacman_img.put("pacman_right_ferme_invincible", new Image("sprites/jeu/pacman_right_ferme_invincible.png"));
		pacman_img.put("pacman_left_ferme_invincible", new Image("sprites/jeu/pacman_left_ferme_invincible.png"));
		pacman_img.put("pacman_up_ferme_invincible", new Image("sprites/jeu/pacman_up_ferme_invincible.png"));
		pacman_img.put("pacman_down_ferme_invincible", new Image("sprites/jeu/pacman_down_ferme_invincible.png"));
		pacman_img.put("pacman_right_invincible", new Image("sprites/jeu/pacman_right_invincible.png"));
		pacman_img.put("pacman_left_invincible", new Image("sprites/jeu/pacman_left_invincible.png"));
		pacman_img.put("pacman_up_invincible", new Image("sprites/jeu/pacman_up_invincible.png"));
		pacman_img.put("pacman_down_invincible", new Image("sprites/jeu/pacman_down_invincible.png"));
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
