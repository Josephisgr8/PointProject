package com.example.Interfaces;

import java.util.ArrayList;

public interface InterfaceThemeSubject {
    public void register(InterfaceThemeObserver obs);   // function used to add observers
    public void notifyObservers();  //function used to tell observers to do something
    
}
