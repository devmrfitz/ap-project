package com.example.ap_project;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public abstract class Orc extends GameObject implements Jumpable{
    private String color;
    private int hitPoints;
    private int size;
    private Timeline jumpTimeline;

    public Orc(ImageView _node){
        super(_node);
        startJumping();
    }


    private void die(){

    }

    public void spawn(){

    }

    public void deSpawn(){
        getNode().setVisible(false);
    }

    public void push(){

    }

    public void decreaseHitPoints(int decrement){

    }


    @Override
    public void interact(int interaction, Hero hero) {
        if (Utility.checkIthBit(interaction, 3)){
            // along Y axis
            if (Utility.checkIthBit(interaction, 2)){
                // hero at top
                hero.jump();
            }
            else{
                // hero at bottom
                hero.die();
            }
        }
        else{
            // along X axis
            if(Utility.checkIthBit(interaction, 1))
                moveForward();
        }
    }

    private void moveForward() {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        final KeyValue kv = new KeyValue(getNode().layoutXProperty(), getNode().getLayoutX() + 150,
                Interpolator.EASE_OUT);
        final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

    private void startJumping(){
        System.out.println("Hero Jumping");
        jumpTimeline = new Timeline();
        jumpTimeline.setCycleCount(1);
        jumpTimeline.setAutoReverse(false);
        final KeyValue up_key_value = new KeyValue(getNode().layoutYProperty(), getNode().getLayoutY()-140,
                Interpolator.EASE_OUT);
        final KeyFrame up_keyframe = new KeyFrame(Duration.millis(700), up_key_value);
        final KeyValue down_key_value = new KeyValue(getNode().layoutYProperty(), 1000,
                Interpolator.EASE_IN);
        final KeyFrame down_keyframe = new KeyFrame(Duration.millis(7000), down_key_value);
        jumpTimeline.getKeyFrames().add(up_keyframe);
        jumpTimeline.getKeyFrames().add(down_keyframe);
        jumpTimeline.play();
    }


    public void jump(){
        jumpTimeline.stop();
        startJumping();
    }

    @Override
    public void deathByFall() {
        deSpawn();
    }
}
