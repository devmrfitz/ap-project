package com.example.ap_project;

import javafx.scene.Node;

import java.io.Serializable;

public class Island implements Bouncer, Interactable, Serializable {
    private transient Node node;

    public Island(Node node){
        this.node = node;
    }

    @Override
    public Node getNode() {
        return this.node;
    }

    @Override
    public void setNode(Node node) {
        this.node = node;
    }

    @Override
    public void rehydrate(Node node) {

    }

    @Override
    public void interact(int interaction, Hero hero) {

    }


}
