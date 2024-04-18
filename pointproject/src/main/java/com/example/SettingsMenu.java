package com.example;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.Button;

public class SettingsMenu extends Group{

    final String DONE_BUTTON_TEXT = "Done!";
    final int DONE_BUTTON_WIDTH = 150;
    final int DONE_BUTTON_HEIGHT = 50;

    int scrX;
    int scrY;
    Stage stage;

    public SettingsMenu(int X, int Y, Stage s) {
        super();
        scrX = X;
        scrY = Y;
        stage = s;

        addDoneButton();
    }

    //CREATION FUNCTIONS

    private void addDoneButton() {
        Button exitBtn = new Button(DONE_BUTTON_TEXT);
        exitBtn.setPrefSize(DONE_BUTTON_WIDTH, DONE_BUTTON_HEIGHT);
        exitBtn.setTranslateX(scrX/2 - DONE_BUTTON_WIDTH/2);
        exitBtn.setTranslateY(scrY - DONE_BUTTON_HEIGHT*2);
        exitBtn.setOnMouseClicked(e -> {
            moveToMainMenu();
        });
        this.getChildren().add(exitBtn);
    }

    //HELPER FUNCTIONS

    private void moveToMainMenu(){
        stage.getScene().setRoot(new MainMenu(scrX, scrY, stage));
    }
}
