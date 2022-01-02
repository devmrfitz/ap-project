package com.example.ap_project;

import com.example.ap_project.animation_timers.CannonHandler;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.Random;

public class Cannon {
    private final ImageView cannonShooter;
    private static final Image image = new Image("file:src/main/resources/com/example/ap_project/images/cannon.png");
    private static final Random rand = new Random();

    public Cannon(ImageView cannonShooter) {
        this.cannonShooter = cannonShooter;
    }

    public void shoot() {
        if (rand.nextInt(4)!=0)
            return;
        ImageView cannonBall = new ImageView(image);
        cannonBall.setLayoutX(cannonShooter.getLayoutX());
        cannonBall.setLayoutY(cannonShooter.getLayoutY());
        cannonBall.setFitHeight(cannonShooter.getFitHeight()/2);
        cannonBall.preserveRatioProperty().set(true);
        cannonBall.setVisible(true);
        cannonBall.setOpacity(1);
        cannonBall.setRotate(0);

        ((AnchorPane)cannonShooter.getParent()).getChildren().add(cannonBall);

        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        final KeyValue kv = new KeyValue(cannonBall.layoutXProperty(), cannonBall.getLayoutX() - 15000,
                Interpolator.EASE_OUT);
        final KeyFrame kf = new KeyFrame(Duration.millis(50000), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();

        (new CannonHandler(cannonBall)).start();

    }
}
