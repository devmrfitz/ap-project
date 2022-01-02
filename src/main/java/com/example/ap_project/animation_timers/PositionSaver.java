package com.example.ap_project.animation_timers;

import com.example.ap_project.Positionable;

public class PositionSaver extends GameAnimationTimer{

    private final Positionable positionable;

    public PositionSaver(Positionable positionable) {
        this.positionable = positionable;
    }

    @Override
    public void work(long now) {
        positionable.savePosition();
    }
}
