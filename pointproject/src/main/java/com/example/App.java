package com.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.*;
import javafx.scene.paint.Color;
import java.io.IOException;

/**
 * JavaFX App
 */


public class App extends Application {
    final int START_BUTTON_WIDTH = 300;
    final int START_BUTTON_HEIGHT = 100;
    final String START_BUTTON_TEXT = "Start Game!";
    final String SETTINGS_BUTTON_TEXT = "Settings";
    final String EXIT_BUTTON_TEXT = "Exit";
    final String TITLE_TEXT = "Point Game";
    final int TITLE_TEXT_FONT_SIZE = 100;
    final int TITLE_TEXT_WIDTH = 520;
    final int TITLE_TEXT_HEIGHT = 200;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        stage.setFullScreen(true);
        stage.setTitle(TITLE_TEXT);
        stage.setScene(createScene());
        addKeyCommands(stage.getScene());
        stage.show();
    }

    public int getScreenX(){
        return (int)Screen.getPrimary().getBounds().getWidth();
    }

    public int getScreenY(){
        return (int)Screen.getPrimary().getBounds().getHeight();
    }

    private Scene createScene() {
        Group root = new Group();
        addStartGameButton(root);
        addSettingsButton(root);
        addExitButton(root);
        addTitelText(root);
        return new Scene(root, getScreenX(), getScreenY());
    }

    private void addStartGameButton(Group root) {
        Button startBtn = new Button(START_BUTTON_TEXT);
        startBtn.setPrefSize(START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
        startBtn.setTranslateX(getScreenX()/2 - START_BUTTON_WIDTH/2);
        startBtn.setTranslateY(getScreenY()/2 - START_BUTTON_HEIGHT/2);
        root.getChildren().add(startBtn);
    }

    private void addSettingsButton(Group root) {
        Button settingsBtn = new Button(SETTINGS_BUTTON_TEXT);
        settingsBtn.setPrefSize(START_BUTTON_WIDTH/2, START_BUTTON_HEIGHT/2);
        settingsBtn.setTranslateX(getScreenX()/2 - START_BUTTON_WIDTH/4);
        settingsBtn.setTranslateY(getScreenY()/2 + START_BUTTON_HEIGHT);
        root.getChildren().add(settingsBtn);
    }

    private void addExitButton(Group root) {
        Button exitBtn = new Button(EXIT_BUTTON_TEXT);
        exitBtn.setPrefSize(START_BUTTON_WIDTH/2, START_BUTTON_HEIGHT/2);
        exitBtn.setTranslateX(getScreenX()/2 - START_BUTTON_WIDTH/4);
        exitBtn.setTranslateY(getScreenY()/2 + START_BUTTON_HEIGHT*2);
        root.getChildren().add(exitBtn);
    }


    private void addTitelText(Group root) {
        Label titleText = new Label(TITLE_TEXT);
        titleText.setFont(new Font(TITLE_TEXT_FONT_SIZE));
        titleText.setPrefWidth(TITLE_TEXT_WIDTH);
        //titleText.setPrefHeight(TITLE_TEXT_HEIGHT);
        titleText.setTextFill(Color.valueOf("RED"));
        System.out.println(titleText.getMinWidth());
        titleText.setTranslateX(getScreenX()/2 - TITLE_TEXT_WIDTH/2);
        root.getChildren().add(titleText);
    }

    private void addKeyCommands(Scene scene){
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.ESCAPE) {
                Platform.exit();
            }
        });
    }
}