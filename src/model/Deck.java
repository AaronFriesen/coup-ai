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
        }

        Collections.shuffle(cards);
    }

    public Card draw() {
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

}
