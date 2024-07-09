package com.example.menus;

import javafx.stage.Stage;

import java.util.ArrayList;

import com.example.GameThemeHandler;
import com.example.MenuController;
import com.example.Interfaces.InterfaceKeyEventHandle;
import com.example.Interfaces.InterfaceMenu;
//import com.example.MenuBackgroundColor.*;
import com.example.helpClasses.CustomButton;
import com.example.helpClasses.CustomButtonWithArrows;
import com.example.helpClasses.ListElement;
import com.example.helpClasses.CustomButton.ButtonFunction;

import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;

public class SettingsMenu extends Group implements InterfaceMenu, InterfaceKeyEventHandle{

    final String DONE_BUTTON_TEXT = "Done!";
    final String BACKGROUND_COLOR_TEXT = "Change Theme";

    private int scrX;
    private int scrY;
    private MenuController menuController;
    private Stage stage;
    private ArrayList<ListElement> settingsList = new ArrayList<ListElement>();
    //private GameThemeHandler gameThemeHandler;// = new GameThemeHandler();

    public SettingsMenu(MenuController mC) {
        super();
        menuController = mC;
        getInfo();
        createButtons();
    }

    //CREATION FUNCTIONS

    private void createButtons(){

        //backgroundcolor button
        //settingsList.add(newBtn(BACKGROUND_COLOR_TEXT, new ChangeBackgroundColor()));
        CustomButtonWithArrows changeTheme = new CustomButtonWithArrows(BACKGROUND_COLOR_TEXT, new ChangeBackgroundColorPrev(), new ChangeBackgroundColorNext());
        changeTheme.setSubject(menuController.getGameThemeHandler());
        settingsList.add(changeTheme);

        //Done button
        CustomButton doneSettings = new CustomButton(DONE_BUTTON_TEXT, new ReturnToMenu());
        doneSettings.setSubject(menuController.getGameThemeHandler());
        settingsList.add(doneSettings);

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

    private void moveToMainMenu(){
        menuController.setMainMenu();
    }

    private void getInfo(){
        scrX = menuController.getScrX();
        scrY = menuController.getScrY();
        stage = menuController.getStage();
        //gameThemeHandler = menuController.getGameThemeHandler();
    }

    //HELPER CLASSES

    private class ChangeBackgroundColorPrev implements ButtonFunction{
        public void assignFunction(){
            menuController.getGameThemeHandler().prevBackgroundColor();
            //stage.getScene().setFill(gameThemeHandler.nextBackgroundColor().getPrimaryColor());
            //menuController.updateBackground(gameThemeHandler);
        }
    }

    private class ChangeBackgroundColorNext implements ButtonFunction{
        public void assignFunction(){
            menuController.getGameThemeHandler().nextBackgroundColor();
            //stage.getScene().setFill(gameThemeHandler.nextBackgroundColor().getPrimaryColor());
            //menuController.updateBackground(gameThemeHandler);
        }
    }

    private class ReturnToMenu implements ButtonFunction{
        public void assignFunction(){
            menuController.goToPrevMenu();
        }
    }
    //INTERFACE

    /*private interface ButtonFunction {
        public void assignFunction();
    }
 */
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
