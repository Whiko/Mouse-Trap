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
	private JLabel scoretxt;
	private JLabel score;
	private InfoScores infoScores;

	public Fenetre(int newscore) throws IOException, ClassNotFoundException{
		container = new JPanel();
		infoScores = new InfoScores();
		getScores();
		
	    this.setTitle("Enregistrement");
	    this.setSize(260, 160);
	    this.setLocationRelativeTo(null); //fenetre au centre
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    
	    bouton = new JButton("Enregistrer");
	    cancelBouton = new JButton("Annuler");
	    
	    scoretxt = new JLabel("Votre score: ");
	    pseudo= new JLabel("Votre pseudo: ");
	    score = new JLabel(Integer.toString(newscore));
	    
	    JPanel top = new JPanel();
	    JPanel top2=new JPanel();
	    JPanel boutons=new JPanel();
	    
	    Font police = new Font("Comic sans MS", Font.BOLD, 14);
	    jtf = new JTextField("Votre nom");
	    jtf.setFont(police);
	    jtf.setPreferredSize(new Dimension(150, 30));
	    
	    bouton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		if(jtf.getText().equals("votre nom") || jtf.getText().equals(""))
					jtf.setText("Anonyme");
	    		infoScores.put(jtf.getText(), Integer.parseInt(score.getText()));
	    		try {
					saveScores();
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

	    top.add(scoretxt);
	    top.add(score);
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

		oosConfig.writeObject(infoScores);
		oosConfig.flush();
		oosConfig.close();
	}
	
	public void getScores () throws IOException, ClassNotFoundException
	{
		System.out.println("scores loading");
		try {
			FileInputStream fisConfig = new FileInputStream("config/scores.dat");
			ObjectInputStream oisConfig = new ObjectInputStream(fisConfig);
			infoScores = (InfoScores) oisConfig.readObject();
			
			oisConfig.close();
		} catch (Exception e) {
			System.out.println("scores loading failed. scores reset");
			saveScores();
		}
	}
}

