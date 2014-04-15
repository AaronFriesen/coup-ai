package model;
import java.util.List;

public interface Game {

    void makeMove(Move m);
    void makeMove(Move m, Player target);
    void makeMove(Player source, Move m, Player target);
    void setPlayers(List<Player> players);

    void resolveFailedBluff(Player caller, Move bluffType);
    void resolveSuccessfulCall(Move bluffType);
    GameState getState();


}
