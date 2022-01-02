package com.example.ap_project;

import com.example.ap_project.animation_timers.PositionSaver;
import javafx.scene.Node;
import javafx.util.Pair;

import java.io.Serializable;

abstract public class GameObject implements Positionable, Interactable, Serializable {
    private Pair<Double,Double> position;
    private transient final Node node;
    private boolean exists;

    public GameObject(Node node){
        this.node = node;
        this.exists = true;
        (new PositionSaver(this)).start();
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
        return position;
    }

    @Override
    public void setPosition(Pair<Double, Double> position) {
        this.position = position;
    }

    @Override
    public void savePosition() {
        Pair<Double, Double> p = new Pair<>(node.getLayoutX(), node.getLayoutY());
        setPosition(p);
    }
}
