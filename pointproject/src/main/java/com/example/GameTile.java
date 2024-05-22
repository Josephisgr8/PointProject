package com.example;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class GameTile extends Group{

    final int TILE_THICKNESS = 3;
    final Color TILE_COLOR = Color.WHITE;
    final Color TILE_BORDER_COLOR = Color.BLACK;
    final Color TILE_SELECT_BORDER_COLOR = Color.RED;


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

    public void setXLoc(int X){
        this.setTranslateX(X);
        innerRect.setTranslateX(innerRect.getTranslateX()+TILE_THICKNESS);
    }

    public void setYLoc(int Y){
        this.setTranslateY(Y);
        innerRect.setTranslateY(innerRect.getTranslateY()+TILE_THICKNESS);
    }

    public void addLabel(){
        double fontSize = tileSize/1.33333; //conversion from pixels to font size
        label = new Label("9");
        label.setFont(new Font("Arial", fontSize));
        label.setTranslateX(label.getTranslateX() + (tileSize/4));
        this.getChildren().add(label);
    }

    public void unSelect(){
        outerRect.setFill(TILE_BORDER_COLOR);
    }
    
    //Private Functions

    private void addSelectHandling(){
        this.setOnMouseClicked(e -> selectTile());
    }

    private void selectTile(){
        gameBoard.selectTile(this);
        outerRect.setFill(TILE_SELECT_BORDER_COLOR);
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
