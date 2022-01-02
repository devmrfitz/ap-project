package com.example.ap_project;

import javafx.scene.Node;
import javafx.util.Pair;

import java.io.Serializable;

abstract public class GameObject implements Positionable, Interactable, Serializable {
    private Pair<Integer,Integer> position;
    private final Node node;
    private boolean exists;

    public GameObject(Node node){
        this.node = node;
        this.exists = true;
    }

    public boolean exists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public Node getNode() {
        return node;
    }

    @Override
    public Pair<Double, Double> getPosition() {
        return null;
    }

    @Override
    public void setPosition(Pair<Double, Double> position) {

    }
}
