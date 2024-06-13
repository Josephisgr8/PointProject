package com.example.menus;

import java.util.ArrayList;

import com.example.MenuController;
import com.example.Interfaces.InterfaceMenu;
import com.example.helpClasses.CustomButton;
import com.example.helpClasses.CustomButton.ButtonFunction;

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
    private ArrayList<CustomButton> difficultyButtons = new ArrayList<CustomButton>();

    public GameDifficultyMenu(int X, int Y, MenuController mC){
        super();
        scrX = X;
        scrY = Y;
        menuController = mC;

        createBtns();
    }

    private void createBtns(){
        //create buttons
        CustomButton diff1 = new CustomButton(DIFFICULTY_1_TEXT, new SetDifficultyEasy());
        CustomButton diff2 = new CustomButton(DIFFICULTY_2_TEXT, new SetDifficultyMedium());
        CustomButton diff3 = new CustomButton(DIFFICULTY_3_TEXT, new SetDifficultyHard());

        diff1.setSubject(menuController.getGameThemeHandler());
        diff2.setSubject(menuController.getGameThemeHandler());
        diff3.setSubject(menuController.getGameThemeHandler());

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

    private void sizeBtn(CustomButton b){
        int menuListSizeX = scrX / 2;
        int menuListSizeY = scrY / 2;

        b.setPrefSize(menuListSizeX, menuListSizeY / (difficultyButtons.size()));
        b.setTranslateX(scrX/2 - menuListSizeX/2);
        b.setTranslateY(menuListSizeY / difficultyButtons.size() * difficultyButtons.indexOf(b) + ((difficultyButtons.indexOf(b)+1) * DIFFICULTY_LIST_SPACER));
        //b.setTranslateY(b.getTranslateY());
    }

    public class SetDifficultyEasy implements ButtonFunction{
        public void assignFunction(){
            menuController.difficultyChosen(3);
        }
    }

    public class SetDifficultyMedium implements ButtonFunction{
        public void assignFunction(){
            menuController.difficultyChosen(2);
        }
    }

    public class SetDifficultyHard implements ButtonFunction{
        public void assignFunction(){
            menuController.difficultyChosen(1);
        }
    }
}

