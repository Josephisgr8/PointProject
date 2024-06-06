package com.example.menus;

import javafx.stage.Stage;

import java.util.ArrayList;

import com.example.MenuBackgroundColor;
import com.example.MenuController;
import com.example.Interfaces.InterfaceKeyEventHandle;
import com.example.Interfaces.InterfaceMenu;

import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;

public class SettingsMenu extends Group implements InterfaceMenu, InterfaceKeyEventHandle{

    final String DONE_BUTTON_TEXT = "Done!";
    final String BACKGROUND_COLOR_TEXT = "Background Color";

    private int scrX;
    private int scrY;
    private MenuController menuController;
    private Stage stage;
    private ArrayList<Button> settingsList = new ArrayList<Button>();
    private MenuBackgroundColor menuBackgroundColor = new MenuBackgroundColor();

    public SettingsMenu(int X, int Y, MenuController mC, Stage s) {
        super();
        scrX = X;
        scrY = Y;
        menuController = mC;
        stage = s;
        createButtons();
    }

    //CREATION FUNCTIONS

    private void createButtons(){

        //backgroundcolor button
        settingsList.add(newBtn(BACKGROUND_COLOR_TEXT, new ChangeBackgroundColor()));
        //Done button
        settingsList.add(newBtn(DONE_BUTTON_TEXT, new ReturnToMenu()));

        sizeButtons();
        showList();
    }

    private void sizeButtons(){
        int menuListSizeX = scrX * 3 / 4;
        int menuListSizeY = scrY * 3 / 4;

        for (int i = 0; i < settingsList.size(); i++){
            settingsList.get(i).setPrefSize(menuListSizeX, menuListSizeY/(settingsList.size()+1));
            settingsList.get(i).setTranslateX(scrX/2 - menuListSizeX/2);
            settingsList.get(i).setTranslateY(menuListSizeY/settingsList.size() * i);
            settingsList.get(i).setTranslateY(settingsList.get(i).getTranslateY() + scrY/4);
        }

    }

    private void showList(){
        for (int i = 0; i < settingsList.size(); i++){
            this.getChildren().add(settingsList.get(i));
        }
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

    private void moveToMainMenu(){
        menuController.setMainMenu();
    }

    //HELPER CLASSES

    private class ChangeBackgroundColor implements ButtonFunction{
        public void assignFunction(){
            stage.getScene().setFill(menuBackgroundColor.nextBackgroundColor());
        }
    }

    private class ReturnToMenu implements ButtonFunction{
        public void assignFunction(){
            moveToMainMenu();
        }
    }
    //INTERFACE

    private interface ButtonFunction {
        public void assignFunction();
    }

    //Interface Requirements

    public void keyStrokeRecieved(KeyCode kC){
        switch (kC) {
            case ESCAPE:
                menuController.goToPrevMenu();
                break;
            default:
                break;
        }
    }

}
