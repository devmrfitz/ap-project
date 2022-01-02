package com.example.ap_project;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileNotFoundException;

public class WeaponChest extends Chest{
    private final Weapon weapon;
    public WeaponChest(Weapon weapon,ImageView _node){
        super(_node);
        this.weapon = weapon;
    }

    public void spawn(){

    }

    protected void open(MouseEvent event) throws FileNotFoundException {
        super.open(event);
    }

}
