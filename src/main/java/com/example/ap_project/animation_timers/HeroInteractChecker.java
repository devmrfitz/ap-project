package com.example.ap_project.animation_timers;

import com.example.ap_project.Bouncer;
import com.example.ap_project.Hero;
import com.example.ap_project.Interactable;
import com.example.ap_project.Utility;

import java.util.ArrayList;

public class HeroInteractChecker extends GameAnimationTimer {
    private final Hero hero;
    private final ArrayList<Interactable> interactables;


    public HeroInteractChecker(Hero hero, ArrayList<Interactable> interactables, int id) {
        this.hero = hero;
        this.interactables = interactables;
    }

    @Override
    public void work(long now) {
            for (Interactable interactable : interactables) {
                if (interactable.exists()) {
//                    System.out.println("interactable exists "+interactable);
                    int interaction = Utility.intersectDirection(hero.getNode(), interactable.getNode());
                    interact(interactable, interaction);
                }
            }
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