package com.example.ap_project;

import javafx.util.Pair;

public class GameObjects implements Positionable, Serializable {
    private Pair<Integer,Integer> position;

    public GameObjects(){

    }

    public void interact(){

    }

    @Override
    public Pair<Double, Double> getPosition() {
        return null;
    }

    @Override
    public void setPosition(Pair<Double, Double> position) {

    }
}
