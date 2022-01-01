package com.example.ap_project;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileNotFoundException;

public class CoinChest extends Chest{
    private final int coinCount;

    public CoinChest(int coins, ImageView _node){
        super(_node);
        coinCount = coins;
    }

    public void spawn(){

    }

    protected void open(MouseEvent event) throws FileNotFoundException {
        super.open(event);
    }

    public void interact(){

    }
}
