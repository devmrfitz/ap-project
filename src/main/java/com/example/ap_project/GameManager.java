package com.example.ap_project;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class GameManager {
    private int distanceCovered;
//    private ArrayList<GameObject> gameObjectsOnScreen;  ?? change this to game objects , rn testing is on string
    private final ArrayList<Object> gameObjectsOnScreen;
    private int gravity;
    private int highScore;

    public GameManager(){
        gameObjectsOnScreen = new ArrayList<Object>();

    }

    public void newGame(){

    }

    public static void deserialize() throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("out.txt"))) {
            Object a = in.readObject();
            System.out.println("test" + a);
        }
        }

    public void loadGame() throws IOException, ClassNotFoundException {
        deserialize();
    }

    public void restartGame(){

    }

    public void serialize() throws IOException {
        gameObjectsOnScreen.add("lol");
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("out.txt"))) {
            out.writeObject(gameObjectsOnScreen);
        }
    }

    public void saveGame() throws IOException {
        serialize();

    }

    public void deleteGame(){

    }

    public void parseGame(){

    }

    public void exitGame(ActionEvent event){
        System.out.println("exit clicked");
        Stage stage = (Stage)((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    public void resurrectGame(){

    }

    public void increaseDistanceCovered(int dist){
        System.out.println(dist);
    }

    public void setHighScore(int score){
        System.out.println(score);
    }
}
