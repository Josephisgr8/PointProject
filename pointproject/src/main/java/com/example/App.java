package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
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


    private MenuController menuController;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setTitle(WINDOW_TITLE_TEXT);
        menuController = new MenuController(getScreenX(), getScreenY(), stage);
        menuController.setMainMenu();
        stage.show();
    }

    public int getScreenX(){
        return (int)Screen.getPrimary().getBounds().getWidth();
    }

    public int getScreenY(){
        return (int)Screen.getPrimary().getBounds().getHeight();
    }    

    /*
    private void addKeyCommands(Scene scene){
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.ESCAPE) {
                Platform.exit();
            }
        });
    }
    */
}