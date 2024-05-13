package com.example;

import javafx.scene.*;
//import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

import java.util.ArrayList;

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
    int menuListSizeY;
    int menuListSizeX;
    int buttonSizeY;
    Stage stage;
    ArrayList<Button> menuList = new ArrayList<Button>();

    
    public MainMenu(int X, int Y, Stage s){
        super();
        scrX = X;
        scrY = Y;
        stage = s;
        menuListSizeY = Y/2;
        menuListSizeX = X/4;

        createButtons();
        addTitelText();
    }

    //CREATION FUNCTIONS
    private void assignMenuSize(){
        
    }

    private void createButtons(){
        Button startBtn = newBtn(START_BUTTON_TEXT);
        menuList.add(startBtn);
        Button settingsBtn = newBtn(SETTINGS_BUTTON_TEXT, new MoveToSettingsPage());
        menuList.add(settingsBtn);
        Button exitBtn = newBtn(EXIT_BUTTON_TEXT, new ExitApp());
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
        for (int i = 0; i < menuList.size(); i++){
            menuList.get(i).setPrefSize(menuListSizeX, menuListSizeY/(menuList.size()+1));
            menuList.get(i).setTranslateX(scrX/2 - menuListSizeX/2);
            menuList.get(i).setTranslateY(menuListSizeY/menuList.size() * i);
            menuList.get(i).setTranslateY(menuList.get(i).getTranslateY() + scrY/4);
        }
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

    private Button newBtn(String txt){
        Button newBtn = new Button(txt);
        return newBtn;
    }

    private Button newBtn(String txt, ButtonFunction fnc){
        Button newBtn = new Button(txt);
        newBtn.setOnMouseClicked( e-> {
            fnc.assignFunction();
        });
        return newBtn;
    }

    //HELPER CLASSES
    
    private class ExitApp implements ButtonFunction{
        public void assignFunction(){
            Platform.exit();
        }
    }

    private class MoveToSettingsPage implements ButtonFunction{
        public void assignFunction(){
            stage.getScene().setRoot(new SettingsMenu(scrX, scrY, stage));
        }
    }
    //INTERFACE

    private interface ButtonFunction {
        public void assignFunction();
    }

}
