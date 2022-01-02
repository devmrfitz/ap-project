package com.example.ap_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public abstract class Chest {
    private final ImageView imageView;

    private boolean isOpen;

    public Chest(ImageView _node){
//        super(_node);
        imageView = _node;
    }

    protected void open(MouseEvent event) throws FileNotFoundException {
        InputStream stream = new FileInputStream("src/main/resources/com/example/ap_project/images/chestOpen.png");
        Image image = new Image(stream);
        ImageView imageView = (ImageView) event.getSource();
        imageView.setImage(image);
    }

    public void spawn(){

    }

    public void deSpawn(){

    }
}
