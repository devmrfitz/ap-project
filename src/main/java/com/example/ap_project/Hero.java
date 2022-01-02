package com.example.ap_project;

import com.example.ap_project.fxml.GameController;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import javafx.util.Pair;

import java.util.ArrayList;

public class Hero implements Positionable, Serializable, Jumpable {
    private final ArrayList<Weapon> activeWeapons;
    private int distanceTravelled;
    private final Pane node;
    private Timeline jumpTimeline;
    private int coinsCollected;
    private Weapon currentWeapon;


    public Hero(Pane _node) {
        activeWeapons = new ArrayList<>();
        activeWeapons.add(null);
        activeWeapons.add(null);
        node = _node;
        startJumping();
    }

    private void startJumping(){
        System.out.println("Hero Jumping");
        jumpTimeline = new Timeline();
        jumpTimeline.setCycleCount(1);
        jumpTimeline.setAutoReverse(false);
        final KeyValue up_key_value = new KeyValue(node.layoutYProperty(), node.getLayoutY()-100,
                Interpolator.EASE_OUT);
        final KeyFrame up_keyframe = new KeyFrame(Duration.millis(700), up_key_value);
        final KeyValue down_key_value = new KeyValue(node.layoutYProperty(), 1000,
                Interpolator.EASE_IN);
        final KeyFrame down_keyframe = new KeyFrame(Duration.millis(7000), down_key_value);
        jumpTimeline.getKeyFrames().add(up_keyframe);
        jumpTimeline.getKeyFrames().add(down_keyframe);
        jumpTimeline.play();
    }

    public Weapon getCurrentWeapon(){
        return currentWeapon;
    }


    public void jump(){
//        System.out.println("Hero Jumping");
        jumpTimeline.stop();
        startJumping();
    }

    @Override
    public void deathByFall() {
        System.out.println("Hero died by falling");
        node.setVisible(false);
        GameController.game_over();
    }

    @Override
    public Node getNode() {
        return this.node;
    }

    public void die(){
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        GameController.game_over();

    }

    public void resurrect(){

    }

    public void addWeapon(Weapon weapon){
        if (activeWeapons.get(weapon.getType()) == null) {
            activeWeapons.set(weapon.getType(), weapon.clone());
        }
        else
            activeWeapons.set(weapon.getType(), activeWeapons.get(weapon.getType()).getUpgradedVersion());
        equipWeapon(weapon.getType());
    }

    public void equipWeapon(int type){
        currentWeapon = activeWeapons.get(type);
        ((ImageView)node.getChildren().get(1)).setImage(currentWeapon.getImage());
    }

    public void moveForward() {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        final KeyValue kv = new KeyValue(node.layoutXProperty(), node.getLayoutX() + 120,
                Interpolator.EASE_OUT);
        final KeyFrame kf = new KeyFrame(Duration.millis(700), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();

        if (getCurrentWeapon() != null) {
            getCurrentWeapon().attack(this);
        }
    }

//    public void hit(ImageView rectangle, double delay) {
//        final Timeline timeline = new Timeline();
//        timeline.setCycleCount(2);
//        timeline.setAutoReverse(true);
//        final KeyValue kv = new KeyValue(rectangle.rotateProperty(), 90,
//                Interpolator.EASE_BOTH);
//        final KeyFrame kf = new KeyFrame(Duration.millis(700), kv);
//        timeline.getKeyFrames().add(kf);
//        timeline.setDelay(Duration.seconds(delay));
//        timeline.setAutoReverse(true);
//        timeline.play();
//    }

    public ArrayList<Weapon> listWeapons(){
        return activeWeapons;
    }

    public void addCoins(int coins){

    }

    @Override
    public Pair<Double, Double> getPosition() {
        return null;
    }

    @Override
    public void setPosition(Pair<Double, Double> position) {

    }
}
