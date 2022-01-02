package com.example.ap_project.animation_timers;

import com.example.ap_project.Jumpable;
import com.example.ap_project.Utility;
import com.example.ap_project.fxml.GameController;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;

public class JumpableFallChecker extends AnimationTimer {
    private final Rectangle deathBox;
    private final Jumpable jumpable;
    private final int id;

    public JumpableFallChecker(Jumpable jumpable, Rectangle deathBox, int id) {
        this.deathBox = deathBox;
        this.jumpable = jumpable;
        this.id = id;
    }

    @Override
    public void handle(long now) {
        if (GameController.getInstance().getId() == id) {
            if (Utility.intersects(deathBox, jumpable.getNode())) {
                jumpable.deathByFall();
            }
        }
        else
            stop();

    }
}
