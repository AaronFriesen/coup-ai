package model;

public interface Game {

    void makeMove(Move m);
    void makeMove(Move m, Player target);
    GameState getState();


}
