package com.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.*;
import java.io.IOException;

/**
 * JavaFX App
 */


public class App extends Application {
    

    final String WINDOW_TITLE_TEXT = "Point Game";

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        stage.setFullScreen(true);
        stage.setTitle(WINDOW_TITLE_TEXT);
        stage.setScene(new MainMenu(new Group(), getScreenX(), getScreenY()));
        //addKeyCommands(stage.getScene());
        stage.show();
    }

    public int getScreenX(){
        return (int)Screen.getPrimary().getBounds().getWidth();
    }

    public int getScreenY(){
        return (int)Screen.getPrimary().getBounds().getHeight();
    }    

    private void addKeyCommands(Scene scene){
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.ESCAPE) {
                Platform.exit();
            }
        });
    }
}