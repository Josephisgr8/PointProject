package com.example;

import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

public abstract class GameTileSelectState {
    public static GameTileSelectState currState;
    public Rectangle outerRect;

    public GameTileSelectState(Rectangle oR){
        outerRect = oR;
    }

    public abstract Label valueTyped(int i, GameTileValueState gtvs);
    public abstract GameTileSelectState tileLeftClicked();
    public abstract GameTileSelectState tileRightClicked();
    public abstract GameTileSelectState unselect();
    public abstract Label updateLabel(Label l, GameTileValueState gtvs); //just used to re-locate label
}

class GameTileSelectStateGuess extends GameTileSelectState{
    public GameTileSelectStateGuess(Rectangle oR){
        super(oR);
        outerRect.setFill(GameTile.TILE_SELECT_GUESS_BORDER_COLOR);
    }

    public Label valueTyped(int i, GameTileValueState gtvs){
        gtvs.guessedValue(i);
        return gtvs.updateLabel();
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

    public Label updateLabel(Label l, GameTileValueState gtvs){
        gtvs.readyForGuess();
        return gtvs.updateLabel();
    }
}

class GameTileSelectStatePossibility extends GameTileSelectState{
    public GameTileSelectStatePossibility(Rectangle oR){
        super(oR);
        outerRect.setFill(GameTile.TILE_SELECT_POSSIBLE_BORDER_COLOR);
    }

    public Label valueTyped(int i, GameTileValueState gtvs){
        return gtvs.possibleValue(i);
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

    public Label updateLabel(Label l, GameTileValueState gtvs){
        gtvs.readyForPossible();
        return gtvs.updateLabel();
    }
}

class GameTileSelectStateNone extends GameTileSelectState{
    public GameTileSelectStateNone(Rectangle oR){
        super(oR);
        outerRect.setFill(GameTile.TILE_BORDER_COLOR);
    }

    public Label valueTyped(int i, GameTileValueState gtvs){
        //should never happen
        System.out.println("THIS SHOULD NEVER PRINT!!!");
        return null;
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

    public Label updateLabel(Label l, GameTileValueState gtvs){
        return l;
    }
}