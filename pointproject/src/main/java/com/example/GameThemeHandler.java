package com.example;

import com.example.Interfaces.InterfaceThemeObserver;
import com.example.Interfaces.InterfaceThemeSubject;
import com.example.helpClasses.ColorPackage;

import java.util.ArrayList;

import javafx.scene.paint.Color;


public class GameThemeHandler implements InterfaceThemeSubject {

    private ArrayList<Color> primColors = new ArrayList<Color>();
    private ArrayList<Color> secColors = new ArrayList<Color>();
    private ArrayList<Color> accColors = new ArrayList<Color>();
    private ArrayList<Color> primSelColors = new ArrayList<Color>();
    private ArrayList<Color> secSelColors = new ArrayList<Color>();
    private int currIndex;
    private ArrayList<InterfaceThemeObserver> observerList = new ArrayList<InterfaceThemeObserver>();
    public GameThemeHandler(){
        currIndex = 0;
        addThemesToList();
    }

    public ColorPackage nextBackgroundColor() {
        currIndex += 1;
        if (currIndex >= primColors.size()){
            currIndex = 0;
        }
        notifyObservers();
        return getTheme();
    }

    public ColorPackage prevBackgroundColor() {
        currIndex -= 1;
        if (currIndex < 0){
            currIndex = primColors.size()-1;
        }
        notifyObservers();
        return getTheme();
    }

    public ColorPackage getTheme(){
        return new ColorPackage(primColors.get(currIndex), secColors.get(currIndex), accColors.get(currIndex), primSelColors.get(currIndex), secSelColors.get(currIndex));
    }


    //PRIVATE FUNCTIONS

    private void addThemesToList(){
        //Light, Dark, Light, Dark, Dark
        addTheme(Color.WHITE, Color.BLACK, Color.GRAY, Color.BURLYWOOD, Color.TOMATO);
        addTheme(Color.LAVENDER, Color.MEDIUMPURPLE, Color.LAVENDERBLUSH, Color.NAVY, Color.SEAGREEN);
        addTheme(Color.LIGHTPINK, Color.MEDIUMORCHID, Color.LIGHTSKYBLUE, Color.LAWNGREEN, Color.MAROON);
        addTheme(Color.LIGHTGOLDENRODYELLOW, Color.LIGHTSLATEGRAY, Color.LIGHTCYAN, Color.INDIGO, Color.FIREBRICK);
        addTheme(Color.MEDIUMAQUAMARINE, Color.MIDNIGHTBLUE, Color.PALETURQUOISE, Color.OLIVEDRAB, Color.THISTLE);
        //colorPackage.addTheme(Color.WHITE, Color.BLACK);

        //CANNOT USE RED HERE^ IT IS USED FOR WRONG GUESSES
    }

    private void addTheme(Color p, Color s, Color a, Color ps, Color ss){
        primColors.add(p);
        secColors.add(s);
        accColors.add(a);
        primSelColors.add(ps);
        secSelColors.add(ss);
    }

    //INTERFACE REQUIREMENTS
    public void register(InterfaceThemeObserver obs){
        if (!observerList.contains(obs)) {
            observerList.add(obs);
            obs.update(getTheme()); //if observer is added, update it
        }
    }

    public void notifyObservers(){
        observerList.forEach( (e) -> {
            e.update(getTheme());
        });
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
