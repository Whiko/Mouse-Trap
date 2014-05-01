import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;


public class Fenetre extends JFrame{
	private static final long serialVersionUID = 1L;
	public JPanel container;
	private JButton bouton;
	private JButton cancelBouton;
	private JTextField jtf;
	private JTextField jtf2;
	private JLabel pseudo;
	private JLabel score;
	private JLabel label;
	private JLabel IP;

	public Fenetre(int getscore){
		container = new JPanel();
		
	    this.setTitle("Enregistrement");
	    this.setSize(260, 200);
	    this.setLocationRelativeTo(null); //fenetre au centre
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    
	    bouton = new JButton("Enregistrer");
	    cancelBouton = new JButton("Annuler");
	    
	    score = new JLabel("Votre score: ");
	    pseudo= new JLabel("Votre pseudo: ");
	    label = new JLabel(Integer.toString(getscore));
	    IP=new JLabel("IP serveur:");
	    
	    JPanel top = new JPanel();
	    JPanel top2=new JPanel();
	    JPanel boutons=new JPanel();
	    
	    Font police = new Font("Comic sans MS", Font.BOLD, 14);
	    jtf = new JTextField("votre nom");
	    jtf2 = new JTextField();
	    jtf.setFont(police);
	    jtf.setPreferredSize(new Dimension(150, 30));
	    jtf2.setPreferredSize(new Dimension(150, 30));
	    
	    bouton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		if(jtf.getText().equals("votre nom") || jtf.getText().equals(""))
					jtf.setText("Anonyme");
	    		System.out.println("Pseudo: " + jtf.getText());
	  	      	//Joueur.setPseudo(jtf.getText());
	  	      	setVisible(false);
	  	    }
	    });
	    cancelBouton.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {
	        setVisible(false);
	      }      
	    });
	    
	    top2.add(score);
	    top2.add(label);
	    top.add(pseudo);
	    top.add(jtf);
	    top.add(IP);
	    top.add(jtf2);
	    
	    boutons.add(bouton);
	    boutons.add(cancelBouton);
	    this.getContentPane().add(top2, BorderLayout.NORTH);
	    this.getContentPane().add(top, BorderLayout.CENTER);
	    this.getContentPane().add(boutons, BorderLayout.SOUTH);
	    this.setVisible(true);
	  } 
}

