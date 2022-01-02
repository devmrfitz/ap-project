package com.example.ap_project.animation_timers;

import com.example.ap_project.Orc;
import com.example.ap_project.Utility;
import com.example.ap_project.Weapon;
import com.example.ap_project.fxml.GameController;
import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class ThrowingWeaponHandler extends AnimationTimer {
    private static ArrayList<Orc> orcs;
    private final ImageView imageView;
    private final Weapon weapon;
    private final int id;

    public ThrowingWeaponHandler(ImageView imageView, Weapon weapon) {
        this.imageView = imageView;
        this.weapon = weapon;
        this.id = GameController.getInstance().getId();
    }


    public static void setOrcs(ArrayList<Orc> orcs) {
        ThrowingWeaponHandler.orcs = orcs;
    }

    @Override
    public void handle(long now) {
        if (GameController.getInstance().getId() == id) {
            for (Orc orc : orcs) {
                if (orc.exists()) {
                    if (Utility.intersects(imageView, orc.getNode())) {
                        orc.decreaseHitPoints(weapon.getDamage());
                        //                imageView.setVisible(false);
                        imageView.setLayoutX(-10);
                        this.stop();
                        if (imageView.getParent() != null)
                            ((AnchorPane) imageView.getParent()).getChildren().remove(imageView);
                    }
                }
            }
        }
        else
            this.stop();
    }
}
