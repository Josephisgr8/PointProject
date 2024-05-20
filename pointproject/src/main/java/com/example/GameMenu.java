package com.example;

import java.util.ArrayList;

import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class GameMenu extends Group {

    final int BOARD_SIZE = 9;
    final int BOARD_PADDING = 2;//MEASURED IN TILES
    final int TILE_THICKNESS = 3;
    final Color TILE_COLOR = Color.WHITE;
    final Color TILE_BORDER_COLOR = Color.BLACK;
    final Color TILE_SELECT_BORDER_COLOR = Color.RED;
     

    private int scrX;
    private int scrY;
    private MenuController menuController;
    private ArrayList<GameTile> gameTiles = new ArrayList<GameTile>();
    //private ArrayList<ArrayList> gameTileRowList = new ArrayList<ArrayList>();


    public GameMenu(int X, int Y, MenuController mC){
        super();

        scrX = X;
        scrY = Y;
        menuController = mC;

        createBoard();
        showBoard();  
    }
    
    private void createBoard(){

        //Set tile size
        int tileSize;

        if (scrX > scrY){
            tileSize = scrY/(BOARD_SIZE+BOARD_PADDING*2);
        }
        else{
            tileSize = scrX/(BOARD_SIZE+BOARD_PADDING*2);            
        }

        for (int y = 0; y < BOARD_SIZE; y++){
            
            int YLoc = tileSize * (y + BOARD_PADDING);

            //empty list
            //gameTileRow.clear();
            //loop through to fill row
            for (int i=0; i< BOARD_SIZE; i++){
                GameTile currTile = new GameTile();

                //Set tile size and add labels
                currTile.setWandH(tileSize);
                currTile.addLabel();

                //Now set tile's x and y-locations and add to list
                int XLoc =((i+ BOARD_PADDING) * tileSize);
                currTile.setXLoc(XLoc);
                currTile.setYLoc(YLoc);
                gameTiles.add(currTile);
            }   
        }
    }

    private void showBoard(){
        for (int i = 0; i < gameTiles.size();i++){
            //System.out.println(gameTiles.get(i));
            this.getChildren().add(gameTiles.get(i));
        }
    }

    private void unselectTiles(){
        gameTiles.forEach((n) -> n.unSelect());
    }







    //Classes to create game

    private class GameTile extends Group {

        private int tileSize;
        private Rectangle outerRect;
        private Rectangle innerRect;
        private int realValue;
        private Label label;
        private boolean isSelected;

        public GameTile(){
            super();
            addRectangles();
            addSelectHandling();
            isSelected = false;
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

        private void addRectangles(){
            outerRect = new Rectangle();
            innerRect = new Rectangle();
            outerRect.setFill(TILE_BORDER_COLOR);
            innerRect.setFill(TILE_COLOR);
            this.getChildren().add(outerRect);
            this.getChildren().add(innerRect);
        }

        private void addSelectHandling(){
            this.setOnMouseClicked(e -> selectTile());
        }

        private void selectTile(){
            unselectTiles();
            isSelected = true;
            outerRect.setFill(TILE_SELECT_BORDER_COLOR);
        }

        private void unSelect(){
            outerRect.setFill(TILE_BORDER_COLOR);
            isSelected = false;
        }

        
    }
}


