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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

public class FenetreScores extends JFrame{
	private static final long serialVersionUID = 1L;
	public JPanel container;
	private JButton bouton;
	private JLabel pseudo1;
	private JLabel pseudo2;
	private JLabel pseudo3;
	private JLabel pseudo4;
	private JLabel pseudo5;
	private JLabel score1;
	private JLabel score2;
	private JLabel score3;
	private JLabel score4;
	private JLabel score5;
	private Hashtable<String, String> scoresTable;

	public FenetreScores() throws IOException, ClassNotFoundException{
		container = new JPanel();
		scoresTable = new Hashtable<String, String>();
		
	    this.setTitle("Meilleurs scores");
	    this.setSize(260, 300);
	    this.setLocationRelativeTo(null); //fenetre au centre
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    
	    bouton = new JButton("OK");
	    pseudo1 = new JLabel("1- ");
	    pseudo2 = new JLabel("2- ");
	    pseudo3 = new JLabel("3- ");
	    pseudo4 = new JLabel("4- ");
	    pseudo5 = new JLabel("5- ");
	    score1 = new JLabel("1- ");
	    score2 = new JLabel("2- ");
	    score3 = new JLabel("3- ");
	    score4 = new JLabel("4- ");
	    score5 = new JLabel("5- ");
	    
	    JPanel top = new JPanel();
	    JPanel top2=new JPanel();
	    JPanel boutons=new JPanel();
	    
	  	    
	    bouton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    	  	setVisible(false);
	  	    }
	    });
	    
	    top.add(pseudo1);
	    top.add(pseudo2);
	    top.add(pseudo3);
	    top.add(pseudo4);
	    top.add(pseudo5);
	    top.add(score1);
	    top.add(score2);
	    top.add(score3);
	    top.add(score4);
	    top.add(score5);
	    
	    
	    boutons.add(bouton);
	    this.getContentPane().add(top, BorderLayout.NORTH);
	    this.getContentPane().add(top2, BorderLayout.CENTER);
	    this.getContentPane().add(boutons, BorderLayout.SOUTH);
	    this.setVisible(true);
	 } 

	
	public void getScores () throws IOException, ClassNotFoundException
	{
		System.out.println("loading config");
		try {
			FileInputStream fisConfig = new FileInputStream("config/scores.dat");
			ObjectInputStream oisConfig = new ObjectInputStream(fisConfig);
			scoresTable = (Hashtable<String, String>)oisConfig.readObject();
			
			oisConfig.close();
			
		} catch (Exception e) {System.out.println("scores loading failed. scores reset");}
	}
}

