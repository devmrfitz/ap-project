package com.example.ap_project.animation_timers;

import com.example.ap_project.Hero;
import com.example.ap_project.Utility;
import com.example.ap_project.fxml.GameController;
import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;


public class CannonHandler extends AnimationTimer {
    private static Hero hero;
    private final ImageView imageView;
    private final int id;

    public CannonHandler(ImageView imageView) {
        this.imageView = imageView;
        this.id = GameController.getInstance().getId();
    }

    public static void setHero(Hero hero) {
        CannonHandler.hero = hero;
    }

    @Override
    public void handle(long now) {
//        if (GameController.getInstance().getId() == id) {
//
//            int interaction = Utility.intersectDirection(imageView, hero.getNode());
//            if (interaction != -1 && Utility.checkIthBit(interaction, 1) && Utility.checkIthBit(interaction, 3))
//                hero.die();
//        }
//        else
//            stop();
    }
}
