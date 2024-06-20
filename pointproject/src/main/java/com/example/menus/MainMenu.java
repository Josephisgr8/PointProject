package com.example.menus;

import javafx.scene.*;
//import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import com.example.MenuController;
import com.example.Interfaces.InterfaceKeyEventHandle;
import com.example.Interfaces.InterfaceMenu;
import com.example.Interfaces.InterfaceThemeObserver;
import com.example.Interfaces.InterfaceThemeSubject;
import com.example.helpClasses.ColorPackage;
import com.example.helpClasses.CustomButton;
import com.example.helpClasses.ListElement;
import com.example.helpClasses.CustomButton.ButtonFunction;

import javafx.application.Platform;

public class MainMenu extends Group implements InterfaceMenu, InterfaceKeyEventHandle, InterfaceThemeObserver{

    final String START_BUTTON_TEXT = "Start Game";
    final String SETTINGS_BUTTON_TEXT = "Settings";
    final String EXIT_BUTTON_TEXT = "Exit";
    final String TITLE_TEXT = "Point Game";
    final int TITLE_TEXT_FONT_SIZE = 100;
    final int TITLE_TEXT_WIDTH = 520;
    final int TITLE_TEXT_HEIGHT = 200;

    private int scrX;
    private int scrY;
    private Label titleLabel = new Label(TITLE_TEXT);
    private MenuController menuController;
    private ArrayList<ListElement> menuList = new ArrayList<ListElement>();

    
    public MainMenu(int X, int Y, MenuController mC){
        super();
        scrX = X;
        scrY = Y;
        menuController = mC;
        setSubject(menuController.getGameThemeHandler()); 

        createButtons();
        addTitleText();
    }

    //CREATION FUNCTIONS

    private void createButtons(){
        CustomButton startBtn = new CustomButton(START_BUTTON_TEXT, new StartGame());
        startBtn.setSubject(menuController.getGameThemeHandler());
        menuList.add(startBtn);

        CustomButton settingsBtn = new CustomButton(SETTINGS_BUTTON_TEXT, new MoveToSettingsPage());
        settingsBtn.setSubject(menuController.getGameThemeHandler());
        menuList.add(settingsBtn);

        CustomButton exitBtn = new CustomButton(EXIT_BUTTON_TEXT, new ExitApp());
        exitBtn.setSubject(menuController.getGameThemeHandler());
        menuList.add(exitBtn);

        sizeButtons();
        showList();
    }

    private void showList(){
        for (int i = 0; i < menuList.size(); i++){
            this.getChildren().add(menuList.get(i));
        }
    }

    private void sizeButtons(){

        int menuListSizeX = scrX / 4;
        int menuListSizeY = scrY / 2;
        for (int i = 0; i < menuList.size(); i++){
            menuList.get(i).setPrefSize(menuListSizeX, menuListSizeY/(menuList.size()+1));
            menuList.get(i).setTranslateX(scrX/2 - menuListSizeX/2);
            menuList.get(i).setTranslateY(menuListSizeY/menuList.size() * i);
            menuList.get(i).setTranslateY(menuList.get(i).getTranslateY() + scrY/4);
        }
    }

    private void addTitleText() {
        titleLabel.setFont(new Font(TITLE_TEXT_FONT_SIZE));
        titleLabel.setPrefWidth(TITLE_TEXT_WIDTH);
        //titleLabel.setTextFill(Color.valueOf("RED"));
        System.out.println(titleLabel.getMinWidth());
        titleLabel.setTranslateX(scrX/2 - TITLE_TEXT_WIDTH/2);
        this.getChildren().add(titleLabel);
    }

    //HELPER FUNCTIONS

    /* private Button newBtn(String txt){
        Button newBtn = new Button(txt);
        return newBtn;
    }

    private Button newBtn(String txt, ButtonFunction fnc){
        Button newBtn = new Button(txt);
        newBtn.setOnMouseClicked( e-> {
            fnc.assignFunction();
        });
        return newBtn;
    } */

    //HELPER CLASSES
    
    private class ExitApp implements ButtonFunction{
        public void assignFunction(){
            Platform.exit();
        }
    }

    private class MoveToSettingsPage implements ButtonFunction{
        public void assignFunction(){
            menuController.setSettingsMenu();
        }
    }
    private class StartGame implements ButtonFunction{
        public void assignFunction(){
            menuController.setDifficultyMenu();
        }
    }
    //INTERFACE Requirments
    public void update(ColorPackage cP){
        titleLabel.setTextFill(cP.getSecondaryColor());
    }

    public void setSubject(InterfaceThemeSubject sub){
        sub.register(this);
    }

    /*private interface ButtonFunction {
        public void assignFunction();
    } */

    //Interface Requirements

    public void keyStrokeRecieved(KeyCode kC){
        switch (kC) {
            case ENTER:
                
                break;
            case ESCAPE:
                break;
            default:
                break;
        }
    }

}
