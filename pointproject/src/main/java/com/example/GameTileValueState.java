package com.example;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public abstract class GameTileValueState {
    public abstract Label updateLabel();
    public abstract Label initializeLabel();
    public abstract GameTileValueState nextState();
    public abstract Label setValue(int v);
}

class GameTileValueStateHidden extends GameTileValueState{
    private Label label;
    private int value;
    private int size;

    public GameTileValueStateHidden(Label l, int val, int s){
        label = l;
        value = val;
        size = s;
        updateLabel();
    }

    public Label updateLabel(){
        if (label == null){
            label = initializeLabel();
        }
        label.setText(" ");
        return label;
    }
    
    public Label initializeLabel(){
        double fontSize = size/1.33333; //conversion from pixels to font size
        label = new Label(" ");
        label.setFont(new Font("Arial", fontSize));
        label.setTranslateX(label.getTranslateX() + (size/4));
        return label;
    }

    public GameTileValueState nextState(){
        return new GameTileValueStateShown(label, value, size);
    }

    public Label setValue(int v) {
        value = v;
        return updateLabel();
    }
}

class GameTileValueStateShown extends GameTileValueState{
    private Label label;
    private int value;
    private int size;

    public GameTileValueStateShown(Label l, int val, int s){
        label = l;
        value = val;
        size = s;
        updateLabel();
    }

    public Label updateLabel(){
        if (label == null){
            label = initializeLabel();
        }
        label.setText(Integer.toString(value));
        return label;
    }

    public Label initializeLabel(){
        double fontSize = size/1.33333; //conversion from pixels to font size
        label = new Label(Integer.toString(GameTile.DEFAULT_TILE_VALUE));
        label.setFont(new Font("Arial", fontSize));
        label.setTranslateX(label.getTranslateX() + (size/4));
        return label;
    }

    public GameTileValueState nextState(){
        return new GameTileValueStateHidden(label, value, size);
    }

    public Label setValue(int v) {
        value = v;
        return updateLabel();
    }
}