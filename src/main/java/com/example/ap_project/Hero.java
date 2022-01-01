package com.example.ap_project;

import javafx.util.Pair;

import java.util.ArrayList;

public class Hero implements Positionable, Serializable {
    private ArrayList<Weapon> activeWeapons;
    private int distanceTravelled;
    private int coinsCollected;

    public void jump(){

    }

    public void die(){

    }

    public void resurrect(){

    }

    public void addWeapon(){

    }

    public void equipWeapon(){

    }

    public void moveForward(){

    }

    public ArrayList<Weapon> listWeapons(){
        return activeWeapons;
    }

    public void addCoins(int coins){

    }

    @Override
    public Pair<Double, Double> getPosition() {
        return null;
    }

    @Override
    public void setPosition(Pair<Double, Double> position) {

    }
}
