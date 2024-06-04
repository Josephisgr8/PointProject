package com.example;

import java.util.ArrayList;
import java.util.Comparator;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public abstract class GameTileValueState {
    public abstract Label updateLabel();
    public abstract void initializeLabel();
    public abstract GameTileValueState nextState();
    public abstract Label setValue(int v);
    public abstract void guessedValue(int i);
    public abstract Label possibleValue(int i);
    public abstract void readyForPossible();// both this and the method below are used to re-locate the label in the tile
    public abstract void readyForGuess();
}

class GameTileValueStateHidden extends GameTileValueState{ //hidden means the real value is not shown
    private Label label;
    private int value;
    private int size;
    private ArrayList<Integer> possibleValues = new ArrayList<Integer>();

    public GameTileValueStateHidden(Label l, int val, int s){
        label = l;
        value = val;
        size = s;
        initializeLabel();
    }

    public Label updateLabel(){
        /*if (label == null){
            label = initializeLabel();
        }
        label.setText(" "); */
        return label;
    }
    
    public void initializeLabel(){
        double fontSize = size/1.33333; //conversion from pixels to font size
        label.setText(" ");
        label.setFont(new Font("Arial", fontSize));
        label.setTranslateX(label.getTranslateX() + (size/4));
    }

    public GameTileValueState nextState(){
        return new GameTileValueStateShown(label, value, size);
    }

    public Label setValue(int v) {
        value = v;
        return updateLabel();
    }

    public void guessedValue(int i){
        if (i == value){
            double fontSize = size / (1.33333); //conversion from pixels to font size
            label.setFont(new Font("Arial", fontSize));
            label.setText(Integer.toString(value));
        }
    }


    public Label possibleValue(int i){
        if (!possibleValues.contains(i)){
            possibleValues.add(i);
        }
        else{
            possibleValues.remove(possibleValues.indexOf(i));
        }

        possibleValues.sort(Comparator.naturalOrder());

        double fontSize = size / (1.33333 * 3); //conversion from pixels to font size, and then 1/3 of that
        label.setFont(new Font("Arial", fontSize));
        label.setWrapText(true);
        label.setMaxWidth(size);
        label.setText("");

        possibleValues.forEach( (n) -> {
            label.setText(label.getText() + Integer.toString(n) + "  ");
        });

        return label;
    }

    public void readyForPossible(){
        label.setTranslateX(size/10);
    }

    public void readyForGuess(){
        label.setTranslateX(size/4);
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
        initializeLabel();
    }

    public Label updateLabel(){
        /*if (label == null){
            label = initializeLabel();
        }
        label.setText(Integer.toString(value)); */
        return label;
    }

    public void initializeLabel(){
        if (label == null){
            label = new Label(Integer.toString(value));
        }
        double fontSize = size/1.33333; //conversion from pixels to font size
        label.setText(Integer.toString(value));
        label.setFont(new Font("Arial", fontSize));
        label.setTranslateX((size/4));
    }

    public GameTileValueState nextState(){
        return new GameTileValueStateHidden(label, value, size);
    }

    public Label setValue(int v) {
        value = v;
        label.setText(Integer.toString(value));
        return updateLabel();
    }

    public void guessedValue(int i){
        
    }

    public Label possibleValue(int i){
        return null;
    }

    public void readyForPossible(){
        
    }

    public void readyForGuess(){
        
    }
}