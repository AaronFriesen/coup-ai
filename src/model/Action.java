package model;

import model.Move;
import model.Player;

public class Action {
    public Move move;
    public Player player;

    public Action(Player player, Move m) {
        this.move = move;
        this.player = player;
    }

    public boolean equals(Object o) {
        if (o instanceof Action) {
            Action a = (Action)o;
            return this.move == a.move && this.player.equals(a.player);
        }
        return false;
    }

    public String toString() {
        return player + ": " + move;
    }
}
