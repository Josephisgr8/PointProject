package com.example;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;

public class GameBoard extends Group implements InterfaceKeyEventHandle{

    final int BOARD_SIZE = 9; //MUST BE DIVISIBLE BY 3
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
                currTile.intializeLabel();

                //Now set tile's x and y-locations and add to list
                int XLoc =((i+ BOARD_PADDING) * tileSize);
                currTile.setXLoc(XLoc);
                currTile.setYLoc(YLoc);
                gameTiles.add(currTile);
            }   
        }
        generateBoardValues();
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
            case A:
                moveLeft();
                break;
            case S:
                moveDown();
                break;
            case D:
                moveRight();
                break;
            case W:
                moveUp();
                break;
            default:
                break;
        }
    }

    //Private Functions
    
    private void moveLeft(){
        if (selectedTile == null) {return;}
        int selTileInd = gameTiles.indexOf(selectedTile);
        if (selTileInd % BOARD_SIZE == 0) {
            gameTiles.get(selTileInd + BOARD_SIZE - 1).selectTile();
            return;
            }
        else {
            gameTiles.get(selTileInd - 1).selectTile();
        }
    }

    private void moveRight(){
        if (selectedTile == null) {return;}
        int selTileInd = gameTiles.indexOf(selectedTile);
        if (selTileInd % BOARD_SIZE == BOARD_SIZE-1){
            gameTiles.get(selTileInd - BOARD_SIZE + 1).selectTile();
        }
        else {
            gameTiles.get(selTileInd + 1).selectTile();
        }
    }

    private void moveUp(){
        if (selectedTile==null) {return;}
        int selTileInd = gameTiles.indexOf(selectedTile);
        if (selTileInd < BOARD_SIZE) {
            gameTiles.get(BOARD_SIZE * (BOARD_SIZE - 1) + selTileInd).selectTile();
        }
        else{
            gameTiles.get(selTileInd - BOARD_SIZE).selectTile();
        }
    }

    private void moveDown(){
        if (selectedTile == null) {return;}
        int selTileInd = gameTiles.indexOf(selectedTile);
        if (selTileInd >= BOARD_SIZE*(BOARD_SIZE-1)) {
            gameTiles.get(selTileInd % BOARD_SIZE).selectTile();
        }
        else {
            gameTiles.get(selTileInd + BOARD_SIZE).selectTile();
        }
    }

    private void generateBoardValues(){
        for (int i = 0; i < gameTiles.size(); i++) {
            //create list of possible values
            ArrayList<Integer> possibleValues = new ArrayList<Integer>();
            for (int j=1; j<BOARD_SIZE+1;j++){
                possibleValues.add(j);
            }
            //System.out.println(possibleValues);

            ArrayList<Integer> compTileInds = new ArrayList<Integer>();
            //check and add those values from the row to the list
            int rowNum = i / BOARD_SIZE;
            //System.out.println(rowNum);
            for (int j=0; j < BOARD_SIZE; j++){
                compTileInds.add(rowNum * BOARD_SIZE + j);
                //System.out.println(compTileInds);
            }
            //Remove current Tile
            compTileInds.remove(compTileInds.indexOf(i));
            //System.out.println(compTileInds);


            //check and add those values from the column to the list
            int colNum = i % BOARD_SIZE;
            for (int j=0; j< BOARD_SIZE; j++){
                compTileInds.add(j * BOARD_SIZE + colNum);
                //System.out.println(compTileInds);
            }
            //Remove current Tile
            compTileInds.remove(compTileInds.indexOf(i));
            //System.out.println(compTileInds);


            //check and add those values from the square to the list

            /*int squareRow = rowNum / 3;
            int squareCol = colNum / 3;
            
            for (int j = squareRow*3; j < squareRow*3 + 2; j++){
                compTileInds.add(j + (BOARD_SIZE * 3 * squareCol));
                compTileInds.add(j + BOARD_SIZE + (BOARD_SIZE * 3 * squareCol));
                compTileInds.add(j + BOARD_SIZE * 2 + (BOARD_SIZE * 3 * squareCol));
            }

            //Remove current Tile
            if (compTileInds.contains(i)){
                compTileInds.remove(compTileInds.indexOf(i));
            } */

            compTileInds.forEach((n) -> {
                if (possibleValues.contains(gameTiles.get(n).getValue())){
                    possibleValues.remove(possibleValues.indexOf(gameTiles.get(n).getValue()));
                }
            });

            //Pick a random value from possible values and assign
            Random rng = new Random();
            int randValInd;
            if (possibleValues.size() == 0){
                System.out.println("uh oh");
                gameTiles.get(i).setValue(0);
            }
            else if (possibleValues.size() > 1){
                randValInd = rng.nextInt(possibleValues.size()-1);
                gameTiles.get(i).setValue(possibleValues.get(randValInd));
            }
            else {
                gameTiles.get(i).setValue(possibleValues.get(0));
            }
                        
        }
    }
}
