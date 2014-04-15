package model;

import java.util.List;


public interface GameState {

    List<Player> getPlayers();

    GameState generateSuccessorState(Player source, Move m, Player target);
    GameState resolveFailedCall(Player caller, Move bluffType, Player target);
    GameState resolveSuccessfulCall(Move bluffType);
}
