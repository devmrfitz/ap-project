package com.example.ap_project;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.animation.PathTransition;
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
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
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
    void pause(MouseEvent event) throws IOException, InterruptedException {
        System.out.println("Pause");
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("pausePage.fxml")));
//        stage = (Stage)((Node) (event.getSource())).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//        for (int y=0;y<100;y++) {
//            translateRectangle(hero, 0, -1, 0.02);
//        }
//        translateRectangle(hero, 0, -70, 2);
//        Thread.sleep(2000);
        translateRectangle(hero, 0, 70, 2);


//        for (int y=0;y<100;y++) {
//            translateRectangle(hero, 0, 1, 2/100);
//        }


    }

    @FXML
    void initialize() {

    }


    //Translate a rectangle JAVAFx
    public void translateRectangle(Region rectangle, double x, double y, double duration) {
        PathTransition pathTransition = new PathTransition();
        Path path = new Path();
        path.getElements().add (new MoveTo(0f, 50f));
        path.getElements().add (new CubicCurveTo(40f, 10f, 390f, 240f, 1904, 50f));

        pathTransition.setDuration(Duration.millis(10000));
        pathTransition.setNode(rectangle);
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(4);
        pathTransition.setAutoReverse(true);

        pathTransition.play();
    }

    //setRotate
//    private

}
