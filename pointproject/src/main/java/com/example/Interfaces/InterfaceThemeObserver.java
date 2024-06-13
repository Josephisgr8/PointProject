package com.example.Interfaces;

import com.example.helpClasses.ColorPackage;

public interface InterfaceThemeObserver {
    public void update(ColorPackage cP);    //function called whenever the subject wants to update all the observers
    public void setSubject(InterfaceThemeSubject sub);  //function used to tell the subject that you are observing them
}
