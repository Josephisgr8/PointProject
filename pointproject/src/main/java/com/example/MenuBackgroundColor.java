package com.example;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class MenuBackgroundColor {


    private ArrayList<Color> backgroundColorList = new ArrayList<Color>();
    private Color currColor;


    public MenuBackgroundColor(){

        addColorsToList();
        currColor = backgroundColorList.get(0);

    }

    private void addColorsToList(){
        backgroundColorList.add(Color.WHITE);
        backgroundColorList.add(Color.ALICEBLUE);
        backgroundColorList.add(Color.BROWN);
        backgroundColorList.add(Color.BLACK);
        backgroundColorList.add(Color.CORNSILK);
        backgroundColorList.add(Color.DARKGRAY);
    }


    public Color nextBackgroundColor() {
        int currColInd = backgroundColorList.indexOf(currColor);
        if (currColInd + 1 >= backgroundColorList.size()){ //Next index would be out of bounds
            currColor = backgroundColorList.get(0);
        }
        else {
            currColor = backgroundColorList.get(currColInd+1);
        }
        return currColor;
    }

}
