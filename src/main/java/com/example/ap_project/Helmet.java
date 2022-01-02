package com.example.ap_project;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ArrayList;

abstract public class Helmet {
    private final ArrayList<Weapon> activeWeapons;
    private Weapon currentWeapon;
    private transient final AnchorPane weaponPane;


    protected Helmet(AnchorPane weaponPane) {
        this.weaponPane = weaponPane;
        activeWeapons = new ArrayList<>();
        activeWeapons.add(null);
        activeWeapons.add(null);
        registerWeaponPaneHandlers();
    }

    private void registerWeaponPaneHandlers() {
        for (int i = 0; i < weaponPane.getChildren().size(); i++) {
            final int index = i;
            weaponPane.getChildren().get(i).setOnMouseClicked(event -> equipWeapon(index));
        }
    }

    public void equipWeapon(int type){
        if (activeWeapons.get(type) != null) {
            System.out.println("Equipping weapon: " + activeWeapons.get(type).getType());
            currentWeapon = activeWeapons.get(type);
            weaponPane.getChildren().get(type).setOpacity(1);
            weaponPane.getChildren().get(1 - type).setOpacity(0.5);

        }
    }

    public ArrayList<Weapon> getActiveWeapons() {
        return activeWeapons;
    }

    public Weapon getCurrentWeapon(){
        return currentWeapon;
    }

    void superRehydrate() {
        registerWeaponPaneHandlers();
    }

    public void addWeapon(Weapon weapon){
        if (activeWeapons.get(weapon.getType()) == null) {
            activeWeapons.set(weapon.getType(), weapon.clone());
            weaponPane.getChildren().get(weapon.getType()).setVisible(true);
        }
        else {
            activeWeapons.set(weapon.getType(), activeWeapons.get(weapon.getType()).getUpgradedVersion());
        }
        ((Text)((Pane)weaponPane.getChildren().get(weapon.getType())).getChildren().get(2)).setText(String.valueOf(
                activeWeapons.get(weapon.getType()).getLevel()));
        equipWeapon(weapon.getType());
    }
}
