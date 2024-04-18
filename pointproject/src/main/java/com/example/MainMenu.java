package com.example;

import javafx.scene.*;
//import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.application.Platform;

public class MainMenu extends Group {

    final int START_BUTTON_WIDTH = 300;
    final int START_BUTTON_HEIGHT = 100;
    final String START_BUTTON_TEXT = "Start Game!";
    final String SETTINGS_BUTTON_TEXT = "Settings";
    final String EXIT_BUTTON_TEXT = "Exit";
    final String TITLE_TEXT = "Point Game";
    final int TITLE_TEXT_FONT_SIZE = 100;
    final int TITLE_TEXT_WIDTH = 520;
    final int TITLE_TEXT_HEIGHT = 200;

    int scrX;
    int scrY;
    Stage stage;

    
    public MainMenu(int X, int Y, Stage s){
        super();
        scrX = X;
        scrY = Y;
        stage = s;

        addStartGameButton();
        addSettingsButton();
        addExitButton();
        addTitelText();
    }

    //CREATION FUNCTIONS
    private void addStartGameButton() {
        Button startBtn = new Button(START_BUTTON_TEXT);
        startBtn.setPrefSize(START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
        startBtn.setTranslateX(scrX/2 - START_BUTTON_WIDTH/2);
        startBtn.setTranslateY(scrY/2 - START_BUTTON_HEIGHT/2);
        this.getChildren().add(startBtn);
    }

    private void addSettingsButton() {
        Button settingsBtn = new Button(SETTINGS_BUTTON_TEXT);
        settingsBtn.setPrefSize(START_BUTTON_WIDTH/2, START_BUTTON_HEIGHT/2);
        settingsBtn.setTranslateX(scrX/2 - START_BUTTON_WIDTH/4);
        settingsBtn.setTranslateY(scrY/2 + START_BUTTON_HEIGHT);
        settingsBtn.setOnMouseClicked( e -> {
            moveToSettingsPage();
        });
        this.getChildren().add(settingsBtn);
    }

    private void addExitButton() {
        Button exitBtn = new Button(EXIT_BUTTON_TEXT);
        exitBtn.setPrefSize(START_BUTTON_WIDTH/2, START_BUTTON_HEIGHT/2);
        exitBtn.setTranslateX(scrX/2 - START_BUTTON_WIDTH/4);
        exitBtn.setTranslateY(scrY/2 + START_BUTTON_HEIGHT*2);
        exitBtn.setOnMouseClicked(e -> {
            exitApp();
        });
        this.getChildren().add(exitBtn);
    }


    private void addTitelText() {
        Label titleText = new Label(TITLE_TEXT);
        titleText.setFont(new Font(TITLE_TEXT_FONT_SIZE));
        titleText.setPrefWidth(TITLE_TEXT_WIDTH);
        titleText.setTextFill(Color.valueOf("RED"));
        System.out.println(titleText.getMinWidth());
        titleText.setTranslateX(scrX/2 - TITLE_TEXT_WIDTH/2);
        this.getChildren().add(titleText);
    }

    //HELPER FUNCTIONS
    
    private void exitApp(){
        Platform.exit();
    }

    private void moveToSettingsPage() {
        stage.getScene().setRoot(new SettingsMenu(scrX, scrY, stage));
    }

}
