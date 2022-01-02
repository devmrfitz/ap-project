package com.example.ap_project;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Sword extends Weapon implements Comparable<Weapon>, Serializable {
    private static transient final Image image1 = new Image("file:src/main/resources/com/example/ap_project/images/Sword1.png");
    private static transient final Image image2 = new Image("file:src/main/resources/com/example/ap_project/images/Sword2.png");
    private static transient final Image image3 = new Image("file:src/main/resources/com/example/ap_project/images/Sword3.png");
    private static transient final Image[] images = {null, image1, image2, image3};
    private static final int weaponType = 0;

    public Sword(int level){
        super(level);
    }

    private static final Map<String, Sword> instances =
            new HashMap<>();

    public static Sword getInstance(int level) {

        String key = level+ "";
        if (!instances.containsKey(key)) {
            instances.put(key, new Sword(level));
        }
        return instances.get(key);
    }

    public boolean equals(Object obj){
        if (obj instanceof Weapon){
            Weapon a = new Sword(1);
            return a.equals(obj);
        }
        else{
            return false;
        }
    }

    @Override
    public int compareTo(Weapon o) {
        if (getLevel() == o.getLevel()){
            return 0;
        }
        else if(getLevel() > o.getLevel()){
            return 0;
        }
        else{
            return -1;
        }
    }

    public int getType(){
        return weaponType;
    }

    public Weapon getUpgradedVersion(){
        if (getLevel() >= 3)
            return this;
        String key = (getLevel()+1)+ "";
        if (!instances.containsKey(key)) {
            instances.put(key, new Sword(getLevel()+1));
        }
        return instances.get(key);
    }

    public Image getImage(){
        return images[getLevel()];
    }

    public void attack(Hero hero) {
        hit((ImageView) ((Pane)hero.getNode()).getChildren().get(1));
    }

    private void hit(ImageView rectangle) {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(true);
        final KeyValue kv = new KeyValue(rectangle.rotateProperty(), rectangle.rotateProperty().get()+90,
                Interpolator.EASE_BOTH);
        final KeyFrame kf = new KeyFrame(Duration.millis(700), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setDelay(Duration.seconds(0));
        timeline.setAutoReverse(true);
        timeline.play();
    }
}
