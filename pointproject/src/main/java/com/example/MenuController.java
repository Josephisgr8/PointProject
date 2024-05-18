//This class is made to hold an instance of all pages so a new one doesn't have to be made each transition
package com.example;

import javafx.scene.Scene;
import javafx.stage.Stage;


public class MenuController {

    private MainMenu mainMenu;
    private SettingsMenu settingsMenu;
    private MenuBackgroundColor menuBackgroundColor;
    private GameMenu gameMenu;

    private int scrX;
    private int scrY;
    private Stage stage;


    public MenuController(int X, int Y, Stage s){

        scrX = X;
        scrY = Y;
        stage = s;

        mainMenu = new MainMenu(scrX, scrY, this);
        settingsMenu = new SettingsMenu(scrX, scrY, this, stage);
        gameMenu = new GameMenu(scrX, scrY, this);
        menuBackgroundColor = new MenuBackgroundColor();
        
    }

    //Accessors
    public void setMainMenu(){
        if (stage.getScene() == null){
            stage.setScene(new Scene(mainMenu));
        }
        stage.getScene().setRoot(mainMenu);
    }

    public void setSettingsMenu(){
        stage.getScene().setRoot(settingsMenu);
    }

    public void setGameMenu(){
        stage.getScene().setRoot(gameMenu);
    }

}
