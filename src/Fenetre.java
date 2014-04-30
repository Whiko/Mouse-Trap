import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;





import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout; 
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;

import org.newdawn.slick.Input;


public class Fenetre extends JFrame{
	private JButton bouton = new JButton("Enregistrer");
	private JButton cancelBouton = new JButton("Annuler");
	public JPanel container = new JPanel();
	private JTextField jtf = new JTextField("votre nom");
	private JLabel label = new JLabel("Pseudo: ");
	private JLabel label2 = new JLabel("Votre score: ");
	private JLabel label3 = new JLabel("0"/*+JoueurPacman.getScore()*/);


	public Fenetre(){
	    this.setTitle("Enregistrement");
	    this.setSize(300, 240);
	    this.setLocationRelativeTo(null); //fenetre au centre
	    //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    
	    //Changement de la couleur du texte
	    //label.setForeground(Color.blue);
	    //On modifie l'alignement du texte grâce aux attributs statiques
	    //de la classe JLabel
	    //label.setHorizontalAlignment(JLabel.CENTER);
	          
	    
	    this.setLayout(new GridLayout(3, 2));
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    JPanel top = new JPanel();
	    Font police = new Font("Comic sans MS", Font.BOLD, 14);
	    jtf.setFont(police);
	    jtf.setPreferredSize(new Dimension(150, 30));
	    bouton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	  	      System.out.println("Pseudo: " + jtf.getText());
	  	      //JoueurPacman.setPseudo(jtf.getText());
	  	      setVisible(false);
	  	    }
	    });
	    cancelBouton.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {
	        setVisible(false);
	      }      
	    });
	    JPanel label5=new JPanel();
	    label5.add(label2);
	    label5.add(label3);
	    top.add(label);
	    top.add(jtf);
	    JPanel boutons=new JPanel();
	    boutons.add(bouton);
	    boutons.add(cancelBouton);
	    this.getContentPane().add(label5, BorderLayout.NORTH);
	    this.getContentPane().add(top, BorderLayout.CENTER);
	    this.getContentPane().add(boutons, BorderLayout.SOUTH);
	    this.setVisible(true);         
	    
	    
	  }
	
	
	
	/*class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	      System.out.println("Pseudo: " + jtf.getText());
	      //JoueurPacman.setPseudo(jtf.getText());
	    
	    }
	  }*/
    
}

