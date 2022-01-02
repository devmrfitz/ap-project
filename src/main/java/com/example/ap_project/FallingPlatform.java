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
        final KeyFrame kf = new KeyFrame(Duration.millis(3700), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setDelay(Duration.seconds(0));
        timeline.play();
    }

    @Override
    public void interact(int interaction, Hero hero) {
        if (Utility.checkIthBit(interaction, 2)) {
            fall();
        }
    }

    @Override
    public void rehydrate(Node node) {

    }
}
