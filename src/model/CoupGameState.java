package model;

import java.util.List;
import java.util.ArrayList;

public class CoupGameState implements GameState {

    private List<Player> players;
    private int activePlayer;
    private Deck deck;
    public CoupGameState(CoupGameState c) { //copy constructor
        players = new ArrayList<Player>();
        for (Player p : c.players) {
            players.add(p.clone());
        }
        this.deck = c.deck.clone();
        this.activePlayer = c.activePlayer;
    }

    public CoupGameState() {
        players = new ArrayList<Player>();
        for (int i = 0; i < 8; i++) {
            players.add(new CoupPlayer());
        }
        this.deck = new Deck();

        for (Player p : players) {
            List<Card> living = new ArrayList<Card>();
            living.add(deck.draw());
            living.add(deck.draw());
            p.setLivingCards(living);
        }

        this.activePlayer = 0;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public GameState generateSuccessorState(Player source, Move m,
                                                Player target) {
        GameState newState = new CoupGameState(this);
        int newSource = 0;
        int newTarget = 0;

        for (int i = 0; i < players.length(); i++) {
            if (newState.players.get(i).equals(source)) {
                newSource = i;
            }

            if (newState.players.get(i).equals(target)) {
                newTarget = i;
            }

        }



        switch (m) {
            case INCOME:
                newState.players.get(activePlayer).addIsk(1); break;
            case FOREIGN_AID:
                newState.players.get(activePlayer).addIsk(2); break;
            case COUP:
                newState.players.get(activePlayer).removeIsk(7);
                newState.players.get(newTarget).killCard(Card.DUKE); break;
            case TAX:
                newState.players.get(activePlayer).addIsk(3); break;
            case ASSASSINATE:
                newState.players.get(activePlayer).removeIsk(3);
                newState.players.get(newTarget).killCard(Card.DUKE); break;
            case EXCHANGE:
                List<Card> newCards = new ArrayList<Card>();
                newCards.add(newState.deck.draw());
                newCards.add(newState.deck.draw());
                newState.players.get(activePlayer).setLivingCards(newCards); break;
            case STEAL:
                newState.players.get(activePlayer).addIsk(newState.players.get(newTarget.stealFrom())); break;
            default: break;
        }

        newState.activePlayer++;
        return newState;

    }

    public GameState resolveFailedCall(Player caller, Move bluffType, Player target) {
        GameState newState = new CoupGameState(this);
        int newCaller = 0;
        int newTarget = 0;

        for (int i = 0; i < players.length(); i++) {
            if (newState.players.get(i).equals(caller)) {
                newCaller = i;
            }

            if (newState.players.get(i).equals(target)) {
                newTarget = i;
            }

        }
        GameController gc = GameController.getInstance();
        gc.playerChooseDeadCard(newState.players.get(newCaller));

        Card replacement = deck.draw();
        switch (bluffType) {
            case CALL_BLUFF_AMBASSADOR:
                newState.players.get(newTarget).replaceCard(Card.AMBASSADOR, replacement);
                deck.discardAndShuffle(Card.AMBASSADOR); break;
            case CALL_BLUFF_ASSASSIN:
                newState.players.get(newTarget).replaceCard(Card.ASSASSIN, replacement);
                deck.discardAndShuffle(Card.ASSASSIN); break;
            case CALL_BLUFF_CAPTAIN:
                newState.players.get(newTarget).replaceCard(Card.CAPTAIN, replacement);
                deck.discardAndShuffle(Card.CAPTAIN); break;
            case CALL_BLUFF_CONTESSA:
                newState.players.get(newTarget).replaceCard(Card.CONTESSA, replacement);
                deck.discardAndShuffle(Card.CONTESSA); break;
            case CALL_BLUFF_DUKE:
                newState.players.get(newTarget).replaceCard(Card.DUKE, replacement);
                deck.discardAndShuffle(Card.DUKE); break;
        }

        return newState;
    }

    public GameState resolveSuccessfulCall(Move bluffType) {
        GameState newState = new CoupGameState(this);
        GameController gc = GameController.getInstance();
        gc.playerChooseDeadCard(newState.players.get(activePlayer));

        return newState;
    }

}
