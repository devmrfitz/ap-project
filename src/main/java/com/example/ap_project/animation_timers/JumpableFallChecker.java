package com.example.ap_project.animation_timers;

import com.example.ap_project.Jumpable;
import com.example.ap_project.Utility;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;

public class JumpableFallChecker extends AnimationTimer {
    private final Rectangle deathBox;
    private final Jumpable jumpable;

    public JumpableFallChecker(Jumpable jumpable, Rectangle deathBox) {
        this.deathBox = deathBox;
        this.jumpable = jumpable;
    }

    @Override
    public void handle(long now) {
        if (Utility.intersects(deathBox, jumpable.getNode())) {
            jumpable.deathByFall();
        }

    }
}
