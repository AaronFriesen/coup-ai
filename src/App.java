//import view.HelloWorld;
//import view.JFXHelloWorld;
import controller.GameController;
import model.CoupGameState;
import view.GameStateView;

public class App {
    public static void main(String ... args) {
        //here is where we start + run the program. Makefile targets this.
        GameController myControl = GameController.getInstance();
        GameStateView.main(args);
        //CoupGameState();
    }
}
