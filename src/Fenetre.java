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

public class Fenetre extends JFrame{
	private static final long serialVersionUID = 1L;
	public JPanel container;
	private JButton bouton;
	private JButton cancelBouton;
	private JTextField jtf;
	private JLabel pseudo;
	private JLabel score;
	private JLabel label;
	private Hashtable<String, String> scoresTable;

	public Fenetre(int getscore) throws IOException, ClassNotFoundException{
		container = new JPanel();
		scoresTable = new Hashtable<String, String>();
		
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
	    		scoresTable.put(jtf.getText(), label.getText());
	    		try {
					saveScores();
				} catch (IOException e1) {e1.printStackTrace();}
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

	public void saveScores () throws IOException
	{
		FileOutputStream fosConfig = new FileOutputStream("config/scores.dat");
		ObjectOutputStream oosConfig = new ObjectOutputStream(fosConfig);

		oosConfig.writeObject(scoresTable);
		oosConfig.flush();
		oosConfig.close();
	}
	
	public void getScores () throws IOException, ClassNotFoundException
	{
		System.out.println("loading config");
		try {
			FileInputStream fisConfig = new FileInputStream("config/scores.dat");
			ObjectInputStream oisConfig = new ObjectInputStream(fisConfig);
			scoresTable = (Hashtable<String, String>)oisConfig.readObject();
			
			oisConfig.close();
		} catch (Exception e) {
			System.out.println("scores loading failed. scores reset");
			saveScores();
		}
	}
}

