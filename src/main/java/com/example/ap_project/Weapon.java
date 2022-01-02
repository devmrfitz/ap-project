package com.example.ap_project;

public class Weapon implements Cloneable{
    private int level;

    public Weapon(){
        level = 1;
    }

    public void equip(){

    }

    public void unEquip(){

    }

    public void attack(){

    }

    public void setLevel(int level){
        this.level = level;
    }

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

    public boolean equals(Object obj){
        if (obj instanceof Weapon){
            Weapon a = new Weapon();
            return a.equals(obj);
        }
        else{
            return false;
        }
    }
}
