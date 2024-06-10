package com.example;

import com.example.helpClasses.ColorPackage;

import java.util.ArrayList;

import javafx.scene.paint.Color;


public class GameThemeHandler {

    private ArrayList<Color> primColors = new ArrayList<Color>();
    private ArrayList<Color> secColors = new ArrayList<Color>();
    private int currIndex;

    public GameThemeHandler(){
        currIndex = 0;
        addThemesToList();
    }

    public ColorPackage nextBackgroundColor() {
        currIndex += 1;
        if (currIndex >= primColors.size()){
            currIndex = 0;
        }
        return getTheme();
    }

    public ColorPackage getTheme(){
        return new ColorPackage(primColors.get(currIndex), secColors.get(currIndex));
    }


    //PRIVATE FUNCTIONS

    private void addThemesToList(){
        addTheme(Color.WHITE, Color.BLACK);
        addTheme(Color.LAVENDERBLUSH, Color.LAVENDER);
        addTheme(Color.LIGHTCORAL, Color.LIGHTPINK);
        addTheme(Color.LIGHTGOLDENRODYELLOW, Color.LIGHTCYAN);
        addTheme(Color.MEDIUMAQUAMARINE, Color.MIDNIGHTBLUE);
        //colorPackage.addTheme(Color.WHITE, Color.BLACK);
    }

    private void addTheme(Color p, Color s){
        primColors.add(p);
        secColors.add(s);
    }

}

/*class ColorPackage{ //only used to send multiple colors in one object
    private Color primaryColor;
    private Color secondaryColor;

    public ColorPackage(Color p, Color s){
        primaryColor = p;
        secondaryColor = s;
    }

    public Color getPrimaryColor(){
        return primaryColor;
    }

    public Color getSecondaryColor(){
        return secondaryColor;
    }

} */
