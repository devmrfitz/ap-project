package com.example.ap_project.animation_timers;

import com.example.ap_project.Bouncer;
import com.example.ap_project.Hero;
import com.example.ap_project.Interactable;
import com.example.ap_project.Utility;
import com.example.ap_project.fxml.GameController;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;

public class HeroInteractChecker extends AnimationTimer {
    private final Hero hero;
    private final ArrayList<Interactable> interactables;
    private final int id;

    public HeroInteractChecker(Hero hero, ArrayList<Interactable> interactables, int id) {
        this.hero = hero;
        this.interactables = interactables;
        this.id = id;
    }

    @Override
    public void handle(long now) {
        if (GameController.getInstance().getId() == id) {
            for (Interactable interactable : interactables) {
                if (interactable.exists()) {
                    int interaction = Utility.intersectDirection(hero.getNode(), interactable.getNode());
                    interact(interactable, interaction);
                }
            }
        }
        else
            stop();
    }

    private void interact(Interactable interactable, int interaction) {
        if (interaction != -1) {
//            System.out.println("intersects with " + interactable.getNode() + " " + interaction);
            interactable.interact(interaction, hero);
            if (interactable instanceof Bouncer && Utility.checkIthBit(interaction, 2)) {
                hero.jump();
            }
        }
    }
}