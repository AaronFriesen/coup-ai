package model;

import java.util.List;
import java.util.ArrayList;
import controller.GameController;

public class CoupPlayer implements Player {

	private int numIsk;
	private List<Card> livingCards;
	private List<Card> deadCards;
	private boolean isAI;
	private boolean isAlive;

    public CoupPlayer(){

    	numIsk = 2;
    	livingCards = new ArrayList();
    	deadCards = new ArrayList();


    }

    public int getIsk(){
    	return numIsk;
    }

    public int stealFrom(){
    	if(numIsk>=2){
    		numIsk-=2;
    		return 2;
    	}else{
    		int has = numIsk;
    		numIsk = 0;
    		return has;
    	}
    }

    public void addIsk(int numIskToAdd){
    	numIsk+=numIskToAdd;
    }

    public void removeIsk(int numIskToSub){
    	numIsk-=numIskToSub;
    	if(numIsk<0){
    		numIsk=0;
    	}
    }

    public boolean isAI(){
    	return isAI;
    }

    public boolean hasCard(Card c){
    	return livingCards.contains(c);
    }

    public boolean isAlive(){
    	return isAlive;
    }

    public void killCard(Card c){
    	if(livingCards.contains(c)){
    		deadCards.add(c);
    		livingCards.remove(c);
    	}
    }

    public void replaceCard(Card replaced, Card replacing){

    	if(livingCards.contains(replaced)){
    		livingCards.set(livingCards.indexOf(replaced), replacing);
    	}

    }

    //TODO
    public List<Card> setLivingCards(List<Card> list){
    	List<Card> tempList = livingCards;
    	livingCards = list;
    	return tempList;
    }

    public List<Card> getDeadCards(){

    	return deadCards;

    }

    public List<Card> getLivingCards(){

    	return livingCards;

    }

    public Player clone(){
    	CoupPlayer newGuy = new CoupPlayer();
    	//newGuy.livingCards = this.livingCards.clone();

    	for(Card item:this.livingCards){
    		newGuy.livingCards.add(item);
    	}



    	for(Card item:this.deadCards){
    		newGuy.deadCards.add(item);
    	}



    	newGuy.isAI = this.isAI;
    	newGuy.isAlive = this.isAlive;
    	return newGuy;


    }


	public boolean equals(Object other) {
		if (other instanceof CoupPlayer) {
			CoupPlayer p = (CoupPlayer)other;
			return p.numIsk == this.numIsk && this.livingCards.equals(p.livingCards)
				   && this.deadCards.equals(p.deadCards);
		}
		return false;
	}

	public int hashCode() {
		return this.numIsk + this.livingCards.hashcode();
	}
}
