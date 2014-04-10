import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javafx.scene.image.Image;


public class HelloWorld extends Application {
    public static void main(String ... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        StackPane root = new StackPane();

        Canvas canvas = new Canvas(800, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //gc.setFill(Color.ORANGE);
        gc.setLineWidth(5);
        gc.setStroke(Color.ORANGE);

        //gc.strokeOval(100, 100, 100, 100);

        Image buff = new Image("images/card-ambassador.jpg");


        gc.drawImage(buff, 0, 0);

        root.getChildren().add(canvas);

        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }
}
