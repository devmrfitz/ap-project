package com.example.ap_project;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.File;
import java.time.LocalDate;
import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import javafx.stage.FileChooser;

public class GameManager {
    private int distanceCovered;
//    private ArrayList<GameObject> gameObjectsOnScreen;  ?? change this to game objects , rn testing is on string
    private final ArrayList<Object> gameObjectsOnScreen;
    private int gravity;
    private int highScore;
    private FileChooser fileChooser;

    private Stage stage;

    public GameManager(){
        gameObjectsOnScreen = new ArrayList<Object>();

    }

    public void newGame(){

    }

    public void deserialize() throws IOException, ClassNotFoundException {
        System.out.println("load clicked");
        fileChooser = new FileChooser();
        fileChooser.setTitle("Save Dialog");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file","*.txt"));
        try{
            File file = fileChooser.showOpenDialog(stage);
            //load file here
            try (ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(file.getPath()))) {
                Object a = in.readObject();
                System.out.println("test" + a);
            }
        }
        catch (Exception e){
            System.out.println("error");
        }

    }

    public void loadGame() throws IOException, ClassNotFoundException {
        deserialize();
    }

    public void restartGame(){

    }

    public void serialize() throws IOException {
        gameObjectsOnScreen.add("lol");
        String myObj = String.valueOf(LocalTime.now());
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("out" + myObj.split("\\.")[1] + ".txt"))) {
            out.writeObject(gameObjectsOnScreen);
            System.out.println("Game saved");
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
