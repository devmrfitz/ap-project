package com.example.ap_project;

import javafx.scene.Node;

public class Island implements Bouncer, Interactable{
    private Node node;

    public Island(Node node){
        this.node = node;
    }

    @Override
    public Node getNode() {
        return this.node;
    }

    @Override
    public void interact(int interaction, Hero hero) {

    }
}
