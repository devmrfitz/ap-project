package com.example.ap_project.animation_timers;

import com.example.ap_project.Jumpable;
import com.example.ap_project.Utility;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;

import java.util.ArrayList;

public class JumpGravityFallHandler extends AnimationTimer {

    private final Jumpable jumpable;
    private final ArrayList<Node> nodes;

    public JumpGravityFallHandler(Jumpable jumpable, ArrayList<Node> nodes) {
        this.jumpable = jumpable;
        this.nodes = nodes;
    }


    @Override
    public void handle(long now) {
        for (Node node : nodes) {
            if (Utility.intersects(jumpable.getNode(), node))
                jumpable.jump();
        }
    }
}
