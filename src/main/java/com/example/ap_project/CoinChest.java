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

    protected void open() throws FileNotFoundException {
        super.open();
    }

    public void interact(int interaction, Hero hero) throws FileNotFoundException {
        if (interaction>0){ // this means its on the chest
            Coin a = new Coin();
            hero.addCoins(a.getValue());
            open();
        }
    }
}
