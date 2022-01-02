package com.example.ap_project.animation_timers;

import com.example.ap_project.Jumpable;
import com.example.ap_project.Utility;
import com.example.ap_project.fxml.GameController;
import javafx.scene.shape.Rectangle;

public class JumpableWinChecker extends GameAnimationTimer {
    private final Rectangle deathBox;
    private final Jumpable jumpable;

    public JumpableWinChecker(Jumpable jumpable, Rectangle deathBox, int id) {
        this.deathBox = deathBox;
        this.jumpable = jumpable;
    }

    @Override
    public void work(long now) {
        if (Utility.intersects(deathBox, jumpable.getNode())) {
            GameController.getInstance().win();
        }
    }
}
