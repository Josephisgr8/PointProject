package com.example;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;

public class GameBoard extends Group implements InterfaceKeyEventHandle{

    final int BOARD_SIZE = 9; //MUST BE DIVISIBLE BY 3
    final int BOARD_PADDING = 2;//MEASURED IN TILES
    final int BOARD_DIFFICULTY_MODIFIER = 4; //higher is easier

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
        //hideBoardValues();

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
        //create list of possible values
        ArrayList<Integer> possibleValues = new ArrayList<Integer>();
            
        for (int i = 0; i < gameTiles.size();) {

            resetPossibleValues(possibleValues);

            //remove any values that didn't work out
            gameTiles.get(i).impossibleValues.forEach((n) -> {
                possibleValues.remove(possibleValues.indexOf(n));
            });

            //list of tiles indexes that are in the same row, column, or square
            ArrayList<Integer> compTileInds = new ArrayList<Integer>();

            //check and add those values from the row to the list
            int rowNum = i / BOARD_SIZE;
            for (int j=0; j < BOARD_SIZE; j++){
                compTileInds.add(rowNum * BOARD_SIZE + j);
            }

            //check and add those values from the column to the list
            int colNum = i % BOARD_SIZE;
            for (int j=0; j< BOARD_SIZE; j++){
                compTileInds.add(j * BOARD_SIZE + colNum);
            }

            //check and add those values from the square to the list
            int squareRow = rowNum / 3;
            int squareCol = colNum / 3;
            
            for (int j = squareRow*3; j < squareRow*3 + 3; j++){
                int leftmostVal = (j * BOARD_SIZE) + (squareCol * 3);
                compTileInds.add(leftmostVal);
                compTileInds.add(leftmostVal+1);
                compTileInds.add(leftmostVal+2);
            }

            //Remove current Tile
            compTileInds.remove(compTileInds.indexOf(i));

            //remove all values from possible values list who are already in the row, column, or square
            compTileInds.forEach((n) -> {
                if (possibleValues.contains(gameTiles.get(n).getValue())){
                    possibleValues.remove(possibleValues.indexOf(gameTiles.get(n).getValue()));
                }
            });

            //Pick a random value from possible values and assign
            Random rng = new Random();
            int randValInd;
            if (possibleValues.size() == 0){ //if this is the case, we want to go back to the previous tile and try again with a different value
                gameTiles.get(i).impossibleValues.clear(); //if there are no possible values, clear the list as we are going back further
                gameTiles.get(i).setValue(GameTile.DEFAULT_TILE_VALUE); //set value to default since we are retrying
                i--;
                gameTiles.get(i).impossibleValues.add(gameTiles.get(i).getValue());
                
            }
            else if (possibleValues.size() > 1){
                randValInd = rng.nextInt(possibleValues.size()-1);
                gameTiles.get(i).setValue(possibleValues.get(randValInd));
                i++;
            }
            else {
                gameTiles.get(i).setValue(possibleValues.get(0));
                i++;
            }          
        }
    }

    private void resetPossibleValues(ArrayList<Integer> ls){
        ls.clear();
        for (int j=1; j<BOARD_SIZE+1;j++){
            ls.add(j);
        }
    }

    private void hideBoardValues() {
        //we want to go through the entire board, and for each tile we will randomly decide whether to hide it or not. Once it is hidden we will call a function to see if the board is still solvable. If so we continue on, if not we replace the value then continue.
        for (int i = 0; i < gameTiles.size(); i++){
            Random rng = new Random();
            if (rng.nextInt(BOARD_DIFFICULTY_MODIFIER) != 0){continue;} // 1 in BOARD_DIFFICULTY_MODIFIER tiles will be possbily hidden
            gameTiles.get(i).changeLabelState();
            if (!boardSolvable()){
                gameTiles.get(i).changeLabelState();
            }
        }
    } 

    private boolean boardSolvable(){ // loop through the tiles and for each hidden value tile, you will try to solve for it. If after the algorithm you are left with only 1 possible value for each hidden tile, then it is solvable. Else return false
        //loop through to get all hidden tiles in the board
        ArrayList<GameTile> hiddenTiles = new ArrayList<GameTile>();
        for (int i=0; i<gameTiles.size(); i++){
            if (gameTiles.get(i).tileValueState instanceof GameTileValueStateHidden){
                hiddenTiles.add(gameTiles.get(i));
            }
        }

        //now loop through hidden tiles to get a list of possible values for each hidden tile
        ArrayList<Integer> possibleValues = new ArrayList<Integer>();
        for (int i=0; i< hiddenTiles.size(); i++){

            resetPossibleValues(possibleValues);
            ArrayList<GameTile> compTiles = comparisonTiles(hiddenTiles.get(i)); //list of tiles in same row, col, and square

            //loop through comparison tiles and remove each value from possible values if it is there, ONLY if the value if not hidden
            compTiles.forEach( (n) -> {
                if (possibleValues.contains(n.getValue()) && n.tileValueState instanceof GameTileValueStateShown){
                    possibleValues.remove(possibleValues.indexOf(n.getValue()));
                }
            });

            //save these possible values in the tile
            gameTiles.get(i).possibleValues = possibleValues;

        }

        //now loop through hidden tiles again, and when you find one that has only 1 possible value, you will 

        boolean changeMade = true;
        while (changeMade){
            changeMade = false;
            //loop through hidden tiles. if possible value list is size 1, then go through its row, col, and square, and remove that possbile value from their lists
            for (int i=0; i<hiddenTiles.size(); i++){
                GameTile currTile = hiddenTiles.get(i);
                if (currTile.possibleValues.size() == 1){
                    changeMade = true;
                    comparisonTiles(currTile).forEach((n) -> {
                        if (n.possibleValues.contains(currTile.possibleValues.get(0))) { //if the comparison tile possible values contains the current hidden tiles 1 possible value, remove it
                            n.possibleValues.remove(currTile.possibleValues.get(0));
                        }
                    });
                    hiddenTiles.remove(hiddenTiles.get(i)); //remove the tile that had one possible value
                    //hiddenTiles.get(i).tileValueState.nextState(); //we want this to not be hidden anymore so that it isn'
                }
            }
        }

        //now after the while loop, if there are any values left in hiddenTiles list, that means they have more than 1 possible value, so the most recently hidden tile cannot be hidden.
        if (hiddenTiles.size() > 0){
            return false;
        }
        return true;

    }

    //returns list of game tiles in the same row, col, and square, minus the given tile
    private ArrayList<GameTile> comparisonTiles(GameTile gT){
        ArrayList<GameTile> compTiles = new ArrayList<GameTile>();
        
        //get tiles in same row
        int rowNum = gameTiles.indexOf(gT) / BOARD_SIZE;
        for (int j=0; j < BOARD_SIZE; j++){
            compTiles.add(gameTiles.get(rowNum * BOARD_SIZE + j));
        }

        //get tiles in same column
        int colNum = gameTiles.indexOf(gT) % BOARD_SIZE;
        for (int j=0; j< BOARD_SIZE; j++){
            compTiles.add(gameTiles.get(j * BOARD_SIZE + colNum));
        }

        //get tiles in same square
        int squareRow = rowNum / 3;
        int squareCol = colNum / 3;
        
        for (int j = squareRow*3; j < squareRow*3 + 3; j++){
            int leftmostVal = (j * BOARD_SIZE) + (squareCol * 3);
            compTiles.add(gameTiles.get(leftmostVal));
            compTiles.add(gameTiles.get(leftmostVal+1));
            compTiles.add(gameTiles.get(leftmostVal+2));
        }

        //now remove hidden tile from comparison tile list and return
        compTiles.remove(gT);

        return compTiles;
    }

    /*private void generateBoardValues2(){
        for (int i=1; i<BOARD_SIZE+1; i++){
            //loop through rows assigning each row a single instance of i
            for (int row=0; row<BOARD_SIZE; row++){
                //a list of possible tiles to assign the vale i
                ArrayList<GameTile> possibleTiles = new ArrayList<GameTile>();
                for (int j=0; j< BOARD_SIZE; j++){
                    int currTileInd = row * BOARD_SIZE + j;
                    if (gameTiles.get(currTileInd).getValue() == GameTile.DEFAULT_TILE_VALUE){ //this means the tile is available to be assigned
                        possibleTiles.add(gameTiles.get(currTileInd));
                    }
                }

                boolean tileIsChosen = false;
                while (!tileIsChosen){
                    //now pick a random one
                    Random rng = new Random();
                    int randValInd;
                    GameTile randTile;
                    if (possibleTiles.size() == 0){
                        System.out.println("we messed up");
                        return;
                    }
                    else if (possibleTiles.size() == 1){
                        randTile = possibleTiles.get(0);
                    }
                    else {
                        randValInd = rng.nextInt(possibleTiles.size()-1);
                        randTile = possibleTiles.get(randValInd); 
                    }

                    //and check to see if its column is available
                    int currCol = gameTiles.indexOf(masterListOfThisTile(randTile)) % BOARD_SIZE;
                    boolean colTaken = false;
                    for (int j=0; j< BOARD_SIZE; j++){
                        GameTile currCompTile = gameTiles.get(BOARD_SIZE * j + currCol);
                        if (gameTiles.get(BOARD_SIZE * j + currCol) != masterListOfThisTile(randTile)){//if the currtile in the column is not the one we chose
                            if (currCompTile.getValue() == i) {//and if the value we are assigning is already taken
                                colTaken=true;
                            }
                        }
                    }

                    //if at the end the column is not already taken, assign the tile the current value. If it is taken, remove it from possile tiles and try again
                    if (!colTaken){
                        masterListOfThisTile(randTile).setValue(i);
                        tileIsChosen = true;
                    }
                    else {
                        possibleTiles.remove(randTile);
                    }
                }
                
            }
        }
    }

    private GameTile masterListOfThisTile(GameTile gT){
        return gameTiles.get(gameTiles.indexOf(gT));
    } */
}
