package com.example.menus.gamemenu;

import java.util.ArrayList;

import com.example.MenuController;
import com.example.Interfaces.InterfaceThemeObserver;
import com.example.Interfaces.InterfaceThemeSubject;
import com.example.helpClasses.ColorPackage;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class GameLivesIndicator extends Group implements InterfaceThemeObserver {

    final static int LIVES_BORDER_WIDTH = 5;
    final static String LIVES_TEXT = "Lives";

    private ArrayList<Rectangle> lifeList;
    private int width, height, boxHeight;
    private Rectangle bound;
    private Label livesLabel;

    public GameLivesIndicator(MenuController mC, int w, int h){
        lifeList = new ArrayList<Rectangle>();
        width = w;
        height = h;
        boxHeight = h/2;

        addLivesToList(MenuController.NUMBER_OF_STARTING_LIVES);
        createBound();
        createLabel();
        updateLives(MenuController.NUMBER_OF_STARTING_LIVES);
    }

    public void updateLives(int livs){
        this.getChildren().clear();
        this.getChildren().add(bound);
        this.getChildren().add(livesLabel);
        for (int i = 0; i < livs; i++){
            this.getChildren().add(lifeList.get(i));
        }
    }

    //Accessors

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }


    //private functions
    private void addLivesToList(int numOfLives){
        for (int i = 0; i < numOfLives; i++){

            int heightRatio = boxHeight * 2/3;
            int lifeWidth = width / (numOfLives + 1);
            int lifePos = ((i+1) * lifeWidth/(numOfLives + 1)) + (i * lifeWidth);

            Rectangle life = new Rectangle();
            life.setWidth(lifeWidth);
            life.setHeight(heightRatio);
            life.setTranslateX(lifePos);
            life.setTranslateY((boxHeight - heightRatio) / 2);
            life.setTranslateY(life.getTranslateY() + boxHeight);//added to move everythign down from the label
            lifeList.add(life);
        }
    }

    private void createBound(){
        bound = new Rectangle();
        bound.setWidth(width);
        bound.setHeight(boxHeight);
        bound.setStrokeWidth(LIVES_BORDER_WIDTH);
        bound.setTranslateY(bound.getTranslateY() + boxHeight);// added to move everything down from the label
    }

    private void createLabel(){
        livesLabel = new Label(LIVES_TEXT);
        livesLabel.setFont(new Font((boxHeight) / 1.33333));
        livesLabel.setMaxWidth(width);
        //livesLabel.setTranslateX(0);
        //livesLabel.setTranslateY(0);
        this.getChildren().add(livesLabel);
    }

    //Interface Requirements
    public void update(ColorPackage cP){
        lifeList.forEach( (n) -> {
            n.setFill(cP.getPrimaryColor());
        });
        
        bound.setFill(cP.getSecondaryColor());
        bound.setStroke(cP.getPrimaryColor());
        livesLabel.setTextFill(cP.getSecondaryColor());
    }

    public void setSubject(InterfaceThemeSubject sub){
        sub.register(this);
    }
}
