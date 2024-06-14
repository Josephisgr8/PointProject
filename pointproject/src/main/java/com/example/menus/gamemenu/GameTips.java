package com.example.menus.gamemenu;

import com.example.MenuController;
import com.example.Interfaces.InterfaceThemeObserver;
import com.example.Interfaces.InterfaceThemeSubject;
import com.example.helpClasses.ColorPackage;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameTips extends Group implements InterfaceThemeObserver{
    //private Rectangle bounds;
    private GameTileDummy selectedTile;
    private GameTileDummy possibleTile;
    private GameTileDummy unselectedTile;
    private int tileSize;
    private MenuController menuController;
    
    public GameTips(int tS, MenuController mC){
        tileSize = tS;
        menuController = mC;
        //setupBounds();
        setupTiles();
    }

    //Private functions

    /*private void setupBounds(){
        bounds = new Rectangle();
        bounds.setOpacity(0);
        bounds.setTranslateX(menuController.getScrX() - (tileSize * GameBoard.BOARD_PADDING));
        bounds.setTranslateY();
        bounds.setWidth();
        bounds.setHeight();

    } */

    private void setupTiles(){
        selectedTile = new GameTileDummy();
        possibleTile = new GameTileDummy();
        unselectedTile = new GameTileDummy();

        selectedTile.setTranslateX(menuController.getScrX() - (tileSize * GameBoard.BOARD_PADDING));
        selectedTile.setTranslateY(tileSize * GameBoard.BOARD_PADDING);
        selectedTile.setTileSize(tileSize);

        possibleTile.setTranslateX(menuController.getScrX() - (tileSize * GameBoard.BOARD_PADDING));
        possibleTile.setTranslateY(tileSize * (GameBoard.BOARD_PADDING + 2));
        possibleTile.setTileSize(tileSize);

        unselectedTile.setTranslateX(menuController.getScrX() - (tileSize * GameBoard.BOARD_PADDING));
        unselectedTile.setTranslateY(tileSize * (GameBoard.BOARD_PADDING + 4));
        unselectedTile.setTileSize(tileSize);

        this.getChildren().addAll(selectedTile, possibleTile, unselectedTile);
    }


    //Interface Requirements
    public void update(ColorPackage cP){
        unselectedTile.setInnerFill(cP.getPrimaryColor());
        unselectedTile.setOuterFill(cP.getSecondaryColor());

        selectedTile.setInnerFill(cP.getPrimaryColor());
        selectedTile.setOuterFill(cP.getPrimarySelectColor());

        possibleTile.setInnerFill(cP.getPrimaryColor());
        possibleTile.setOuterFill(cP.getSecondarySelectColor());
    }

    public void setSubject(InterfaceThemeSubject sub){
        sub.register(this);
    }
}

class GameTileDummy extends Group{
    private Rectangle inner;
    private Rectangle outer;

    public GameTileDummy(){
        inner = new Rectangle();
        outer = new Rectangle();
        this.getChildren().addAll(outer, inner);

        inner.setTranslateX(inner.getTranslateX() + GameTile.TILE_THICKNESS);
        inner.setTranslateY(inner.getTranslateY() + GameTile.TILE_THICKNESS);
    }

    public void setInnerFill(Color c){
        inner.setFill(c);
    }

    public void setOuterFill(Color c){
        outer.setFill(c);
    }

    public void setTileSize(int s){
        outer.setWidth(s);
        outer.setHeight(s);
        inner.setWidth(s - (2 * GameTile.TILE_THICKNESS));
        inner.setHeight(s - (2 * GameTile.TILE_THICKNESS));
    }
}