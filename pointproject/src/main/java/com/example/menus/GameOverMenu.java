package com.example.menus;

import com.example.MenuController;
import com.example.Interfaces.InterfaceMenu;
import com.example.Interfaces.InterfaceThemeObserver;
import com.example.Interfaces.InterfaceThemeSubject;
import com.example.helpClasses.ColorPackage;
import com.example.helpClasses.CustomButton;
import com.example.helpClasses.CustomButton.ButtonFunction;
import com.example.menus.gamemenu.GameBoard;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;

public class GameOverMenu extends Group implements InterfaceThemeObserver{ //We don't want to implement interfaceMenu because the player could then go back to the gameMenu from the gameOver screen

    final static String BACK_TO_MAIN_MENU_BUTTON_TEXT = "Return to main menu";
    final static String LOSS_END_GAME_TEXT = "You have failed to complete the game.";

    private MenuController menuController;
    private GameBoard gameBoard;
    private int winOrLoss;
    private Label endGameInfo;
    private CustomButton mainMenuButton;

    public GameOverMenu(MenuController mC, GameBoard gB, int wol){
        menuController = mC;
        gameBoard = gB;
        winOrLoss = wol;
        endGameInfo = new Label();

        createButton();
        createLabel();

        this.getChildren().addAll(gameBoard, endGameInfo, mainMenuButton);

        gameBoard.disableEvents();
    }



    //Private Functions

    private void createButton(){
        mainMenuButton = new CustomButton(BACK_TO_MAIN_MENU_BUTTON_TEXT, new BackToMainMenu());
        mainMenuButton.setSubject(menuController.getGameThemeHandler());

        int screenXButtonRatio = 3;
        int screenYButtonRatio = 10;

        mainMenuButton.setPrefSize(menuController.getScrX() / 3, menuController.getScrY() / screenYButtonRatio);
        mainMenuButton.setTranslateX(menuController.getScrX() / 2 - (menuController.getScrX() / (screenXButtonRatio * 2)));
        mainMenuButton.setTranslateY(menuController.getScrY() - menuController.getScrY() / (screenYButtonRatio - 1));
    }

    private void createLabel(){
        if (winOrLoss == 0){ //0 means loss
            endGameInfo.setText(LOSS_END_GAME_TEXT);
            endGameInfo.setMaxWidth(menuController.getScrX()/2);
            endGameInfo.setFont(new Font(menuController.getScrY() / 20));
            endGameInfo.setWrapText(true);

            endGameInfo.setTranslateX(menuController.getScrX() / 2);
        }
    }

    //Helpers

    class BackToMainMenu implements ButtonFunction{
        public void assignFunction(){
            menuController.resetMenus();
            menuController.setMainMenu();
        }
    }

    //Interface Requirements

    public void update(ColorPackage cP){

    }

    public void setSubject(InterfaceThemeSubject sub){
        sub.register(this);
    }



    public void keyStrokeRecieved(KeyCode kC){

    }
}
