package com.example.ap_project.animation_timers;

import com.example.ap_project.Positionable;
import com.example.ap_project.fxml.GameController;

public class PositionSaver extends GameAnimationTimer{

    private final Positionable positionable;

    public PositionSaver(Positionable positionable) {
        this.positionable = positionable;
    }

    @Override
    public void work(long now) {
        if (GameController.getInstance().isReady())
            positionable.savePosition();
        else
            System.out.println("PositionSaver: not ready");
    }
}
