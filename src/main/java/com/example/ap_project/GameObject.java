package com.example.ap_project;

import javafx.scene.Node;
import javafx.util.Pair;

abstract public class GameObject implements Positionable, Serializable, Interactable {
    private Pair<Integer,Integer> position;
    private final Node node;

    public GameObject(Node node){
        this.node = node;
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
