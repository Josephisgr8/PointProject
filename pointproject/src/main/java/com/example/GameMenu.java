package com.example;

import java.util.ArrayList;

import javafx.scene.*;
import javafx.scene.shape.Rectangle;

public class GameMenu extends Group {

    final int BOARD_SIZE = 9;
    final int BOARD_PADDING = 3;
     

    private int scrX;
    private int scrY;
    private MenuController menuController;
    private ArrayList<GameTile> gameTileRow = new ArrayList<GameTile>();
    private ArrayList<ArrayList> gameTileRowList = new ArrayList<ArrayList>();


    public GameMenu(int X, int Y, MenuController mC){
        super();

        scrX = X;
        scrY = Y;
        menuController = mC;

        createBoard();
        //showBoard();
    }
    
    private void createBoard(){

        //Set tile size
        int tileSize;

        for (int y = 0; y < BOARD_SIZE; y++){
            if (scrX > scrY){
                tileSize = scrY/(BOARD_SIZE+BOARD_PADDING*2);
            }
            else{
                tileSize = scrX/(BOARD_SIZE+BOARD_PADDING*2);            
            }
            int YLoc = BOARD_PADDING + (tileSize * y);

            //empty list
            gameTileRow.clear();
            //loop through to fill row
            for (int i=0; i< BOARD_SIZE; i++){
                GameTile currTile = new GameTile();

                //Set tile size
                currTile.setWandH(tileSize);

                //Now set tile's x and y-locatiosn and add to list

                int XLoc = BOARD_PADDING*2 + (i * tileSize);
                currTile.setXLoc(XLoc);
                currTile.setYLoc(YLoc);
                
                gameTileRow.add(currTile);
            }

            gameTileRowList.add(gameTileRow);

        }
    }

    private void showBoard(){
        gameTileRowList.forEach((n) -> displayRow(n));
    }

    private void displayRow(ArrayList<GameTile> tile){
        tile.forEach((n) -> this.getChildren().add(n));
    }







    //Classes to create game

    private class GameTile extends Rectangle {
        public GameTile(){
            super();

        }

        public void setWandH(int l){
            this.setWidth(l);
            this.setHeight(l);
        }

        public void setXLoc(int X){
            this.setTranslateX(X);
        }

        public void setYLoc(int Y){
            this.setTranslateY(Y);
        }
    }
}


