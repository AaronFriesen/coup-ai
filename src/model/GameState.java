package model;

import java.util.List;
import model.Player;


public interface GameState {

    List<Player> getPlayers();
    Player getCurrentPlayer();
    void setPlayers(List<Player> l);
    Player getHumanPlayer();
    GameState generateSuccessorState(Player source, Move m, Player target);
    GameState resolveFailedCall(Player caller, Move bluffType, Player target);
    GameState resolveSuccessfulCall(Move bluffType);
}
