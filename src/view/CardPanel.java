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
	final int cardBorder = 20;
	final int iskDist = 15;
	final int iskSize = 75;
	
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
		
		
		
		
		
		
		//Table
		
		
		BufferedImage table = null;
		
		try{
		 table = ImageIO.read(new File(("images/table.png")));
		 
		 double tiles = (getWidth()*1.0)/(1.0*table.getWidth());
		 for(int i = 0; i < tiles; i++){
			 for(int y = 0; y < tiles+1; y++){
		 
				 g.drawImage(table, i*table.getWidth(),y*table.getHeight(),table.getWidth(),table.getHeight(), null);
		 
			 }
		 }
		} catch (IOException e){
			System.out.println("FAILED");
		}
	

		
		
		
		
		
		
		//Player Cards

		List<Card> playerCards = gs.getPlayers()[0].getLivingCards();
		List<Card> playerDeadCards = gs.getPlayers()[0].getDeadCards();
		int iskNum = gs.getPlayers()[0].getIsk();

		for(int i = 0; i < playerCards.size(); i++){
			BufferedImage img = null;
			
			try{
			 img = ImageIO.read(new File((playerCards.get(i).getFront())));
			 g.drawImage(img, (i*cardWidth)+(getWidth()/2-((playerCards.size()+playerDeadCards.size())*cardWidth)/2),getHeight()-cardHeight-cardBorder, 100, 150, null);
			} catch (IOException e){
			}
		}
		
		for(int i = 0; i < playerDeadCards.size(); i++){
			BufferedImage img = null;
			
			try{
			 img = ImageIO.read(new File((playerCards.get(i).getFront())));
			 g.drawImage(img, ((i+playerCards.size())*cardWidth)+(getWidth()/2-((playerCards.size()+playerDeadCards.size())*cardWidth)/2),getHeight()-cardHeight-cardBorder, 100, 150, null);
			} catch (IOException e){
			}
		}
		
		
		
		
		try{
			BufferedImage isk;
			 isk = ImageIO.read(new File(("images/isk.png")));
			 
			 int x = ((playerDeadCards.size()+playerCards.size())*cardWidth)+(getWidth()/2-((playerCards.size()+playerDeadCards.size())*cardWidth)/2) + iskDist;
			 int y = getHeight()-cardHeight+cardHeight/4;
			 
			 g.drawImage(isk,x,y, iskSize, iskSize, null);
			 
			 
			 g.setFont(new Font("TimesRoman", Font.BOLD, 20)); 
			 g.setColor(Color.white);
			 g.drawString(""+iskNum+" ISKs", x,y+iskSize+iskDist);
			 
			} catch (IOException e){
				System.out.println("FAILED");
			}
		
		
		
		
		
		
		//Left Cards
		
		List<Card> leftDeadCards = gs.getPlayers()[1].getDeadCards();
		List<Card> leftCards = gs.getPlayers()[1].getLivingCards();
		
		
		for(int i = 0; i < leftDeadCards.size(); i++){
			BufferedImage img = null;
			
			try{
			AffineTransform at = new AffineTransform();
			
			//at.rotate(Math.PI/2);
			at.translate(cardHeight +cardBorder, (i*cardWidth) + ((getHeight()/2)-((leftDeadCards.size()+leftCards.size())*cardWidth)/2));
			at.rotate(Math.PI/2);
				
			 img = ImageIO.read(new File((leftDeadCards.get(i).getFront())));
			 
			 at.scale((cardWidth*1.0)/img.getWidth(), (cardHeight*1.0)/img.getHeight());
			 
			 g2d.drawImage(img, at, null);
			
			
			
			} catch (IOException e){
			}
		}
		

		
		for(int i = 0; i < leftCards.size(); i++){
			BufferedImage img = null;
			
			try{
			AffineTransform at = new AffineTransform();
			
			//at.rotate(Math.PI/2);
			at.translate(cardHeight+cardBorder, ((i+leftDeadCards.size())*cardWidth) + ((getHeight()/2)-((leftCards.size()+leftDeadCards.size())*cardWidth)/2));
			at.rotate(Math.PI/2);
				
			 img = ImageIO.read(new File((leftCards.get(i).getFront())));
			 
			 at.scale((cardWidth*1.0)/img.getWidth(), (cardHeight*1.0)/img.getHeight());
			 
			 g2d.drawImage(img, at, null);
			
			
			
			} catch (IOException e){
			}
		}
		
		
		
		try{
			BufferedImage isk;
			iskNum = gs.getPlayers()[1].getIsk(); 
			
			isk = ImageIO.read(new File(("images/isk.png")));
			 
			 int x = cardBorder;
			 int y = ((leftCards.size()+leftDeadCards.size())*cardWidth) + ((getHeight()/2)-((leftCards.size()+leftDeadCards.size())*cardWidth)/2)+iskDist;
			 
			 g.drawImage(isk,x,y, iskSize, iskSize, null);
			 
			 
			 g.setFont(new Font("TimesRoman", Font.BOLD, 20)); 
			 g.setColor(Color.white);
			 g.drawString(""+iskNum+" ISKs", x,y+iskSize+iskDist);
			 
			} catch (IOException e){
				System.out.println("FAILED");
			}
		
		
		
		
		
		
		
		//Top Cards
		
		List<Card> topCards = gs.getPlayers()[2].getLivingCards();
		List<Card> topDeadCards = gs.getPlayers()[2].getDeadCards();

		for(int i = 0; i < topDeadCards.size(); i++){
			BufferedImage img = null;
			
			try{
			 img = ImageIO.read(new File((topCards.get(i).getFront())));
			 g.drawImage(img, (i*cardWidth)+(getWidth()/2-((topCards.size()+topDeadCards.size())*cardWidth)/2),cardBorder, 100, 150, null);
			} catch (IOException e){
			}
		}
		
		
		
		for(int i = 0; i < topCards.size(); i++){
			BufferedImage img = null;
			
			try{
			 img = ImageIO.read(new File((topCards.get(i).getFront())));
			 g.drawImage(img, ((i+topDeadCards.size())*cardWidth)+(getWidth()/2-((topCards.size()+topDeadCards.size())*cardWidth)/2),cardBorder, 100, 150, null);
			} catch (IOException e){
			}
		}
		
		
		
		try{
			BufferedImage isk;
			iskNum = gs.getPlayers()[2].getIsk(); 
			
			isk = ImageIO.read(new File(("images/isk.png")));
			 
			 int x = (topCards.size()+topDeadCards.size())+(getWidth()/2-((topCards.size()+topDeadCards.size())*cardWidth)/2)-iskSize-iskDist;
			 int y = iskDist;//((leftCards.size()+leftDeadCards.size())*cardWidth) + ((getHeight()/2)-((leftCards.size()+leftDeadCards.size())*cardWidth)/2)+iskDist;
			 
			 g.drawImage(isk,x,y, iskSize, iskSize, null);
			 
			 
			 g.setFont(new Font("TimesRoman", Font.BOLD, 20)); 
			 g.setColor(Color.white);
			 g.drawString(""+iskNum+" ISKs", x,y+iskSize+iskDist);
			 
			} catch (IOException e){
				System.out.println("FAILED");
			}
		
		
		
		
		//Right Cards
		
		List<Card> rightCards = gs.getPlayers()[3].getLivingCards();
		List<Card> rightDeadCards = gs.getPlayers()[3].getDeadCards();
		
		
		
		for(int i = 0; i < rightDeadCards.size(); i++){
			BufferedImage img = null;
			
			try{
			AffineTransform at = new AffineTransform();
			
			//at.rotate(Math.PI/2);
			at.translate(getWidth()-cardHeight-cardBorder, (i*cardWidth)+cardWidth + ((getHeight()/2)-((rightCards.size()+rightDeadCards.size())*cardWidth)/2));
			at.rotate(-Math.PI/2);
				
			 img = ImageIO.read(new File((rightCards.get(i).getFront())));
			 
			 at.scale((cardWidth*1.0)/img.getWidth(), (cardHeight*1.0)/img.getHeight());
			 
			 g2d.drawImage(img, at, null);
			
			
			
			} catch (IOException e){
			}
		}
		
		
		for(int i = 0; i < rightCards.size(); i++){
			BufferedImage img = null;
			
			try{
			AffineTransform at = new AffineTransform();
			
			//at.rotate(Math.PI/2);
			at.translate(getWidth()-cardHeight-cardBorder, (i*cardWidth)+cardWidth + ((getHeight()/2)-(rightCards.size()*cardWidth)/2));
			at.rotate(-Math.PI/2);
				
			 img = ImageIO.read(new File((rightCards.get(i).getFront())));
			 
			 at.scale((cardWidth*1.0)/img.getWidth(), (cardHeight*1.0)/img.getHeight());
			 
			 g2d.drawImage(img, at, null);
			
			
			
			} catch (IOException e){
			}
		}
		
		try{
			BufferedImage isk;
			iskNum = gs.getPlayers()[3].getIsk(); 
			
			isk = ImageIO.read(new File(("images/isk.png")));
			 
			 int x = getWidth()-iskSize-iskDist;
			 int y = ((getHeight()/2)-((rightCards.size()+rightDeadCards.size())*cardWidth)/2)+iskDist- ((rightCards.size()+rightDeadCards.size())*cardWidth)+iskSize;
			 
			 g.drawImage(isk,x,y, iskSize, iskSize, null);
			 
			 
			 g.setFont(new Font("TimesRoman", Font.BOLD, 20)); 
			 g.setColor(Color.white);
			 g.drawString(""+iskNum+" ISKs", x,y+iskSize+iskDist);
			 
			} catch (IOException e){
				System.out.println("FAILED");
			}

		
		
	}
	
	
}