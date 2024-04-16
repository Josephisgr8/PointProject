package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.IOException;

/**
 * JavaFX App
 */


public class App extends Application {
    final int WINDOW_SIZE_X = 600;
    final int WINDOW_SIZE_Y = 600;
    final int START_BUTTON_WIDTH = 300;
    final int START_BUTTON_HEIGHT = 150;
    final String START_BUTTON_TEXT = "Start Game!";
    final String TITLE = "Point Game";

    @Override
    public void start(Stage stage) {
        stage.setFullScreen(true);
        stage.setTitle(TITLE);
        stage.setScene(createScene());
        stage.show();
    }

    private Scene createScene() {
        Group root = new Group();
        addStartGameButton(root);
        return new Scene(root, WINDOW_SIZE_X, WINDOW_SIZE_Y);
    }

    private void addStartGameButton(Group root) {
        Button startBtn = new Button(START_BUTTON_TEXT);
        startBtn.setPrefSize(START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
        startBtn.setLayoutX(WINDOW_SIZE_X/2 - START_BUTTON_WIDTH/2);
        startBtn.setLayoutY(WINDOW_SIZE_Y/2 - START_BUTTON_HEIGHT/2);
        root.getChildren().add(startBtn);
    }
}