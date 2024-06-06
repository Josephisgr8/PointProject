package com.example.menus.gamemenu;

import com.example.MenuController;
import com.example.Interfaces.InterfaceKeyEventHandle;
import com.example.Interfaces.InterfaceMenu;

import javafx.scene.*;
import javafx.scene.input.KeyCode;

public class GameMenu extends Group implements InterfaceMenu, InterfaceKeyEventHandle{

    private int scrX;
    private int scrY;
    private MenuController menuController;
    private GameBoard gameBoard;
    //private ArrayList<ArrayList> gameTileRowList = new ArrayList<ArrayList>();


    public GameMenu(int X, int Y, MenuController mC, GameBoard gB){
        super();

        scrX = X;
        scrY = Y;
        menuController = mC;
        gameBoard = gB;

        createBoard();
        showBoard();  
    }
    
    private void createBoard(){

        //Set tile size based off of minimum screen size
        if (scrX > scrY){;
            gameBoard.setTileSize(scrY);
        }
        else{
            gameBoard.setTileSize(scrX);            
        }

        gameBoard.setupGameBoard();
    }

    private void showBoard(){
        gameBoard.showBoard();
        this.getChildren().add(gameBoard);
    }

    //Interface Requirements

    public void keyStrokeRecieved(KeyCode kC){
        gameBoard.keyStrokeRecieved(kC);

        switch (kC) {
            case ESCAPE:
                menuController.goToPrevMenu();
                break;
            default:
                break;
        }
    }
}


