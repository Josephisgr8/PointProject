package com.example.menus.gamemenu;

import java.util.Timer;
import java.util.TimerTask;

import com.example.MenuController;
import com.example.Interfaces.InterfaceKeyEventHandle;
import com.example.Interfaces.InterfaceMenu;

import javafx.application.Platform;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class GameMenu extends Group implements InterfaceMenu, InterfaceKeyEventHandle{

    final static int LIVES_CONTAINER_RATIO = 5; // 1/this of the screen will be dedicated to the lives contatiner
    final static int GAME_TIMER_HEIGHT_RATIO = 10; // 1/this of the screen will be dedicated to the timer;
    final static int WRONG_GUESS_DISPLAY_TIME = 500; //time the big red X will show on screen after a wrong guess in milliseconds
    final static String LIVES_TEXT = "Lives";
    final static Color WRONG_GUESS_COLOR = Color.RED;

    private int scrX;
    private int scrY;
    private MenuController menuController;
    private GameBoard gameBoard;
    private GameTips gameTips;
    private GameLivesIndicator gameLivesIndicator;
    private GameTimer gameTimer;
    private Group wrongGuessX;
    private Timer wrongGuessTimer;


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
        createTimer();
    }

    public void updateLives(int i){ //This is called when a life is rmeoved
        gameLivesIndicator.updateLives(i);

        createWrongGuessX();
        wrongGuessTimer = new Timer(true); //created as Daemon thread so that it will shutdonw when the application stops
        wrongGuessTimer.schedule(new WrongGuessTimerTask(), WRONG_GUESS_DISPLAY_TIME);

    }

    //Accessors

    public GameTimer getGameTimer(){
        return gameTimer;
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
        gameLivesIndicator = new GameLivesIndicator(menuController, scrX/(2 * LIVES_CONTAINER_RATIO), scrY/LIVES_CONTAINER_RATIO);
        gameLivesIndicator.setSubject(menuController.getGameThemeHandler());
        gameLivesIndicator.setTranslateX(scrX - 1.5 * gameLivesIndicator.getWidth());
        gameLivesIndicator.setTranslateY(scrY - 1.5 * gameLivesIndicator.getHeight());
        this.getChildren().add(gameLivesIndicator);
        
    }

    private void createTimer(){
        gameTimer = new GameTimer(scrX / (GAME_TIMER_HEIGHT_RATIO / 2), scrY / GAME_TIMER_HEIGHT_RATIO);
        gameTimer.setSubject(menuController.getGameThemeHandler());
        gameTimer.setTranslateX(scrX / 2);
        gameTimer.setTranslateY(scrY - 3 * gameTimer.getMaxHeight());
        this.getChildren().add(gameTimer);
        gameTimer.startTimer();
    }

    private void createWrongGuessX(){
        if (wrongGuessX != null && this.getChildren().contains(wrongGuessX)){
            this.getChildren().remove(wrongGuessX);
            wrongGuessTimer.cancel();
        }
        wrongGuessX = new Group();
        Rectangle r1 = new Rectangle();

        r1.setWidth(scrX / 10);
        r1.setHeight(scrY);
        r1.setTranslateX(scrX / 2 - (r1.getWidth()/2));
        r1.setFill(WRONG_GUESS_COLOR);

        Rectangle r2 = new Rectangle();
        r2.setWidth(r1.getWidth());
        r2.setHeight(r1.getHeight());
        r2.setTranslateX(r1.getTranslateX());
        r2.setFill(WRONG_GUESS_COLOR);
        r2.setRotate(90);

        wrongGuessX.getChildren().addAll(r1, r2);
        wrongGuessX.setRotate(45);
        this.getChildren().add(wrongGuessX);
    }

    private void removeWrongGuessIndicator(){
        if (!this.getChildren().contains(wrongGuessX)) {return;}
        this.getChildren().remove(wrongGuessX);
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

    //Helper Class
    class WrongGuessTimerTask extends TimerTask{

        @Override
        public void run() {
            Platform.runLater(() -> {removeWrongGuessIndicator();}); //Need to use platform.runlater because this code needs to be run on the javafx application thread, not the timer thread 
        }
    }
}


