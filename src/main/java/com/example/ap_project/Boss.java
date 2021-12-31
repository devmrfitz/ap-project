package com.example.ap_project;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

public class Boss implements Runnable {
    private ImageView boss;
    private Region bossRegion;

    public Boss(ImageView boss, Region bossRegion) {
        this.boss = boss;
        this.bossRegion = bossRegion;
    }

    @Override
    public void run() {
        System.out.println("Boss is running");
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Boss is moving");
            System.out.println(Utility.intersects(boss, bossRegion));
//            System.out.println(boss.getBoundsInLocal().intersects(bossRegion.getBoundsInLocal()));
            boss.setX(boss.getX() + 2);
        }
    }

    // move boss downwards
    public void moveDown() {
        boss.setY(boss.getY() + 2);
    }


}
