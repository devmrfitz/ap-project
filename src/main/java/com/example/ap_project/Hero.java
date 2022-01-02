package com.example.ap_project;

import com.example.ap_project.fxml.GameController;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import javafx.util.Pair;

import java.util.ArrayList;

public class Hero implements Positionable, Jumpable {
    private final ArrayList<Weapon> activeWeapons;
    private int distanceTravelled;
    private final Pane node;
    private Timeline jumpTimeline;
    private int coinsCollected;
    private Weapon currentWeapon;
    private final AnchorPane weaponPane;


    public Hero(Pane _node, AnchorPane weaponPane) {
        activeWeapons = new ArrayList<>();
        activeWeapons.add(null);
        activeWeapons.add(null);
        node = _node;
        this.weaponPane = weaponPane;
        coinsCollected = 0;
        registerWeaponPaneHandlers();
        startJumping();
    }

    private void registerWeaponPaneHandlers() {
        for (int i = 0; i < weaponPane.getChildren().size(); i++) {
            final int index = i;
            weaponPane.getChildren().get(i).setOnMouseClicked(event -> equipWeapon(index));
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

    public Weapon getCurrentWeapon(){
        return currentWeapon;
    }


    public void jump(){
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
            weaponPane.getChildren().get(weapon.getType()).setVisible(true);
        }
        else {
            activeWeapons.set(weapon.getType(), activeWeapons.get(weapon.getType()).getUpgradedVersion());
        }
        ((Text)((Pane)weaponPane.getChildren().get(weapon.getType())).getChildren().get(2)).setText(String.valueOf(
                activeWeapons.get(weapon.getType()).getLevel()));
        equipWeapon(weapon.getType());
    }

    public void equipWeapon(int type){
        if (activeWeapons.get(type) != null) {
            System.out.println("Equipping weapon: " + activeWeapons.get(type).getType());
            currentWeapon = activeWeapons.get(type);
            weaponPane.getChildren().get(type).setOpacity(1);
            weaponPane.getChildren().get(1 - type).setOpacity(0.5);
            ((ImageView) node.getChildren().get(1)).setImage(currentWeapon.getImage());
        }
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

    public ArrayList<Weapon> listWeapons(){
        return activeWeapons;
    }

    public void addCoins(int coins){
        coinsCollected += coins;
    }

    @Override
    public Pair<Double, Double> getPosition() {
        return null;
    }

    @Override
    public void setPosition(Pair<Double, Double> position) {

    }
}
