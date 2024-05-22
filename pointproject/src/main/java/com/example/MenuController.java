//This class is made to hold an instance of all pages so a new one doesn't have to be made each transition
package com.example;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class MenuController {

    private MainMenu mainMenu;
    private SettingsMenu settingsMenu;
    private MenuBackgroundColor menuBackgroundColor;
    private GameMenu gameMenu;
    private GameBoard gameBoard;

    private int scrX;
    private int scrY;
    private Stage stage;
    private InterfaceMenu prevMenu;
    private InterfaceMenu currMenu;


    public MenuController(int X, int Y, Stage s){

        scrX = X;
        scrY = Y;
        stage = s;
        createPages();
        stage.setScene(new Scene(mainMenu));    //start on Main Menu
        createKeyEventHandler();
        
    }

    //Accessors
    public void setMainMenu(){
        stage.getScene().setRoot(mainMenu);
        currMenu = mainMenu;
    }

    public void setSettingsMenu(){
        stage.getScene().setRoot(settingsMenu);
        currMenu = settingsMenu;
    }

    public void setGameMenu(){
        stage.getScene().setRoot(gameMenu);
        currMenu = gameMenu;
    }

    //Private functions

    private void createPages(){

        mainMenu = new MainMenu(scrX, scrY, this);
        settingsMenu = new SettingsMenu(scrX, scrY, this, stage);
        gameBoard = new GameBoard(this);
        gameMenu = new GameMenu(scrX, scrY, this, gameBoard);
        menuBackgroundColor = new MenuBackgroundColor();

    }

    private void createKeyEventHandler(){
        stage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, (key) ->{
            currMenu.keyStrokeRecieved(key.getCode());
        });
    }

}
