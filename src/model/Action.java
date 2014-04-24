package model;

public class Action {
    public Move move;
    public Player player;

    public Action(Move move, Player player) {
        this.move = move;
        this.player = player;
    }
}
