package com.example;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class GameTile extends Group{

    final int TILE_THICKNESS = 3;
    final Color TILE_COLOR = Color.WHITE;
    final Color TILE_BORDER_COLOR = Color.BLACK;
    final Color TILE_SELECT_BORDER_COLOR = Color.BLUE;
    final static int DEFAULT_TILE_VALUE = 0;

    public ArrayList<Integer> impossibleValues = new ArrayList<Integer>(); //for assigning tile values
    public ArrayList<Integer> possibleValues = new ArrayList<Integer>();    //for checking hidden tile values
    public String shownValue;
    public GameTileValueState tileValueState;


    private int tileSize;
    private Rectangle outerRect;
    private Rectangle innerRect;
    private int realValue;
    private Label label;
    private GameBoard gameBoard;

    public GameTile(GameBoard gB){
        super();
        gameBoard = gB;
        addRectangles();
        addSelectHandling();
    }

    public void setWandH(int l){
        tileSize = l;
        outerRect.setWidth(l);
        outerRect.setHeight(l);
        innerRect.setWidth(l - (2*TILE_THICKNESS));
        innerRect.setHeight(l - (2*TILE_THICKNESS));
    }

    public void intializeLabel(){
        tileValueState = new GameTileValueStateShown(label, DEFAULT_TILE_VALUE, tileSize);
        label = this.tileValueState.initializeLabel();
        this.getChildren().add(label);
    }

    public void setXLoc(int X){
        this.setTranslateX(X);
        innerRect.setTranslateX(innerRect.getTranslateX()+TILE_THICKNESS);
    }

    public void setYLoc(int Y){
        this.setTranslateY(Y);
        innerRect.setTranslateY(innerRect.getTranslateY()+TILE_THICKNESS);
    }

    public void setValue(int newVal){
        realValue = newVal;
        label = tileValueState.setValue(realValue);
    }

    public int getValue(){
        return realValue;
    }

    public void selectTile(){
        gameBoard.selectTile(this);
        outerRect.setFill(TILE_SELECT_BORDER_COLOR);
    }

    public void unSelect(){
        outerRect.setFill(TILE_BORDER_COLOR);
    }

    public void changeLabelState(){
        tileValueState = tileValueState.nextState();
        label = tileValueState.updateLabel();
    }
    
    //Private Functions

    private void addSelectHandling(){
        this.setOnMouseClicked(e -> selectTile());
    }

    private void addRectangles(){
        outerRect = new Rectangle();
        innerRect = new Rectangle();
        outerRect.setFill(TILE_BORDER_COLOR);
        innerRect.setFill(TILE_COLOR);
        this.getChildren().add(outerRect);
        this.getChildren().add(innerRect);
    }

}
