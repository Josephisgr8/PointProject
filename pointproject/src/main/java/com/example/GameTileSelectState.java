package com.example;

import javafx.scene.shape.Rectangle;

public abstract class GameTileSelectState {
    public static GameTileSelectState currState;
    public Rectangle outerRect;

    public GameTileSelectState(Rectangle oR){
        outerRect = oR;
    }

    public abstract void valueTyped(int i);
    public abstract GameTileSelectState tileLeftClicked();
    public abstract GameTileSelectState tileRightClicked();
    public abstract GameTileSelectState unselect();
}

class GameTileSelectStateGuess extends GameTileSelectState{
    public GameTileSelectStateGuess(Rectangle oR){
        super(oR);
        outerRect.setFill(GameTile.TILE_SELECT_GUESS_BORDER_COLOR);
    }

    public void valueTyped(int i){

    }

    public GameTileSelectState tileLeftClicked(){
        return new GameTileSelectStateNone(outerRect);
    }

    public GameTileSelectState tileRightClicked(){
        return new GameTileSelectStatePossibility(outerRect);
    }

    public GameTileSelectState unselect(){
        return new GameTileSelectStateNone(outerRect);
    }
}

class GameTileSelectStatePossibility extends GameTileSelectState{
    public GameTileSelectStatePossibility(Rectangle oR){
        super(oR);
        outerRect.setFill(GameTile.TILE_SELECT_POSSIBLE_BORDER_COLOR);
    }

    public void valueTyped(int i){

    }

    public GameTileSelectState tileLeftClicked(){
        return  new GameTileSelectStateGuess(outerRect);
    }

    public GameTileSelectState tileRightClicked(){
        return new GameTileSelectStateNone(outerRect);
    }

    public GameTileSelectState unselect(){
        return new GameTileSelectStateNone(outerRect);
    }
}

class GameTileSelectStateNone extends GameTileSelectState{
    public GameTileSelectStateNone(Rectangle oR){
        super(oR);
        outerRect.setFill(GameTile.TILE_BORDER_COLOR);
    }

    public void valueTyped(int i){

    }

    public GameTileSelectState tileLeftClicked(){
        return new GameTileSelectStateGuess(outerRect);
    }

    public GameTileSelectState tileRightClicked(){
        return new GameTileSelectStatePossibility(outerRect);
    }

    public GameTileSelectState unselect(){
        return new GameTileSelectStateNone(outerRect);
    }
}