package controller;

import model.*;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This class implements taking game turns and contains the logic for blocking,
 * calling bluffs, and blocking bluffs. Implemented as a stack of Actions,
 * which are Move, Player(target) pairs.
 *
 * @author Aaron Friesen
 * @version 0.75
 */
public class GameController {

    private Game game;

    private LinkedList<Action> stack;

    private static GameController instance;

    private GameController() {
        // this.game = new Game(); // This is where we put concrete game.
        this.game.setPlayers(null); // This is where we make Player list.
        this.stack = new LinkedList<Action>();
    }

    public static GameController getInstance() {
        return instance == null ? instance = new GameController() : instance;
    }

    public Game getGame() {
        return this.game;
    }

    public Player getCurrentPlayer() {
        return this.game.getCurrentPlayer();
    }

    public void pushAction(Action m) {
        stack.push(m);
    }

    public void executeActions() {
        //go from the top down
        while (!stack.isEmpty()) {
            Action a = stack.pop();
            Move m = a.move;
            //if m is an interesting move
            if (m != Move.PASS) {
                Move temp = Move.PASS;
                //if m is blocking stealing, then eat through the stack until we remove the stealing move
                if (m == Move.BLOCK_STEAL_AMBASSADOR || m == Move.BLOCK_STEAL_CAPTAIN) {
                    while (temp != Move.STEAL) {
                        temp = stack.pop().move;
                    }
                } else if (m == Move.BLOCK_ASSASSINATE) { //same for assassinate
                    while (temp != Move.ASSASSINATE) {
                        temp = stack.pop().move;
                    }
                } else if (m == Move.BLOCK_BLUFF_AMBASSADOR) {
                    while (temp != Move.CALL_BLUFF_AMBASSADOR) {
                        temp = stack.pop().move;
                    }
                    game.resolveFailedCall(a.player, a.move);
                } else if (m == Move.BLOCK_BLUFF_ASSASSIN) {
                    while (temp != Move.CALL_BLUFF_ASSASSIN) {
                        temp = stack.pop().move;
                    }
                    game.resolveFailedCall(a.player, a.move);
                } else if (m == Move.BLOCK_BLUFF_CAPTAIN) {
                    while (temp != Move.CALL_BLUFF_CAPTAIN) {
                        temp = stack.pop().move;
                    }
                    game.resolveFailedCall(a.player, a.move);
                } else if (m == Move.BLOCK_BLUFF_CONTESSA) {
                    while (temp != Move.CALL_BLUFF_CONTESSA) {
                        temp = stack.pop().move;
                    }
                    game.resolveFailedCall(a.player, a.move);
                } else if (m == Move.BLOCK_BLUFF_DUKE) {
                    while (temp != Move.CALL_BLUFF_DUKE) {
                        temp = stack.pop().move;
                    }
                    game.resolveFailedCall(a.player, a.move);
                } else if (m == Move.CALL_BLUFF_ASSASSIN || m == Move.CALL_BLUFF_AMBASSADOR
                        || m == Move.CALL_BLUFF_CAPTAIN || m == Move.CALL_BLUFF_CONTESSA
                        || m == Move.CALL_BLUFF_DUKE) {
                    while (temp == Move.PASS) {
                        temp = stack.pop().move;
                    }
                    game.resolveSuccessfulCall(m);
                } else {
                    game.makeMove(m, a.player);
                }
            }
        }
    }




    public Card playerChooseDeadCard(Player p) {
        //do stuff involving picking that player's dead card. Probably needs UI.
        //for now, just kill their first card
        List<Card> psCards = p.getLivingCards();
        Card deadCard = psCards.get(0);
        p.killCard(deadCard);
        return deadCard;
    }


}
