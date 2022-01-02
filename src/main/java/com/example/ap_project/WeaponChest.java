package com.example.ap_project;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Random;

public class WeaponChest extends Chest{
    private final Weapon weapon;
    private static transient final Image closedImage = new
            Image("file:src/main/resources/com/example/ap_project/images/chestClosed.png");
    private static transient final Image openedImage = new
            Image("file:src/main/resources/com/example/ap_project/images/chestOpen.png");
    private static final double height = 50, width = 65;
    private static final Random rand = new Random();

    private WeaponChest(Weapon weapon,ImageView _node){
        super(_node);
        this.weapon = weapon;
    }

    public static WeaponChest getInstance(double x, double y){
        ImageView img = new ImageView(closedImage);
        img.setFitHeight(height);
        img.setFitWidth(width);
        img.setLayoutX(x+width/2);
        img.setLayoutY(y-height);
        Weapon weapon;
        if (rand.nextInt(2) == 0)
            weapon = Sword.getInstance(1);
        else
            weapon = ThrowingStars.getInstance(1);
        return new WeaponChest(weapon, img);
    }


    protected void open() {
        ((ImageView)getNode()).setImage(openedImage);
    }

    @Override
    public void interact(int interaction, Hero hero) {
        if (!isOpen()){ // this means its on the chest
            hero.addWeapon(weapon);
            open();
            setOpen();
        }
    }

    @Override
    public void rehydrate(Node _mainAnchorPane) {
        AnchorPane mainAnchorPane = (AnchorPane) _mainAnchorPane;
        ImageView img;
        if (isOpen())
            img = new ImageView(openedImage);
        else
            img = new ImageView(closedImage);
        img.setFitHeight(height);
        img.setFitWidth(width);
        img.setLayoutX(getPosition().getKey());
        img.setLayoutY(getPosition().getValue());
        setNode(img);
        mainAnchorPane.getChildren().add(img);
    }
}
