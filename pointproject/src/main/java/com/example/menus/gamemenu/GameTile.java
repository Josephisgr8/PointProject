package com.example.menus.gamemenu;

import java.util.ArrayList;

import com.example.Interfaces.InterfaceThemeObserver;
import com.example.Interfaces.InterfaceThemeSubject;
import com.example.helpClasses.ColorPackage;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class GameTile extends Group implements InterfaceThemeObserver{

    final static int TILE_THICKNESS = 6;
    final static Color TILE_COLOR = Color.WHITE;
    final static Color TILE_BORDER_COLOR = Color.BLACK;
    final static Color TILE_SELECT_GUESS_BORDER_COLOR = Color.RED;
    final static Color TILE_SELECT_POSSIBLE_BORDER_COLOR = Color.BLUE;
    final static int DEFAULT_TILE_VALUE = 0;

    public ArrayList<Integer> impossibleValues = new ArrayList<Integer>(); //for assigning tile values
    public ArrayList<Integer> possibleValues = new ArrayList<Integer>();    //for checking hidden tile values
    public String shownValue;
    public GameTileValueState tileValueState;
    public GameTileSelectState tileSelectState;


    private int tileSize;
    private Rectangle outerRect;
    private Rectangle innerRect;
    private int realValue;
    private Label label;
    private GameBoard gameBoard;

    public GameTile(GameBoard gB){
        super();
        gameBoard = gB;
        addRectangles();
        addSelectHandling();
        tileSelectState = new GameTileSelectStateNone(outerRect);
    }

    public void setWandH(int l){
        tileSize = l;
        outerRect.setWidth(l);
        outerRect.setHeight(l);
        innerRect.setWidth(l - (2*TILE_THICKNESS));
        innerRect.setHeight(l - (2*TILE_THICKNESS));
    }

    public void intializeLabel(){
        tileValueState = new GameTileValueStateShown(label, DEFAULT_TILE_VALUE, tileSize);
        label = this.tileValueState.updateLabel();//initializeLabel();
        this.getChildren().add(label);
    }

    public void setXLoc(int X){
        this.setTranslateX(X);
        innerRect.setTranslateX(innerRect.getTranslateX()+TILE_THICKNESS);
    }

    public void setYLoc(int Y){
        this.setTranslateY(Y);
        innerRect.setTranslateY(innerRect.getTranslateY()+TILE_THICKNESS);
    }

    public void setValue(int newVal){
        realValue = newVal;
        label = tileValueState.setValue(realValue);
    }

    public int getValue(){
        return realValue;
    }

    public void unSelect(){
        tileSelectState = tileSelectState.unselect(gameBoard.getCurrTheme());        
    }

    public void changeLabelState(){
        tileValueState = tileValueState.nextState();
        label = tileValueState.updateLabel();
    }

    public void valueTyped(int i){
        label = this.tileSelectState.valueTyped(i, tileValueState);
        if (!this.tileValueState.getWasGuessedRight() && this.tileSelectState instanceof GameTileSelectStateGuess){ //you made a wrong guess, and the tile was not in the wrong state
            gameBoard.wrongValueGuessed();
        }
        if (tileValueState.getWasGuessedRight() && tileValueState instanceof GameTileValueStateHidden){
            //System.out.println("tile guessed right");
            tileValueState = tileValueState.nextState();
            gameBoard.correctValueGuessed();
            gameBoard.removePossibleTileValues(this);
        }
        //this.tileValueState.valueTyped(i);
        /*if (this.realValue==i && this.tileValueState instanceof GameTileValueStateHidden){
            this.changeLabelState();
        } */
    }

    public void tileMovedTo(GameTileSelectState gtss){
        if (gtss instanceof GameTileSelectStateGuess){
            this.tileSelectState = new GameTileSelectStateGuess(outerRect, gameBoard.getCurrTheme());
        }
        else{
            this.tileSelectState = new GameTileSelectStatePossibility(outerRect, gameBoard.getCurrTheme());
        }
        gameBoard.tileClicked(this);
    }

    public void updateLabel(){
        label = tileValueState.updateLabel();
    }

    /* public void makeUnclickable(){
        this.setDisabled(true);
    } */
    
    //Private Functions

    private void addSelectHandling(){
        this.setOnMouseClicked(e -> tileClicked(e));
    }

    private void addRectangles(){
        outerRect = new Rectangle();
        innerRect = new Rectangle();
        outerRect.setFill(TILE_BORDER_COLOR);
        innerRect.setFill(TILE_COLOR);
        this.getChildren().add(outerRect);
        this.getChildren().add(innerRect);
    }

    private void tileClicked(MouseEvent e){
        if (e.getButton() == MouseButton.SECONDARY) {
            //System.out.println("tile right clicked");
            tileSelectState = tileSelectState.tileRightClicked(gameBoard.getCurrTheme());
            label = tileSelectState.updateLabel(label, tileValueState);
        }
        else if (e.getButton() == MouseButton.PRIMARY){
            //System.out.println("tile left clicked");
            tileSelectState = tileSelectState.tileLeftClicked(gameBoard.getCurrTheme());
            label = tileSelectState.updateLabel(label, tileValueState);
        }

        gameBoard.tileClicked(this);
    }

    //INTERFACE REQUIREMENTS
    public void update(ColorPackage cP){
        if (this.label != null){
            innerRect.setFill(cP.getPrimaryColor());
            outerRect.setFill(cP.getSecondaryColor());
            label.setTextFill(cP.getSecondaryColor());
        }
    }

    public void setSubject(InterfaceThemeSubject sub){
        sub.register(this);
    }
}
