package com.example.ap_project;

import javafx.scene.Node;

import java.util.ArrayList;

public class JumpHandler implements Runnable {

    private Jumpable jumpable;
    private ArrayList<Node> nodes;

    public JumpHandler(Jumpable jumpable, ArrayList<Node> nodes) {
        this.jumpable = jumpable;
        this.nodes = nodes;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // iterate through all nodes and check if they are in the same position as the jumpable
            for (Node node : nodes) {
                if (Utility.intersects((Node)jumpable, node))
                    jumpable.jump();
            }

        }
    }


}
