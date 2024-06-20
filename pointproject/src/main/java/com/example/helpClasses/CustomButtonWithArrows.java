package com.example.helpClasses;

import com.example.Interfaces.InterfaceThemeObserver;
import com.example.Interfaces.InterfaceThemeSubject;
import com.example.helpClasses.CustomButton.ButtonFunction;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class CustomButtonWithArrows extends ListElement implements InterfaceThemeObserver{

    final static int ARROW_WIDTH_RATIO = 5; // 1/this amount of the total width will be dedicated to each arrow button
    final static int NAME_WIDTH_RATIO = 2; // 1/this amount of the total width will be dedicated to the name

    private Rectangle leftOuter, leftInner, centerOuter, centerInner, rightOuter, rightInner;
    private Label leftLabel, centerLabel, rightLabel;
    public CustomButtonWithArrows(String cenTxt, ButtonFunction fncL, ButtonFunction fncR){

        leftOuter = new Rectangle();
        leftInner = new Rectangle();
        centerOuter = new Rectangle();
        centerInner = new Rectangle();
        rightOuter = new Rectangle();
        rightInner = new Rectangle();

        leftLabel = new Label("<-");
        centerLabel = new Label(cenTxt);
        rightLabel = new Label("->");

        this.getChildren().addAll(leftOuter, leftInner, leftLabel, centerOuter, centerInner, centerLabel, rightOuter, rightInner, rightLabel);

        leftOuter.setOnMouseClicked((e) -> {
            fncL.assignFunction();
        });
        leftInner.setOnMouseClicked((e) -> {
            fncL.assignFunction();
        });
        leftLabel.setOnMouseClicked((e) -> {
            fncL.assignFunction();
        });

        rightOuter.setOnMouseClicked((e) -> {
            fncR.assignFunction();
        });
        rightInner.setOnMouseClicked((e) -> {
            fncR.assignFunction();
        });
        rightLabel.setOnMouseClicked((e) -> {
            fncR.assignFunction();
        });
    }

    public void setPrefSize(int x, int y){
        double actualBorderX = x * CustomButton.BUTTON_BORDER_PERCENT/100;
        double actualBorderY = y * CustomButton.BUTTON_BORDER_PERCENT/100;
        double spacing = x - ((x/ARROW_WIDTH_RATIO * 2) + (x/NAME_WIDTH_RATIO));
        //set width and height
        leftOuter.setWidth(x/ARROW_WIDTH_RATIO);
        leftInner.setWidth(x/ARROW_WIDTH_RATIO - (2 * actualBorderX));
        leftOuter.setHeight(y);
        leftInner.setHeight(y - (2 * actualBorderY));
        leftLabel.setMaxWidth(x/ARROW_WIDTH_RATIO);
        leftLabel.setFont(new Font(y / 4));

        centerOuter.setWidth(x/NAME_WIDTH_RATIO);
        centerInner.setWidth(x/NAME_WIDTH_RATIO - (2 * actualBorderX));
        centerOuter.setHeight(y);
        centerInner.setHeight(y - (2 * actualBorderY));
        centerLabel.setMaxWidth(x/NAME_WIDTH_RATIO);
        centerLabel.setFont(new Font(y / 4));

        rightOuter.setWidth(x/ARROW_WIDTH_RATIO);
        rightInner.setWidth(x/ARROW_WIDTH_RATIO - (2 * actualBorderX));
        rightOuter.setHeight(y);
        rightInner.setHeight(y - (2 * actualBorderY));
        rightLabel.setMaxWidth(x/ARROW_WIDTH_RATIO);
        rightLabel.setFont(new Font(y / 4));

        //set positioning
        leftInner.setTranslateX(leftOuter.getTranslateX() + actualBorderX);
        leftInner.setTranslateY(leftOuter.getTranslateY() + actualBorderY);
        leftLabel.setTranslateX((leftOuter.getTranslateX() + (x / ARROW_WIDTH_RATIO)/2) - ((leftLabel.getText().length()/2) * (leftLabel.getFont().getSize() / 2))); // trial and error for this math
        leftLabel.setTranslateY(leftInner.getTranslateY() + leftLabel.getFont().getSize());
        leftLabel.setTextFill(Color.BLACK);

        centerOuter.setTranslateX(leftOuter.getTranslateX() + leftOuter.getWidth() + spacing/2);
        centerInner.setTranslateX(centerOuter.getTranslateX() + actualBorderX);
        centerInner.setTranslateY(centerOuter.getTranslateY() + actualBorderY);
        centerLabel.setTranslateX((centerLabel.getTranslateX() + x/2) - ((centerLabel.getText().length()/2) * (centerLabel.getFont().getSize() / 2))); // trial and error for this math
        centerLabel.setTranslateY((centerLabel.getTranslateY() + y/2) - centerLabel.getFont().getSize() / 1.333);
        centerLabel.setTextFill(Color.BLACK);

        rightOuter.setTranslateX(centerOuter.getTranslateX() + centerOuter.getWidth() + spacing/2);
        rightInner.setTranslateX(rightOuter.getTranslateX() + actualBorderX);
        rightInner.setTranslateY(rightOuter.getTranslateY() + actualBorderY);
        rightLabel.setTranslateX((rightOuter.getTranslateX() + (x / ARROW_WIDTH_RATIO)/2) - ((rightLabel.getText().length()/2) * (rightLabel.getFont().getSize() / 2))); // trial and error for this math
        rightLabel.setTranslateY(rightInner.getTranslateY() + rightLabel.getFont().getSize());
        rightLabel.setTextFill(Color.BLACK);
    }

    //Interface Requirements
    public void update(ColorPackage cP){
        leftInner.setFill(cP.getPrimaryColor());
        leftOuter.setFill(cP.getSecondaryColor());
        centerInner.setFill(cP.getPrimaryColor());
        centerOuter.setFill(cP.getSecondaryColor());
        rightInner.setFill(cP.getPrimaryColor());
        rightOuter.setFill(cP.getSecondaryColor());
    }

    public void setSubject(InterfaceThemeSubject sub){
        sub.register(this);
    }
}
