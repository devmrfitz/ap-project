package com.example.ap_project.animation_timers;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;

import java.util.ArrayList;

public class ScreenScroller extends AnimationTimer {
    private final ArrayList<Node> nodes;
    private final Scene scene;

    public ScreenScroller(ArrayList<Node> nodes, Scene scene) {
        this.nodes = nodes;
        this.scene = scene;
    }

    public void scroll(double offset) {
        for (Node node : nodes) {
            node.setTranslateX(node.getTranslateX() + offset);
        }
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    @Override
    public void handle(long now) {
//        scroll(-0.5);
        System.out.println("cameraX: " + scene.getCamera().getTranslateX());
//        scene.getCamera().setTranslateX(scene.getCamera().getTranslateX() + 5);
//        scene.getCamera().translateXProperty().set(50);
    }
}
