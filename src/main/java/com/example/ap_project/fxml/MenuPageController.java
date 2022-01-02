package com.example.ap_project.fxml;


import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.ap_project.exceptions.NoSaveFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MenuPageController {

    private transient Stage stage;
    private transient Scene scene;
    private transient Parent root;

    @FXML
    private transient ResourceBundle resources;

    @FXML
    private transient URL location;

    @FXML
    void exitGame(ActionEvent event) {
        System.out.println("exit clicked");
        Stage stage = (Stage)((Node) (event.getSource())).getScene().getWindow();
        stage.close();
    }

    @FXML
    void gameStart(ActionEvent event) throws IOException {
        System.out.println("play clicked");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Objects.requireNonNull(getClass().getResource("game.fxml")));
        GameController gameController = new GameController();
        GameController.setInstance(gameController);
        loader.setController(gameController);

        scene = loader.load();
        //Move back a little to get a good view of the sphere
        stage = (Stage)((Node) (event.getSource())).getScene().getWindow();
        GameController.setStage(stage);
//        ((GameController)(loader.getController())).setStage(stage);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMaxWidth(900);
        stage.setMaxHeight(480);
        stage.show();

    }

    @FXML
    void loadGame(ActionEvent event) throws IOException, ClassNotFoundException, NoSaveFoundException {
        System.out.println("load clicked");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Dialog");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file","*.txt"));
        File file = fileChooser.showOpenDialog(stage);
        //load file here
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(file.getAbsoluteFile()))) {
            System.out.println(file.getPath());
            Object a = in.readObject();
            System.out.println("test" + a);
            System.out.println("play clicked");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Objects.requireNonNull(getClass().getResource("game.fxml")));
            GameController.setInstance((GameController) a);
            loader.setController(a);
            scene = loader.load();
            //Move back a little to get a good view of the sphere
            stage = (Stage)((Node) (event.getSource())).getScene().getWindow();
            GameController.setStage(stage);
//        ((GameController)(loader.getController())).setStage(stage);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setMaxWidth(900);
            stage.setMaxHeight(480);
            stage.show();
        }
        catch (FileNotFoundException e) {
            throw new NoSaveFoundException("No save file exists");
        }
    }

    @FXML
    void initialize() {

    }

}
