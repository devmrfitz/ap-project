package com.example.ap_project;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class menuPageController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private FileChooser fileChooser;

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
    void gameStart(ActionEvent event) throws IOException {
        System.out.println("play clicked");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("game.fxml")));
        stage = (Stage)((Node) (event.getSource())).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void loadGame(ActionEvent event) {
        System.out.println("load clicked");
        fileChooser = new FileChooser();
        fileChooser.setTitle("Save Dialog");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file","*.txt"));
        try{
            File file = fileChooser.showOpenDialog(stage);
            //load file here
            System.out.println(file.getAbsolutePath());
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void initialize() {

    }

}
