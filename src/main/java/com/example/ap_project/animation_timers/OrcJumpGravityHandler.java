package com.example.ap_project.animation_timers;

import com.example.ap_project.Island;
import com.example.ap_project.Jumpable;
import com.example.ap_project.Orc;
import com.example.ap_project.Utility;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;

import java.util.ArrayList;

public class OrcJumpGravityHandler extends AnimationTimer {

    private final Orc jumpable;
    private final ArrayList<Island> islands;

    public OrcJumpGravityHandler(Orc jumpable, ArrayList<Island> nodes) {
        this.jumpable = jumpable;
        this.islands = nodes;
    }


    @Override
    public void handle(long now) {
//        System.out.println("JumpGravityFallHandler"+jumpable.getNode().getLayoutY()+" "+jumpable.getNode().getLayoutX());
        if (jumpable.exists()){
            for (Island island : islands) {
                Node node = island.getNode();
                if (Utility.intersects(jumpable.getNode(), node) && jumpable.getNode().getLayoutY() < node.getLayoutY()) {
                    System.out.println("intersects with " + node);
                    jumpable.jump();
                }
            }
        }
        else
            this.stop();
    }
}
