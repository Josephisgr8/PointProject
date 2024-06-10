package com.example.helpClasses;

import javafx.scene.paint.Color;

public class ColorPackage{ //only used to send multiple colors in one object
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
}