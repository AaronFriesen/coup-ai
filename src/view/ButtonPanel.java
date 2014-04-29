package view;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.*;
import javax.swing.*; 
import java.awt.geom.AffineTransform;

import model.*;
import java.util.List;

public class ButtonPanel extends JPanel{
	
	private GameState gs;

	
	public ButtonPanel(){
		this.setPreferredSize(new Dimension(200,600));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
	}
	
	
	
	public void populate(List<Move> moves){
		
		
		this.add(Box.createVerticalGlue());
		for(int i = 0; i < moves.size(); i++){
			JButton a = new JButton("This is button "+i);
			this.add(a);
			
			
		}
		
		this.add(Box.createVerticalGlue());
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