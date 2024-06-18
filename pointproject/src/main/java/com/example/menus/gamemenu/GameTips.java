package com.example.menus.gamemenu;

import com.example.MenuController;
import com.example.Interfaces.InterfaceThemeObserver;
import com.example.Interfaces.InterfaceThemeSubject;
import com.example.helpClasses.ColorPackage;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class GameTips extends Group implements InterfaceThemeObserver{

    final static double TIP_ELEMENT_PADDING = 1.5;
    final static double TIP_LABEL_TEXT_SIZE_MODIFIER = 3; //bigger means a smaller label

    final static int TIP_LABEL_WIDTH = 5; //measured in terms of tile widths

    final static String SELECTED_TILE_EXPLANATION = "Left-click a tile when you are ready to guess a value and it will look like this --->";
    final static String POSSIBLE_TILE_EXPLANATION = "Right-click a tile when you want to type a possible value and it will look like this --->";
    final static String UNSELECTED_TILE_EXPLANATION = "This is what a tile will look like when it is unselected. If all tiles look like this, typing will do nothing --->";
    final static String MOVEMENT_LABEL_EXPLANATION = "You can use the W, A, S, and D keys to change what tile is selected.";
    
    //private Rectangle bounds;
    private GameTileDummy selectedTile, possibleTile, unselectedTile;
    private Label selectedLabel, possibleLabel, unselectedLabel, movementLabel;
    private int tileSize;
    private MenuController menuController;
    
    public GameTips(int tS, MenuController mC){
        tileSize = tS;
        menuController = mC;
        //setupBounds();
        setupTiles();
    }

    //Private functions

    private void setupTiles(){
        selectedTile = new GameTileDummy();

        possibleTile = new GameTileDummy();

        unselectedTile = new GameTileDummy();

        selectedTile.setTranslateX(menuController.getScrX() - (tileSize * TIP_ELEMENT_PADDING));
        selectedTile.setTranslateY(tileSize * TIP_ELEMENT_PADDING);
        selectedTile.setTileSize(tileSize);

        possibleTile.setTranslateX(menuController.getScrX() - (tileSize * TIP_ELEMENT_PADDING));
        possibleTile.setTranslateY(tileSize * (TIP_ELEMENT_PADDING + 2));
        possibleTile.setTileSize(tileSize);

        unselectedTile.setTranslateX(menuController.getScrX() - (tileSize * TIP_ELEMENT_PADDING));
        unselectedTile.setTranslateY(tileSize * (TIP_ELEMENT_PADDING + 4));
        unselectedTile.setTileSize(tileSize);

        double fontSize = tileSize / (1.33333) / TIP_LABEL_TEXT_SIZE_MODIFIER; //conversion from pixels to font size, then divide by the modifier
        selectedLabel = createLabel(fontSize, selectedTile, SELECTED_TILE_EXPLANATION);
        possibleLabel = createLabel(fontSize, possibleTile, POSSIBLE_TILE_EXPLANATION);
        unselectedLabel = createLabel(fontSize, unselectedTile, UNSELECTED_TILE_EXPLANATION);

        movementLabel = new Label(MOVEMENT_LABEL_EXPLANATION);
        movementLabel.setFont(new Font("Comic Sans MS", fontSize));
        movementLabel.setWrapText(true);
        movementLabel.setMaxWidth((fontSize * 1.33) + (tileSize * (TIP_LABEL_WIDTH + 2)));//subtract a little here to give this label more width since it doesn't come with an associated tile
        movementLabel.setTranslateX(possibleLabel.getTranslateX());
        movementLabel.setTranslateY(tileSize * (TIP_ELEMENT_PADDING + 6));


        this.getChildren().addAll(selectedTile, selectedLabel, possibleTile, possibleLabel, unselectedTile, unselectedLabel, movementLabel);
    }


    //private functions

    private Label createLabel(double fS, GameTileDummy tile, String txt){
        Label lab = new Label(txt);
        lab.setFont(new Font("Comic Sans MS", fS));
        lab.setWrapText(true);
        lab.setMaxWidth((fS * 1.333) + (tileSize * TIP_LABEL_WIDTH));
        lab.setTranslateX(tile.getTranslateX() - (lab.getMaxWidth() + tileSize)); //added extra tilesize length for better spacing
        lab.setTranslateY(tile.getTranslateY());
        return lab;
    }

    //Interface Requirements
    public void update(ColorPackage cP){
        unselectedTile.setInnerFill(cP.getPrimaryColor());
        unselectedTile.setOuterFill(cP.getSecondaryColor());
        unselectedLabel.setTextFill(cP.getSecondaryColor());

        selectedTile.setInnerFill(cP.getPrimaryColor());
        selectedTile.setOuterFill(cP.getPrimarySelectColor());
        selectedLabel.setTextFill(cP.getSecondaryColor());

        possibleTile.setInnerFill(cP.getPrimaryColor());
        possibleTile.setOuterFill(cP.getSecondarySelectColor());
        possibleLabel.setTextFill(cP.getSecondaryColor());

        movementLabel.setTextFill(cP.getSecondaryColor());
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