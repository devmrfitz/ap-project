package com.example.ap_project.animation_timers;

import com.example.ap_project.Hero;
import com.example.ap_project.Interactable;
import com.example.ap_project.Utility;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;

public class HeroInteractChecker extends AnimationTimer {
    private final Hero hero;
    private final ArrayList<Interactable> interactables;

    public HeroInteractChecker(Hero hero, ArrayList<Interactable> interactables) {
        this.hero = hero;
        this.interactables = interactables;
    }

    @Override
    public void handle(long now) {
        for (Interactable interactable : interactables) {
            int interaction = Utility.intersectDirection(hero.getNode(), interactable.getNode());
            interact(interactable, interaction);
        }
    }

    public void interact(Interactable interactable, int interaction) {
        if (interaction != -1)
            interactable.interact(interaction);
    }
}