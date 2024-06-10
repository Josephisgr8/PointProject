package com.example.helpClasses;

import com.example.MenuController;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class CustomButton extends Group {

    final int BUTTON_BORDER_PERCENT = 4; // EVEN SO NOT OFF-CENTER

    private Rectangle btnInner;
    private Rectangle btnOuter;
    private Label label;

    public CustomButton(String txt, ButtonFunction func){
        btnOuter = new Rectangle();
        btnInner = new Rectangle();
        label = new Label(txt);

        this.getChildren().addAll(btnOuter, btnInner, label);

        //updateTheme();

        this.setOnMouseClicked( (e) -> {
            func.assignFunction();
        });
    }   

    public void updateTheme(MenuController menuController){
        btnInner.setFill(menuController.getBackgroundColor().getPrimaryColor());
        btnOuter.setFill(menuController.getBackgroundColor().getSecondaryColor());
    }

    public void setPrefSize(int x, int y){
        int actualBorderX = x * BUTTON_BORDER_PERCENT/100;
        int actualBorderY = y * BUTTON_BORDER_PERCENT/100;
        //set width and height
        btnOuter.setWidth(x);
        btnInner.setWidth(x - (2 * actualBorderX));
        btnOuter.setHeight(y);
        btnInner.setHeight(y - (2 * actualBorderY));
        label.setMaxWidth(x);
        label.setFont(new Font(y / 2));

        //set positioning
        btnInner.setTranslateX(btnOuter.getTranslateX() + actualBorderX);
        btnInner.setTranslateY(btnOuter.getTranslateY() + actualBorderY);
        label.setTranslateX((label.getTranslateX() + x/2) - ((label.getText().length()/2) * (label.getFont().getSize() / 2))); // trial and error for this math
        label.setTranslateY((label.getTranslateY() + y/2) - label.getFont().getSize() / 1.333);

        label.setTextFill(Color.BLACK);
        //this.prefWidth(x);
        //this.prefHeight(y);
    }








    //Interface to assign buttons functions
    public interface ButtonFunction {
        public void assignFunction();
    }
}
