package com.example.ap_project;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Boss extends Orc {
    private static final transient Image image = new Image("file:src/main/resources/com/example/ap_project/images/red_orc.png");
    private static final double height = 100, width = 100;

    private Boss(ImageView imageView){
        super(imageView);
        setHitPoints(100);
    }

    public static Boss getInstance(double x, double y){
        ImageView img = new ImageView(image);
        img.setFitHeight(height);
        img.setFitWidth(width);
        img.setLayoutX(x+width/2);
        img.setLayoutY(y-height);
        return new Boss(img);
    }

    @Override
    public void rehydrate(Node _mainAnchorPane) {
        AnchorPane mainAnchorPane = (AnchorPane) _mainAnchorPane;
        ImageView img = new ImageView(image);
        img.setFitHeight(height);
        img.setFitWidth(width);
        img.setLayoutX(getPosition().getKey());
        img.setLayoutY(getPosition().getValue());
        setNode(img);
        mainAnchorPane.getChildren().add(img);
        superRehydrate();

    }

}
