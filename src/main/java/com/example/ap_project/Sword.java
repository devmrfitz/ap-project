package com.example.ap_project;

import java.util.HashMap;
import java.util.Map;

public class Sword extends Weapon implements Comparable<Weapon>{
    public Sword(int level){
        super(level);
    }

    private static Map<String, Sword> instances =
            new HashMap<String, Sword>();

    public static Sword getInstance(int level) {
        String key = level+ "";
        if (!instances.containsKey(key)) {
            instances.put(key, new Sword(level));
        }
        return instances.get(key);
    }

    public void attack(){

    }

    public void equip(){

    }

    public void uEquip(){

    }

    public boolean equals(Object obj){
        if (obj instanceof Weapon){
            Weapon a = new Sword(1);
            return a.equals(obj);
        }
        else{
            return false;
        }
    }

    @Override
    public int compareTo(Weapon o) {
        if (getLevel() == o.getLevel()){
            return 0;
        }
        else if(getLevel() > o.getLevel()){
            return 0;
        }
        else{
            return -1;
        }
    }
}
