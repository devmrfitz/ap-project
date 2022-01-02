package com.example.ap_project.animation_timers;

import com.example.ap_project.fxml.GameController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class AutoSaver extends GameAnimationTimer{

    @Override
    public void work(long now) {
        GameController a = GameController.getInstance();
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("out_temp.o"))) {
            out.writeObject(a);
            System.out.println("Game saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
