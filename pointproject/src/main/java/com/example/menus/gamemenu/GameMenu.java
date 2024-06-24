package com.example.menus.gamemenu;

import com.example.MenuController;
import com.example.Interfaces.InterfaceKeyEventHandle;
import com.example.Interfaces.InterfaceMenu;

import javafx.scene.*;
import javafx.scene.input.KeyCode;

public class GameMenu extends Group implements InterfaceMenu, InterfaceKeyEventHandle{

    final static int LIVES_CONTAINER_RATIO = 10; // 1/this of the screen will be dedicated to the lives contatiner

    private int scrX;
    private int scrY;
    private MenuController menuController;
    private GameBoard gameBoard;
    private GameTips gameTips;
    private GameLivesIndicator gameLivesIndicator;
    //private ArrayList<ArrayList> gameTileRowList = new ArrayList<ArrayList>();


    public GameMenu(int X, int Y, MenuController mC, GameBoard gB){
        super();

        scrX = X;
        scrY = Y;
        menuController = mC;
        menuController.resetLives();
        gameBoard = gB;

        createBoard();
        showBoard();  
        createTips();
        createLives();
    }

    public void updateLives(int i){
        gameLivesIndicator.updateLives(i);
    }

    //private functions
    
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

    private void createTips(){
        gameTips = new GameTips(gameBoard.tileSize, menuController);
        gameTips.setSubject(menuController.getGameThemeHandler());
        this.getChildren().add(gameTips);
        //gameTips.setBorderSize()
    }

    private void createLives(){
        gameLivesIndicator = new GameLivesIndicator(menuController, scrX/LIVES_CONTAINER_RATIO, scrY/LIVES_CONTAINER_RATIO);
        gameLivesIndicator.setSubject(menuController.getGameThemeHandler());
        gameLivesIndicator.setTranslateX(scrX - (scrX / LIVES_CONTAINER_RATIO));
        gameLivesIndicator.setTranslateY(scrY - (scrY / LIVES_CONTAINER_RATIO));
        this.getChildren().add(gameLivesIndicator);
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


