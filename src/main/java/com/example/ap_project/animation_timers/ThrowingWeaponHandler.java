package com.example.ap_project.animation_timers;

import com.example.ap_project.Orc;
import com.example.ap_project.Utility;
import com.example.ap_project.Weapon;
import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class ThrowingWeaponHandler extends AnimationTimer {
    private static ArrayList<Orc> orcs;
    private final ImageView imageView;
    private final Weapon weapon;

    public ThrowingWeaponHandler(ImageView imageView, Weapon weapon) {
        this.imageView = imageView;
        this.weapon = weapon;
    }


    public static void setOrcs(ArrayList<Orc> orcs) {
        ThrowingWeaponHandler.orcs = orcs;
    }

    @Override
    public void handle(long now) {
        for (Orc orc : orcs) {
            if (Utility.intersects(imageView, orc.getNode())) {
                orc.decreaseHitPoints(weapon.getDamage());
                imageView.setVisible(false);
                imageView.setLayoutY(10000);
                this.stop();
                if (imageView.getParent() != null)
                    ((AnchorPane)imageView.getParent()).getChildren().remove(imageView);
            }
        }
    }
}
