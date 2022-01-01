package com.example.ap_project;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class FallingPlatform extends Obstacle {
    private final Rectangle rectangle;
    private final double delay;
    public FallingPlatform(Rectangle _node, double delay){
        rectangle = _node;
        this.delay = delay;
    }

    public void interact(){
        fall();
    }

    private void fall (){
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        final KeyValue kv = new KeyValue(rectangle.yProperty(), 1000,
                Interpolator.EASE_BOTH);
        final KeyFrame kf = new KeyFrame(Duration.millis(4700), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setDelay(Duration.seconds(delay));
        timeline.play();
    }
}
