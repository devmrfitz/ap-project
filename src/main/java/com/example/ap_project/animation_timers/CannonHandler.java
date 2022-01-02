package com.example.ap_project.animation_timers;

import com.example.ap_project.Hero;
import com.example.ap_project.Utility;
import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;


public class CannonHandler extends AnimationTimer {
    private static Hero hero;
    private final ImageView imageView;

    public CannonHandler(ImageView imageView) {
        this.imageView = imageView;
    }

    public static void setHero(Hero hero) {
        CannonHandler.hero = hero;
    }

    @Override
    public void handle(long now) {
        if (Utility.intersects(imageView, hero.getNode()))
            hero.die();
    }
}
