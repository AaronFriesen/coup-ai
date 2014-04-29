package model;

import model.CoupPlayer;
import java.util.List;
import be.ac.ulg.montefiore.run.jahmm.Hmm;
import be.ac.ulg.montefiore.run.jahmm.ObservationInteger;
import be.ac.ulg.montefiore.run.jahmm.OpdfIntegerFactory;

public class CompPlayer extends CoupPlayer {

    private Hmm<ObservationInteger> hmm =
        new Hmm<ObservationInteger >(5, new OpdfIntegerFactory(10));
    public CompPlayer() {
        this.isAI = true;
    }

    public Move makeMove(List<Move> valids) {
        return super.makeMove(valids);
    }
}
