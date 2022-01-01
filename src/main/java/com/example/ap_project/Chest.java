package com.example.ap_project;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Chest extends GameObjects{
    private final ImageView imageView;

    private boolean isOpen;

    public Chest(){
        imageView = new ImageView();
    }

    protected void open(MouseEvent event) throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/resources/com/example/ap_project/images/chestOpen.png");
        Image image = new Image(stream);
        ImageView imageView = (ImageView) event.getSource();
        imageView.setImage(image);
    }

    public ImageView getNode() {
        return imageView;
    }

    public void spawn(){

    }

    public void deSpawn(){

    }
}
