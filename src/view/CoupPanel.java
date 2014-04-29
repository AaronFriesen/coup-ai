package view;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.*;
import javax.swing.*;
import java.awt.geom.AffineTransform;

import model.*;
import controller.*;
import java.util.List;

public class CoupPanel extends JPanel{

	private GameState gs;
	final int cardWidth = 100;
	final int cardHeight = 150;

	private static CoupPanel instance;

	public static CoupPanel getInstance() {
		return instance == null ? instance = new CoupPanel() : instance;
	}

	public CoupPanel(){
		this.setPreferredSize(new Dimension(600,600));
		this.setLayout(new BorderLayout());
	}





	public void setState(GameState gs){
		this.gs = gs;
		ButtonPanel bp = new ButtonPanel();
		CardPanel cp = new CardPanel();
		cp.setState(gs);
		this.add(bp, BorderLayout.WEST);
		this.add(cp, BorderLayout.CENTER);
		GameController instance = GameController.getInstance();
		bp.populate(instance.getValidMoves(gs));

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
