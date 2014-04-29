//import view.HelloWorld;
//import view.JFXHelloWorld;
import controller.GameController;
import model.CoupGameState;
import view.CoupPanel;
import model.CoupGame;
import model.Game;
import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String ... args) {
        //here is where we start + run the program. Makefile targets this.
        //GameController myControl = GameController.getInstance();

        JFrame frame = new JFrame("Play Coup!");
        Game game = new CoupGame();
        CoupPanel coupPanel = new CoupPanel();
        coupPanel.setState(game.getState());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(coupPanel);

        frame.pack();
        frame.setVisible(true);
    }
}
