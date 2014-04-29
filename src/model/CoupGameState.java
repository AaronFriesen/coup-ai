package model;

import java.util.List;
import java.util.ArrayList;
import controller.*;

public class CoupGameState implements GameState {

    private static final int NUM_PLAYERS = 4;



    private CoupPlayer[] players;

    private int activePlayer;
    private Deck deck;

    private Player humanPlayer;
    public CoupGameState(CoupGameState c) { //copy constructor
        players = new CoupPlayer[4];
        for (int i = 0; i < c.players.length; i++) {
            this.players[i] = (CoupPlayer)c.players[i].clone();

        }
        this.deck = c.deck.clone();
        this.activePlayer = c.activePlayer;
        this.humanPlayer = c.humanPlayer;
    }

    public CoupGameState() {
        players = new CoupPlayer[4];
        for (int i = 1; i < NUM_PLAYERS; i++) {
            players[i] = (new CompPlayer());
            players[i].myNum = i;
        }
        humanPlayer = new CoupPlayer();
        players[0] = (CoupPlayer)(humanPlayer);
        players[0].myNum = 0;
        this.deck = new Deck();

        for (Player p : players) {
            List<Card> living = new ArrayList<Card>();
            living.add(deck.draw());
            living.add(deck.draw());
            p.setLivingCards(living);
        }

        this.activePlayer = 0;
    }

    public Player[] getPlayers() {
        return this.players;
    }

    public Player getCurrentPlayer() {

        return players[activePlayer];
    }

    public Player getHumanPlayer() {
        return humanPlayer;
    }

    public GameState generateSuccessorState(Player source, Move m,
                                                Player target) {
        CoupGameState newState = new CoupGameState(this);
        int newSource = 0;
        int newTarget = 0;

        for (int i = 0; i < players.length; i++) {
            if (newState.players[i].equals(source)) {
                newSource = i;
            }

            if (newState.players[i].equals(target)) {
                newTarget = i;
            }

        }



        switch (m) {
            case INCOME:
                newState.players[activePlayer].addIsk(1); break;
            case FOREIGN_AID:
                newState.players[activePlayer].addIsk(2); break;
            case COUP:
                newState.players[activePlayer].removeIsk(7);
                newState.players[newTarget].killCard(Card.DUKE); break;
            case TAX:
                newState.players[activePlayer].addIsk(3); break;
            case ASSASSINATE:
                newState.players[activePlayer].removeIsk(3);
                newState.players[newTarget].killCard(Card.DUKE); break;
            case EXCHANGE:
                List<Card> newCards = new ArrayList<Card>();
                newCards.add(newState.deck.draw());
                newCards.add(newState.deck.draw());
                newState.players[activePlayer].setLivingCards(newCards); break;
            case STEAL:
                newState.players[activePlayer].addIsk(newState.players[newTarget].stealFrom()); break;
            default: break;
        }

        newState.activePlayer++;
        newState.activePlayer %= NUM_PLAYERS;
        return newState;

    }

    public GameState resolveFailedCall(Player caller, Move bluffType, Player target) {
        CoupGameState newState = new CoupGameState(this);
        int newCaller = 0;
        int newTarget = 0;

        for (int i = 0; i < newState.players.length; i++) {
            if (newState.players[i].equals(caller)) {
                newCaller = i;
            }

            if (newState.players[i].equals(target)) {
                newTarget = i;
            }

        }
        GameController gc = GameController.getInstance();
        gc.playerChooseDeadCard(newState.players[newCaller]);

        Card replacement = deck.draw();
        switch (bluffType) {
            case CALL_BLUFF_AMBASSADOR:
                newState.players[newTarget].replaceCard(Card.AMBASSADOR, replacement);
                deck.discardAndShuffle(Card.AMBASSADOR); break;
            case CALL_BLUFF_ASSASSIN:
                newState.players[newTarget].replaceCard(Card.ASSASSIN, replacement);
                deck.discardAndShuffle(Card.ASSASSIN); break;
            case CALL_BLUFF_CAPTAIN:
                newState.players[newTarget].replaceCard(Card.CAPTAIN, replacement);
                deck.discardAndShuffle(Card.CAPTAIN); break;
            case CALL_BLUFF_CONTESSA:
                newState.players[newTarget].replaceCard(Card.CONTESSA, replacement);
                deck.discardAndShuffle(Card.CONTESSA); break;
            case CALL_BLUFF_DUKE:
                newState.players[newTarget].replaceCard(Card.DUKE, replacement);
                deck.discardAndShuffle(Card.DUKE); break;
        }

        return newState;
    }

    public GameState resolveSuccessfulCall(Move bluffType) {
        CoupGameState newState = new CoupGameState(this);
        GameController gc = GameController.getInstance();
        gc.playerChooseDeadCard(newState.players[activePlayer]);
        newState.activePlayer++;
        newState.activePlayer %= NUM_PLAYERS;

        return newState;
    }

    public void setPlayers(Player[] l) {
        this.players = (CoupPlayer[])l;
    }

    public String toString() {
        String ret = "Game with active player " + activePlayer;
        for (Player p : players) {
            ret += "\n" + p;
        }
        return ret;
    }

}
