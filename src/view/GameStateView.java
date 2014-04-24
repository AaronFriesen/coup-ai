package view;

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
import model.Move;


public class GameStateView extends Application {

    private GameController control = GameController.getInstance();

    public static void main(String ... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Play Coup");
        Button btn = new Button();
        btn.setText("Take Income");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                control.pushAction(new Action(control.getCurrentPlayer(), Move.INCOME));
            }
        });

        Button end = new Button();
        btn.setText("End Turn");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                control.executeActions();
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
        try (Image buff = new Image("images/card-ambassador.jpg", 150, 300, true, true)){

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (buff != null) gc.drawImage(buff, 0, 0);

        root.getChildren().add(canvas);

        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }
}
