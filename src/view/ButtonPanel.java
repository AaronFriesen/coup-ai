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

		System.out.println("Button Panel wants to add these: " + moves);

		//this.add(Box.createVerticalGlue());

		//remove old buttons;
		int len = this.getComponents().length;
		for(int i = 0; i < len; i++){
			
			this.remove(this.getComponent(0));

		}

		this.revalidate();

		//


		for(int i = 0; i < moves.size(); i++){


			Move m = moves.get(i);
			JButton a = new JButton(m.toString());
			this.add(a);
			a.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GameController control = GameController.getInstance();
					System.out.println("player 1 took " + m.toString() + " a derpa derp");
					if (m != null) control.pushAction(new Action(control.getHumanPlayer(), m));
					control.aiTurns();
				}
			});

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
