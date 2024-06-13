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

    public ColorPackage getTheme(){
        return new ColorPackage(primColors.get(currIndex), secColors.get(currIndex), accColors.get(currIndex));
    }


    //PRIVATE FUNCTIONS

    private void addThemesToList(){
        //Light, Dark, Light
        addTheme(Color.WHITE, Color.BLACK, Color.GRAY);
        addTheme(Color.LAVENDER, Color.MEDIUMPURPLE, Color.LAVENDERBLUSH);
        addTheme(Color.LIGHTCORAL, Color.LIGHTPINK, Color.LIGHTSALMON);
        addTheme(Color.LIGHTGOLDENRODYELLOW, Color.LIGHTCYAN, Color.LIGHTSLATEGRAY);
        addTheme(Color.MEDIUMAQUAMARINE, Color.MIDNIGHTBLUE, Color.MEDIUMBLUE);
        //colorPackage.addTheme(Color.WHITE, Color.BLACK);
    }

    private void addTheme(Color p, Color s, Color a){
        primColors.add(p);
        secColors.add(s);
        accColors.add(a);
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
