package com.example;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;

public class GameBoard extends Group implements InterfaceKeyEventHandle{

    final int BOARD_SIZE = 9;
    final int BOARD_PADDING = 2;//MEASURED IN TILES

    private MenuController menuController;
    private ArrayList<GameTile> gameTiles = new ArrayList<GameTile>();
    private GameTile selectedTile;
    private int tileSize;

    public GameBoard(MenuController mC){
        super();
        menuController = mC;

    }

    public void setTileSize(int minScrLength){
        //Sets tile size based on given min screen size
        tileSize = minScrLength/ (BOARD_SIZE + BOARD_PADDING*2);
    }

    public void setupGameBoard(){
        for (int y = 0; y < BOARD_SIZE; y++){
            
            int YLoc = tileSize * (y + BOARD_PADDING);

            for (int i=0; i< BOARD_SIZE; i++){
                GameTile currTile = new GameTile(this);

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

    public void showBoard(){
        for (int i = 0; i < gameTiles.size(); i++){
            this.getChildren().add(gameTiles.get(i));
        }
    }

    public void selectTile(GameTile gT){
        if (selectedTile != null){
            selectedTile.unSelect();
        }
        selectedTile = gT;
    }

    //Interface Requirements
    public void keyStrokeRecieved(KeyCode kC){
        switch (kC) {
            case ESCAPE:
                break;
            default:
                break;
        }
    }
}
