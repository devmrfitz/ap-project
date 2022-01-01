package com.example.ap_project;

import javafx.scene.Node;

import java.util.ArrayList;

public class JumpHandler implements Runnable {

    private Jumpable jumpable;
    private ArrayList<Node> nodes;

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


}
