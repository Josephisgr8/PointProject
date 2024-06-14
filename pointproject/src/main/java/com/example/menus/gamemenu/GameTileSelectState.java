package com.example.menus.gamemenu;

import com.example.helpClasses.ColorPackage;

import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

public abstract class GameTileSelectState {
    public Rectangle outerRect;

    public GameTileSelectState(Rectangle oR){
        outerRect = oR;
    }

    public abstract Label valueTyped(int i, GameTileValueState gtvs);
    public abstract GameTileSelectState tileLeftClicked(ColorPackage cP);
    public abstract GameTileSelectState tileRightClicked(ColorPackage cP);
    public abstract GameTileSelectState unselect(ColorPackage cP);
    public abstract Label updateLabel(Label l, GameTileValueState gtvs); //just used to re-locate label
}

class GameTileSelectStateGuess extends GameTileSelectState{
    public GameTileSelectStateGuess(Rectangle oR){
        super(oR);
        outerRect.setFill(GameTile.TILE_SELECT_GUESS_BORDER_COLOR);
    }

    public GameTileSelectStateGuess(Rectangle oR, ColorPackage cP){
        super(oR);
        outerRect.setFill(cP.getPrimarySelectColor());
        //System.out.println(cP.getPrimarySelectColor());
    }

    public Label valueTyped(int i, GameTileValueState gtvs){
        gtvs.guessedValue(i);
        return gtvs.updateLabel();
    }

    public GameTileSelectState tileLeftClicked(ColorPackage cP){
        return new GameTileSelectStateNone(outerRect, cP);
    }

    public GameTileSelectState tileRightClicked(ColorPackage cP){
        return new GameTileSelectStatePossibility(outerRect, cP);
    }

    public GameTileSelectState unselect(ColorPackage cP){
        return new GameTileSelectStateNone(outerRect, cP);
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

    public GameTileSelectStatePossibility(Rectangle oR, ColorPackage cP){
        super(oR);
        outerRect.setFill(cP.getSecondarySelectColor());
    }

    public Label valueTyped(int i, GameTileValueState gtvs){
        return gtvs.possibleValue(i);
    }

    public GameTileSelectState tileLeftClicked(ColorPackage cP){
        return  new GameTileSelectStateGuess(outerRect, cP);
    }

    public GameTileSelectState tileRightClicked(ColorPackage cP){
        return new GameTileSelectStateNone(outerRect, cP);
    }

    public GameTileSelectState unselect(ColorPackage cP){
        return new GameTileSelectStateNone(outerRect, cP);
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

    public GameTileSelectStateNone(Rectangle oR, ColorPackage cP){
        super(oR);
        outerRect.setFill(cP.getSecondaryColor());
    }

    public Label valueTyped(int i, GameTileValueState gtvs){
        //should never happen
        System.out.println("THIS SHOULD NEVER PRINT!!!");
        return null;
    }

    public GameTileSelectState tileLeftClicked(ColorPackage cP){
        return new GameTileSelectStateGuess(outerRect, cP);
    }

    public GameTileSelectState tileRightClicked(ColorPackage cP){
        return new GameTileSelectStatePossibility(outerRect, cP);
    }

    public GameTileSelectState unselect(ColorPackage cP){
        return new GameTileSelectStateNone(outerRect, cP);
    }

    public Label updateLabel(Label l, GameTileValueState gtvs){
        return l;
    }
}