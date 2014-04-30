package model;

import java.util.List;
import model.Player;


public interface GameState {

    Player[] getPlayers();
    Player getCurrentPlayer();
    int getCurrentPlayerIndex();
    void setPlayers(Player[] l);
    Player getHumanPlayer();
    GameState generateSuccessorState(Player source, Move m, Player target);
    GameState resolveFailedCall(Player caller, Move bluffType, Player target);
    GameState resolveSuccessfulCall(Move bluffType);
}
