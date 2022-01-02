package com.example.ap_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StandardRedOrc extends Orc {
    private static final Image image = new Image("file:src/main/resources/com/example/ap_project/images/red_orc.png");
    private static final double height = 50, width = 50;

    private StandardRedOrc(ImageView imageView){
        super(imageView);
    }

    public static StandardRedOrc getInstance(double x, double y){
        ImageView img = new ImageView(image);
        img.setFitHeight(height);
        img.setFitWidth(width);
        img.setX(x+width/2);
        img.setY(y-height);
        return new StandardRedOrc(img);
    }

}
