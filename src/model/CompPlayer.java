package model;

import model.CoupPlayer;
import java.util.List;
import be.ac.ulg.montefiore.run.jahmm.*;

public class CompPlayer extends CoupPlayer {

    private Hmm<ObservationInteger> hmm =
        new Hmm<ObservationInteger >(5, OpdfIntegerFactory(10));
    public CompPlayer() {
        this.isAI = true;
    }

    public Move makeMove(List<Move> valids) {
        super.makeMove(valids);
    }
}
