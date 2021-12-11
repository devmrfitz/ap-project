package com.example.ap_project;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class gameController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Region hero;

    @FXML
    void move(KeyEvent event) {

    }

    @FXML
    void pause(MouseEvent event) throws IOException {
        System.out.println("Pause");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("pausePage.fxml")));
        stage = (Stage)((Node) (event.getSource())).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    void initialize() {
        translateRectangle(hero, 0, -100, 2);
    }


    //Translate a rectangle JAVAFx
    public void translateRectangle(Region rectangle, double x, double y, int duration) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), rectangle);
        translateTransition.setByX(x);
        translateTransition.setByY(y);
        translateTransition.setCycleCount(18);
        translateTransition.setRate(3);
        translateTransition.setDelay(Duration.seconds(0));
        translateTransition.setAutoReverse(true);
        translateTransition.play();
    }

    //setRotate
//    private

}
