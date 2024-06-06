package com.example;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;

public class GameDifficultyMenu extends Group implements InterfaceMenu{

    final String DIFFICULTY_1_TEXT = "Easy";
    final String DIFFICULTY_2_TEXT = "Medium";
    final String DIFFICULTY_3_TEXT = "Hard";
    final int DIFFICULTY_LIST_SPACER = 10;

    private int scrX;
    private int scrY;
    private MenuController menuController;
    private ArrayList<Button> difficultyButtons = new ArrayList<Button>();

    public GameDifficultyMenu(int X, int Y, MenuController mC){
        super();
        scrX = X;
        scrY = Y;
        menuController = mC;

        createBtns();
    }

    private void createBtns(){
        //create buttons
        Button diff1 = new Button();
        Button diff2 = new Button();
        Button diff3 = new Button();

        //setup clicking
        diff1.setOnMouseClicked((e) -> {
            menuController.difficultyChosen(3);
        });

        diff2.setOnMouseClicked((e) -> {
            menuController.difficultyChosen(2);
        });

        diff3.setOnMouseClicked((e) -> {
            menuController.difficultyChosen(1);
        });

        //add text
        diff1.setText(DIFFICULTY_1_TEXT);
        diff2.setText(DIFFICULTY_2_TEXT);
        diff3.setText(DIFFICULTY_3_TEXT);

        //add to list
        difficultyButtons.add(diff1);
        difficultyButtons.add(diff2);
        difficultyButtons.add(diff3);

        difficultyButtons.forEach( (n) -> {
            sizeBtn(n);
            this.getChildren().add(n);
        });
    }

    public void keyStrokeRecieved(KeyCode kC){
        switch (kC) {
            case ESCAPE:
                menuController.goToPrevMenu();
                break;
            default:
                break;
        }
    }

    private void sizeBtn(Button b){
        int menuListSizeX = scrX / 2;
        int menuListSizeY = scrY / 2;

        b.setPrefSize(menuListSizeX, menuListSizeY / (difficultyButtons.size()));
        b.setTranslateX(scrX/2 - menuListSizeX/2);
        b.setTranslateY(menuListSizeY / difficultyButtons.size() * difficultyButtons.indexOf(b) + ((difficultyButtons.indexOf(b)+1) * DIFFICULTY_LIST_SPACER));
        //b.setTranslateY(b.getTranslateY());
    }
}
