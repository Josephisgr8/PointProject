package com.example.menus.gamemenu;

import java.util.Timer;
import java.util.TimerTask;

import com.example.Interfaces.InterfaceThemeObserver;
import com.example.Interfaces.InterfaceThemeSubject;
import com.example.helpClasses.ColorPackage;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class GameTimer extends Label implements InterfaceThemeObserver{
    
    private Timer tickTimer;
    private int mil, sec, min, hr;
    private int width, height;

    public GameTimer(int w, int h){
        super();
        tickTimer = new Timer(true);
        mil = 0;
        sec = 0;
        min = 0;
        hr = 0;
        width = w;
        height = h;

        setupLabel();
    }

    public void startTimer(){
        tickTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                incrementMil();
            }
        }, 0, 1);
    }

    public void stopTimer(){
        tickTimer.cancel();
    }


    //Private functions

    private void setupLabel(){
        this.setFont(new Font(height / (1.33333 * 1.5))); 
        this.setMaxHeight(height);
        this.setMaxWidth(width);

    }

    private String formattedTimer(){
        String str = "";
        str += String.format("%01d", hr);
        str += ": ";
        str += String.format("%02d", min);
        str += ": ";
        str += String.format("%02d", sec);
        str += ": ";
        str += String.format("%03d", mil);
        return str;
    }

    private void incrementMil(){
        mil += 1;
        if (mil == 1000){
            mil = 0;
            sec += 1;
        }
        if (sec == 60){
            sec = 0;
            min += 1;
        }
        if (min == 60){
            min = 0;
            hr += 1;
        }

        updateLabel();
    }

    private void updateLabel(){
        Platform.runLater(() -> {this.setText(formattedTimer());}); //must do it this way for same reason as in GameMenu
    }


    //Interface Requirements

    public void update(ColorPackage c){
        this.setTextFill(c.getSecondaryColor());
    }

    public void setSubject(InterfaceThemeSubject sub){
        sub.register(this);
    }
}
