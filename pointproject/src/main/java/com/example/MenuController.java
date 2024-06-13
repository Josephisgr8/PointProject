//This class is made to hold an instance of all pages so a new one doesn't have to be made each transition
package com.example;

import java.util.ArrayList;

import com.example.Interfaces.InterfaceMenu;
import com.example.Interfaces.InterfaceThemeObserver;
import com.example.Interfaces.InterfaceThemeSubject;
import com.example.menus.GameDifficultyMenu;
import com.example.menus.MainMenu;
import com.example.menus.SettingsMenu;
import com.example.menus.gamemenu.GameBoard;
import com.example.menus.gamemenu.GameMenu;
import com.example.helpClasses.ColorPackage;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class MenuController implements InterfaceThemeObserver {

    private GameThemeHandler gameThemeHandler = new GameThemeHandler();
    private MainMenu mainMenu;
    private SettingsMenu settingsMenu;
    private GameDifficultyMenu gameDifficultyMenu;
    private GameMenu gameMenu;
    private GameBoard gameBoard;
   // private MusicController musicController;

    private int scrX;
    private int scrY;
    private Stage stage;
    //private InterfaceMenu prevMenu;
    //private InterfaceMenu currMenu;
    private ArrayList<InterfaceMenu> menuAccessList = new ArrayList<InterfaceMenu>();


    public MenuController(int X, int Y, Stage s){

        scrX = X;
        scrY = Y;
        stage = s;
        createPages();
        stage.setScene(new Scene(mainMenu));    //start on Main Menu
        createKeyEventHandler();
        this.setSubject(gameThemeHandler);
        
    }

    //Accessors
    public void setMainMenu(){
        setMenu(mainMenu);
    }

    public void setSettingsMenu(){
        setMenu(settingsMenu);
    }

    public void setDifficultyMenu(){
        setMenu(gameDifficultyMenu);
    }

    public void setGameMenu(){
        setMenu(gameMenu);
    }

    public void goToPrevMenu(){
        menuAccessList.remove(menuAccessList.size()-1);
        setMenu(menuAccessList.get(menuAccessList.size()-1));
    }

    /*public ColorPackage getBackgroundColor(){
        return gameThemeHandler.getTheme();
    } */

    public int getScrX(){
        return scrX;
    }

    public int getScrY(){
        return scrY;
    }

    public Stage getStage(){
        return stage;
    }

    public GameThemeHandler getGameThemeHandler(){
        return gameThemeHandler;
    }

    //-----------------------------

    public void updateBackground(GameThemeHandler mbc){ //to keep the MenuController's gameThemeHandler up to date
        gameThemeHandler = mbc;
    }

    public void difficultyChosen(int i){
        //DIFFICULTY SELECTED SO CREATE GAMEBOARD AND GAME MENU
        gameBoard = new GameBoard(this, i);
        gameMenu = new GameMenu(scrX, scrY, this, gameBoard);
        setGameMenu();
    }

    //Private functions

    private InterfaceMenu currMenu(){
        return menuAccessList.get(menuAccessList.size()-1);
    }

    private void setMenu(InterfaceMenu iM){
        if (menuAccessList.contains(iM)){
            menuAccessList.remove(iM);
        }
        menuAccessList.add(iM);
        stage.getScene().setRoot((Group)iM);
    }

    private void createPages(){

        mainMenu = new MainMenu(scrX, scrY, this);
        settingsMenu = new SettingsMenu(this);
        //menuBackgroundColor = new MenuBackgroundColor();
        gameDifficultyMenu = new GameDifficultyMenu(scrX, scrY, this);

    }

    private void createKeyEventHandler(){
        stage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, (key) ->{
            currMenu().keyStrokeRecieved(key.getCode());
        });
    }


    //Interface Requirements
    public void update(ColorPackage cP){
        stage.getScene().setFill(cP.getAccentingColor());
    }

    public void setSubject(InterfaceThemeSubject sub){
        sub.register(this);
    }

}
