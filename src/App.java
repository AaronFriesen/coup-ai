import view.HelloWorld;
import view.JFXHelloWorld;
import model.CoupGameState;

public class App {
    public static void main(String ... args) {
        //here is where we start + run the program. Makefile targets this.
        HelloWorld.main(args);
        JFXHelloWorld.main(args);
        CoupGameState();
    }
}
