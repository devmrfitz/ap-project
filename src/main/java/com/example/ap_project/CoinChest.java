package com.example.ap_project;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileNotFoundException;
import java.util.Random;

public class CoinChest extends Chest{
    private final int coinCount;
    private static final Image closedImage = new
            Image("file:src/main/resources/com/example/ap_project/images/chestClosed.png");
    private static final Image openedImage = new
            Image("file:src/main/resources/com/example/ap_project/images/chestOpen.png");
    private static final double height = 50, width = 65;
    private static final Random rand = new Random();


    private CoinChest(int coins, ImageView _node){
        super(_node);
        coinCount = coins;
    }

    public static CoinChest getInstance(double x, double y){
        ImageView img = new ImageView(closedImage);
        img.setFitHeight(height);
        img.setFitWidth(width);
        img.setX(x+width/2);
        img.setY(y-height);
        return new CoinChest(rand.nextInt(10)+1, img);
    }

    public void spawn(){
 
    }

    protected void open() {
        ((ImageView)getNode()).setImage(openedImage);
    }

    public void interact(int interaction, Hero hero) {
        if (interaction>0){ // this means its on the chest
            hero.addCoins(coinCount);
            open();
        }
    }
}
