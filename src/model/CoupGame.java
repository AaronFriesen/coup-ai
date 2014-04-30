package model;

import model.Game;
import model.CoupGameState;
import model.GameState;
import model.Action;
import model.Move;
import view.CoupPanel;
import controller.GameController;
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
        aiTurnsBeforePlayer();

    }

    public void setPlayers(Player[] l) {
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

    public Player getHumanPlayer() {
        return this.gameState.getHumanPlayer();
    }

    public Player[] getPlayers() {
        return this.gameState.getPlayers();
    }

    public void aiTurns() {
        Player[] players = this.gameState.getPlayers();
        GameController control = GameController.getInstance();

        int index = gameState.getCurrentPlayerIndex();
        if (index == 0) index = 4;
        for (int i = 1; i < index; i++) {
            Player cur = players[i];
            if (cur.isAlive()) {
                List<Move> valids = control.getValidMoves(this.gameState, cur);
                Move move = cur.makeMove(valids);
                System.out.println(cur + " wants to make move " + move + " out of " + valids);
                control.pushAction(new Action(cur, move));
            }
        }
        System.out.println("===============AI TURN OVER==================");
        control.executeActions();
    }

    public void aiTurnsBeforePlayer() {
        int index = gameState.getCurrentPlayerIndex();
        if (index == 0) {
            return;
        }
        Player[] players = this.gameState.getPlayers();
        GameController control = GameController.getInstance();
        for (int i = index; i < players.length; i++) {
            //System.out.println("computer player should be taking turn before player");
            Player cur = players[i];
            System.out.println("Index of player's turn: " + index);
            if (cur.isAI() && cur.isAlive()) {
                List<Move> valids = control.getValidMoves(this.gameState, cur);
                Move move = cur.makeMove(valids);
                System.out.println(cur + " really wants to make move " + move + " out of " + valids);
                control.pushAction(new Action(cur, move));
            }
        }
        System.out.println("------PLAYER SHOULD GO HERE----------");
    }
}
