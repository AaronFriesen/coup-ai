package model;

import java.util.List;


public interface GameState {

    List<Player> getPlayers();
    GameState generateSuccessorState(Player active, Move m);

}
