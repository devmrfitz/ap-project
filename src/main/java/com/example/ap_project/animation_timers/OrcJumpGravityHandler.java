package com.example.ap_project.animation_timers;

import com.example.ap_project.Jumpable;
import com.example.ap_project.Utility;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;

import java.util.ArrayList;

public class OrcJumpGravityHandler extends AnimationTimer {

    private final Jumpable jumpable;
    private final ArrayList<Node> nodes;

    public OrcJumpGravityHandler(Jumpable jumpable, ArrayList<Node> nodes) {
        this.jumpable = jumpable;
        this.nodes = nodes;
    }


    @Override
    public void handle(long now) {
//        System.out.println("JumpGravityFallHandler"+jumpable.getNode().getLayoutY()+" "+jumpable.getNode().getLayoutX());
        for (Node node : nodes) {
            if (Utility.intersects(jumpable.getNode(), node) && jumpable.getNode().getLayoutY() < node.getLayoutY()) {
                System.out.println("intersects with " + node);
                jumpable.jump();
            }
        }
    }
}
