package com.example.ap_project;

import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.util.Duration;

public class Boss implements Runnable {
    private ImageView boss;
    private Region bossRegion;
    private Timeline bossTimeline;

    public Boss(ImageView boss, Region bossRegion, Timeline bossTimeline) {
        this.boss = boss;

        this.bossRegion = bossRegion;
        this.bossTimeline = bossTimeline;
    }

    @Override
    public void run() {
        System.out.println("Boss is running");
        while (true) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Boss is moving");
            System.out.println(Utility.intersects(boss, bossRegion));
            if (Utility.intersects(boss, bossRegion)) {
                if (bossTimeline.getCurrentTime().greaterThanOrEqualTo(Duration.millis(700)))
                bossTimeline.playFromStart();
            }
//            System.out.println(boss.getBoundsInLocal().intersects(bossRegion.getBoundsInLocal()));
//            boss.setX(boss.getX() + 2);
        }
    }

    // move boss downwards
    public void moveDown() {
        boss.setY(boss.getY() + 2);
    }


}
