package model;

import model.Game;
import model.CoupGameState;
import model.GameState;
import model.Action;
import model.Move;
import java.util.List;

public class CoupGame implements Game {
    private GameState gameState;

    public CoupGame() {
        this.gameState = new CoupGameState();
    }

    public void makeMove(Move m) {
        makeMove(gameState.getCurrentPlayer(), m, null);
    }

    public void makeMove(Action a) {
        switch (a.move) {
            case INCOME:
                makeMove(a.move); break;
            case FOREIGN_AID:
                makeMove(a.move); break;
            case COUP:
                makeMove(gameState.getCurrentPlayer(), a.move, a.player); break;
            case TAX:
                makeMove(a.move); break;
            case ASSASSINATE:
                makeMove(gameState.getCurrentPlayer(), a.move, a.player); break;
            case STEAL:
                makeMove(gameState.getCurrentPlayer(), a.move, a.player); break;
            case EXCHANGE:
                makeMove(a.move); break;
        }
    }

    public void makeMove(Move m, Player p) {
        makeMove(new Action(p, m));
    }

    public void makeMove(Player p1, Move m, Player target) {
        System.out.println("Making move " + m);
        this.gameState = gameState.generateSuccessorState(p1, m, target);
        CoupPanel.getInstance().setState(gameState);
    }

    public void setPlayers(List<Player> l) {
        this.gameState.setPlayers(l);
    }

    public void resolveFailedCall(Player caller, Move bluffType) {
        this.gameState = this.gameState.resolveFailedCall(caller, bluffType, gameState.getCurrentPlayer());
    }

    public void resolveSuccessfulCall(Move bluffType) {
        this.gameState = this.gameState.resolveSuccessfulCall(bluffType);
    }

    public GameState getState() {
        return this.gameState;
    }

    public Player getCurrentPlayer() {
        return this.gameState.getCurrentPlayer();
    }

    public void aiMoves() {
        List<Player> players = this.gameState.getPlayers();
        GameController control = GameController.getInstance();
        for (int i = 1; i < players.size(); i++) {
            Player cur = players.get(i);
            control.pushAction(new Action(cur, cur.makeMove(control.getValidMoves(this.gameState, cur)));
        }
        control.executeActions();
    }
}
