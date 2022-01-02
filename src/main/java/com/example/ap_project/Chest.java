package com.example.ap_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public abstract class Chest extends GameObject {
//    private final ImageView imageView;

    private boolean isOpen;

    public Chest(ImageView imageView){
        super(imageView);
        this.isOpen = false;
    }


//    protected void open() throws FileNotFoundException {
//        InputStream stream = new FileInputStream("file:src/main/resources/com/example/ap_project/images/chestOpen.png");
//        Image image = new Image(stream);
//        imageView.setImage(image);
//    }

    public void setOpen() {
        this.isOpen = true;
    }

    public boolean isOpen() {
        return isOpen;
    }
}
