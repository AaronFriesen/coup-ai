package view;


import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import javax.swing.*;        
import model.*;
import java.util.List;

public class CoupPanel extends JPanel{
	
	private GameState gs;
	
	public CoupPanel(){
		this.setPreferredSize(new Dimension(400,400));
		
		
	}
	
	
	public void paint(Graphics g) {

		//Player Cards
		//g.drawImage(new Image(), arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9)
		List<Card> playerCards = gs.getPlayers().get(0).getLivingCards();
		
		//BufferedImage bi;
		
		BufferedImage img = null;
		
		try{
		 img = ImageIO.read(new File((playerCards.get(0).getFront())));
		 g.drawImage(img, 0, 0, 100, 150, null);
		} catch (IOException e){
			
		}

		
	}
	
	public void setState(GameState gs){
		
		this.gs = gs;	
	}
	
	
	
	
/*
	
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("Hello World");
        //frame.getContentPane().add(label);
        //frame.getContentPane().setLayout(new BorderLayout());
        
        

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }*/
}