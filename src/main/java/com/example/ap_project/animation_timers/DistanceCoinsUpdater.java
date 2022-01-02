package com.example.ap_project.animation_timers;

import com.example.ap_project.Hero;
import javafx.scene.text.Text;

public class DistanceCoinsUpdater extends GameAnimationTimer{
    private final Text distanceText, coinsText;
    private final Hero hero;

    public DistanceCoinsUpdater(Text distanceText, Text coinsText, Hero hero){
        this.distanceText = distanceText;
        this.coinsText = coinsText;
        this.hero = hero;
    }

    @Override
    public void work(long now) {
        distanceText.setText(String.valueOf(hero.getDistance()));
        coinsText.setText(String.valueOf(hero.getCoins()));
    }
}
