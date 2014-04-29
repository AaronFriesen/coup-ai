package model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {


    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();

        for (Card c : Card.values()) {
            cards.add(c);
            cards.add(c);
            cards.add(c);
            System.out.println("adding card: " + c);
        }

        Collections.shuffle(cards);
    }

    public Card draw() {
    	System.out.println(cards);
        return cards.remove(0);
    }

    public void discardAndShuffle(Card c) {
        cards.add(c);
        Collections.shuffle(cards);
    }

    public void discardAndShuffle(Card first, Card second) {
        discardAndShuffle(first);
        discardAndShuffle(second);
    }

    public int cardsInDeck() {
        return cards.size();
    }

    public Deck clone() {
        Deck d = new Deck();
        d.cards = new ArrayList<Card>();
        for (Card c : cards) {
            d.cards.add(c);
        }
        return d;
    }

}
