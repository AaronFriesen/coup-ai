package model;
import java.util.List;

public interface Player {


    int getIsk();
    int stealFrom();

    List<Card> getDeadCards();
    List<Card> setLivingCards(List<Card> list);

    boolean isAI();
    boolean hasCard(Card c);
    boolean isAlive();

    void addIsk(int numIsk);
    void removeIsk(int numIsk);
    void killCard(Card c);
    void replaceCard(Card replaced, Card replacing);

    Player clone();


}
