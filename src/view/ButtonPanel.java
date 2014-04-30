package view;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.*;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.geom.AffineTransform;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.GameController;
import model.Action;
import model.Move;
import javax.swing.JComboBox;

import model.*;
import java.util.List;

public class ButtonPanel extends JPanel{

	private GameState gs;
	private List<Move> moves;


	public ButtonPanel(){
		this.setPreferredSize(new Dimension(200,600));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBackground(Color.darkGray);
		//this.getContentPane().setBackground(Color.black);
	}


	/*public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		//super();




		//Table


		BufferedImage table = null;

		try{
		 table = ImageIO.read(new File(("images/oakTable.png")));

		 double tiles = (getWidth()*1.0)/(1.0*table.getWidth());
		 for(int i = 0; i < tiles; i++){
			 for(int y = 0; y < 4*tiles; y++){

				// g.drawImage(table, i*table.getWidth(),y*table.getHeight(),table.getWidth(),table.getHeight(), null);

			 }
		 }
		} catch (IOException e){
			System.out.println("FAILED");
		}

		if(moves != null){
			//populate(moves);
		}

	}*/



	public void populate(final List<Move> moves){

		//System.out.println("Button Panel wants to add these: " + moves);

		//this.add(Box.createVerticalGlue());

		//remove old buttons;
		int len = this.getComponents().length;
		for(int i = 0; i < len; i++){

			this.remove(this.getComponent(0));

		}

		this.revalidate();

		//
		GameController control = GameController.getInstance();
		Player curTarget = control.getPlayers()[1];

		for(int i = 0; i < moves.size(); i++){


			Player[] players = control.getPlayers();
			String[] names = new String[]{"Player 2", "Player 3", "Player 4"};
			JComboBox<String> playersBox = new JComboBox(names);

			Move m = moves.get(i);
			JButton a = new JButton(m.toString());
			this.add(a);
			a.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					//System.out.println("player 1 took " + m.toString() + " a derpa derp");

					if(m!= null){

						Player targetPlayer = players[1];
						String boxSelection = (String)playersBox.getSelectedItem();
						if(boxSelection.equals("Player 2")){
							targetPlayer = players[1];
						}else if(boxSelection.equals("Player 3")){
							targetPlayer = players[2];
						}else if(boxSelection.equals("Player 4")){
							targetPlayer = players[3];
						}


						if(m == Move.PASS){
							//System.out.println("TESTING SELECT "+targetPlayer.toString());
						}else if(m == Move.STEAL){
							control.pushAction(new Action(targetPlayer, Move.STEAL));
						}else if(m == Move.ASSASSINATE){
							control.pushAction(new Action(targetPlayer, Move.ASSASSINATE));

						}else if(m == Move.COUP){
							control.pushAction(new Action(targetPlayer, Move.COUP));
						}else{
							control.pushAction(new Action(control.getHumanPlayer(), m));
						}

						control.aiTurns();
					}
				}
			});



			if(m == Move.PASS || m == Move.STEAL || m== Move.ASSASSINATE || m == Move.COUP){



				playersBox.setMaximumSize(new Dimension(100,20));


				//playersBox.setSelectedIndex(4);
				playersBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JComboBox cb = (JComboBox)e.getSource();
						String out = (String)cb.getSelectedItem();

						if(out.equals("Player 2")){
							//curTarget = players[1];
						}

						//System.out.println(curTarget);
					}
				});
				//System.out.println("COUP!");
				this.add(playersBox);
			}

		}

		//this.add(Box.createVerticalGlue());
		//repaint();
		revalidate();
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
