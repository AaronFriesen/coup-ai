package model;
import java.util.List;

public interface Player {

    int getIsk();
    List<Card> getDeadCards();
    boolean isAI();
}
