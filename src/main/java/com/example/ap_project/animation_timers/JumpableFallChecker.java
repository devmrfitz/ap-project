package com.example.ap_project.animation_timers;

import com.example.ap_project.Jumpable;
import com.example.ap_project.Utility;
import javafx.scene.shape.Rectangle;

public class JumpableFallChecker extends GameAnimationTimer {
    private final Rectangle deathBox;
    private final Jumpable jumpable;

    public JumpableFallChecker(Jumpable jumpable, Rectangle deathBox, int id) {
        this.deathBox = deathBox;
        this.jumpable = jumpable;
    }

    @Override
    public void work(long now) {
        if (Utility.intersects(deathBox, jumpable.getNode())) {
            jumpable.deathByFall();
        }
    }
}
