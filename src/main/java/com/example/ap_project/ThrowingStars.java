package com.example.ap_project;

import com.example.ap_project.animation_timers.ThrowingWeaponHandler;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

public class ThrowingStars extends Weapon implements Comparable<Weapon>{
    private static final Image image1 = new Image("file:src/main/resources/com/example/ap_project/images/ThrowingKnives1.png");
    private static final Image image2 = new Image("file:src/main/resources/com/example/ap_project/images/ThrowingKnives2.png");
    private static final Image image3 = new Image("file:src/main/resources/com/example/ap_project/images/ThrowingKnives3.png");
    private static final Image[] images = {null, image1, image2, image3};
    private static final int weaponType = 1;

    private ThrowingStars(int level){
        super(level);
    }

    @Override
    public void attack(Hero hero) {
        AnchorPane mainAnchorPane = (AnchorPane) hero.getNode().getParent();
        ImageView imageView = new ImageView(getImage());
        imageView.setFitHeight(((ImageView)(((Pane)hero.getNode()).getChildren().get(1))).getFitHeight());
        imageView.setFitWidth(((ImageView)(((Pane)hero.getNode()).getChildren().get(1))).getFitWidth());
        mainAnchorPane.getChildren().add(imageView);
        imageView.setLayoutX(((Pane)hero.getNode()).getLayoutX());
        imageView.setLayoutY(((Pane)hero.getNode()).getLayoutY());
        imageView.setRotate(90);


        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        final KeyValue kv = new KeyValue(imageView.layoutXProperty(), imageView.getLayoutX() + 15000,
                Interpolator.EASE_OUT);
        final KeyFrame kf = new KeyFrame(Duration.millis(50000), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();

        (new ThrowingWeaponHandler(imageView, this)).start();
    }

    private static final Map<String, ThrowingStars> instances =
            new HashMap<>();

    public static Weapon getInstance(int level) {

        String key = level+ "";
        if (!instances.containsKey(key)) {
            instances.put(key, new ThrowingStars(level));
        }
        return instances.get(key);
    }

    public boolean equals(Object obj){
        if (obj instanceof Weapon){
            Weapon a = new ThrowingStars(1);
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
            instances.put(key, new ThrowingStars(getLevel()+1));
        }
        return instances.get(key);
    }

    public Image getImage(){
        return images[getLevel()];
    }
}
