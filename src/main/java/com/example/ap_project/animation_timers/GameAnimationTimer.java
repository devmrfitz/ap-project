package com.example.ap_project.animation_timers;

import com.example.ap_project.fxml.GameController;
import javafx.animation.AnimationTimer;

public abstract class GameAnimationTimer extends AnimationTimer {
    private int id;

    public GameAnimationTimer() {
        this.id = GameController.getInstance().getId();
    }

    @Override
    public void handle(long now) {
        if (GameController.getInstance().getId() == id) {
            work(now);
        }
        else
            stop();
    }

    public abstract void work(long now);


}
