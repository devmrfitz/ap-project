package com.example.ap_project;

import com.example.ap_project.animation_timers.PositionSaver;
import com.example.ap_project.exceptions.InsufficientCoinsException;
import com.example.ap_project.fxml.GameController;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import javafx.util.Pair;

import java.io.Serializable;

public class Hero extends Helmet implements Positionable, Jumpable, Serializable {
    private int distanceTravelled;
    private transient Pane node;
    private transient Timeline jumpTimeline;
    private int coinsCollected;

    private Pair<Double, Double> position;


    public Hero(Pane _node, AnchorPane weaponPane) {
        super(weaponPane);
        node = _node;
        coinsCollected = 0;
        distanceTravelled = 0;
        (new PositionSaver(this)).start();
        startJumping();
    }

    public Hero() {
        super(null);
    }

    public void equipWeapon(int type){
        if (getActiveWeapons().get(type) != null) {
            super.equipWeapon(type);
            ((ImageView) node.getChildren().get(1)).setImage(getCurrentWeapon().getImage());
        }
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



    public void jump(){
        jumpTimeline.stop();
        startJumping();
    }

    @Override
    public void deathByFall() {
        System.out.println("Hero died by falling");
        node.setVisible(false);
        GameController.getInstance().game_over();
    }

    @Override
    public Node getNode() {
        return this.node;
    }

    @Override
    public void setNode(Node node) {
        this.node = (Pane) node;
    }

    @Override
    public void rehydrate(Node node) {
        node.setLayoutX(position.getKey());
        node.setLayoutY(position.getValue());
        (new PositionSaver(this)).start();

        superRehydrate();
        startJumping();
    }

    public void die(){
        System.out.println("Hero died");
        GameController.getInstance().game_over();
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
        distanceTravelled += 120;

        if (getCurrentWeapon() != null) {
            getCurrentWeapon().attack(this);
        }
    }

    public void addCoins(int coins){
        coinsCollected += coins;
    }

    private void subtractCoins() throws InsufficientCoinsException {
        if (coinsCollected<5)
            throw new InsufficientCoinsException("oops");
        coinsCollected -= 5;
    }

    @Override
    public Pair<Double, Double> getPosition() {
        return position;
    }

    @Override
    public void setPosition(Pair<Double, Double> position) {
        this.position = position;
//        System.out.println("Hero position set to " + position.getKey() + " " + position.getValue());
    }

    @Override
    public void savePosition() {
        Pair<Double, Double> p = new Pair<>(node.getLayoutX(), node.getLayoutY());
        setPosition(p);
    }



    public int getCoins() {
        return coinsCollected;
    }

    public double getDistance() {
        return distanceTravelled;
    }

    public void revive() throws InsufficientCoinsException {
        subtractCoins();
    }
}
