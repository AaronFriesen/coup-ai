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

public class CardPanel extends JPanel{
	
	private GameState gs;
	final int cardWidth = 100;
	final int cardHeight = 150;
	
	public CardPanel(){
		this.setPreferredSize(new Dimension(600,600));
		this.setLayout(new BorderLayout());
	}
	
	


	
	public void setState(GameState gs){
		this.gs = gs;
		//ButtonPanel bp = new ButtonPanel();
		//this.add(bp, BorderLayout.WEST);
		//GameController instance = GameController.getInstance();
		//bp.populate(instance.getValidMoves(gs));
		
	}
	

	public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		
		
		//Player Cards

		List<Card> playerCards = gs.getPlayers().get(0).getLivingCards();

		for(int i = 0; i < playerCards.size(); i++){
			BufferedImage img = null;
			
			try{
			 img = ImageIO.read(new File((playerCards.get(i).getFront())));
			 g.drawImage(img, (i*cardWidth)+(getWidth()/2-(playerCards.size()*cardWidth)/2),getHeight()-cardHeight, 100, 150, null);
			} catch (IOException e){
			}
		}
		
		
		//Left Cards
		
		List<Card> leftCards = gs.getPlayers().get(1).getLivingCards();
		
		for(int i = 0; i < leftCards.size(); i++){
			BufferedImage img = null;
			
			try{
			AffineTransform at = new AffineTransform();
			
			//at.rotate(Math.PI/2);
			at.translate(cardHeight, (i*cardWidth) + ((getHeight()/2)-(leftCards.size()*cardWidth)/2));
			at.rotate(Math.PI/2);
				
			 img = ImageIO.read(new File((leftCards.get(i).getFront())));
			 
			 at.scale((cardWidth*1.0)/img.getWidth(), (cardHeight*1.0)/img.getHeight());
			 
			 g2d.drawImage(img, at, null);
			
			
			
			} catch (IOException e){
			}
		}
		
		
		
		//Top Cards
		
		List<Card> topCards = gs.getPlayers().get(2).getLivingCards();

		for(int i = 0; i < topCards.size(); i++){
			BufferedImage img = null;
			
			try{
			 img = ImageIO.read(new File((topCards.get(i).getFront())));
			 g.drawImage(img, (i*cardWidth)+(getWidth()/2-(topCards.size()*cardWidth)/2),0, 100, 150, null);
			} catch (IOException e){
			}
		}
		
		
		//Right Cards
		
		List<Card> rightCards = gs.getPlayers().get(3).getLivingCards();
		
		for(int i = 0; i < leftCards.size(); i++){
			BufferedImage img = null;
			
			try{
			AffineTransform at = new AffineTransform();
			
			//at.rotate(Math.PI/2);
			at.translate(getWidth()-cardHeight, (i*cardWidth)+cardWidth + ((getHeight()/2)-(rightCards.size()*cardWidth)/2));
			at.rotate(-Math.PI/2);
				
			 img = ImageIO.read(new File((rightCards.get(i).getFront())));
			 
			 at.scale((cardWidth*1.0)/img.getWidth(), (cardHeight*1.0)/img.getHeight());
			 
			 g2d.drawImage(img, at, null);
			
			
			
			} catch (IOException e){
			}
		}
		
		
		
		
		
		//
		
		
	}
	
	
}