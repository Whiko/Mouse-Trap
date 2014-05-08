import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FenetreScores extends JFrame{
	private static final long serialVersionUID = 1L;
	public JPanel container;
	private InfoScores infoScores;

	public FenetreScores() throws IOException, ClassNotFoundException{
		container = new JPanel();
		infoScores = new InfoScores();
		getScores();
		
	    this.setTitle("Meilleurs scores");
	    this.setSize(260, 250);
	    this.setLocationRelativeTo(null); //fenetre au centre
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    
	    JButton buttonOK = new JButton("OK");
	    
	    JTextArea scores = new JTextArea(infoScores.toString());
	    scores.disable();	//empeche d'ecrire
	    JPanel top = new JPanel();
	    JPanel boutons=new JPanel();
	    
	  	    
	    buttonOK.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    	  	setVisible(false);
	  	    }
	    });
	    
	    top.add(scores);
	    boutons.add(buttonOK);
	    this.getContentPane().add(top, BorderLayout.CENTER);
	    this.getContentPane().add(boutons, BorderLayout.SOUTH);
    	setVisible(true);
	 } 
	
	// serialisation des scores
	public void saveScores () throws IOException
	{
		FileOutputStream fosConfig = new FileOutputStream("config/scores.dat");
		ObjectOutputStream oosConfig = new ObjectOutputStream(fosConfig);

		oosConfig.writeObject(infoScores);
		oosConfig.flush();
		oosConfig.close();
	}
	
	// Deserialisation des scores
	public void getScores () throws IOException, ClassNotFoundException
	{
		System.out.println("scores loading");
		try {
			FileInputStream fisConfig = new FileInputStream("config/scores.dat");
			ObjectInputStream oisConfig = new ObjectInputStream(fisConfig);
			infoScores = (InfoScores)oisConfig.readObject();
			
			oisConfig.close();
			
		} catch (Exception e) {System.out.println("scores loading failed. scores reset"); saveScores();}
	}
}

