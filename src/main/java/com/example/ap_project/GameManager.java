package com.example.ap_project;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class GameManager {
    private int distanceCovered;
    private int gravity;
    private int highScore;

    private Stage stage;

    public GameManager(){

    }

    public void newGame(){

    }

    public void restartGame(){

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
