package com.example.ap_project;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class FallingPlatform extends Obstacle implements Bouncer {
    public FallingPlatform(Rectangle _node) {
        super(_node);
    }


    private void fall() {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        final KeyValue kv = new KeyValue(getNode().layoutYProperty(), 1000,
                Interpolator.EASE_BOTH);
        final KeyFrame kf = new KeyFrame(Duration.millis(4700), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setDelay(Duration.seconds(1));
        timeline.play();
    }

    @Override
    public void interact(int interaction) {
        if (interaction == 1) {
            fall();
        }
    }
}
