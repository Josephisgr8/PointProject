package com.example.helpClasses;

import javafx.scene.paint.Color;

public class ColorPackage{ //only used to send multiple colors in one object
    private Color primaryColor;
    private Color secondaryColor;
    private Color accentingColor;

    public ColorPackage(Color p, Color s, Color a){
        primaryColor = p;
        secondaryColor = s;
        accentingColor = a;
    }

    public Color getPrimaryColor(){
        return primaryColor;
    }

    public Color getSecondaryColor(){
        return secondaryColor;
    }

    public Color getAccentingColor(){
        return accentingColor;
    }
}