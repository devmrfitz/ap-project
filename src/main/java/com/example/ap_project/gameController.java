package com.example.ap_project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    private ImageView hero;

    @FXML
    private ImageView orc;

    @FXML
    private ImageView boss;

    @FXML
    void move(KeyEvent event) {
        System.out.println("moved");
        System.out.println(event.getSource());

    }

    @FXML
    void openChest(MouseEvent event) throws FileNotFoundException {
//        System.out.println(event.getSource().getClass());
        InputStream stream = new FileInputStream("src/main/resources/com/example/ap_project/images/chestOpen.png");
        Image image = new Image(stream);
        ImageView imageView = (ImageView) event.getSource();
        imageView.setImage(image);
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
    void initialize() throws InterruptedException {
        translateRectangle(hero, 0);
        translateRectangle(orc, 0.5);
        translateRectangle(boss, 1);

    }


    //Translate a rectangle JAVAFx
    public void translateRectangle(ImageView rectangle, double delay) {
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        final KeyValue kv = new KeyValue(rectangle.yProperty(), -100,
                Interpolator.EASE_OUT);
        final KeyFrame kf = new KeyFrame(Duration.millis(700), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setDelay(Duration.seconds(delay));
        timeline.play();
    }

    @FXML
    void moveForward(MouseEvent event) throws FileNotFoundException {
        System.out.println("moved");
//        final Timeline timeline = new Timeline();
//        timeline.setCycleCount(1);
//        timeline.setAutoReverse(false);
//        final KeyValue kv = new KeyValue(hero.xProperty(), 70,
//                Interpolator.EASE_OUT);
//        final KeyFrame kf = new KeyFrame(Duration.millis(700), kv);
//        timeline.getKeyFrames().add(kf);
//        timeline.play();
        hero.setLayoutX(hero.getLayoutX() + 70);

    }
    //setRotate
//    private

}
