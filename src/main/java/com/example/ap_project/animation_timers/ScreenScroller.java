package com.example.ap_project.animation_timers;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;

public class ScreenScroller extends AnimationTimer {
    private final Node node;
    private final double speed;

    public ScreenScroller(Node node, double speed) {
        this.node = node;
        this.speed = -1*speed;
    }

    private void scroll(double offset) {
        node.setTranslateX(node.getTranslateX() + offset);
    }

    @Override
    public void handle(long now) {
        scroll(speed);
    }
}
