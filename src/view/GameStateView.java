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
import model.Action;
import model.Card;
import controller.GameController;


public class GameStateView extends Application {

    private GameController control = null;//GameController.getInstance();

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
        end.setText("End Turn");
        end.setOnAction(new EventHandler<ActionEvent>() {
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


        Card a, b;
        a = Card.random();
        b = Card.random();
        Image[] buff = new Image[2];
        //gc.strokeOval(100, 100, 100, 100);
        try {
            buff[0] = new Image(a.getFront(), 150, 300, true, true);
            buff[1] = new Image(b.getFront(), 150, 300, true, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        gc.drawImage(buff[0], 0, 0);
        gc.drawImage(buff[1], 150, 0);
        root.getChildren().add(canvas);

        root.getChildren().add(btn);
        root.getChildren().add(end);
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }
}
