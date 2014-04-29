package controller;

import model.Game;
import model.GameState;
import model.Card;
import model.Player;
import model.Action;
import model.Move;
import model.CoupGame;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;


/**
 * This class implements taking game turns and contains the logic for blocking,
 * calling bluffs, and blocking bluffs. Implemented as a stack of Actions,
 * which are Move, Player(target) pairs.
 *
 * @author Aaron Friesen
 * @version 0.8
 */
public class GameController {

    private Game game;

    private LinkedList<Action> stack;

    private static GameController instance;

    private GameController() {
        this.game = new CoupGame(); // This is where we put concrete game.
        //this.game.setPlayers(null); // This is where we make Player list.
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



    /**
     * This method makes me only mostly completely ashamed. Probably should be made
     * into something less fugly.
     *
     */
    public List<Move> getValidMoves(GameState gS, Player p) {
        //probably for use by bots
        List<Move> legals = new LinkedList<Move>();
        if (p.equals(gS.getCurrentPlayer())) { //if it's player 1's turn
            if (stack.isEmpty()) {
                if (p.getIsk() < 10) { //if you have less than 10, you can do standard moves
                    legals.add(Move.INCOME);
                    legals.add(Move.FOREIGN_AID);
                    if (p.getIsk() >= 7) legals.add(Move.COUP); //if you have 7 or more you can even coup!
                    legals.add(Move.TAX);
                    legals.add(Move.ASSASSINATE);
                    legals.add(Move.EXCHANGE);
                    legals.add(Move.STEAL);
                } else { //otherwise, you gotta coup
                    legals.add(Move.COUP);
                }
            } else if (stackContainsMove(Move.CALL_BLUFF_ASSASSIN)) { //if someone says you don't have the assassin
                if (p.hasCard(Card.ASSASSIN)) { //and you do
                    legals.add(Move.BLOCK_BLUFF_ASSASSIN); //you can refute that
                }
                legals.add(Move.PASS); //technically you can always pass but why
            } else if (stackContainsMove(Move.CALL_BLUFF_AMBASSADOR)) { //"you don't have the ambassador"
                if (p.hasCard(Card.AMBASSADOR)) {//"yes I do"
                    legals.add(Move.BLOCK_BLUFF_AMBASSADOR);
                }
                legals.add(Move.PASS);
            } else if (stackContainsMove(Move.CALL_BLUFF_CAPTAIN)) {//"you don't have the captain"
                if (p.hasCard(Card.CAPTAIN)) {//"yes I do"
                    legals.add(Move.BLOCK_BLUFF_CAPTAIN);
                }
                legals.add(Move.PASS);
            } else if (stackContainsMove(Move.CALL_BLUFF_CONTESSA)) {//"you don't have the contessa"
                if (p.hasCard(Card.CONTESSA)) {//"yes... I do!"
                    legals.add(Move.BLOCK_BLUFF_CONTESSA);
                }
                legals.add(Move.PASS);
            } else if (stackContainsMove(Move.CALL_BLUFF_DUKE)) {//"etc etc duke"
                if (p.hasCard(Card.DUKE)) {//"jesus christ stop already"
                    legals.add(Move.BLOCK_BLUFF_DUKE);
                }
                legals.add(Move.PASS);
            } else if (stackContainsMove(Move.BLOCK_FOREIGN_AID)) {//if they said they had a duke
                legals.add(Move.PASS);
                legals.add(Move.CALL_BLUFF_DUKE); //you can challenge
            } else if (stackContainsMove(Move.BLOCK_ASSASSINATE)) {//if they said they had a contessa
                legals.add(Move.PASS);
                legals.add(Move.CALL_BLUFF_CONTESSA); //etc
            } else if (stackContainsMove(Move.BLOCK_STEAL_AMBASSADOR)) { //if they say they know an ambassador
                legals.add(Move.PASS);
                legals.add(Move.CALL_BLUFF_AMBASSADOR); //etc
            } else if (stackContainsMove(Move.BLOCK_STEAL_CAPTAIN)) { //same with captain
                legals.add(Move.PASS);
                legals.add(Move.CALL_BLUFF_CAPTAIN);
            }
        } else { //otherwise, it's ANOTHER PLAYER!
            legals.add(Move.PASS); //can always pass. Will /usually/ pass.
            if (stackContainsMove(Move.EXCHANGE)) { //no exchanging
                legals.add(Move.CALL_BLUFF_AMBASSADOR);
            }
            if (stackContainsMove(Move.FOREIGN_AID)) { //no foreign aid
                legals.add(Move.BLOCK_FOREIGN_AID);
            }
            if (stack.contains(new Action(p, Move.ASSASSINATE))) { //no killing
                legals.add(Move.BLOCK_ASSASSINATE);
                legals.add(Move.CALL_BLUFF_ASSASSIN);
            }
            if (stack.contains(new Action(p, Move.STEAL))) { //no stealing
                legals.add(Move.BLOCK_STEAL_AMBASSADOR);
                legals.add(Move.BLOCK_STEAL_CAPTAIN);
                legals.add(Move.CALL_BLUFF_CAPTAIN);
            }
            if (stackContainsMove(Move.BLOCK_ASSASSINATE)) { //no kill blocking
                legals.add(Move.CALL_BLUFF_CONTESSA);
            }
            if (stackContainsMove(Move.TAX)) { //no taxing
                legals.add(Move.CALL_BLUFF_DUKE);
            }
        }

        return legals;
    }

    public List<Move> getValidMoves(GameState gS) {
        return getValidMoves(gS, gS.getPlayers().get(0));
    }


    private boolean stackContainsMove(Move m) {
        for (Action a : stack) {
            System.out.println(a);
            if (m.equals(a.move)) {
                return true;
            }
        }
        return false;
    }

    public void aiTurns() {
        this.game.aiTurns();
    }

}
