package com.example.menus.gamemenu;

import java.util.ArrayList;

import com.example.MenuController;
import com.example.Interfaces.InterfaceThemeObserver;
import com.example.Interfaces.InterfaceThemeSubject;
import com.example.helpClasses.ColorPackage;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class GameLivesIndicator extends Group implements InterfaceThemeObserver {

    final static int LIVES_BORDER_WIDTH = 5;

    private ArrayList<Rectangle> lifeList;
    //private MenuController menuController;
    private int width, height;
    private Rectangle bound;

    public GameLivesIndicator(MenuController mC, int w, int h){
        lifeList = new ArrayList<Rectangle>();
        //menuController = mC;
        width = w;
        height = h;

        addLivesToList();
        createBound();
        updateLives(MenuController.NUMBER_OF_STARTING_LIVES);
    }

    public void updateLives(int livs){
        this.getChildren().clear();
        this.getChildren().add(bound);
        for (int i = 0; i < livs; i++){
            this.getChildren().add(lifeList.get(i));
        }
    }


    //private functions
    private void addLivesToList(){
        for (int i = 0; i < MenuController.NUMBER_OF_STARTING_LIVES; i++){

            int heightRatio = height * 2/3;
            int lifeWidth = width / (MenuController.NUMBER_OF_STARTING_LIVES + 1);
            int lifePos = ((i+1) * lifeWidth/(MenuController.NUMBER_OF_STARTING_LIVES + 1)) + (i * lifeWidth);
            //int lifePos = lifeWidth/2 * (i+1);
            //int lifePos = i * (width / MenuController.NUMBER_OF_STARTING_LIVES);
            //lifeWidth - lifewidht/2 * i+1

            Rectangle life = new Rectangle();
            life.setWidth(lifeWidth);
            life.setHeight(heightRatio);
            life.setTranslateX(lifePos);
            life.setTranslateY((height - heightRatio) / 2);
            lifeList.add(life);
        }
    }

    private void createBound(){
        bound = new Rectangle();
        bound.setWidth(width);
        bound.setHeight(height);
        bound.setStrokeWidth(LIVES_BORDER_WIDTH);
        //bound.setFill(Color.WHITE);
        //bound.setOpacity(0);
        //bound.setFill()
    }

    //Interface Requirements
    public void update(ColorPackage cP){
        lifeList.forEach( (n) -> {
            n.setFill(cP.getPrimaryColor());
        });
        
        bound.setFill(cP.getSecondaryColor());
        //bound.setStrokeType(null);
        bound.setStroke(cP.getPrimaryColor());
    }

    public void setSubject(InterfaceThemeSubject sub){
        sub.register(this);
    }
}
