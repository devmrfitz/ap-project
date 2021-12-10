package com.example.ap_project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class menuPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void exitGame(ActionEvent event) {
        System.out.println("exit clicked");
        Stage stage = (Stage)((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    @FXML
    void gameStart(ActionEvent event) {
        System.out.println("play clicked");

    }

    @FXML
    void loadGame(ActionEvent event) {
        System.out.println("load clicked");
    }

    @FXML
    void initialize() {

    }

}
