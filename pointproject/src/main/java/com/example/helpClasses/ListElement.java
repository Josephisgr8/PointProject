package com.example.helpClasses;

import javafx.scene.Group;

public abstract class ListElement extends Group{
    public ListElement(){
        super();
    }

    public abstract void setPrefSize(int x, int y);
}
