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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;

public class Fenetre extends JFrame{
	private static final long serialVersionUID = 1L;
	public JPanel container;
	private JButton bouton;
	private JButton cancelBouton;
	private JTextField jtf;
	private JLabel pseudo;
	private JLabel score;
	private JLabel label;

	public Fenetre(final int getscore){
		container = new JPanel();
		
	    this.setTitle("Enregistrement");
	    this.setSize(260, 160);
	    this.setLocationRelativeTo(null); //fenetre au centre
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    
	    bouton = new JButton("Enregistrer");
	    cancelBouton = new JButton("Annuler");
	    
	    score = new JLabel("Votre score: ");
	    pseudo= new JLabel("Votre pseudo: ");
	    label = new JLabel(Integer.toString(getscore));
	    
	    JPanel top = new JPanel();
	    JPanel top2=new JPanel();
	    JPanel boutons=new JPanel();
	    
	    Font police = new Font("Comic sans MS", Font.BOLD, 14);
	    jtf = new JTextField("votre nom");
	    jtf.setFont(police);
	    jtf.setPreferredSize(new Dimension(150, 30));
	    
	    bouton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		if(jtf.getText().equals("votre nom") || jtf.getText().equals(""))
					jtf.setText("Anonyme");
	    		System.out.println("Pseudo: " + jtf.getText());
	    		try {
					saveScore(getscore);
				} catch (IOException e1) {e1.printStackTrace();}
	  	      	//Joueur.setPseudo(jtf.getText());
	  	      	setVisible(false);
	  	    }
	    });
	    cancelBouton.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {
	        setVisible(false);
	      }      
	    });
	    
	    top.add(score);
	    top.add(label);
	    top2.add(pseudo);
	    top2.add(jtf);
	    
	    boutons.add(bouton);
	    boutons.add(cancelBouton);
	    this.getContentPane().add(top, BorderLayout.NORTH);
	    this.getContentPane().add(top2, BorderLayout.CENTER);
	    this.getContentPane().add(boutons, BorderLayout.SOUTH);
	    this.setVisible(true);
	 } 
	
	public void saveScore(int getscore) throws IOException
	{
		FileWriter fw = new FileWriter("config/scores.txt", true);
		BufferedWriter output = new BufferedWriter(fw);
		output.write(jtf.getText()+":");
	    output.write(Integer.toString(getscore)+"\n");
		output.flush();
		output.close();
	}
	
}

