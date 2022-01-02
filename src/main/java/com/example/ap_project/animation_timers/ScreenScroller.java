package com.example.ap_project.animation_timers;

import com.example.ap_project.fxml.GameController;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;

public class ScreenScroller extends AnimationTimer {
    private final Node node;
    private final double speed;
    private final int id;

    public ScreenScroller(Node node, double speed, int id) {
        this.node = node;
        this.speed = -1*speed;
        this.id = id;
    }

    private void scroll(double offset) {
        if (offset==-0.5)
            System.out.println("offset"+node.getLayoutX());
        node.setLayoutX(node.getLayoutX() + offset);
        if (offset==-0.5)
            GameController.getInstance().setAnchorPaneLayoutX(node.getLayoutX());
    }

    @Override
    public void handle(long now) {
        if (GameController.getInstance().getId() == id)
            scroll(speed);

    }
}
