package com.example.ap_project;

import javafx.scene.image.Image;

public abstract class Weapon implements Cloneable{
    private final int level;

    public Weapon(int level){
        this.level = level;
    }

    public void equip(){

    }

    public void unEquip(){

    }

    public abstract void attack(Hero hero);


    public int getLevel(){
        return level;
    }

    @Override
    public Weapon clone() {
        try {
            Weapon clone = (Weapon) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }


    public abstract int getType();

    public abstract Weapon getUpgradedVersion();

    public abstract Image getImage();

    public int getDamage(){
        return level*200;
    }
}
