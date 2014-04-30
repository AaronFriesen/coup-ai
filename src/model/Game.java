package model;
import java.util.List;

public interface Game {

    void makeMove(Action a); //Player performing the move
    void makeMove(Move m); //Assuming Player = current Player
    void makeMove(Move m, Player target);
    void makeMove(Player source, Move m, Player target); //Player source makes move targetting player target
    void setPlayers(Player[] players);

    void resolveFailedCall(Player caller, Move bluffType);
    void resolveSuccessfulCall(Move bluffType);
    GameState getState();
    Player getCurrentPlayer();

    void aiTurns();
    void aiTurnsBeforePlayer();
    Player getHumanPlayer();
}
