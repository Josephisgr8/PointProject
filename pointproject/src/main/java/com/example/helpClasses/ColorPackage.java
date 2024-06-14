package com.example.helpClasses;

import javafx.scene.paint.Color;

public class ColorPackage{ //only used to send multiple colors in one object
    private Color primaryColor;
    private Color secondaryColor;
    private Color accentingColor;
    private Color primSelectColor;
    private Color secSelectColor;

    public ColorPackage(Color p, Color s, Color a){
        primaryColor = p;
        secondaryColor = s;
        accentingColor = a;
    }

    public ColorPackage(Color p, Color s, Color a, Color ps, Color ss){
        primaryColor = p;
        secondaryColor = s;
        accentingColor = a;
        primSelectColor = ps;
        secSelectColor = ss;
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

    public Color getPrimarySelectColor(){
        return primSelectColor;
    }

    public Color getSecondarySelectColor(){
        return secSelectColor;
    }
}